package com.openpromt.coffeee.swf2023.openpromtserver.user.repository;

import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
