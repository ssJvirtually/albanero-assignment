package com.albanero.order.repository;

import com.albanero.order.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findByEmail(String email);

    User findByEmailAndName(String email, String name);
}
