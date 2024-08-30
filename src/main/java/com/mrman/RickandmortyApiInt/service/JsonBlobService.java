package com.mrman.RickandmortyApiInt.service;

import com.mrman.RickandmortyApiInt.model.ApprovalStatus;
import com.mrman.RickandmortyApiInt.model.Character;

public interface JsonBlobService {
    String submitCharacterForApproval(int id);

    Character getCharacterFromJSONBlob(String blobId);

    void updateCharacterApprovalStatus(int id, ApprovalStatus approvalStatus);
}
