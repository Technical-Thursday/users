package com.pracore.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "users"})
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

//    Using User hobbies entity
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
//    private List<UserHobby> userHobbies;

    @ManyToMany
    @JoinTable(
            name = "user_hobby",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private List<Hobby> userHobbies;

    public String test() {
        return "Original";
    }

}

