package ftn.ktsnvt.culturalofferings.integration.rating;

import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.service.RatingService;
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

import static ftn.ktsnvt.culturalofferings.integration.rating.RatingConstants.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RatingServiceTest {

    @Autowired
    RatingService ratingService;

    @Test
    public void findAllTest(){
        List<RatingDTO> ratingList = ratingService.findAll();
        assertEquals(ratingList.size(), LIST_SIZE);
    }

    @Test
    public void findAllByPageTest(){
        Page<RatingDTO> ratingPage = ratingService.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        assertEquals(ratingPage.getNumber(), PAGE_NUMBER);
        assertEquals(ratingPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(ratingPage.getTotalElements(), LIST_SIZE);
        assertEquals(ratingPage.getNumberOfElements(), PAGE_SIZE);
    }

    @Test
    public void findAllByPageTestFails(){
        Page<RatingDTO> ratingPage = ratingService.findAll(PageRequest.of(UNREACHABLE_PAGE_NUMBER, PAGE_SIZE));
        assertEquals(ratingPage.getNumber(), UNREACHABLE_PAGE_NUMBER);
        assertEquals(ratingPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(ratingPage.getTotalElements(), LIST_SIZE);
        assertEquals(ratingPage.getNumberOfElements(), 0);
    }

    @Test
    public void findOneTest(){
        RatingDTO ratingDTO = ratingService.findOne(EXISTING_ENTITY_ID);
        assertNotNull(ratingDTO);
        assertEquals(ratingDTO.getId().longValue(), EXISTING_ENTITY_ID);
    }

    @Test
    public void findOneTestFails(){
        assertThrows(EntityNotFoundException.class, () -> ratingService.findOne(NON_EXISTING_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void insertTest() throws Exception {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setCulturalOfferingId(EXISTING_CULTURAL_OFFERING_ID);
        ratingDTO.setUserId(EXISTING_USER_ID);
        ratingDTO.setValue(NEW_VALUE);
        assertEquals(ratingService.findAll().size(), LIST_SIZE);
        RatingDTO newRatingDTO = ratingService.create(ratingDTO, EXISTING_USER_EMAIL);
        assertEquals(ratingService.findAll().size(), LIST_SIZE); //before add, previous rating is deleted
        assertEquals(ratingDTO.getCulturalOfferingId(), newRatingDTO.getCulturalOfferingId());
        assertEquals(ratingDTO.getUserId(), newRatingDTO.getUserId());
        assertEquals(ratingDTO.getValue(), newRatingDTO.getValue());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTest() throws Exception {
        RatingDTO ratingDTO = ratingService.findOne(EXISTING_ENTITY_ID);
        assertNotNull(ratingDTO);
        assertEquals(ratingDTO.getId().longValue(), EXISTING_ENTITY_ID);
        ratingService.delete(EXISTING_ENTITY_ID);
        assertThrows(EntityNotFoundException.class, () -> ratingService.findOne(EXISTING_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTestFails(){
        assertThrows(EntityNotFoundException.class, () -> ratingService.delete(NON_EXISTING_ENTITY_ID));
    }
}
