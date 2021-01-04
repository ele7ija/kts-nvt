package ftn.ktsnvt.culturalofferings.unit.cultural_offering_type;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeUpsertDTO;
import ftn.ktsnvt.culturalofferings.mapper.CulturalOfferingTypeMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.exceptions.ModelConstraintViolationException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CulturalOfferingTypeMapperTest {

    @InjectMocks
    CulturalOfferingTypeMapper culturalOfferingTypeMapper;

    @Mock
    ImageService imageService;

    @Mock
    CulturalOfferingSubtypeService culturalOfferingSubtypeService;

    @Mock
    CulturalOfferingTypeService culturalOfferingTypeService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntityTest(){
        String typeName = "Name";
        long imageModelId = 5l;

        CulturalOfferingTypeDTO arg = new CulturalOfferingTypeDTO();
        ImageModel imageModelStub = new ImageModel();

        arg.setTypeName(typeName);
        arg.setImageId(imageModelId);
        imageModelStub.setId(imageModelId);

        when(imageService.findOne(imageModelId)).thenReturn(imageModelStub);

        CulturalOfferingType culturalOfferingType = culturalOfferingTypeMapper.toEntity(arg);
        assertEquals(typeName, culturalOfferingType.getTypeName());
        assertEquals(imageModelId, culturalOfferingType.getImageModel().getId().longValue());
    }

    @Test
    public void toDtoTest(){
        String typeName = "Name";
        long imageModelId = 123l;

        CulturalOfferingType arg = new CulturalOfferingType();
        ImageModel imageModel = new ImageModel();

        imageModel.setId(imageModelId);
        arg.setTypeName(typeName);
        arg.setImageModel(imageModel);

        CulturalOfferingTypeDTO culturalOfferingTypeDTO = culturalOfferingTypeMapper.toDto(arg);
        assertEquals(typeName, culturalOfferingTypeDTO.getTypeName());
        assertEquals(imageModelId, culturalOfferingTypeDTO.getImageId().longValue());
    }

    @Test
    public void toEntityAddSubTypesTestFail(){
        CulturalOfferingTypeUpsertDTO arg = new CulturalOfferingTypeUpsertDTO();
        arg.setId(1l);
        arg.setTypeName("Name");

        CulturalOfferingType stub = new CulturalOfferingType();
        stub.setId(2l);
        stub.setTypeName("Name");

        List<CulturalOfferingType> listStub = new ArrayList<CulturalOfferingType>(Arrays.asList(stub));

        when(culturalOfferingTypeService.findAllByTypeName(arg.getTypeName())).thenReturn(listStub);
        assertThrows(ModelConstraintViolationException.class, () -> this.culturalOfferingTypeMapper.toEntityAddSubTypes(arg));
    }
}
