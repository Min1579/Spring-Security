package me.min.springsecurity.security;

import lombok.RequiredArgsConstructor;
import me.min.springsecurity.model.User;
import me.min.springsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username : " + username));

        return AuthUser.create(user);
    }

    public UserDetails loadUserById(final Long id) throws UsernameNotFoundException {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id : " + id));

        return AuthUser.create(user);
    }
}
