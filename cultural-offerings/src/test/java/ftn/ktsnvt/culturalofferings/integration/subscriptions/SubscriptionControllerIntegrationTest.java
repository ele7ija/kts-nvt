package ftn.ktsnvt.culturalofferings.integration.subscriptions;

import ftn.ktsnvt.culturalofferings.TestUtil;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.model.Subscription;
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

import static ftn.ktsnvt.culturalofferings.integration.subscriptions.SubscriptionConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class SubscriptionControllerIntegrationTest {
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
        mockMvc.perform(get("/subscriptions"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:read")
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/subscriptions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(SUBSCRIPTIONS)));
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:read")
    public void findOneTestFail() throws Exception {
        mockMvc.perform(get("/subscriptions/{id}", NON_EXISTING_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:read")
    public void findOneTestSucceed() throws Exception {
        mockMvc.perform(get("/subscriptions/{id}", EXISTING_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user", is(EXISTING_USER_ID.intValue())));
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:read")
    @Transactional
    @Rollback(true)
    public void createTestFail() throws Exception {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setUser(EXISTING_USER_ID);
        dto.setCulturalOffering(EXISTING_CO_ID);

        mockMvc.perform(
                post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:write")
    @Transactional
    @Rollback(true)
    public void createTestSucceed() throws Exception {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setUser(EXISTING_USER_ID);
        dto.setCulturalOffering(EXISTING_CO_ID);

        mockMvc.perform(
                post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:write")
    @Transactional
    @Rollback(true)
    public void updateTestFail1() throws Exception {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCulturalOffering(EXISTING_CO_ID);
        dto.setUser(EXISTING_USER_ID);

        mockMvc.perform(
                put("/subscriptions/{id}", NON_EXISTING_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:write")
    @Transactional
    @Rollback(true)
    public void updateTestFail2() throws Exception {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCulturalOffering(NON_EXISTING_CO_ID);
        dto.setUser(EXISTING_USER_ID);

        mockMvc.perform(
                put("/subscriptions/{id}", EXISTING_ID_TO_UPDATE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:write")
    @Transactional
    @Rollback(true)
    public void updateTestFail3() throws Exception {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCulturalOffering(EXISTING_CO_ID);
        dto.setUser(NON_EXISTING_USER_ID);

        mockMvc.perform(
                put("/subscriptions/{id}", EXISTING_ID_TO_UPDATE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:write")
    @Transactional
    @Rollback(true)
    public void updateTestSucceed() throws Exception {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCulturalOffering(EXISTING_CO_ID);
        dto.setUser(EXISTING_USER_ID);

        mockMvc.perform(
                put("/subscriptions/{id}", EXISTING_ID_TO_UPDATE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.json(dto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user", is(EXISTING_USER_ID.intValue())))
                .andExpect(jsonPath("$.culturalOffering", is(EXISTING_CO_ID.intValue())));
    }

    @Test
    @WithMockUser(authorities = "SUBSCRIPTION:write")
    @Transactional
    @Rollback(true)
    public void deleteTestFail1() throws Exception {
        mockMvc.perform(delete("/subscriptions/{id}", NON_EXISTING_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {"SUBSCRIPTION:write", "SUBSCRIPTION:read"})
    @Transactional
    @Rollback(true)
    public void deleteTestSucceed() throws Exception {
        mockMvc.perform(delete("/subscriptions/{id}", EXISTING_ID_TO_DELETE))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/subscriptions/{id}", EXISTING_ID_TO_DELETE))
                .andExpect(status().isNotFound());
    }
}
