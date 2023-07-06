package br.com.douglas.userapi.services;

import br.com.douglas.userapi.domain.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
}
