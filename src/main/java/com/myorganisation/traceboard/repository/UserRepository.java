package com.myorganisation.traceboard.repository;

import com.myorganisation.traceboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
