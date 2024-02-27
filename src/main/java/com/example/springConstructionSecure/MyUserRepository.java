package com.example.springConstructionSecure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    public Optional<MyUser> findMyUserByUsername(String username);
}

