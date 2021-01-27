package ftn.ktsnvt.culturalofferings.unit.news;

import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.mapper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingRepository;
import ftn.ktsnvt.culturalofferings.repository.NewsRepository;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.EmailServiceImpl;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import ftn.ktsnvt.culturalofferings.service.NewsService;
import ftn.ktsnvt.culturalofferings.service.UserService;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.rowset.serial.SerialArray;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NewsServiceTest {
    @InjectMocks
    private NewsService service;

    @Mock
    private NewsRepository repo;

    @Mock
    private UserService userService;

    @Mock
    private CulturalOfferingService culturalOfferingService;

    @Mock
    private ImageService imageService;

    @Mock
    private EmailServiceImpl emailService;

    @Mock
    private NewsMapper newsMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        News id1 = new News();
        News id1_saved = new News();
        id1_saved.setId(1L);
        given(repo.findById(1L)).willReturn(Optional.of(id1_saved));
        given(repo.save(id1)).willReturn(id1_saved);
    }

    @Test
    public void findAll() {
        List<News> news = new ArrayList<>();
        news.add(new News());
        news.add(new News());
        given(repo.findAll()).willReturn(news);

        List<News> found = service.findAll();
        verify(repo, times(1)).findAll();

        assertEquals(2, found.size());
    }

    @Test
    public void testFindAllPageable() {
        List<News> news = new ArrayList<>();
        news.add(new News());
        news.add(new News());
        Pageable pageable = PageRequest.of(0, 2);
        Page<News> page = new PageImpl<>(news, pageable, 2);
        given(repo.findAll(pageable)).willReturn(page);

        Page<News> found = service.findAll(pageable);

        verify(repo, times(1)).findAll(pageable);
        assertEquals(2, found.getNumberOfElements());
    }

    @Test
    public void testFindById() {
        News id1_saved = new News();
        id1_saved.setId(1L);
        given(repo.findById(1L)).willReturn(Optional.of(id1_saved));

        News found = service.findOne(1L);

        verify(repo, times(1)).findById(1L);
        assertEquals(Long.valueOf(1L), found.getId());
    }

    @Test
    public void testCreate() {
        News id1 = new News();
        News id1_saved = new News();
        id1_saved.setId(1L);
        given(repo.save(id1)).willReturn(id1_saved);

        service.create(id1);

        verify(repo, times(1)).save(id1);
        assertEquals(id1_saved.getId(), Long.valueOf(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        News n = new News();
        n.setId(1L);
        n.setTitle("Sacuvan");
        given(repo.findById(1L)).willReturn(Optional.of(n));

        News n_updated = new News();
        n_updated.setId(1L);
        n_updated.setTitle("Izmenjen");
        given(repo.save(n_updated)).willReturn(n_updated);

        // Postoji entitet u bazi pre
        assertEquals("Sacuvan", service.findOne(1L).getTitle());
        // Izmeni entitet
        assertEquals("Izmenjen", service.update(n_updated, 1L).getTitle());
        // Promenjen entitet
        given(repo.findById(1L)).willReturn(Optional.of(n_updated));
        assertEquals("Izmenjen", service.findOne(1L).getTitle());

        verify(repo, times(3)).findById(1L);
        verify(repo, times(1)).save(n_updated);
    }

    @Test
    public void testNotifyNews() {
        CulturalOffering co = new CulturalOffering();

        Set<Subscription> subscriptions = new HashSet<Subscription>();

        Subscription s = new Subscription();
        s.setCulturalOffering(co);
        User u = new User();
        u.setEmail("bpoprzen@gmail.com");
        String[] users = {u.getEmail()};
        u.setId(1L);
        s.setUser(u);
        s.setId(1L);
        s.setUser(u);
        subscriptions.add(s);
        co.setSubscriptions(subscriptions);

        News n = new News();
        n.setId(1L);
        n.setCulturalOffering(co);

        given(repo.findById(1l)).willReturn(Optional.of(n));

        service.notifyNews(1L);

        verify(emailService, times(1)).sendNewsLetter(n, users, null);
    }

    @Test
    public void testFindByIdOfCulturalOffering() {
        CulturalOffering co = new CulturalOffering();
        co.setId(1L);

        News n = new News();
        n.setId(1L);
        n.setCulturalOffering(co);

        Set<News> ns = new HashSet<News>();
        ns.add(n);

        co.setNews(ns);

        given(culturalOfferingService.findOne(1L)).willReturn(co);

        List<NewsDTO> dtos = new ArrayList<NewsDTO>();
        NewsDTO dto = new NewsDTO();
        dto.setCulturalOffering(1L);
        dto.setId(1L);
        dtos.add(dto);
        Page<NewsDTO> page = new PageImpl<NewsDTO>(dtos);

        given(newsMapper.toDto(n)).willReturn(dto);

        Page<NewsDTO> res = service.findAllNewsById(PageRequest.of(0, 10), 1L);

        assertEquals(1, res.getTotalElements());
    }
}
