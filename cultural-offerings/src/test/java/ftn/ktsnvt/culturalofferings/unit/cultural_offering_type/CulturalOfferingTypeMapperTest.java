package ftn.ktsnvt.culturalofferings.unit.cultural_offering_type;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.helper.CulturalOfferingTypeMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CulturalOfferingTypeMapperTest {

    @InjectMocks
    CulturalOfferingTypeMapper culturalOfferingTypeMapper;

    @Mock
    ImageService imageService;

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
}
