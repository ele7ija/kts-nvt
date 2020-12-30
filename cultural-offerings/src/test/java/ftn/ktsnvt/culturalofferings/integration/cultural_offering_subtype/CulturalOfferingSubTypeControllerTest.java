package ftn.ktsnvt.culturalofferings.integration.cultural_offering_subtype;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingSubTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
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

import static ftn.ktsnvt.culturalofferings.integration.cultural_offering_subtype.CulturalOfferingSubTypeConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingSubTypeControllerTest {
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
        mockMvc.perform(get("/cultural-offering-subtypes"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/cultural-offering-subtypes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(LIST_SIZE)));
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findOneTestFail() throws Exception {
        mockMvc.perform(get("/cultural-offering-subtypes/{id}", NON_EXISTENT_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    public void findOneTestSucceed() throws Exception {
        mockMvc.perform(get("/cultural-offering-subtypes/{id}", EXISTING_ENTITY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subTypeName", is(EXISTING_ENTITY_SUB_TYPE_NAME)));
    }

    @Test
    @WithMockUser(authorities = READ_AUTHORITY)
    @Transactional
    @Rollback
    public void createTestFail() throws Exception {
        CulturalOfferingSubTypeDTO culturalOfferingSubTypeDTO = new CulturalOfferingSubTypeDTO(null, NEW_ENTITY_SUB_TYPE_NAME, EXISTING_ENTITY_CULTURAL_OFFERING_TYPE_ID);

        mockMvc.perform(
                post("/cultural-offering-subtypes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingSubTypeDTO))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void createTestSucceed() throws Exception {
        CulturalOfferingSubTypeDTO culturalOfferingSubTypeDTO = new CulturalOfferingSubTypeDTO(null, NEW_ENTITY_SUB_TYPE_NAME, EXISTING_ENTITY_CULTURAL_OFFERING_TYPE_ID);

        mockMvc.perform(
                post("/cultural-offering-subtypes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingSubTypeDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subTypeName", is(NEW_ENTITY_SUB_TYPE_NAME)));
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void updateTestFail1() throws Exception {
        CulturalOfferingSubTypeDTO culturalOfferingSubTypeDTO = new CulturalOfferingSubTypeDTO(NON_EXISTENT_ENTITY_ID, UPDATE_ENTITY_SUB_TYPE_NAME, EXISTING_ENTITY_CULTURAL_OFFERING_TYPE_ID);

        mockMvc.perform(
                put("/cultural-offering-types/{id}", NON_EXISTENT_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingSubTypeDTO))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void updateTestFail2() throws Exception {
        CulturalOfferingSubTypeDTO culturalOfferingSubTypeDTO = new CulturalOfferingSubTypeDTO(EXISTING_ENTITY_ID, UPDATE_ENTITY_SUB_TYPE_NAME, 123456l);

        mockMvc.perform(
                put("/cultural-offering-subtypes/{id}", EXISTING_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingSubTypeDTO))
        )
                .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void updateTestSucceed() throws Exception {
        CulturalOfferingSubTypeDTO culturalOfferingSubTypeDTO = new CulturalOfferingSubTypeDTO(UPDATE_ENTITY_ID, UPDATE_ENTITY_SUB_TYPE_NAME, EXISTING_ENTITY_CULTURAL_OFFERING_TYPE_ID);

        mockMvc.perform(
                put("/cultural-offering-subtypes/{id}", UPDATE_ENTITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(culturalOfferingSubTypeDTO))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subTypeName", is(UPDATE_ENTITY_SUB_TYPE_NAME)));
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void deleteTestFail1() throws Exception {
        mockMvc.perform(delete("/cultural-offering-subtypes/{id}", NON_EXISTENT_ENTITY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = WRITE_AUTHORITY)
    @Transactional
    @Rollback
    public void deleteTestFail2() throws Exception {
        mockMvc.perform(delete("/cultural-offering-subtypes/{id}", ENTITY_ID_WITH_REFERENCES))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(authorities = {READ_AUTHORITY, WRITE_AUTHORITY})
    @Transactional
    @Rollback
    public void deleteTestSucceed() throws Exception {
        mockMvc.perform(get("/cultural-offering-subtypes/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/cultural-offering-subtypes/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isOk());
        mockMvc.perform(get("/cultural-offering-subtypes/{id}", DELETE_ENTITY_ID))
                .andExpect(status().isNotFound());
    }
}
