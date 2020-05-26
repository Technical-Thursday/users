package com.pracore.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hobby")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "userHobbies"})
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hobbyName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hobby", fetch = FetchType.LAZY)
    private List<UserHobby> userHobbies;

}
