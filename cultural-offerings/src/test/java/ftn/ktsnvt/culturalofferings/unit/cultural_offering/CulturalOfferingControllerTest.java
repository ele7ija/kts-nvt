package ftn.ktsnvt.culturalofferings.unit.cultural_offering;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ftn.ktsnvt.culturalofferings.controller.impl.CulturalOfferingController;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.mapper.CulturalOfferingsMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class CulturalOfferingControllerTest {
    public MockMvc mockMvc;

    @InjectMocks
    private CulturalOfferingController controller;

    @Mock
    private CulturalOfferingService service;

    @Mock
    private CulturalOfferingsMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findOneTest() throws Exception {
        long id = 5L;

        CulturalOffering sSTUB = new CulturalOffering();
        sSTUB.setId(id);

        CulturalOfferingDTO dto = new CulturalOfferingDTO();

        when(service.findOne(id)).thenReturn(sSTUB);
        when(mapper.toDto(sSTUB)).thenReturn(dto);

        mockMvc.perform(get("/cultural-offerings/{id}", id))
                .andExpect(status().isOk())
                ;
    }
}