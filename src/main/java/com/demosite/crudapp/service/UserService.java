package com.demosite.crudapp.service;

import com.demosite.crudapp.UserUpdateDTO;
import com.demosite.crudapp.model.User;
import com.demosite.crudapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.metamodel.mapping.internal.EmbeddableMappingTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private User user;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        this.user = user;
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        userRepository.save(user);
        System.out.println(user);
    }

    public void deleterUser(Long userid) {
        boolean exists = userRepository.existsById(userid);
        if (!exists) {
            throw new IllegalStateException("Student with id" + userid + "does not exist");
        }
        userRepository.deleteById(userid);
    }

    @Transactional
    public void updateUser(Long userid, @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalStateException(
                        "user with" + userid + " does not exist"));
        if (userUpdateDTO.getUsername() != null &&
                !Objects.equals(user.getUsername(), userUpdateDTO.getUsername())) {
            user.setUsername(userUpdateDTO.getUsername());
        }
        if (userUpdateDTO.getEmail() != null &&
                !Objects.equals(user.getEmail(), userUpdateDTO.getEmail())) {
            if (userRepository.existsByEmailAndIdNot(userUpdateDTO.getEmail(), userid)) {
                throw new IllegalStateException("Email already taken");
            }
            user.setEmail(userUpdateDTO.getEmail());
        }

    }
}
