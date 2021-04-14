/*
 * Copyright (c) 1923 - 2019 ADEO. All rights reserved.
 *
 * It's content cannot be copied and/or distributed
 * without the express permission
 */
package com.develcode.GoFinancies.model.repository;

import com.develcode.GoFinancies.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void ItShouldBeAbleToVerifyIfEmailExist(){
        // cenario
            User fakeUser = GenerateUserForTests()  ;
            entityManager.persist(fakeUser);

        // acao / execucação
            boolean checkResult = repository.existsByEmail("teste@teste.com.br");

        // verificação
            Assertions.assertThat(checkResult).isTrue();
    }

    @Test
    public void ItShouldBeAbleToReturnFalseIfEmailNotExist(){
        //cenario

        //acao / execução
            boolean checkResult = repository.existsByEmail("teste@teste.com.br");
        //verificação
            Assertions.assertThat(checkResult).isFalse();
    }

    @Test
    public void ItShouldBeAbleToPersistAUserIndataBase(){
        //cenario
            User newUser = GenerateUserForTests();
        //acao

            User checkUser = repository.save(newUser);

        //verificacao

            Assertions.assertThat(checkUser.getId()).isNotNull();
    }

    @Test
    public void ItShouldBeABleToSearchAUserByEmail(){
        //cenario
        User newUser = GenerateUserForTests();
        entityManager.persist(newUser);
        //acao
        Optional<User> result = repository.findByEmail("teste@teste.com.br");
        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void ItShouldBeABleToReturnEmptyWhenNotFoundEmailInDatabase(){
        //cenario

        //acao
        Optional<User> result = repository.findByEmail("teste@teste.com.br");
        Assertions.assertThat(result.isPresent()).isFalse();
    }

    public static User GenerateUserForTests(){
        return User.builder()
            .name("New User")
            .email("teste@teste.com.br")
            .password("password")
            .build();
    }
}
