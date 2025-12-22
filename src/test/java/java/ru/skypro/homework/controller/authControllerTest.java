package java.ru.skypro.homework.controller;

import org.h2.engine.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.ru.skypro.homework.controller.authController;
import java.ru.skypro.homework.dto.login;
import java.ru.skypro.homework.dto.register;
import java.ru.skypro.homework.dto.role;
import java.ru.skypro.homework.service.authService;
import java.ru.skypro.homework.testSecurityConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(authController.class)
@Import(testSecurityConfig.class)
class authControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private authService authService;

    @Test
    void login_ShouldReturnOk_WhenCredentialsAreValid() throws Exception {
        login login = new login();
        login.setUsername("user@gmail.com");
        login.setPassword("password");

        when(authService.login("user@gmail.com", "password")).thenReturn(true);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk());
    }

    @Test
    void login_ShouldReturnUnauthorized_WhenCredentialsAreInvalid() throws Exception {
        login login = new login();
        login.setUsername("user@gmail.com");
        login.setPassword("wrongpassword");

        when(authService.login("user@gmail.com", "wrongpassword")).thenReturn(false);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void register_ShouldReturnCreated_WhenRegistrationIsSuccessful() throws Exception {
        register register = new register();
        register.setUsername("newuser@gmail.com");
        register.setPassword("password123");
        register.setFirstName("John");
        register.setLastName("Doe");
        register.setPhone("+79999999999");
        register.setRole(role.USER);

        when(authService.register(any(register.class))).thenReturn(true);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isCreated());
    }

    @Test
    void register_ShouldReturnBadRequest_WhenRegistrationFails() throws Exception {
        register register = new register();
        register.setUsername("user@gmail.com");
        register.setPassword("password");

        when(authService.register(any(register.class))).thenReturn(false);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isBadRequest());
    }
}