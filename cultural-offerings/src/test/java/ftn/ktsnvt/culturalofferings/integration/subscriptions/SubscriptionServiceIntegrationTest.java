package ftn.ktsnvt.culturalofferings.integration.subscriptions;

import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.mapper.SubscriptionMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.SQLDeleteEntityException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.SubscriptionService;

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

import static ftn.ktsnvt.culturalofferings.integration.subscriptions.SubscriptionConstants.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class SubscriptionServiceIntegrationTest {
    @Autowired
    private SubscriptionService service;

    @Autowired
    private SubscriptionMapper mapper;

    @Test
    public void findAllTest() {
        List<Subscription> subs = service.findAll();
        assertEquals(SUBSCRIPTIONS, subs.size());
    }

    @Test
    public void findAllByPageTest() {
        Page<Subscription> subs = service.findAll(PageRequest.of(0, 10));
        assertEquals(SUBSCRIPTIONS, subs.getNumberOfElements());
    }

    @Test
    public void findOneFail() {
        assertThrows(EntityNotFoundException.class, () -> service.findOne(NON_EXISTING_ID));
    }

    @Test
    public void findOneSuccess() {
        Subscription s = service.findOne(EXISTING_ID);
        assertNotNull(s);
        assertEquals(s.getId(), Long.valueOf(EXISTING_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void create(){
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCulturalOffering(EXISTING_CO_ID);
        dto.setUser(EXISTING_USER_ID);
        Subscription s = mapper.toEntity(dto);
        s = service.create(s);

        assertEquals(EXISTING_USER_ID, s.getUser().getId());
        assertEquals(EXISTING_CO_ID, s.getCulturalOffering().getId());
    }

    @Test
    @Transactional
    @Rollback
    public void updateFailTest(){
        Subscription s = new Subscription();
        assertThrows(EntityNotFoundException.class, () -> service.update(s, NON_EXISTING_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void updateSuccessTest(){
        Subscription s = new Subscription();
        assertDoesNotThrow(() -> service.update(s, EXISTING_ID_TO_UPDATE));
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
