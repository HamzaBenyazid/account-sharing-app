package com.winchesters.accountsharingapp.user;


import com.winchesters.accountsharingapp.user.User;
import com.winchesters.accountsharingapp.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Container
    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("account_sharing_app")
            .withUsername("account_sharing_app")
            .withPassword("account_sharing_app");

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser(){
        User expectedUserObject = new User(null,"firstname","lastname","email","username",null,null,null,null,null,null,null,null,null);
        User actualUserObject = userRepository.save(expectedUserObject);
        assertThat(actualUserObject).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedUserObject);
    }
}
