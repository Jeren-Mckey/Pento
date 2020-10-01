package com.example.PentoApi.doa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    User findUser(Long aLong);
}
