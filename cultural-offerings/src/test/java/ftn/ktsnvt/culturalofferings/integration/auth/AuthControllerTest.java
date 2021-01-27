package ftn.ktsnvt.culturalofferings.integration.auth;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.RegisterDTO;
import ftn.ktsnvt.culturalofferings.security.jwt.EmailAndPasswordAuthenticationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static ftn.ktsnvt.culturalofferings.integration.user.UserConstants.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AuthControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @Transactional
    @Rollback
    public void failLoginTest() throws Exception {
        EmailAndPasswordAuthenticationRequest emailAndPasswordAuthenticationRequest = new EmailAndPasswordAuthenticationRequest();
        emailAndPasswordAuthenticationRequest.setEmail(NON_EXISTENT_ENTITY_EMAIL);
        emailAndPasswordAuthenticationRequest.setPassword("password");
        mockMvc.perform(
                post("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtil.json(emailAndPasswordAuthenticationRequest))
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    @Rollback
    public void successLoginTest() throws Exception {
        EmailAndPasswordAuthenticationRequest emailAndPasswordAuthenticationRequest = new EmailAndPasswordAuthenticationRequest();
        emailAndPasswordAuthenticationRequest.setEmail(EXISTING_ENTITY_EMAIL);
        emailAndPasswordAuthenticationRequest.setPassword(EXISTING_ENTITY_PASSWORD);
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(emailAndPasswordAuthenticationRequest))
        )
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.jwt", isA(String.class)));
    }

    @Test
    @Transactional
    @Rollback
    public void failRegisterTest() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(EXISTING_ENTITY_EMAIL);
        registerDTO.setPassword(EXISTING_ENTITY_PASSWORD);
        registerDTO.setFirstName(EXISTING_ENTITY_FIRST_NAME);
        registerDTO.setLastName(EXISTING_ENTITY_LAST_NAME);
        mockMvc.perform(
                post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(registerDTO))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    @Rollback
    public void successRegisterTest() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(NEW_ENTITY_EMAIL);
        registerDTO.setPassword(NEW_ENTITY_PASSWORD);
        registerDTO.setFirstName(NEW_ENTITY_FIRST_NAME);
        registerDTO.setLastName(NEW_ENTITY_LAST_NAME);
        mockMvc.perform(
                post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(registerDTO))
        )
                .andExpect(status().isCreated());
    }
}
