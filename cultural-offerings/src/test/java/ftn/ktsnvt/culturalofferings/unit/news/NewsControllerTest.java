package ftn.ktsnvt.culturalofferings.unit.news;

import ftn.ktsnvt.culturalofferings.controller.impl.NewsController;
import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.mapper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.service.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NewsControllerTest {
    public MockMvc mockMvc;

    @InjectMocks
    private NewsController controller;

    @Mock
    private NewsService service;

    @Mock
    private NewsMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findAllByPageTest() throws Exception {
        List<News> newsSTUB = new ArrayList<>();
        News n = new News();
        newsSTUB.add(n);
        NewsDTO dto = new NewsDTO();
        Page<News> page = new PageImpl<>(newsSTUB);

        when(service.findAll(PageRequest.of(0, 10))).thenReturn(page);
        when(mapper.toDto(n)).thenReturn(dto);

        mockMvc.perform(get("/news/all/by-page?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(newsSTUB.size())))
        ;
    }

    @Test
    public void findAll() throws Exception {
        List<News> newsSTUB = new ArrayList<>();
        News n = new News();
        newsSTUB.add(n);

        NewsDTO dto = new NewsDTO();

        when(service.findAll()).thenReturn(newsSTUB);
        when(mapper.toDto(n)).thenReturn(dto);

        mockMvc.perform(get("/news/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(newsSTUB.size())))
        ;
    }

    @Test
    public void findOneTest() throws Exception {
        long id = 5L;

        News nSTUB = new News();
        nSTUB.setId(id);

        NewsDTO dto = new NewsDTO();

        when(service.findOne(id)).thenReturn(nSTUB);
        when(mapper.toDto(nSTUB)).thenReturn(dto);

        mockMvc.perform(get("/news/{id}", id))
                .andExpect(status().isOk())
        ;
    }
}

