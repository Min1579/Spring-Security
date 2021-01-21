package me.min.springsecurity;

import me.min.springsecurity.model.User;
import me.min.springsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DirtiesContext
@Profile("test")
@SpringBootTest
class SpringSecurityApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void initDataCheck() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUsername()).isEqualTo("min");
    }
}
