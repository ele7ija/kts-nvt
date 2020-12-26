package ftn.ktsnvt.culturalofferings.unit.news;

import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.mapper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class NewsMapperTest {
    @InjectMocks
    NewsMapper mapper;

    @Mock
    CulturalOfferingService cos;

    @Mock
    UserService us;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntity() {
        NewsDTO dto = new NewsDTO();
        dto.setCulturalOffering(10L);
        dto.setUser(10L);
        dto.setImages(new ArrayList<>());

        News n = new News();
        User u = new User();
        u.setId(10L);
        when(us.findOne(10L)).thenReturn(u);
        n.setUser(u);
        CulturalOffering co = new CulturalOffering();
        co.setId(10L);
        when(cos.findOne(10L)).thenReturn(co);
        n.setCulturalOffering(co);

        News n_dto = mapper.toEntity(dto);
        assertEquals(n_dto.getUser().getId(), n.getUser().getId());
        assertEquals(n_dto.getCulturalOffering().getId(), n.getCulturalOffering().getId());
    }

    @Test
    public void toDto() {
        News n = new News();
        User u = new User();
        u.setId(10L);
        when(us.findOne(10L)).thenReturn(u);
        n.setUser(u);
        CulturalOffering co = new CulturalOffering();
        co.setId(10L);
        when(cos.findOne(10L)).thenReturn(co);
        n.setCulturalOffering(co);
        n.setImages(new ArrayList<>());

        NewsDTO dto = mapper.toDto(n);
        assertEquals(dto.getUser(), n.getUser().getId());
        assertEquals(dto.getCulturalOffering(), n.getCulturalOffering().getId());
    }
}
