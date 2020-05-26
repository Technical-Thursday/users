package com.pracore.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    @Id
//  TODO: Learn about GenerationType
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;

    // if column name is in snake case at table and if name in model is in camelCase we do not need to add @Column, Just keeping it here for an example.
//  @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roleId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserHobby> userHobbies;
}

