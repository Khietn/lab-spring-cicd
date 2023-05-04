package com.sample.repository;
import org.springframework.data.repository.CrudRepository;
import com.sample.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
