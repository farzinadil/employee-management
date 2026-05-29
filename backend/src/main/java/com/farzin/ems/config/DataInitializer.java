package com.farzin.ems.config;

import com.farzin.ems.entity.Role;
import com.farzin.ems.entity.User;
import com.farzin.ems.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(new User(null, "manager", passwordEncoder.encode("manager123"), Role.MANAGER));
            userRepository.save(new User(null, "employee", passwordEncoder.encode("employee123"), Role.EMPLOYEE));
        }
    }
}
