package ftn.ktsnvt.culturalofferings.unit.news;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.NewsRepository;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import ftn.ktsnvt.culturalofferings.service.NewsService;
import ftn.ktsnvt.culturalofferings.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsServiceTest {
    @Autowired
    private NewsService service;

    @MockBean
    private NewsRepository repo;

    @Autowired
    private UserService userService;

    @Autowired
    private CulturalOfferingService culturalOfferingService;

    @Autowired
    private ImageService imageService;

    @Before
    public void setup() {
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
}
