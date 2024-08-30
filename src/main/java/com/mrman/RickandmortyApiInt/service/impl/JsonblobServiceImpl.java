package com.mrman.RickandmortyApiInt.service.impl;

import com.mrman.RickandmortyApiInt.model.ApprovalStatus;
import com.mrman.RickandmortyApiInt.model.Character;
import com.mrman.RickandmortyApiInt.repository.CharacterRepository;
import com.mrman.RickandmortyApiInt.service.JsonBlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class JsonblobServiceImpl implements JsonBlobService {
    @Value("${jsonblob.api.url}")
    private String jsonBlobApiUrl;

    @Autowired
    private CharacterRepository characterRepository;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public String submitCharacterForApproval(int id) {

        Optional<Character> characterOpt = characterRepository.findById(id);
        if (!characterOpt.isPresent()) {
            throw new RuntimeException("Character not found");
        }
        Character character = characterOpt.get();
        HttpEntity<Character> request = new HttpEntity<>(character);
        ResponseEntity<Void> response = restTemplate.postForEntity(jsonBlobApiUrl, request, Void.class);

        HttpHeaders headers = response.getHeaders();
        String location = headers.getLocation().toString();
        String blobId = location.substring(location.lastIndexOf('/') + 1);

        return blobId;


//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<CharacterDto> request = new HttpEntity<>(characterDTO, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(jsonBlobApiUrl, request, String.class);
//        HttpHeaders responseHeaders = response.getHeaders();
//
//        // JSONBlob API returns the location of the created blob in the "Location" header
//        String location = responseHeaders.getLocation().toString();
//        return location.substring(location.lastIndexOf('/') + 1); // Extract blob ID from the location URL
    }

    @Override
    public Character getCharacterFromJSONBlob(String blobId) {
        String blobUrl = jsonBlobApiUrl + "/" + blobId;
        Character character = restTemplate.getForObject(blobUrl, Character.class);
        return character;

//        String url = jsonBlobApiUrl + "/" + blobId;
//        ResponseEntity<CharacterDto> response = restTemplate.getForEntity(url, CharacterDto.class);
//        return response.getBody();
    }



    @Override
    public void updateCharacterApprovalStatus(int id, ApprovalStatus approvalStatus) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        if (character.getApprovalStatus() == ApprovalStatus.PENDING && approvalStatus == ApprovalStatus.APPROVED) {
            character.setApprovalStatus(approvalStatus);
            characterRepository.save(character);
        } else {
            throw new RuntimeException("Invalid status transition");
        }
    }

}
