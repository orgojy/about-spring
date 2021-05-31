package com.example.aboutspring.util.jackson.objectmapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObjectMapperTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static class User {

        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    @Test
    void writeValue() throws IOException {
        // given
        String pathname = "user.json";
        File resultFile = new File(pathname);
        User user = new User("Ryan", 30);

        // when
        objectMapper.writeValue(resultFile, user);

        // then
        assertThat(Files.readAllLines(Paths.get(pathname)).get(0))
                .isEqualTo("{\"name\":\"Ryan\",\"age\":30}");
    }

    @Test
    void writeValueAsString() throws JsonProcessingException {
        // given
        User user = new User("Ryan", 30);

        // when
        String userAsString = objectMapper.writeValueAsString(user);

        // then
        assertThat(userAsString)
                .isEqualTo("{\"name\":\"Ryan\",\"age\":30}");
    }
}
