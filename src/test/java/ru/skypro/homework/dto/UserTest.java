package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.entity.Role;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private String password;


    UserTest(String password) {
        this.password = password;
    }

    @Test
    void testUserGettersAndSetters() {
        User user = new User();
        user.setEmail("kisa12@example.com");
        user.setFirstName("Kisa");
        user.setLastName("Vorobiyaninov");
        user.setPhone("+79001112233");
        user.setPassword(password);
        user.setRole(Role.USER);
        user.setImage("image.jpg");


        assertEquals(12, user.getId());
        assertEquals("kisa12@example.com", user.getEmail());
        assertEquals("Kisa", user.getFirstName());
        assertEquals("Vorobiyaninov", user.getLastName());
        assertEquals("+79001112233", user.getPhone());

        assertEquals(Role.USER, user.getRole());
        assertEquals("image.jpg", user.getImage());

        User user1 = new User();
        User user2 = new User();

        user1.setId(1);
        user2.setId(2);

        assertNotEquals(user1, user2);


    }

}
