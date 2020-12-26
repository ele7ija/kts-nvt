package ftn.ktsnvt.culturalofferings.integration.subscriptions;

import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingSubtypeRepository;
import ftn.ktsnvt.culturalofferings.repository.SubscriptionRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static ftn.ktsnvt.culturalofferings.integration.subscriptions.SubscriptionConstants.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class SubscriptionRepositoryIntegrationTest {
    @Autowired
    private SubscriptionRepository repo;

    @Test
    public void findNonExistingById(){
        assertNull(repo.findById(NON_EXISTING_ID).orElse(null));
    }

    @Test
    public void findExistingById(){
        assertNotNull(this.repo.findById(EXISTING_ID).get());
    }

    @Test
    public void saveNullFieldsSuccess() {
        Subscription s = new Subscription();
        final Subscription s_saved = repo.save(s);
        assertNotNull(s_saved);
        assertDoesNotThrow(() -> repo.delete(s_saved));
    }
}
