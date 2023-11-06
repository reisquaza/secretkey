package br.com.secretkey.secretkey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 92, nullable = false)
    private String name;

    @Column(length = 14, nullable = false, unique = true)
    private String document;

    @Column(length = 62, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(length = 48, columnDefinition = "TEXT", nullable = false)
    private String password;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Column(nullable = false)
    private boolean isActive = true;

    @UpdateTimestamp
    @Column()
    private Date updatedAt;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @Column()
    private Date deletedAt;

    public User() {
    }
    public User(String name, String document, String email, String password) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
