package ru.egar.myOrg.worker.model.enumerated;

public enum Gender {
    MALE("Муж."), FEMALE("Женс.");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
