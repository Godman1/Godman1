package com.mrman.RickandmortyApiInt.service;

import com.mrman.RickandmortyApiInt.model.CharacterDto;

import java.util.List;
import java.util.Optional;

public interface CharacterService {
    List<CharacterDto> fetchCharactersFromApi(List<Integer> ids);

    CharacterDto saveCharacter(CharacterDto.CharacterCreateDTO characterCreateDTO);


    List<CharacterDto> getAllCharacters();

    Optional<CharacterDto> getCharacterById(int id);

     void deleteCharacterById(int id);



}
