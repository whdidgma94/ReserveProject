package com.boot.reserveproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class DbConfigTest {
    @Autowired
    DataSource dataSource;
    @Test
    void checkBean() {
        Assertions.assertNotNull(dataSource);


    }
}