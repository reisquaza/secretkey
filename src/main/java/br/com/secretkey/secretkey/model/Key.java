package br.com.secretkey.secretkey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "key")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private User user;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 62, nullable = false)
    private String reference;

    @CreationTimestamp
    @Column()
    private Date createdAt;
    @UpdateTimestamp
    @Column()
    private Date updatedAt;

    public Key() {

    }
    public Key(User user, String password, String reference) {
        this.user = user;
        this.password = password;
        this.reference = reference;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", user=" + user +
                ", password='" + password + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
