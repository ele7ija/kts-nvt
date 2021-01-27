package ftn.ktsnvt.culturalofferings.integration.comment;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
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

import static ftn.ktsnvt.culturalofferings.integration.comment.CommentConstants.*;
import static ftn.ktsnvt.culturalofferings.integration.cultural_offering.CulturalOfferingConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CommentControllerTest {

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
        mockMvc.perform(get("/comments/by-page")
                .param("page", "0")
                .param("size", "2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "something")
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/comments/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "something")
    public void findByIdTestFail() throws Exception {
        mockMvc.perform(get("/comments/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {WRITE_AUTHORITY})
    @Transactional
    @Rollback(true)
    public void deleteTest() throws Exception {
        mockMvc.perform(get("/comments/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/comments/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(get("/comments/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {WRITE_AUTHORITY})
    @Transactional
    @Rollback(true)
    public void deleteTestFails() throws Exception {
        mockMvc.perform(get("/comments/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
        mockMvc.perform(delete("/comments/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback(true)
    public void createTest() throws Exception {
        CommentDTO dto = new CommentDTO();
        dto.setCulturalOfferingId(EXISTING_CULTURAL_OFFERING_ID);
        dto.setUserId(EXISTING_USER_ID);
        dto.setImageIds(new ArrayList<>());
        dto.setText(NEW_COMMENT_TEXT);

        mockMvc.perform(
                post("/comments")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text", is(NEW_COMMENT_TEXT)));
    }
}
