package ftn.ktsnvt.culturalofferings.unit.cultural_offering_subtype;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingSubTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.mapper.CulturalOfferingSubTypeMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CulturalOfferingSubTypeMapperTest {

    @InjectMocks
    CulturalOfferingSubTypeMapper culturalOfferingSubTypeMapper;

    @Mock
    CulturalOfferingTypeService culturalOfferingTypeService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntityTest(){
        String typeName = "Name";
        long typeId = 5l;
        CulturalOfferingSubTypeDTO arg = new CulturalOfferingSubTypeDTO(typeName, typeId);

        CulturalOfferingType culturalOfferingTypeStub = new CulturalOfferingType();
        culturalOfferingTypeStub.setId(typeId);

        when(culturalOfferingTypeService.findOne(typeId)).thenReturn(culturalOfferingTypeStub);

        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubTypeMapper.toEntity(arg);

        assertEquals(typeName, culturalOfferingSubType.getSubTypeName());
        assertEquals(typeId, culturalOfferingSubType.getCulturalOfferingType().getId().longValue());
    }

    @Test
    public void toDtoTest(){
        String typeName = "Name";
        long typeId = 5l;

        CulturalOfferingSubType arg = new CulturalOfferingSubType();
        CulturalOfferingType culturalOfferingType = new CulturalOfferingType();

        culturalOfferingType.setId(typeId);
        arg.setSubTypeName(typeName);
        arg.setCulturalOfferingType(culturalOfferingType);

        CulturalOfferingSubTypeDTO culturalOfferingSubTypeDTO = culturalOfferingSubTypeMapper.toDto(arg);
        assertEquals(typeName, culturalOfferingSubTypeDTO.getSubTypeName());
        assertEquals(typeId, culturalOfferingSubTypeDTO.getTypeId().longValue());
    }

}
