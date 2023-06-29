package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
