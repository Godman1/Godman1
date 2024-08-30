package com.mrman.RickandmortyApiInt.model;

import lombok.Data;

import java.util.List;

@Data
public class CharacterDto {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private String image;
    private Location location;
    private List<String> episode;
    private String url;
    private String created;
    private ApprovalStatus approvalStatus;



    @Data
    public static class CharacterCreateDTO {
        private String name;
        private String status;
        private String species;
        private String type;
        private String gender;
        private OriginDto origin;
        private LocationDto location;
        private String image;
        private List<String> episode;
        private String url;
        private String created;
        private ApprovalStatus approvalStatus;

        @Data
        public static class OriginDto {
            private String name;
            private String url;

        }
        @Data
        public static class LocationDto {
            private String name;
            private String url;
        }
    }



    @Data
    public static class CharacterUpdateDTO {
        private ApprovalStatus approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {

    }



















}
