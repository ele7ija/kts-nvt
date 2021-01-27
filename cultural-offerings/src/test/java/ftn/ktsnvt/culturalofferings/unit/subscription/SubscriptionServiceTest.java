package ftn.ktsnvt.culturalofferings.unit.subscription;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.SubscriptionRepository;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.SubscriptionService;
import ftn.ktsnvt.culturalofferings.service.UserService;

public class SubscriptionServiceTest {
    
    @InjectMocks
    private SubscriptionService subscriptionService;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private UserService userService;

    @Mock
    private CulturalOfferingService culturalOfferingService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription());
        subscriptions.add(new Subscription());
        given(subscriptionRepository.findAll()).willReturn(subscriptions);

        Pageable pageable = PageRequest.of(0, 2);
        Page<Subscription> page = new PageImpl<>(subscriptions, pageable, 2);
        given(subscriptionRepository.findAll(pageable)).willReturn(page);

        Subscription id1 = new Subscription();
        Subscription id1_saved = new Subscription();
        id1_saved.setId(1L);
        given(subscriptionRepository.findById(1L)).willReturn(Optional.of(id1_saved));
        given(subscriptionRepository.save(id1)).willReturn(id1_saved);
    }

    @Test
    public void testFindAll() {
        List<Subscription> found = subscriptionService.findAll();
        verify(subscriptionRepository, times(1)).findAll();

        assertEquals(2, found.size());
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Subscription> found = subscriptionService.findAll(pageable);

        verify(subscriptionRepository, times(1)).findAll(pageable);
        assertEquals(2, found.getNumberOfElements());
    }

    @Test
    public void testFindById() {
        Subscription found = subscriptionService.findOne(1L);

        verify(subscriptionRepository, times(1)).findById(1L);
        assertEquals(Long.valueOf(1L), found.getId());
    }

    @Test
    public void testCreate() {
        Subscription id1 = new Subscription();
        Subscription id1_saved = subscriptionService.create(id1);

        verify(subscriptionRepository, times(1)).save(id1);
        assertEquals(id1_saved.getId(), Long.valueOf(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        Subscription s1 = new Subscription();
        User user = new User(); 
        user.setId(1L);
        s1.setUser(user);
        CulturalOffering co = new CulturalOffering();
        co.setId(1L);
        s1.setCulturalOffering(co);
        Subscription s1_updated = new Subscription();
        s1_updated.setUser(user);
        s1_updated.setCulturalOffering(co);
        s1_updated.setId(1l);

        // This was previously in the database
        Subscription s2_saved = new Subscription();
        s2_saved.setId(1L);
        assertEquals(null, s2_saved.getCulturalOffering());

        given(subscriptionRepository.findById(1L)).willReturn(Optional.of(s2_saved));
        given(subscriptionRepository.save(s2_saved)).willReturn(s1_updated);

        Subscription s2_updated = subscriptionService.update(s1, 1L);
        assertEquals(Long.valueOf(1L), s2_updated.getCulturalOffering().getId());
        assertEquals(Long.valueOf(1L), s2_updated.getUser().getId());

        // verify(culturalContentCategoryRepository, times(1)).findById(CATEGORY_ID);
        // verify(culturalContentCategoryRepository, times(1)).findByNameAndIdNot(NEW_CATEGORY,CATEGORY_ID);

        // assertEquals(NEW_CATEGORY, created.getName());
    }

    // @Test
    // public void testDelete() throws Exception {
    //     culturalContentCategoryService.delete(CATEGORY_ID);

    //     CulturalContentCategory savedCulturalContentCategory = new CulturalContentCategory(NEW_CATEGORY);
    //     savedCulturalContentCategory.setId(CATEGORY_ID);

    //     verify(culturalContentCategoryRepository, times(1)).findById(CATEGORY_ID);
    // }
}
