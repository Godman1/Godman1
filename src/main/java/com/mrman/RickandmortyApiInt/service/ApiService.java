package com.mrman.RickandmortyApiInt.service;

import com.mrman.RickandmortyApiInt.model.CharacterDto;

import java.util.List;

public interface ApiService {
   List<CharacterDto> fetchCharactersFromApi(List<Integer> ids);
}


