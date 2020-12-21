package ftn.ktsnvt.culturalofferings.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import ftn.ktsnvt.culturalofferings.service.UserService;

@Component
public class CommentMapper {
    public static Comment toEntity(CommentDTO commentDTO, List<ImageModel> images, CulturalOffering culturalOffering, User user) {
        return new Comment(
                commentDTO.getUserId(),
                commentDTO.getText(),
                commentDTO.getDate(),
                images,
                culturalOffering,
                user
        );
    }

    public static CommentDTO toDTO(Comment entity) {
        return new CommentDTO(
                entity.getId(),
                entity.getText(),
                entity.getDate(),
                entity.getImages()
                        .stream()
                        .map(image -> image.getId())
                        .collect(Collectors.toList()),
                entity.getCulturalOffering().getId(),
                entity.getUser().getId()
        );
    }

    public static List<CommentDTO> toDTOs(List<Comment> comments) {
        return comments.stream()
                .map(comment -> CommentMapper.toDTO(comment))
                .collect(Collectors.toList());
    }

}
