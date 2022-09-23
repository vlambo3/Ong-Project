package com.alkemy.ong.security.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import org.springframework.web.context.WebApplicationContext;

import com.alkemy.ong.enums.RoleEnum;
import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.model.Role;
import static com.alkemy.ong.utils.JsonMapper.asJsonString;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserService service;

    private MockMvc mockMvc;

    private AuthenticationRequest authRequest;
    private AuthenticationResponse authResponse;
    private Role role;
    private UserRequestDto userResquest;
    private UserResponseDto userResponse;
    private String ENDPOINT_URL = "/auth/login";


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)                                
                                .apply(springSecurity())
                                .build();

        role = new Role(1L,
                RoleEnum.ADMIN,
                "description",
                LocalDateTime.now(),
                LocalDateTime.now());

        userResquest = UserRequestDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .photo("photo")
                .email("email@email.com")
                .role(role)
                .build();
         
        userResponse = UserResponseDto.builder()
                .id(1L)
                .firstName("firstName")
                .lastName("lastName")
                .photo("photo")
                .email("email@email.com")
                .role(role)
                .token("token")
                .build();

        authResponse = new AuthenticationResponse();        
    }

    
    @Test
    void whenRequestIsOk_shouldReturnToken_status200() throws Exception {
        authRequest = AuthenticationRequest.builder()
                .email("validEmail@testing.com")
                .password("validPassword")
                .build();

        authResponse = new AuthenticationResponse("valid-token");       

        when(service.authenticate(authRequest)).thenReturn(authResponse);

        mockMvc.perform(post(ENDPOINT_URL)
                .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.jwt").value("valid-token"))
                .andExpect(status().isOk());

        verify(service).authenticate(authRequest);
    }
    

    @Test
    void whenRequestIsOk_shouldReturnToken_status2002() throws Exception {
        final ResultActions result = mockMvc.perform(post(ENDPOINT_URL)
                                                    .content(asJsonString(AuthenticationRequest.builder()
                                                                        .email("validEmail@testing.com")
                                                                        .password("validPassword")
                                                                        .build()))                        
                                                    .contentType(APPLICATION_JSON_VALUE)
                                                    .accept(APPLICATION_JSON_VALUE));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.jwt", is(not(nullValue()))));
    }

    @Test
    void whenRequestHasInvalidEmail_shouThrowNotFoundException_status404() {

    }


    ////////////////////////////////////////////////

    @Test
    void register() throws Exception{
       when(service.save(userResquest)).thenReturn(userResponse);

       mockMvc.perform(post("/auth/register")
       .contentType(APPLICATION_JSON))
       .andExpect(jsonPath("$.id").value(userResponse.getId()))
       .andExpect(jsonPath("$.token").value(userResponse.getToken()));
       
        verify(service).save(userResquest);
    }

}

/*
 * @Test
  public void shouldReturnForbiddenWhenAuthenticationTokenIsNotValid() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INF)))
        .andExpect(status().isForbidden());
  }
 */

/*
 * public UserProfileDto getUserProfile(HttpServletRequest request) {

        String email = null;
        String jwt = null;

        String authorizationHeader = request.getHeader("Authorization");
        jwt = authorizationHeader.substring(7);
        email = JwtUtils.decodeToken(jwt);

        UserModel userModel = userRepository.findByEmail(email);
        UserProfileDto dto = userMapper.userModel2UserProfileDto(userModel);

        return dto;
    }
 */