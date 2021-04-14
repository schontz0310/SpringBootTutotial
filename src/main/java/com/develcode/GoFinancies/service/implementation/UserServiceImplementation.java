
package com.develcode.GoFinancies.service.implementation;

import com.develcode.GoFinancies.exception.ExceptionApplicationRule;
import com.develcode.GoFinancies.exception.ExceptionAuthenticationError;
import com.develcode.GoFinancies.model.entity.User;
import com.develcode.GoFinancies.model.repository.UserRepository;
import com.develcode.GoFinancies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    private UserRepository repository;

    //@Autowired
    public UserServiceImplementation(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public User authenticate(String email, String password) {

        Optional<User> user = repository.findByEmail(email);

        if(!user.isPresent()){
            throw new ExceptionAuthenticationError("Email not found");
        }

        if(!user.get().getPassword().equals(password)){
            throw new ExceptionAuthenticationError("Password not found");
        }

        return null;
    }

    @Override
    @Transactional
    public User saveUser(User user) {

        validateEmail(user.getEmail());
        return repository.save(user);
    }

    @Override
    public void validateEmail(String email) {
       boolean exists = repository.existsByEmail(email);
       if (exists){
           throw new ExceptionApplicationRule("This user email already exists");
       }
    }
}
