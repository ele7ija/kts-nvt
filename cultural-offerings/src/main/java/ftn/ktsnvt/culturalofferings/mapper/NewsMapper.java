package ftn.ktsnvt.culturalofferings.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import ftn.ktsnvt.culturalofferings.service.UserService;

@Component
public class NewsMapper implements MapperInterface<News, NewsDTO> {

    @Autowired
    CulturalOfferingService culturalOfferingService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setCulturalOffering(culturalOfferingService.findOne(dto.getCulturalOffering()));
        news.setDate(dto.getDate());
        news.setText(dto.getText());
        news.setTitle(dto.getTitle());
        news.setUser(userService.findOne(dto.getUser()));
        List<ImageModel> images = new ArrayList<ImageModel>();
        for (Long image : dto.getImages()) {
            ImageModel im = imageService.findOne(image);
            images.add(im);
        }
        news.setImages(images);
        return news;
    }

    @Override
    public NewsDTO toDto(News entity) {
        NewsDTO dto = new NewsDTO();
        dto.setCulturalOffering(entity.getCulturalOffering().getId());
        dto.setDate(entity.getDate());
        List<Long> images = new ArrayList<Long>();
        for (ImageModel image : entity.getImages()) {
            images.add(image.getId());
        }
        dto.setImages(images);
        dto.setText(entity.getText());
        dto.setTitle(entity.getTitle());
        dto.setUser(entity.getUser().getId());
        return dto;
    }
    
}
