package com.revature.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.model.Role;
import com.revature.repository.UserRepository;
import com.revature.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Role adminRole;

    @BeforeEach
    public void setup() {
        // Clean up and seed the database if needed
        userRepository.deleteAll();
        roleRepository.deleteAll();

        adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole = roleRepository.save(adminRole);
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setEmail("integration@example.com");
        user.setPassword("password");
        // Link to an existing role.
        Role transientRole = new Role();
        transientRole.setRoleId(adminRole.getRoleId());
        user.setRole(transientRole);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }
}
