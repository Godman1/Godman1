package com.mrman.RickandmortyApiInt.service.impl;

import com.mrman.RickandmortyApiInt.model.*;
import com.mrman.RickandmortyApiInt.model.Character;
import com.mrman.RickandmortyApiInt.repository.CharacterRepository;
import com.mrman.RickandmortyApiInt.service.ApiService;
import com.mrman.RickandmortyApiInt.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ApiService apiService;



    public List<CharacterDto> fetchCharactersFromApi(List<Integer> ids) {
       return apiService.fetchCharactersFromApi(ids);
    }

    @Override
    public CharacterDto saveCharacter(CharacterDto.CharacterCreateDTO characterCreateDTO) {
        Character character  = new Character();
        character.setName(characterCreateDTO.getName());
        character.setStatus(characterCreateDTO.getStatus());
        character.setSpecies(characterCreateDTO.getSpecies());
        character.setType(characterCreateDTO.getType());
        character.setGender(characterCreateDTO.getGender());
        character.setOrigin(convertToOrigin(characterCreateDTO.getOrigin()));
        character.setLocation(convertToLocation(characterCreateDTO.getLocation()));
        character.setImage(characterCreateDTO.getImage());
        character.setEpisode(characterCreateDTO.getEpisode());
        character.setUrl(characterCreateDTO.getUrl());
        character.setCreated(characterCreateDTO.getCreated());
        character.setApprovalStatus(ApprovalStatus.PENDING);

        Character savedCharacter = characterRepository.save(character);
        return convertToDto(savedCharacter);


    }



    @Override
    public List<CharacterDto> getAllCharacters() {
        return characterRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CharacterDto> getCharacterById(int id) {
        Optional<Character> character = characterRepository.findById(id);
        return character.map(this::convertToDto);
    }

    @Override
    public void deleteCharacterById(int id) {
        characterRepository.deleteById(id);
    }




    private CharacterDto convertToDto(Character character) {
        CharacterDto characterDTO = new CharacterDto();
        characterDTO.setId(character.getId());
        characterDTO.setName(character.getName());
        characterDTO.setStatus(character.getStatus());
        characterDTO.setSpecies(character.getSpecies());
        characterDTO.setType(character.getType());
        characterDTO.setGender(character.getGender());
        character.setOrigin(character.getOrigin());
        character.setLocation(character.getLocation());
        characterDTO.setImage(character.getImage());
        characterDTO.setEpisode(character.getEpisode());
        characterDTO.setUrl(character.getUrl());
        characterDTO.setCreated(character.getCreated());
        characterDTO.setApprovalStatus(character.getApprovalStatus());
        return characterDTO;
    }
    private Origin convertToOrigin(CharacterDto.CharacterCreateDTO.OriginDto originDto) {
        if (originDto == null) {
            return null;
        }
        return new Origin(originDto.getName(), originDto.getUrl());
    }

    private Location convertToLocation(CharacterDto.CharacterCreateDTO.LocationDto locationDto) {
        if (locationDto == null) {
            return null;
        }
        return new Location(locationDto.getName(), locationDto.getUrl());
    }
}
