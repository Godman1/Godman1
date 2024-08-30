package com.mrman.RickandmortyApiInt.controller;

import com.mrman.RickandmortyApiInt.model.CharacterDto;
import com.mrman.RickandmortyApiInt.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/character")
public class CharacterController {
    @Autowired
    private CharacterService characterService;



    @GetMapping("/fetch")
    public List<CharacterDto> getCharactersFromApi(@RequestParam("ids") List<Integer> ids) {
         return characterService.fetchCharactersFromApi(ids);
    }



    @PostMapping
    public CharacterDto createCharacter(@RequestBody CharacterDto.CharacterCreateDTO characterCreateDTO) {
        return characterService.saveCharacter(characterCreateDTO);
    }

    @GetMapping("/{id}")
    public Optional<CharacterDto> getCharacterById(@PathVariable("id") int id) {
        return characterService.getCharacterById(id);
    }

    @GetMapping
    public List<CharacterDto> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable int id) {
        characterService.deleteCharacterById(id);
    }

}
