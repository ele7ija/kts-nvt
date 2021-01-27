package ftn.ktsnvt.culturalofferings.integration.rating;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.security.Principal;

import static ftn.ktsnvt.culturalofferings.integration.rating.RatingConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RatingControllerTest {

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
    @WithMockUser(username = "something")
    public void findAllPageable() throws Exception {
        mockMvc.perform(get("/rating/by-page")
                .param("page", "0")
                .param("size", "2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "something")
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/rating/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "something")
    public void findByIdTestFail() throws Exception {
        mockMvc.perform(get("/rating/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {WRITE_AUTHORITY})
    @Transactional
    @Rollback(true)
    public void deleteTest() throws Exception {
        mockMvc.perform(get("/rating/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/rating/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rating/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {WRITE_AUTHORITY})
    @Transactional
    @Rollback(true)
    public void deleteTestFails() throws Exception {
        mockMvc.perform(get("/rating/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
        mockMvc.perform(delete("/rating/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }
}
