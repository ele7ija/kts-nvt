package ftn.ktsnvt.culturalofferings.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import ftn.ktsnvt.culturalofferings.service.UserService;

@Component
public class CommentMapper implements MapperInterface<Comment, CommentDTO> {
    @Autowired
    CulturalOfferingService culturalOfferingService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment c = new Comment();
        c.setDate(dto.getDate());
        c.setText(dto.getText());
        c.setCulturalOffering(culturalOfferingService.findOne(dto.getCulturalOffering()));
        c.setUser(userService.findOne(dto.getUser()));
        List<ImageModel> images = new ArrayList<ImageModel>();
        for (Long image : dto.getImages()) {
            ImageModel im = imageService.findOne(image);
            images.add(im);
        }
        c.setImages(images);
        return c;
    }

    @Override
    public CommentDTO toDto(Comment entity) {
        CommentDTO dto = new CommentDTO();
        dto.setCulturalOffering(entity.getCulturalOffering().getId());
        dto.setDate(entity.getDate());
        dto.setText(entity.getText());
        dto.setUser(entity.getUser().getId());
        List<Long> images = new ArrayList<Long>();
        for (ImageModel image : entity.getImages()) {
            images.add(image.getId());
        }
        dto.setImages(images);
        return dto;
    }
    
}
