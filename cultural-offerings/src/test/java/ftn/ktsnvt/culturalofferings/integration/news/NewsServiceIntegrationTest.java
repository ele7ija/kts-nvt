package ftn.ktsnvt.culturalofferings.integration.news;

import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.mapper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.service.NewsService;
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

import static ftn.ktsnvt.culturalofferings.integration.news.NewsConstants.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class NewsServiceIntegrationTest {

    @Autowired
    private NewsService service;

    @Autowired
    private NewsMapper mapper;

    @Test
    public void findAllTest() {
        List<News> n = service.findAll();
        assertEquals(NEWS, n.size());
    }

    @Test
    public void findAllByPageTest() {
        Page<News> n = service.findAll(PageRequest.of(0, 10));
        assertEquals(NEWS, n.getNumberOfElements());
    }

    @Test
    public void findOneFail() {
        assertThrows(EntityNotFoundException.class, () -> service.findOne(NON_EXISTING_ID));
    }

    @Test
    public void findOneSuccess() {
        News n = service.findOne(EXISTING_ID);
        assertNotNull(n);
        assertEquals(n.getId(), Long.valueOf(EXISTING_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void create(){
        NewsDTO dto = new NewsDTO();
        dto.setCulturalOffering(EXISTING_CO_ID);
        dto.setUser(EXISTING_USER_ID);
        dto.setTitle("");
        dto.setText("");
        dto.setImages(new ArrayList<>());
        News n = mapper.toEntity(dto);
        n = service.create(n);

        assertEquals(EXISTING_USER_ID, n.getUser().getId());
        assertEquals(EXISTING_CO_ID, n.getCulturalOffering().getId());
    }

    @Test
    @Transactional
    @Rollback
    public void updateFailTest(){
        News n = new News();
        n.setImages(new ArrayList<>());
        n.setText("");
        n.setTitle("");
        assertThrows(EntityNotFoundException.class, () -> service.update(n, NON_EXISTING_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void updateSuccessTest(){
        News n = new News();
        n.setImages(new ArrayList<>());
        n.setText("");
        n.setTitle("");
        assertDoesNotThrow(() -> service.update(n, EXISTING_ID_TO_UPDATE));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteFailTest(){
        assertThrows(EntityNotFoundException.class, () -> service.delete(NON_EXISTING_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteSuccessTest(){
        assertDoesNotThrow(() -> service.delete(EXISTING_ID_TO_DELETE));
    }
}
