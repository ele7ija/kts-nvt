package ftn.ktsnvt.culturalofferings.unit.subscription;

import ftn.ktsnvt.culturalofferings.controller.impl.CulturalOfferingTypeController;
import ftn.ktsnvt.culturalofferings.controller.impl.SubscriptionController;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.mapper.CulturalOfferingTypeMapper;
import ftn.ktsnvt.culturalofferings.mapper.SubscriptionMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import ftn.ktsnvt.culturalofferings.service.SubscriptionService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;

public class SubscriptionControllerUnitTest {
    public MockMvc mockMvc;

    @InjectMocks
    private SubscriptionController controller;

    @Mock
    private SubscriptionService service;

    @Mock
    private SubscriptionMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findAllByPageTest() throws Exception {
        List<Subscription> subscriptionsSTUB = new ArrayList<>();
        Subscription s = new Subscription();
        subscriptionsSTUB.add(s);

        SubscriptionDTO dto = new SubscriptionDTO();
        Page<Subscription> page = new PageImpl<>(subscriptionsSTUB);
        when(service.findAll(PageRequest.of(0, 10))).thenReturn(page);
        when(mapper.toDto(s)).thenReturn(dto);

        mockMvc.perform(get("/subscriptions/all/by-page?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(subscriptionsSTUB.size())))
                ;
    }

    @Test
    public void findAll() throws Exception {
        List<Subscription> subscriptionsSTUB = new ArrayList<>();
        Subscription s = new Subscription();
        subscriptionsSTUB.add(s);

        SubscriptionDTO dto = new SubscriptionDTO();

        when(service.findAll()).thenReturn(subscriptionsSTUB);
        when(mapper.toDto(s)).thenReturn(dto);

        mockMvc.perform(get("/subscriptions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(subscriptionsSTUB.size())))
                ;
    }

    @Test
    public void findOneTest() throws Exception {
        long id = 5L;

        Subscription sSTUB = new Subscription();
        sSTUB.setId(id);

        SubscriptionDTO dto = new SubscriptionDTO();

        when(service.findOne(id)).thenReturn(sSTUB);
        when(mapper.toDto(sSTUB)).thenReturn(dto);

        mockMvc.perform(get("/subscriptions/{id}", id))
                .andExpect(status().isOk())
                ;
    }
}
