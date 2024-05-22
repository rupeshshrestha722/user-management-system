package com.rupeshshrestha.usermanagement.security;

import com.rupeshshrestha.usermanagement.entity.User;
import com.rupeshshrestha.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(username);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        Set<GrantedAuthority> authorities = optionalUser.get()
                .getRole()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                optionalUser.get().getUsername(),
                optionalUser.get().getPassword(),
                authorities
        );
    }
}
