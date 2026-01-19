package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

public record UserDetailsServiceAdapter(UserRepository userRepository) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole().getGrantedAuthorities()
        );
    }
}
