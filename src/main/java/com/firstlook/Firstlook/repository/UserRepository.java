package com.firstlook.Firstlook.repository;

import com.firstlook.Firstlook.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
