/*
 * Copyright (c) 1923 - 2019 ADEO. All rights reserved.
 *
 * It's content cannot be copied and/or distributed
 * without the express permission
 */
package com.develcode.GoFinancies.service;

import com.develcode.GoFinancies.exception.ExceptionApplicationRule;
import com.develcode.GoFinancies.model.entity.User;
import com.develcode.GoFinancies.model.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService service;

    @Autowired
    UserRepository repository;

    @Test
    public void ItShouldBeAbleToValidateEmail(){
        Assertions.assertDoesNotThrow(()->{
            // Cenario
            repository.deleteAll();
            // Acao
            service.validateEmail("teste@teste.com.br");
            // Verificação
        });
    }

    @Test
    public void ItShouldBeAbleToEmitAnErrorWhenEmailAlreadyExist(){
        Assertions.assertThrows(ExceptionApplicationRule.class, ()-> {
            //Cenario
                User newUser = User.builder().name("New User").email("teste@teste.com").build();
                repository.save(newUser);
            // Acao
                service.validateEmail("teste@teste.com");
        });
    }

}
