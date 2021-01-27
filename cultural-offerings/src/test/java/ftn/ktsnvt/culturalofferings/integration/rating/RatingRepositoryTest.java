package ftn.ktsnvt.culturalofferings.integration.rating;

import ftn.ktsnvt.culturalofferings.repository.RatingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static ftn.ktsnvt.culturalofferings.integration.rating.RatingConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RatingRepositoryTest {

    @Autowired
    RatingRepository ratingRepository;

    @Test
    public void findAllByCulturalOfferingIdTestSucceed(){
        assertEquals(this.ratingRepository.findAllByCulturalOfferingId(EXISTING_CULTURAL_OFFERING_ID, null).getTotalElements(), 1);
    }

    @Test
    public void findAllByCulturalOfferingIdTestFail(){
        assertEquals(this.ratingRepository.findAllByCulturalOfferingId(NON_EXISTING_CULTURAL_OFFERING_ID, null).getTotalElements(), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteAllByCulturalOfferingIdAndUserIdTestSucceed(){
        assertEquals(this.ratingRepository.deleteAllByCulturalOfferingIdAndUserId(EXISTING_CULTURAL_OFFERING_ID, EXISTING_USER_ID), 1);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteAllByCulturalOfferingIdAndUserIdTestFail(){
        assertEquals(this.ratingRepository.deleteAllByCulturalOfferingIdAndUserId(NON_EXISTING_CULTURAL_OFFERING_ID, EXISTING_USER_ID), 0);
    }
}
