package net.yorksolutions.peternepomucenopantrybe.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Credentials {
    @Id
    @Column(unique = true)
    public String username;
    public String password;

}
