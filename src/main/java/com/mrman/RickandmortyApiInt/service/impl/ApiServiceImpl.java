package com.mrman.RickandmortyApiInt.service.impl;

import com.mrman.RickandmortyApiInt.model.*;
import com.mrman.RickandmortyApiInt.model.Character;
import com.mrman.RickandmortyApiInt.repository.CharacterRepository;
import com.mrman.RickandmortyApiInt.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ApiServiceImpl implements ApiService {

    @Value("${rickmorty.api.url}")
    private String apiUrl;

    @Autowired
    private CharacterRepository characterRepository;


    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public List<CharacterDto> fetchCharactersFromApi(List<Integer> ids) {
        String url = apiUrl + "/character/" + ids.stream().map(String::valueOf).collect(Collectors.joining(","));
        CharacterDto[] charactersArray = restTemplate.getForObject(url, CharacterDto[].class);

        List<CharacterDto> characterDTOs = Arrays.asList(charactersArray);
        List<Character> characters = characterDTOs.stream().map(this::convertToEntity).collect(Collectors.toList());
        characterRepository.saveAll(characters);

        return characterDTOs;

    }

    private Character convertToEntity(CharacterDto characterDTO) {
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setStatus(characterDTO.getStatus());
        character.setSpecies(characterDTO.getSpecies());
        character.setType(characterDTO.getType());
        character.setGender(characterDTO.getGender());
        character.setOrigin(characterDTO.getOrigin());
        character.setLocation(characterDTO.getLocation());
        character.setImage(characterDTO.getImage());
        character.setEpisode(characterDTO.getEpisode());
        character.setUrl(characterDTO.getUrl());
        character.setCreated(characterDTO.getCreated());
        character.setApprovalStatus(ApprovalStatus.PENDING);
        return character;
    }






}


















//    List<CharacterDto.CharacterCreateDTO> characterCreateDtos = ids.stream()
//            .map(id -> {
//                String url = apiUrl + id;
//                return restTemplate.getForObject(url, CharacterDto.CharacterCreateDTO.class);
//            })
//            .collect(Collectors.toList());
//
//    List<Character> characters = characterCreateDtos.stream()
//            .map(this::convertToEntity)
//            .collect(Collectors.toList());
//
//        characterRepository.saveAll(characters);
//}


//    private Character convertToEntity(CharacterDto.CharacterCreateDTO characterDTO) {
//        Character character = new Character();
//        character.setName(characterDTO.getName());
//        character.setStatus(characterDTO.getStatus());
//        character.setSpecies(characterDTO.getSpecies());
//        character.setType(characterDTO.getType());
//        character.setGender(characterDTO.getGender());
//        character.setOrigin(convertToOrigin(characterDTO.getOrigin()));
//        character.setLocation(convertToLocation(characterDTO.getLocation()));
//        character.setImage(characterDTO.getImage());
//        character.setEpisode(characterDTO.getEpisode());
//        character.setUrl(characterDTO.getUrl());
//        character.setCreated(characterDTO.getCreated());
//        character.setApprovalStatus(ApprovalStatus.PENDING);
//        return character;
//    }
//
//    private Origin convertToOrigin(CharacterDto.CharacterCreateDTO.OriginDto originDto) {
//        if (originDto == null) {
//            return null;
//        }
//        return new Origin(originDto.getName(), originDto.getUrl());
//    }
//
//    private Location convertToLocation(CharacterDto.CharacterCreateDTO.LocationDto locationDto) {
//        if (locationDto == null) {
//            return null;
//        }
//        return new Location(locationDto.getName(), locationDto.getUrl());
//    }




