package ftn.ktsnvt.culturalofferings.unit.service;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingTypeRepository;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

public class CulturalOfferingTypeServiceTest {

    @InjectMocks
    private CulturalOfferingTypeService culturalOfferingTypeService;

    @Mock
    private CulturalOfferingTypeRepository culturalOfferingTypeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll(){
        //Create a stub for the return value
        List<CulturalOfferingType> culturalOfferingTypesStub = Mockito.mock(List.class);

        //Mock the return value of the dependency method with the previously created stub
        when(culturalOfferingTypeRepository.findAll()).thenReturn(culturalOfferingTypesStub);

        // Execute method you wish to test
        List<CulturalOfferingType> culturalOfferingTypes = culturalOfferingTypeService.findAll();

        //verify that the dependency was mocked and that the desired method was called
        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findAll();

        //actual test
        assertEquals(culturalOfferingTypes, culturalOfferingTypesStub);
    }

    @Test
    public void testFindAllByPages(){
        //method args
        PageRequest pageRequest = PageRequest.of(1, 5);

        //Create a stub for the return value
        Page<CulturalOfferingType> culturalOfferingTypePageStub = Mockito.mock(Page.class);

        //Mock the return value of the dependency method with the previously created stub
        when(culturalOfferingTypeRepository.findAll(pageRequest)).thenReturn(culturalOfferingTypePageStub);

        // Execute method you wish to test
        Page<CulturalOfferingType> culturalOfferingTypePage = culturalOfferingTypeService.findAll(pageRequest);

        //verify that the dependency was mocked and that the desired method was called
        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findAll(pageRequest);

        //actual test
        assertEquals(culturalOfferingTypePage, culturalOfferingTypePageStub);
    }

    @Test
    public void testFindOneFail(){
        long culturalOfferingTypeId = 5;
        Optional<CulturalOfferingType> culturalOfferingTypeStub = Optional.ofNullable(null);

        when(culturalOfferingTypeRepository.findById(culturalOfferingTypeId)).thenReturn(culturalOfferingTypeStub);

        assertThrows(EntityNotFoundException.class, () -> culturalOfferingTypeService.findOne(culturalOfferingTypeId));
        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findById(culturalOfferingTypeId);
    }

    @Test
    public void testFindOneSucceed(){
        long culturalOfferingTypeId = 5;
        Optional<CulturalOfferingType> culturalOfferingTypeStub = Optional.ofNullable(new CulturalOfferingType());

        when(culturalOfferingTypeRepository.findById(culturalOfferingTypeId)).thenReturn(culturalOfferingTypeStub);

        CulturalOfferingType culturalOffering = culturalOfferingTypeService.findOne(culturalOfferingTypeId);

        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findById(culturalOfferingTypeId);
        assertEquals(culturalOffering, culturalOfferingTypeStub.get());
    }

    @Test
    public void testCreate(){
        CulturalOfferingType arg = new CulturalOfferingType();

        CulturalOfferingType culturalOfferingTypeStub = Mockito.mock(CulturalOfferingType.class);

        when(culturalOfferingTypeRepository.save(arg)).thenReturn(culturalOfferingTypeStub);

        CulturalOfferingType culturalOfferingType = culturalOfferingTypeService.create(arg);

        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).save(arg);
        assertEquals(culturalOfferingType, culturalOfferingTypeStub);
    }

    @Test
    public void testUpdateFail(){
        CulturalOfferingType arg = new CulturalOfferingType();
        Long id = 5l;

        Optional<CulturalOfferingType> culturalOfferingTypeStub = Optional.ofNullable(null);

        when(culturalOfferingTypeRepository.findById(id)).thenReturn(culturalOfferingTypeStub);

        assertThrows(EntityNotFoundException.class, () -> culturalOfferingTypeService.update(arg, id));
        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findById(id);
    }

    @Test
    public void testUpdateSucceed(){
        CulturalOfferingType arg = new CulturalOfferingType();
        Long id = 5l;

        Optional<CulturalOfferingType> culturalOfferingTypeStub1 = Optional.ofNullable(new CulturalOfferingType());
        CulturalOfferingType culturalOfferingTypeStub2 = new CulturalOfferingType();

        when(culturalOfferingTypeRepository.findById(id)).thenReturn(culturalOfferingTypeStub1);
        when(culturalOfferingTypeRepository.save(arg)).thenReturn(culturalOfferingTypeStub2);

        CulturalOfferingType culturalOfferingType = culturalOfferingTypeService.update(arg, id);

        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findById(id);
        verify(culturalOfferingTypeRepository).save(arg);
        assertEquals(culturalOfferingType, culturalOfferingTypeStub2);
    }

    @Test
    public void testDeleteFail(){
        Long id = 5l;

        Optional<CulturalOfferingType> culturalOfferingTypeStub = Optional.ofNullable(null);

        when(culturalOfferingTypeRepository.findById(id)).thenReturn(culturalOfferingTypeStub);

        assertThrows(EntityNotFoundException.class, () -> culturalOfferingTypeService.delete(id));
        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findById(id);
    }

    @Test
    public void testDeleteSucceed(){
        Long id = 5l;

        Optional<CulturalOfferingType> culturalOfferingTypeStub1 = Optional.ofNullable(new CulturalOfferingType());

        when(culturalOfferingTypeRepository.findById(id)).thenReturn(culturalOfferingTypeStub1);

        culturalOfferingTypeService.delete(id);

        assertTrue(mockingDetails(culturalOfferingTypeRepository).isMock());
        verify(culturalOfferingTypeRepository).findById(id);
        verify(culturalOfferingTypeRepository).delete(culturalOfferingTypeStub1.get());
    }

}
