package ftn.ktsnvt.culturalofferings.unit.user;

import ftn.ktsnvt.culturalofferings.dto.UserDTO;
import ftn.ktsnvt.culturalofferings.mapper.UserMapper;
import ftn.ktsnvt.culturalofferings.model.*;
import ftn.ktsnvt.culturalofferings.service.CommentService;
import ftn.ktsnvt.culturalofferings.service.NewsService;
import ftn.ktsnvt.culturalofferings.service.RatingService;
import ftn.ktsnvt.culturalofferings.service.SubscriptionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ftn.ktsnvt.culturalofferings.integration.user.UserConstants.*;

public class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Mock
    NewsService newsService;

    @Mock
    SubscriptionService subscriptionService;

    @Mock
    private CommentService commentService;

    @Mock
    private RatingService ratingService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toDto(){
        User user = new User();
        user.setEmail(EXISTING_ENTITY_EMAIL);
        user.setPassword(EXISTING_ENTITY_PASSWORD);
        user.setFirstName(EXISTING_ENTITY_FIRST_NAME);
        user.setLastName(EXISTING_ENTITY_LAST_NAME);
        user.setRole(EXISTING_ENTITY_USER_ROLE);
        user.setEnabled(true);
        user.setNews(new HashSet<>(Arrays.asList(new News(), new News())));

        user.setComments(new HashSet<>(Arrays.asList(new Comment(), new Comment())));

        user.setSubscriptions(new HashSet<>(Arrays.asList(new Subscription(), new Subscription())));

        user.setRatings(new HashSet<>(Arrays.asList(new Rating(), new Rating())));

        UserDTO userDTO = userMapper.toDto(user);
        assertEquals(userDTO.getEmail(), EXISTING_ENTITY_EMAIL);
        assertEquals(userDTO.getPassword(), EXISTING_ENTITY_PASSWORD);
        assertEquals(userDTO.getFirstName(), EXISTING_ENTITY_FIRST_NAME);
        assertEquals(userDTO.getLastName(), EXISTING_ENTITY_LAST_NAME);
        assertEquals(userDTO.getUserRole(), EXISTING_ENTITY_USER_ROLE);
        assertTrue(userDTO.isEnabled());
        assertEquals(userDTO.getNewsIds().size(), 2);
        assertEquals(userDTO.getCommentIds().size(), 2);
        assertEquals(userDTO.getSubscriptionIds().size(), 2);
        assertEquals(userDTO.getRatingIds().size(), 2);
    }

    @Test
    public void toEntity(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(EXISTING_ENTITY_EMAIL);
        userDTO.setPassword(EXISTING_ENTITY_PASSWORD);
        userDTO.setFirstName(EXISTING_ENTITY_FIRST_NAME);
        userDTO.setLastName(EXISTING_ENTITY_LAST_NAME);
        userDTO.setUserRole(EXISTING_ENTITY_USER_ROLE);
        userDTO.setEnabled(true);

        ArrayList<Long> ids = new ArrayList<>(Arrays.asList(1l,2l));

        userDTO.setNewsIds(ids);
        userDTO.setCommentIds(ids);
        userDTO.setSubscriptionIds(ids);
        userDTO.setRatingIds(ids);

        when(newsService.findAll(ids)).thenReturn(new ArrayList<>(Arrays.asList(new News(), new News())));
        when(commentService.findAll(ids)).thenReturn(new ArrayList<>(Arrays.asList(new Comment(), new Comment())));
        when(subscriptionService.findAll(ids)).thenReturn(new ArrayList<>(Arrays.asList(new Subscription(), new Subscription())));
        when(ratingService.findAll(ids)).thenReturn(new ArrayList<>(Arrays.asList(new Rating(), new Rating())));

        User user = userMapper.toEntity(userDTO);

        assertEquals(user.getEmail(), EXISTING_ENTITY_EMAIL);
        assertEquals(user.getPassword(), EXISTING_ENTITY_PASSWORD);
        assertEquals(user.getFirstName(), EXISTING_ENTITY_FIRST_NAME);
        assertEquals(user.getLastName(), EXISTING_ENTITY_LAST_NAME);
        assertEquals(user.getRole(), EXISTING_ENTITY_USER_ROLE);
        assertTrue(user.isEnabled());
        assertEquals(user.getNews().size(), 2);
        assertEquals(user.getComments().size(), 2);
        assertEquals(user.getSubscriptions().size(), 2);
        assertEquals(user.getRatings().size(), 2);
    }
}
