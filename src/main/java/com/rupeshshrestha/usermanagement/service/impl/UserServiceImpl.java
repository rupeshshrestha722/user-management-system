package com.rupeshshrestha.usermanagement.service.impl;

import com.rupeshshrestha.usermanagement.dto.UserRequestDto;
import com.rupeshshrestha.usermanagement.entity.Role;
import com.rupeshshrestha.usermanagement.entity.User;
import com.rupeshshrestha.usermanagement.exception.BadrequestException;
import com.rupeshshrestha.usermanagement.exception.ServerConfigException;
import com.rupeshshrestha.usermanagement.repository.RoleRepository;
import com.rupeshshrestha.usermanagement.repository.UserRepository;
import com.rupeshshrestha.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User adduser(UserRequestDto userRequestDto) {
        Optional<Role> role = roleRepository.findRoleByName("USER");
        if(role.isEmpty())  {
            throw new ServerConfigException("No Role Configured");
        }

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setDob(userRequestDto.getDob());
        user.setRole(Set.of(role.get()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersByFirstName(String firstName) {
        return userRepository.findUserByFirstName(firstName);
    }

    @Override
    public List<User> findUsersByLastName(String lastName) {
        return userRepository.findUserByLastName(lastName);
    }

    @Override
    public Page<User> findUsers(Pageable pageable) {
        return  userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User updateUser(Long id, UserRequestDto userRequestDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = new User();
            user.setId(id);

            //validate if new email pased, check if it is already registered
            Optional<User> userByEmail = userRepository.findUserByEmail(userRequestDTO.getEmail());

            if(userByEmail.isEmpty() || optionalUser.get().getEmail().equals(userRequestDTO.getEmail())) {
                user.setEmail(userRequestDTO.getEmail());
            }

            //validate if new username is passed, check if it is already used
            Optional<User> userByUsername = userRepository.findUserByUsername(userRequestDTO.getUsername());
            if ( userByUsername.isEmpty() || optionalUser.get().getUsername().equals(userRequestDTO.getUsername())) {
                user.setUsername(userRequestDTO.getUsername());
            }

            if(!userRequestDTO.getPassword().isBlank() ){
                user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            } else {
                user.setPassword(optionalUser.get().getPassword());
            }

            user.setRole(optionalUser.get().getRole());
            user.setFirstName(userRequestDTO.getFirstName());
            user.setLastName(userRequestDTO.getLastName());
            user.setDob(userRequestDTO.getDob());

            return userRepository.save(user);


        } else {
            throw new BadrequestException("No User found");

        }
    }

    @Override
    public void deleteUer(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new BadrequestException("No such user exists");
        }
    }
}
