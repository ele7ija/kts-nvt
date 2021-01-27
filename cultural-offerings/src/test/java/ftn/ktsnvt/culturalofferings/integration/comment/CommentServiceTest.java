package ftn.ktsnvt.culturalofferings.integration.comment;


import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.service.CommentService;
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
import java.util.ArrayList;
import java.util.List;

import static ftn.ktsnvt.culturalofferings.integration.comment.CommentConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    public void findAllTest(){
        List<CommentDTO> commentList = commentService.findAll();
        assertEquals(commentList.size(), LIST_SIZE);
    }

    @Test
    public void findAllByPageTest(){
        Page<CommentDTO> commentPage = commentService.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        assertEquals(commentPage.getNumber(), PAGE_NUMBER);
        assertEquals(commentPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(commentPage.getTotalElements(), LIST_SIZE);
        assertEquals(commentPage.getNumberOfElements(), PAGE_SIZE);
    }

    @Test
    public void findAllByPageTestFails(){
        Page<CommentDTO> commentPage = commentService.findAll(PageRequest.of(UNREACHABLE_PAGE_NUMBER, PAGE_SIZE));
        assertEquals(commentPage.getNumber(), UNREACHABLE_PAGE_NUMBER);
        assertEquals(commentPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(commentPage.getTotalElements(), LIST_SIZE);
        assertEquals(commentPage.getNumberOfElements(), 0);
    }

    @Test
    public void findOneTest(){
        CommentDTO commentDTO = commentService.findOne(EXISTING_ENTITY_ID);
        assertNotNull(commentDTO);
        assertEquals(commentDTO.getId().longValue(), EXISTING_ENTITY_ID);
    }

    @Test
    public void findOneTestFails(){
        assertThrows(EntityNotFoundException.class, () -> commentService.findOne(NON_EXISTING_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void insertTest(){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCulturalOfferingId(EXISTING_CULTURAL_OFFERING_ID);
        commentDTO.setUserId(EXISTING_USER_ID);
        commentDTO.setImageIds(new ArrayList<>());
        commentDTO.setText(NEW_COMMENT_TEXT);
        assertEquals(commentService.findAll().size(), LIST_SIZE);
        CommentDTO newCommentDTO = commentService.create(commentDTO);
        assertEquals(commentService.findAll().size(), LIST_SIZE + 1);
        assertEquals(commentDTO.getCulturalOfferingId(), newCommentDTO.getCulturalOfferingId());
        assertEquals(commentDTO.getUserId(), newCommentDTO.getUserId());
        assertEquals(commentDTO.getText(), newCommentDTO.getText());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTest(){
        CommentDTO commentDTO = commentService.findOne(EXISTING_ENTITY_ID);
        assertNotNull(commentDTO);
        assertEquals(commentDTO.getId().longValue(), EXISTING_ENTITY_ID);
        commentService.delete(EXISTING_ENTITY_ID);
        assertThrows(EntityNotFoundException.class, () -> commentService.findOne(EXISTING_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTestFails(){
        assertThrows(EntityNotFoundException.class, () -> commentService.delete(NON_EXISTING_ENTITY_ID));
    }

}
