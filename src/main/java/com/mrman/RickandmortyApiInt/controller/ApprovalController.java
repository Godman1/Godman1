package com.mrman.RickandmortyApiInt.controller;

import com.mrman.RickandmortyApiInt.model.ApprovalStatus;
import com.mrman.RickandmortyApiInt.model.Character;
import com.mrman.RickandmortyApiInt.service.CharacterService;
import com.mrman.RickandmortyApiInt.service.JsonBlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/approval")
public class ApprovalController {
    @Autowired
    private JsonBlobService jsonBlobService;

    @Autowired
    private CharacterService characterService;

    @PostMapping("/submit/{id}")
    public ResponseEntity<String> submitCharacterForApproval(@PathVariable int id) {
        String blobId = jsonBlobService.submitCharacterForApproval(id);
        return ResponseEntity.ok(blobId);
    }

    @GetMapping("/character/{blobId}")
    public ResponseEntity<Character> getCharacterFromJSONBlob(@PathVariable String blobId) {
        Character character = jsonBlobService.getCharacterFromJSONBlob(blobId);
        return ResponseEntity.ok(character);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Object> updateCharacterApprovalStatus(@PathVariable int id, @RequestParam ApprovalStatus approvalStatus) {
        jsonBlobService.updateCharacterApprovalStatus(id, approvalStatus);
        String message = String.format("Character with ID %d has been updated to %s", id, approvalStatus);
        return ResponseEntity.ok(message);


    }
}
