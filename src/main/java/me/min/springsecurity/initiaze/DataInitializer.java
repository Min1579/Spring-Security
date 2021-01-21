package me.min.springsecurity.initiaze;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.min.springsecurity.model.User;
import me.min.springsecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("test")
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initData();
    }

    private void initData() {
        log.info("data initializer");

        userRepository.deleteAll();
        User savedUser = userRepository.save(User.builder()
                .username("min")
                .password(passwordEncoder.encode("12345"))
                .build());

        log.info("init user : {}", savedUser.getUsername());
    }
}
