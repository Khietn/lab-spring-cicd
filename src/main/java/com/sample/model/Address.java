package com.sample.model;

import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Address {

    public Address(String houseNumber, String street, String city, String state, String postalCode, String country) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    @Indexed
    private String houseNumber;

    @Searchable(nostem = true)
    private String street;

    @Indexed
    private String city;

    @Indexed
    private String state;

    @Indexed
    private String postalCode;

    @Indexed
    private String country;
}