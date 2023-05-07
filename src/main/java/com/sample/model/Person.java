package com.sample.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.geo.Point;

import java.util.Set;

@RequiredArgsConstructor(staticName = "of")
@Data
@Document
public class Person {
    // Id Field, also indexed
    @Id
    @Indexed
    private String id;

    public Person(String id, String firstName, String lastName, Integer age, String personalStatement, Point homeLoc, Address address, Set<String> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.personalStatement = personalStatement;
        this.homeLoc = homeLoc;
        this.address = address;
        this.skills = skills;
    }

    // Indexed for exact text matching
    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    //Indexed for numeric matches
    @Indexed
    private Integer age;

    //Indexed for Full Text matches
    @Searchable
    private String personalStatement;

    //Indexed for Geo Filtering
    @Indexed
    private Point homeLoc;

    // Nest indexed object
    @Indexed
    private Address address;

    @Indexed
    private Set<String> skills;
}