package com.pm.reactive.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "users")
public class User {

    @Id
    private int userId;

    private String firstName;
    private String lastName;
    private String email;

}
