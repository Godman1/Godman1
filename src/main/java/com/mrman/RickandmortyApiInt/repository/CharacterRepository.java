package com.mrman.RickandmortyApiInt.repository;

import com.mrman.RickandmortyApiInt.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
