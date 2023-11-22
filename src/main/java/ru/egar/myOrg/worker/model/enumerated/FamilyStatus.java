package ru.egar.myOrg.worker.model.enumerated;

import lombok.Getter;

@Getter
public enum FamilyStatus {
    SINGLE("Холост"), MARRIED("В браке");
    private String status;

    FamilyStatus(String status) {
        this.status = status;
    }
}
