package ftn.ktsnvt.culturalofferings.integration.news;

import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.repository.NewsRepository;
import ftn.ktsnvt.culturalofferings.repository.SubscriptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static ftn.ktsnvt.culturalofferings.integration.news.NewsConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class NewsRepositoryIntegrationTest {
    @Autowired
    private NewsRepository repo;

    @Test
    public void findNonExistingById(){
        assertNull(repo.findById(NON_EXISTING_ID).orElse(null));
    }

    @Test
    public void findExistingById(){
        assertNotNull(this.repo.findById(EXISTING_ID).get());
    }

    @Test
    public void saveNullFieldsFail() {
        News n = new News();
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(n));
    }
}
