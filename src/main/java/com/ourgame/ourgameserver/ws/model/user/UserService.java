package com.ourgame.ourgameserver.ws.model.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserJpaEntity saveUser(UserJpaEntity userJpaEntity) {
        return userRepository.save(userJpaEntity);
    }


    public UserJpaEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
