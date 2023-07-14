package br.com.douglas.userapi.services.impl;

import br.com.douglas.userapi.domain.User;
import br.com.douglas.userapi.repositories.UserRepository;
import br.com.douglas.userapi.services.UserService;
import br.com.douglas.userapi.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Environment env;

    @Override
    public User findById(Long id) {
        log.info("USER_SERVICE :: GET Request on " + env.getProperty("local.server.port") + " port");
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
