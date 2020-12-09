package ftn.ktsnvt.culturalofferings.helper;

import ftn.ktsnvt.culturalofferings.dto.UserDTO;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.service.CommentService;
import ftn.ktsnvt.culturalofferings.service.NewsService;
import ftn.ktsnvt.culturalofferings.service.RatingService;
import ftn.ktsnvt.culturalofferings.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements MapperInterface<User, UserDTO> {

    @Autowired
    private NewsService newsService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User toEntity(UserDTO dto) {
        return new User(
                dto.getEmail(),
                dto.getPassword(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getUserRole(),
                dto.isEnabled(),
                ratingService.findAll(dto.getRatingIds()).stream().collect(Collectors.toSet()),
                commentService.findAll(dto.getCommentIds()).stream().collect(Collectors.toSet()),
                subscriptionService.findAll(dto.getSubscriptionIds()).stream().collect(Collectors.toSet()),
                newsService.findAll(dto.getNewsIds()).stream().collect(Collectors.toSet())
        );
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.isEnabled(),
                entity.getNews().stream().map(x -> x.getId()).collect(Collectors.toList()),
                entity.getSubscriptions().stream().map(x -> x.getId()).collect(Collectors.toList()),
                entity.getComments().stream().map(x -> x.getId()).collect(Collectors.toList()),
                entity.getRatings().stream().map(x -> x.getId()).collect(Collectors.toList())
        );
    }
}
