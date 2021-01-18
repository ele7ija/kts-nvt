package ftn.ktsnvt.culturalofferings.integration.cultural_offering;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;

import static ftn.ktsnvt.culturalofferings.integration.cultural_offering.CulturalOfferingConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingControllerTest {
	
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
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findAllPageable() throws Exception {
    	mockMvc.perform(get("/cultural-offerings/by-page")
				.param("page", "0")
				.param("size", "2"))
				.andExpect(status().isOk());
    }
    
    @Test
    public void findAllPageableFailForbidden() throws Exception {
        mockMvc.perform(get("/cultural-offerings/by-page")
        		.param("page", "0")
				.param("size", "2"))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/cultural-offerings/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(EXISTING_ENTITY_NAME)));
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findByIdTestFail() throws Exception {
        mockMvc.perform(get("/cultural-offerings/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {READ_AUTHORITY, WRITE_AUTHORITY})
    @Transactional
    @Rollback(true)
    public void deleteTest() throws Exception {
        mockMvc.perform(get("/cultural-offerings/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/cultural-offerings/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(get("/cultural-offerings/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @WithMockUser(authorities = {READ_AUTHORITY, WRITE_AUTHORITY})
    @Transactional
    @Rollback(true)
    public void deleteTestFails() throws Exception {
        mockMvc.perform(get("/cultural-offerings/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
        mockMvc.perform(delete("/cultural-offerings/{id}", NON_EXISTING_ENTITY_ID))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback(true)
    public void updateTest() throws Exception {
        CulturalOfferingDTO dto = new CulturalOfferingDTO(null, EXISTING_ENTITY_NAME, "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", EXISTING_TYPE_NAME, EXISTING_SUBTYPE_NAME, new ArrayList<Long>());

        mockMvc.perform(
                put("/cultural-offerings/{id}", EXISTING_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(EXISTING_ENTITY_NAME)));
    }
    
    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback(true)
    public void updateTestFailsId() throws Exception {
        CulturalOfferingDTO dto = new CulturalOfferingDTO(null, EXISTING_ENTITY_NAME, "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", EXISTING_TYPE_NAME, EXISTING_SUBTYPE_NAME, new ArrayList<Long>());

        mockMvc.perform(
                put("/cultural-offerings/{id}", NON_EXISTING_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isNotFound());
    }
    
    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback(true)
    public void updateTestFailsSubtypeName() throws Exception {
        CulturalOfferingDTO dto = new CulturalOfferingDTO(null, EXISTING_ENTITY_NAME, "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", EXISTING_TYPE_NAME, NON_EXISTING_SUBTYPE_NAME, new ArrayList<Long>());

        mockMvc.perform(
                put("/cultural-offerings/{id}", NON_EXISTING_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isInternalServerError());
    }
    
    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback(true)
    public void createTest() throws Exception {
    	CulturalOfferingDTO dto = new CulturalOfferingDTO(null, NON_EXISTING_ENTITY_NAME, "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", EXISTING_TYPE_NAME, EXISTING_SUBTYPE_NAME, new ArrayList<Long>());

        mockMvc.perform(
                    post("/cultural-offerings")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtil.json(dto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(NON_EXISTING_ENTITY_NAME)));
    }
    
    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback(true)
    public void createTestFailsName() throws Exception {
    	CulturalOfferingDTO dto = new CulturalOfferingDTO(null, EXISTING_ENTITY_NAME, "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", EXISTING_TYPE_NAME, EXISTING_SUBTYPE_NAME, new ArrayList<Long>());

        mockMvc.perform(
                    post("/cultural-offerings")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtil.json(dto))
                )
                .andExpect(status().isInternalServerError());
    }
    
    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback(true)
    public void createTestFailsSubtypeName() throws Exception {
    	CulturalOfferingDTO dto = new CulturalOfferingDTO(null, NON_EXISTING_ENTITY_NAME, "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", EXISTING_TYPE_NAME, NON_EXISTING_SUBTYPE_NAME, new ArrayList<Long>());

        mockMvc.perform(
                    post("/cultural-offerings")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtil.json(dto))
                )
                .andExpect(status().isInternalServerError());
    }

}
