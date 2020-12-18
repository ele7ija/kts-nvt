package ftn.ktsnvt.culturalofferings.integration.cultural_offering_type;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.hamcrest.CoreMatchers.is;
import static ftn.ktsnvt.culturalofferings.integration.cultural_offering_type.CulturalOfferingTypeConstants.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingTypeControllerTest {

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
        mockMvc.perform(get("/cultural-offerings-types"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/cultural-offerings-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(LIST_SIZE)));
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    public void findOneTestFail() throws Exception {
        mockMvc.perform(get("/cultural-offerings-types/{id}", NON_EXISTENT_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    public void findOneTestSucceed() throws Exception {
        mockMvc.perform(get("/cultural-offerings-types/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeName", is(EXISTING_ENTITY_TYPE_NAME)));
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    @Transactional
    @Rollback(true)
    public void createTest() throws Exception {
        CulturalOfferingTypeDTO culturalOfferingTypeDTO = new CulturalOfferingTypeDTO();
        culturalOfferingTypeDTO.setTypeName(NEW_ENTITY_TYPE_NAME);
        culturalOfferingTypeDTO.setImageId(EXISTING_ENTITY_IMAGE_MODEL_ID);

        mockMvc.perform(
                    post("/cultural-offerings-types")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtil.json(culturalOfferingTypeDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.typeName", is(NEW_ENTITY_TYPE_NAME)));
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    @Transactional
    @Rollback(true)
    public void updateTestFail1() throws Exception {
        CulturalOfferingTypeDTO culturalOfferingTypeDTO = new CulturalOfferingTypeDTO();
        culturalOfferingTypeDTO.setTypeName(UPDATE_ENTITY_TYPE_NAME);
        culturalOfferingTypeDTO.setImageId(EXISTING_ENTITY_IMAGE_MODEL_ID);

        mockMvc.perform(
                put("/cultural-offerings-types/{id}", NON_EXISTENT_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingTypeDTO))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    @Transactional
    @Rollback(true)
    public void updateTestFail2() throws Exception {
        CulturalOfferingTypeDTO culturalOfferingTypeDTO = new CulturalOfferingTypeDTO();
        culturalOfferingTypeDTO.setTypeName(UPDATE_ENTITY_TYPE_NAME);
        culturalOfferingTypeDTO.setImageId(123456l);

        mockMvc.perform(
                put("/cultural-offerings-types/{id}", NON_EXISTENT_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingTypeDTO))
        )
                .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    @Transactional
    @Rollback(true)
    public void updateTestSucceed() throws Exception {
        CulturalOfferingTypeDTO culturalOfferingTypeDTO = new CulturalOfferingTypeDTO();
        culturalOfferingTypeDTO.setTypeName(UPDATE_ENTITY_TYPE_NAME);
        culturalOfferingTypeDTO.setImageId(EXISTING_ENTITY_IMAGE_MODEL_ID);

        mockMvc.perform(
                put("/cultural-offerings-types/{id}", UPDATE_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingTypeDTO))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeName", is(UPDATE_ENTITY_TYPE_NAME)));
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    @Transactional
    @Rollback(true)
    public void deleteTestFail() throws Exception {
        mockMvc.perform(delete("/cultural-offerings-types/{id}", NON_EXISTENT_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(MOCK_USER_EMAIL)
    @Transactional
    @Rollback(true)
    public void deleteTestSucceed() throws Exception {
        mockMvc.perform(get("/cultural-offerings-types/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/cultural-offerings-types/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(get("/cultural-offerings-types/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

}
