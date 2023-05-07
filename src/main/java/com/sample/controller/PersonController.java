package com.sample.controller;

import com.sample.model.Address;
import com.sample.model.Person;
import com.sample.repository.PeopleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@Slf4j
public class PersonController {

    @Autowired
    PeopleRepository peopleRepository;

    @GetMapping(path="/person/insert")
    public @ResponseBody Person insertPerson(){
        String thorSays = "The Rabbit Is Correct, And Clearly The Smartest One Among You.";

        // Serendipity, 248 Seven Mile Beach Rd, Broken Head NSW 2481, Australia
        Address thorsAddress = new Address("248", "Seven Mile Beach Rd", "Broken Head", "NSW", "2481", "Australia");
        Point point = new Point(153.616667, -28.716667);
        Set set = Set.of("hammer", "biceps", "hair", "heart");
        Person thor = new Person(String.valueOf(Math.random()),"Chris", "Hemsworth", 38, thorSays, point, thorsAddress, set);

        peopleRepository.save(thor);
        return thor;
    }

    @GetMapping(path="/person/delete")
    public @ResponseBody String deleteAllPerson(){
        peopleRepository.deleteAll();

        return "Delete all key";
    }

    @GetMapping(path="/person/get/{id}")
    public @ResponseBody String getAllUsers(@PathVariable("id") String id){
        Optional<Person> optionalPerson = peopleRepository.findById(id);

        return optionalPerson.isEmpty() ? "Khong co data " + id : "Co data "+id;
    }
}
