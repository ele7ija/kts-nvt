package ftn.ktsnvt.culturalofferings.unit.cultural_offering_type;
import ftn.ktsnvt.culturalofferings.controller.impl.CulturalOfferingTypeController;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.helper.CulturalOfferingTypeMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;

/*
    Cisto kao proof of concept kako bi jedinicni testovi REST kontrolera bili radjeni
 */

public class CulturalOfferingTypeControllerTest {

    public MockMvc mockMvc;

    @InjectMocks
    private CulturalOfferingTypeController culturalOfferingTypeController;

    @Mock
    private CulturalOfferingTypeService culturalOfferingTypeService;

    @Mock
    private CulturalOfferingTypeMapper culturalOfferingTypeMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(culturalOfferingTypeController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        List<CulturalOfferingType> culturalOfferingTypesStub = new ArrayList<>();
        CulturalOfferingType culturalOfferingType = new CulturalOfferingType();
        culturalOfferingType.setTypeName("Name");
        culturalOfferingTypesStub.add(culturalOfferingType);

        CulturalOfferingTypeDTO culturalOfferingTypeDTOStub = new CulturalOfferingTypeDTO(culturalOfferingType.getTypeName(), null);

        when(culturalOfferingTypeService.findAll()).thenReturn(culturalOfferingTypesStub);
        when(culturalOfferingTypeMapper.toDto(culturalOfferingType)).thenReturn(culturalOfferingTypeDTOStub);

        mockMvc.perform(get("/cultural-offerings-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(culturalOfferingTypesStub.size())))
                .andExpect(jsonPath("$[0].typeName", is(culturalOfferingType.getTypeName())));
    }

    @Test
    public void findOneTest() throws Exception {
        long id = 5l;

        CulturalOfferingType culturalOfferingTypeStub = new CulturalOfferingType();
        culturalOfferingTypeStub.setId(id);
        culturalOfferingTypeStub.setTypeName("Name");

        CulturalOfferingTypeDTO culturalOfferingTypeDTOStub = new CulturalOfferingTypeDTO();
        culturalOfferingTypeDTOStub.setTypeName(culturalOfferingTypeStub.getTypeName());

        when(culturalOfferingTypeService.findOne(id)).thenReturn(culturalOfferingTypeStub);
        when(culturalOfferingTypeMapper.toDto(culturalOfferingTypeStub)).thenReturn(culturalOfferingTypeDTOStub);

        mockMvc.perform(get("/cultural-offerings-types/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeName", is(culturalOfferingTypeDTOStub.getTypeName())));
    }

}
