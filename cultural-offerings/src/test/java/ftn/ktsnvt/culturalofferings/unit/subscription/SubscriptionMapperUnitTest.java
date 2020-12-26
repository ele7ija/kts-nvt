package ftn.ktsnvt.culturalofferings.unit.subscription;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.mapper.CulturalOfferingTypeMapper;
import ftn.ktsnvt.culturalofferings.mapper.SubscriptionMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import ftn.ktsnvt.culturalofferings.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SubscriptionMapperUnitTest {
    @InjectMocks
    SubscriptionMapper mapper;

    @Mock
    ImageService imageService;

    @Mock
    CulturalOfferingService cos;

    @Mock
    UserService us;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntityTest(){
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCulturalOffering(10L);
        dto.setUser(10L);

        Subscription s = new Subscription();
        User u = new User();
        u.setId(10L);
        when(us.findOne(10L)).thenReturn(u);
        s.setUser(u);
        CulturalOffering co = new CulturalOffering();
        co.setId(10L);
        when(cos.findOne(10L)).thenReturn(co);
        s.setCulturalOffering(co);

        Subscription s_dto = mapper.toEntity(dto);
        assertEquals(s_dto.getUser().getId(), s.getUser().getId());
        assertEquals(s_dto.getCulturalOffering().getId(), s.getCulturalOffering().getId());
    }

    @Test
    public void toDtoTest(){
        Subscription s = new Subscription();
        User u = new User();
        u.setId(10L);
        when(us.findOne(10L)).thenReturn(u);
        s.setUser(u);
        CulturalOffering co = new CulturalOffering();
        co.setId(10L);
        when(cos.findOne(10L)).thenReturn(co);
        s.setCulturalOffering(co);

        SubscriptionDTO dto = mapper.toDto(s);
        assertEquals(dto.getUser(), s.getUser().getId());
        assertEquals(dto.getCulturalOffering(), s.getCulturalOffering().getId());
    }
}
