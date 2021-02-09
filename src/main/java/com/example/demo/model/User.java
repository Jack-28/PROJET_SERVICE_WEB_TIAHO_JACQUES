package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "correction_gen")
    @SequenceGenerator(name = "correction_gen",sequenceName = "user_seq",allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_Name",length = 40, nullable = false)
    private String firstName;

    @Column(name = "last_Name",length = 30, nullable = false)
    private String lastName;

    @Column(name = "adresse")
    private String ad;

    @Column(name = "email",length = 30, nullable = false)
    private String email;

    @Column(name = "Mot_De_Passe",length = 16, nullable = false)
    private String pwd;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "author")
    @JsonIgnore
    private List<Correction> correctionList;




}
