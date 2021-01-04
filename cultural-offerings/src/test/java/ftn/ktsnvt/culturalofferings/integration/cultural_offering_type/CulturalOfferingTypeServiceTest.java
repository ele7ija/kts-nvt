package ftn.ktsnvt.culturalofferings.integration.cultural_offering_type;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.SQLDeleteEntityException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static ftn.ktsnvt.culturalofferings.integration.cultural_offering_type.CulturalOfferingTypeConstants.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingTypeServiceTest {

    @Autowired
    private CulturalOfferingTypeService culturalOfferingTypeService;

    @Autowired
    private CulturalOfferingSubtypeService culturalOfferingSubtypeService;

    @Test
    public void findAllTest(){
        List<CulturalOfferingType> culturalOfferingTypes = culturalOfferingTypeService.findAll();
        assertEquals(culturalOfferingTypes.size(), LIST_SIZE);
    }

    @Test
    public void findAllByPageTest(){
        Page<CulturalOfferingType> culturalOfferingTypePage = culturalOfferingTypeService.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        assertEquals(culturalOfferingTypePage.getNumber(), PAGE_NUMBER);
        assertEquals(culturalOfferingTypePage.getTotalPages(), 2);
        assertEquals(culturalOfferingTypePage.getTotalElements(), LIST_SIZE);
    }

    @Test
    public void findOneFailTest(){
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingTypeService.findOne(NON_EXISTENT_ENTITY_ID));
    }

    @Test
    public void findOneSucceedTest(){
        CulturalOfferingType culturalOfferingType = culturalOfferingTypeService.findOne(EXISTING_ENTITY_ID);
        assertNotNull(culturalOfferingType);
        assertEquals(culturalOfferingType.getTypeName(), EXISTING_ENTITY_TYPE_NAME);
        assertEquals(culturalOfferingType.getImageModel().getId().longValue(), EXISTING_ENTITY_IMAGE_MODEL_ID);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createTest(){
        String subTypeName = "qweqwfwefwef";
        CulturalOfferingSubType culturalOfferingSubType = new CulturalOfferingSubType();
        culturalOfferingSubType.setSubTypeName(subTypeName);

        CulturalOfferingType culturalOfferingType = new CulturalOfferingType();
        culturalOfferingType.setTypeName(NEW_ENTITY_TYPE_NAME);
        culturalOfferingType.setCulturalOfferingSubTypes(new HashSet<>(Arrays.asList(culturalOfferingSubType)));

        culturalOfferingSubtypeService.create(culturalOfferingSubType);

        CulturalOfferingType culturalOfferingTypeCreated = culturalOfferingTypeService.create(culturalOfferingType);
        assertEquals(culturalOfferingTypeCreated.getTypeName(), culturalOfferingType.getTypeName());
        assertEquals(culturalOfferingTypeCreated.getCulturalOfferingSubTypes().size(), 1);
        assertEquals(culturalOfferingTypeCreated.getCulturalOfferingSubTypes().stream().findFirst().get().getSubTypeName(), subTypeName);
        assertEquals(culturalOfferingTypeCreated.getCulturalOfferingSubTypes().stream().findFirst().get().getCulturalOfferingType(), culturalOfferingTypeCreated);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateFailTest(){
        CulturalOfferingType culturalOfferingType = new CulturalOfferingType();
        culturalOfferingType.setId(NON_EXISTENT_ENTITY_ID);

        assertThrows(EntityNotFoundException.class, () -> culturalOfferingTypeService.update(culturalOfferingType, NON_EXISTENT_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateSucceedTest(){
        CulturalOfferingType culturalOfferingType = new CulturalOfferingType();
        culturalOfferingType.setCulturalOfferingSubTypes(new HashSet<>());
        culturalOfferingType.getCulturalOfferingSubTypes().addAll(culturalOfferingSubtypeService.getAllByTypeId(UPDATE_ENTITY_ID));
        culturalOfferingType.setId(UPDATE_ENTITY_ID);
        culturalOfferingType.setTypeName(UPDATE_ENTITY_TYPE_NAME);

        CulturalOfferingType culturalOfferingTypeUpdated = culturalOfferingTypeService.update(culturalOfferingType, UPDATE_ENTITY_ID);
        assertNotNull(culturalOfferingTypeUpdated);
        assertEquals(culturalOfferingTypeUpdated.getTypeName(), UPDATE_ENTITY_TYPE_NAME);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteFailTest1(){
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingTypeService.delete(NON_EXISTENT_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteFailTest2(){
        assertThrows(SQLDeleteEntityException.class, () -> culturalOfferingTypeService.delete(ENTITY_ID_WITH_REFERENCES));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteSucceedTest(){
        List<CulturalOfferingType> culturalOfferingTypes1 = culturalOfferingTypeService.findAll();
        CulturalOfferingType culturalOfferingType = culturalOfferingTypeService.findOne(DELETE_ENTITY_ID);
        culturalOfferingTypeService.delete(DELETE_ENTITY_ID);
        List<CulturalOfferingType> culturalOfferingTypes2 = culturalOfferingTypeService.findAll();

        assertNotNull(culturalOfferingType);
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingTypeService.findOne(DELETE_ENTITY_ID));
        assertEquals(culturalOfferingTypes1.size(), culturalOfferingTypes2.size() + 1);
    }
}
