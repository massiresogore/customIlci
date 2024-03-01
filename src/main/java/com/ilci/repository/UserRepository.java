package com.ilci.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilci.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndMdp( String login, String mdp );

    User findByUsername(String username);
}
