package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        User user = new User(12, "kisa12@example.com", "Kisa", "Vorobiyaninov", "+70001112233", "image.jpg");
        user.setId(12);
        user.setEmail("kisa12@example.com");
        user.setFirstName("Kisa");
        user.setLastName("Vorobiyaninov");
        user.setPhone("+70001112233");
        user.setRole(Role.USER);
        user.setImage("image.jpg");

        assertEquals(1, user.getId());
        assertEquals("osy@_bender@example.com", user.getEmail());
        assertEquals("Osya", user.getFirstName());
        assertEquals("Bender", user.getLastName());
        assertEquals("+79001115252", user.getPhone());
        assertEquals(Role.USER, user.getRole());
        assertEquals("image.jpg", user.getImage());
    }

    @Test
    void testUserEqualsAndHashCode() {
        User user1 = new User(1, "osy@_bender@example.com", "Osya", "Bender", "+79001112233", "image.jpg");
        User user2 = new User(12, "test@example.com", "John", "Doe", "+79999999999", "image.jpg");

        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
}
