package br.com.secretkey.secretkey.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class KeyDto {
    @NotNull(message = "reference cannot be null")
    private String reference;
    @NotNull(message = "userId cannot be null")
    private UUID userId;
    private String password = "";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
