package com.pracore.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBody {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer roleId;
}
