package com.smnk107.linkedIn.user_service.repository;

import com.smnk107.linkedIn.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByEmail(String email);
    public User findByEmail(String email);
}
