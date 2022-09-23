package com.alkemy.ong.security.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.springframework.security.test.context.support.WithMockUser;

import com.alkemy.ong.enums.RoleEnum;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.ForbiddenException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.security.dto.UserDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.model.Role;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserService service;

    private MockMvc mockMvc;
    private List<UserDto> users;
    private UserDto userOne;
    private UserDto userTwo;
    private Role role;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultRequest(get("/").with(user("user").roles("ADMIN")))
                .alwaysExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .apply(springSecurity())
                .build();

        role = new Role(1L,
                RoleEnum.ADMIN,
                "description",
                LocalDateTime.now(),
                LocalDateTime.now());

        userOne = UserDto.builder()
                .id(1L)
                .firstName("firstName1")
                .lastName("lastName1")
                .email("userOne@email.com")
                .photo("token-url")
                .role(role)
                .build();

        userTwo = UserDto.builder()
                .id(2L)
                .firstName("firstName2")
                .lastName("lastName2")
                .email("userTwo@email.com")
                .photo("token-url")
                .role(role)
                .build();

        users = new ArrayList<>();
    }

    @Nested
    public class GetAllTest {

        private final String ENDPOINT_URL = "/users";

        @Test
        @WithMockUser(username = "user", roles = { "ADMIN" })
        void whenListIsNotEmpty_shouldReturnAll_status200() throws Exception {
            users.add(userOne);
            users.add(userTwo);

            when(service.getAll()).thenReturn(users);

            mockMvc.perform(get(ENDPOINT_URL)
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("ADMIN")))
                    .andExpect(jsonPath("@", hasSize(users.size())))
                    .andExpect(jsonPath("$.[0].firstName").value(userOne.getFirstName()))
                    .andExpect(jsonPath("$.[0].lastName").value(userOne.getLastName()))
                    .andExpect(jsonPath("$.[0].email").value(userOne.getEmail()))
                    .andExpect(jsonPath("$.[0].role").value(notNullValue()))
                    .andExpect(status().isOk());

            verify(service).getAll();
        }

        @Test
        @WithMockUser(roles = { "ADMIN" })
        void whenListIsEmpty_shouldThrowEmptyListException_status200() throws Exception {

            when(service.getAll()).thenThrow(EmptyListException.class);

            mockMvc.perform(get(ENDPOINT_URL)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.exception").value(EmptyListException.class.getSimpleName()))
                    .andExpect(jsonPath("$.path").value(ENDPOINT_URL));

            verify(service).getAll();
        }

    }

    @Nested
    public class GetLoggerUserDataTest {

        private final String ENDPOINT_URL = "/users/me";
        private final String PARAM_NAME = "authorization";

        @ParameterizedTest
        @WithMockUser(roles = { "ADMIN" })
        @ValueSource(strings = { "validToken" })
        void whenValidTokenEntered_shouldReturnDto_status200(String jwt) throws Exception {

            assertEquals("validToken", jwt);

            UserResponseDto dto = UserResponseDto.builder()
                    .id(1L)
                    .firstName("firstName")
                    .lastName("lastName")
                    .email("userResponseDto@email.com")
                    .photo("token-url")
                    .role(role)
                    .token(jwt)
                    .build();

            when(service.getLoggerUserData(jwt)).thenReturn(dto);

            mockMvc.perform(get(ENDPOINT_URL).header(PARAM_NAME, jwt)
                    .contentType(APPLICATION_JSON)
                    .content(jwt))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(dto.getId()))
                    .andExpect(jsonPath("$.firstName").value(dto.getFirstName()))
                    .andExpect(jsonPath("$.lastName").value(dto.getLastName()))
                    .andExpect(jsonPath("$.token").value(jwt))
                    .andExpect(jsonPath("$.email").value(dto.getEmail()))
                    .andExpect(jsonPath("$.photo").value(dto.getPhoto()))
                    .andExpect(jsonPath("$.role.name").value(dto.getRole().getName().name()));

            verify(service).getLoggerUserData(jwt);
        }

        @ParameterizedTest
        @WithMockUser(roles = { "ADMIN" })
        @ValueSource(strings = { "invalidToken" })
        void whenInvalidTokenEntered_shouldThrowForbiddenException_status403(String jwt) throws Exception {

            assertEquals("invalidToken", jwt);

            when(service.getLoggerUserData(jwt)).thenThrow(ForbiddenException.class);

            mockMvc.perform(get(ENDPOINT_URL).header(PARAM_NAME, jwt)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.exception").value(ForbiddenException.class.getSimpleName()))
                    .andExpect(jsonPath("$.path").value(ENDPOINT_URL));

            verify(service).getLoggerUserData(jwt);
        }
    }

    @Nested
    public class DeleteTest {

        private final String ENDPOINT_URL = "/users/";
        private final long INVALID_ID = -2L;
        private final long TEST_ID = 00000000000001L;
        private final long VALID_ID = 123456L;

        @ParameterizedTest
        @WithMockUser(roles = { "ADMIN" })
        @ValueSource(longs = { VALID_ID, TEST_ID })
        void whenValidIdEntered_shouldReturnStatus200(Long id) throws Exception {

            assertTrue(id > 0);

            when(service.delete(id)).thenReturn(true);

            mockMvc.perform(delete(ENDPOINT_URL + id))
                    .andExpect(status().isOk());

            verify(service).delete(id);
        }

        @ParameterizedTest
        @WithMockUser(roles = { "ADMIN" })
        @ValueSource(longs = { VALID_ID })
        void whenValidIdEntered_butNotFound_shouldThrowNotFoundException_Status404(Long id) throws Exception {

            assertTrue(id > 0);

            when(service.delete(VALID_ID)).thenThrow(NotFoundException.class);

            mockMvc.perform(delete(ENDPOINT_URL + VALID_ID))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.exception").value(NotFoundException.class.getSimpleName()))
                    .andExpect(jsonPath("$.path").value(ENDPOINT_URL + VALID_ID));

            verify(service).delete(VALID_ID);
        }

        @ParameterizedTest
        @WithMockUser(roles = { "ADMIN" })
        @ValueSource(longs = { INVALID_ID })
        void whenInvalidIdEntered_shouldThrowBadRequestException_Status400(long id) throws Exception {

            assertFalse(id > 0);

            when(service.delete(INVALID_ID)).thenThrow(BadRequestException.class);

            mockMvc.perform(delete(ENDPOINT_URL + INVALID_ID))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.exception").value(BadRequestException.class.getSimpleName()))
                    .andExpect(jsonPath("$.path").value(ENDPOINT_URL + INVALID_ID));

            verify(service).delete(INVALID_ID);
        }
    }
}
