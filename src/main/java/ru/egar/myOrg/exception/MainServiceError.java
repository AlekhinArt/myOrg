package ru.egar.myOrg.exception;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainServiceError {
    private String message;
    private String reason;
    private String status;
    private String timestamp;
}
