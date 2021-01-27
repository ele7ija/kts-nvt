package ftn.ktsnvt.culturalofferings.integration.comment;

import ftn.ktsnvt.culturalofferings.repository.CommentRepository;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static ftn.ktsnvt.culturalofferings.integration.comment.CommentConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void findAllByCulturalOfferingIdTestSucceed(){
        assertEquals(this.commentRepository.findAllByCulturalOfferingId(EXISTING_CULTURAL_OFFERING_ID, null).getTotalElements(), 1);
    }

    @Test
    public void findAllByCulturalOfferingIdTestFail(){
        assertEquals(this.commentRepository.findAllByCulturalOfferingId(NON_EXISTING_CULTURAL_OFFERING_ID, null).getTotalElements(), 0);
    }
}
