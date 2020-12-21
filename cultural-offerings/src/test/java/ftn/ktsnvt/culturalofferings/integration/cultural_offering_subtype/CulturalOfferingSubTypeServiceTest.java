package ftn.ktsnvt.culturalofferings.integration.cultural_offering_subtype;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.SQLDeleteEntityException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
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
import java.util.List;

import static ftn.ktsnvt.culturalofferings.integration.cultural_offering_subtype.CulturalOfferingSubTypeConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingSubTypeServiceTest {

    @Autowired
    private CulturalOfferingSubtypeService culturalOfferingSubTypeService;

    @Test
    public void findAllTest(){
        List<CulturalOfferingSubType> culturalOfferingSubTypes = culturalOfferingSubTypeService.findAll();
        assertEquals(culturalOfferingSubTypes.size(), LIST_SIZE);
    }

    @Test
    public void findAllByPageTest(){
        Page<CulturalOfferingSubType> culturalOferingSubTypePage = culturalOfferingSubTypeService.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        assertEquals(culturalOferingSubTypePage.getNumber(), PAGE_NUMBER);
        assertEquals(culturalOferingSubTypePage.getTotalPages(), 2);
        assertEquals(culturalOferingSubTypePage.getTotalElements(), LIST_SIZE);
    }

    @Test
    public void findOneFailTest(){
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingSubTypeService.findOne(NON_EXISTENT_ENTITY_ID));
    }

    @Test
    public void findOneSucceedTest(){
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubTypeService.findOne(EXISTING_ENTITY_ID);
        assertNotNull(culturalOfferingSubType);
        assertEquals(culturalOfferingSubType.getSubTypeName(), EXISTING_ENTITY_SUB_TYPE_NAME);
        assertEquals(culturalOfferingSubType.getCulturalOfferingType().getId().longValue(), EXISTING_ENTITY_CULTURAL_OFFERING_TYPE_ID);
    }

    @Test
    @Transactional
    @Rollback
    public void createTest(){
        CulturalOfferingSubType culturalOfferingSubType = new CulturalOfferingSubType();
        culturalOfferingSubType.setSubTypeName(NEW_ENTITY_SUB_TYPE_NAME);
        CulturalOfferingSubType culturalOfferingSubTypeCreated = culturalOfferingSubTypeService.create(culturalOfferingSubType);
        assertEquals(culturalOfferingSubTypeCreated.getSubTypeName(), culturalOfferingSubType.getSubTypeName());

    }

    @Test
    @Transactional
    @Rollback
    public void updateFailTest(){
        CulturalOfferingSubType culturalOfferingSubType = new CulturalOfferingSubType();
        culturalOfferingSubType.setId(NON_EXISTENT_ENTITY_ID);

        assertThrows(EntityNotFoundException.class, () -> culturalOfferingSubTypeService.update(culturalOfferingSubType, NON_EXISTENT_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void updateSucceedTest(){
        CulturalOfferingSubType culturalOfferingSubType = new CulturalOfferingSubType();
        culturalOfferingSubType.setId(UPDATE_ENTITY_ID);
        culturalOfferingSubType.setSubTypeName(UPDATE_ENTITY_SUB_TYPE_NAME);

        CulturalOfferingSubType culturalOfferingSubTypeUpdated = culturalOfferingSubTypeService.update(culturalOfferingSubType, UPDATE_ENTITY_ID);
        assertNotNull(culturalOfferingSubTypeUpdated);
        assertEquals(culturalOfferingSubTypeUpdated.getSubTypeName(), UPDATE_ENTITY_SUB_TYPE_NAME);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteFailTest1(){
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingSubTypeService.delete(NON_EXISTENT_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteFailTest2(){
        assertThrows(SQLDeleteEntityException.class, () -> culturalOfferingSubTypeService.delete(ENTITY_ID_WITH_REFERENCES));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteSucceedTest(){
        List<CulturalOfferingSubType> culturalOfferingSubTypes1 = culturalOfferingSubTypeService.findAll();
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubTypeService.findOne(DELETE_ENTITY_ID);
        culturalOfferingSubTypeService.delete(DELETE_ENTITY_ID);
        List<CulturalOfferingSubType> culturalOfferingSubTypes2 = culturalOfferingSubTypeService.findAll();

        assertNotNull(culturalOfferingSubType);
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingSubTypeService.findOne(DELETE_ENTITY_ID));
        assertEquals(culturalOfferingSubTypes1.size(), culturalOfferingSubTypes2.size() + 1);
    }
}
