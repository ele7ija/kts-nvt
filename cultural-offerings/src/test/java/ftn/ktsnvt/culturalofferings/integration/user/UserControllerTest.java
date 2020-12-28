package ftn.ktsnvt.culturalofferings.integration.user;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.util.ArrayList;

import static ftn.ktsnvt.culturalofferings.integration.user.UserConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class UserControllerTest {

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
    public void findAllFailForbidden() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(LIST_SIZE)));
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findOneTestFail() throws Exception {
        mockMvc.perform(get("/users/{id}", NON_EXISTENT_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findOneTestSucceed() throws Exception {
        mockMvc.perform(get("/users/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(EXISTING_ENTITY_EMAIL)))
                .andExpect(jsonPath("$.firstName", is(EXISTING_ENTITY_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(EXISTING_ENTITY_LAST_NAME)));
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    @Transactional
    @Rollback
    public void createTestFail() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(NEW_ENTITY_EMAIL);
        userDTO.setPassword(NEW_ENTITY_PASSWORD);
        userDTO.setFirstName(NEW_ENTITY_FIRST_NAME);
        userDTO.setLastName(NEW_ENTITY_LAST_NAME);
        userDTO.setUserRole(NEW_ENTITY_USER_ROLE);
        userDTO.setNewsIds(new ArrayList<>());
        userDTO.setCommentIds(new ArrayList<>());
        userDTO.setSubscriptionIds(new ArrayList<>());
        userDTO.setRatingIds(new ArrayList<>());

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(userDTO))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void createTestSucceed() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(NEW_ENTITY_EMAIL);
        userDTO.setPassword(NEW_ENTITY_PASSWORD);
        userDTO.setFirstName(NEW_ENTITY_FIRST_NAME);
        userDTO.setLastName(NEW_ENTITY_LAST_NAME);
        userDTO.setUserRole(NEW_ENTITY_USER_ROLE);
        userDTO.setNewsIds(new ArrayList<>());
        userDTO.setCommentIds(new ArrayList<>());
        userDTO.setSubscriptionIds(new ArrayList<>());
        userDTO.setRatingIds(new ArrayList<>());
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(userDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(NEW_ENTITY_EMAIL)))
                .andExpect(jsonPath("$.firstName", is(NEW_ENTITY_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(NEW_ENTITY_LAST_NAME)));
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void updateTestFail1() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(UPDATE_ENTITY_EMAIL);
        userDTO.setPassword(UPDATE_ENTITY_PASSWORD);
        userDTO.setFirstName(UPDATE_ENTITY_FIRST_NAME);
        userDTO.setLastName(UPDATE_ENTITY_LAST_NAME);
        userDTO.setUserRole(NEW_ENTITY_USER_ROLE);
        userDTO.setNewsIds(new ArrayList<>());
        userDTO.setCommentIds(new ArrayList<>());
        userDTO.setSubscriptionIds(new ArrayList<>());
        userDTO.setRatingIds(new ArrayList<>());

        mockMvc.perform(
                put("/users/{id}", NON_EXISTENT_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(userDTO))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void updateTestSucceed() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(UPDATE_ENTITY_EMAIL);
        userDTO.setPassword(UPDATE_ENTITY_PASSWORD);
        userDTO.setFirstName(UPDATE_ENTITY_FIRST_NAME);
        userDTO.setLastName(UPDATE_ENTITY_LAST_NAME);
        userDTO.setUserRole(NEW_ENTITY_USER_ROLE);
        userDTO.setNewsIds(new ArrayList<>());
        userDTO.setCommentIds(new ArrayList<>());
        userDTO.setSubscriptionIds(new ArrayList<>());
        userDTO.setRatingIds(new ArrayList<>());

        mockMvc.perform(
                put("/users/{id}", UPDATE_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(userDTO))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(UPDATE_ENTITY_EMAIL)))
                .andExpect(jsonPath("$.firstName", is(UPDATE_ENTITY_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(UPDATE_ENTITY_LAST_NAME)));
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void deleteTestFail1() throws Exception {
        mockMvc.perform(delete("/users/{id}", NON_EXISTENT_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {READ_AUTHORITY, WRITE_AUTHORITY})
    @Transactional
    @Rollback
    public void deleteTestSucceed() throws Exception {
        mockMvc.perform(get("/users/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/users/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(get("/users/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

}
