package io.eduardogarcia.ppmtool.services;

import io.eduardogarcia.ppmtool.domain.User;
import io.eduardogarcia.ppmtool.exceptions.ProjectNotFoundException;
import io.eduardogarcia.ppmtool.exceptions.ProjectNotFoundExceptionResponse;
import io.eduardogarcia.ppmtool.exceptions.UsernameAlreadyExistsException;
import io.eduardogarcia.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser) {

       try {
           newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
           //Username has not be unique( expection)
           newUser.setUsername((newUser.getUsername()));
           //Make sure password and confirmPassword match
           //We dont persist or show password
           newUser.setConfirmPassword("");
           return userRepository.save(newUser);


       } catch(Exception e) {
           throw new UsernameAlreadyExistsException("Username: '"+newUser.getUsername() +"' already exists.");
       }

    }

}
