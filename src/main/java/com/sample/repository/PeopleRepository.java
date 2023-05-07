package com.sample.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import com.sample.model.Person;

public interface PeopleRepository extends RedisDocumentRepository<Person,String> {

}