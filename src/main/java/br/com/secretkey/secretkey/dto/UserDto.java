package br.com.secretkey.secretkey.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 92, message = "Name must be lower or equal than 92 characters")
    private String name;
    @NotEmpty(message = "Document cannot be empty")
    @Size(max = 14, message = "Document must be lower or equal than 14 characters")
    private String document;
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 62, message = "Email must be lower or equal than 62 characters")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 12,  message = "Password must be higher than 12 characters")
    @Size(max = 48, message = "Password must be lower or equal than 48 characters")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
