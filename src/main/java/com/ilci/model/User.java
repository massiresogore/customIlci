package com.ilci.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String  sexe = "Default";
    private String  prenom = "Default";
    private String  nom = "Default";

    /*Custom*/
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fullname;
    @Column(nullable = false)
    private String  name;

    private String  login= "Default";
    private String  mdp = "Default";
    private String  ville  = "Paris";
    private String  statut = "ETUDIANT";
}
