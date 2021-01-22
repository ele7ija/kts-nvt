package ftn.ktsnvt.culturalofferings.integration.image;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ftn.ktsnvt.culturalofferings.TestUtil;

import static ftn.ktsnvt.culturalofferings.integration.image.ImageConstants.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ImageControllerTest {

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
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/images/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTestFail() throws Exception {
        mockMvc.perform(get("/images/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void createI() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("imageFile", "filename.txt", "image/png", "dasd".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/images")
            .file(firstFile))
            .andExpect(status().is(200));
    }
}
