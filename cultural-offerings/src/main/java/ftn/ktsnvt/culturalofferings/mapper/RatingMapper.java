package ftn.ktsnvt.culturalofferings.mapper;

import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.Rating;
import ftn.ktsnvt.culturalofferings.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class RatingMapper {
    public static RatingDTO toDTO(Rating newEntity) {
        return new RatingDTO(
                newEntity.getId(),
                newEntity.getValue(),
                newEntity.getCulturalOffering().getId(),
                newEntity.getUser().getId()
        );
    }

    public static Rating toEntity(RatingDTO dto, CulturalOffering culturalOffering, User user) {
        return new Rating(
                dto.getId(),
                dto.getValue(),
                culturalOffering,
                user
        );
    }

    public static List<RatingDTO> toDTOs(List<Rating> ratings) {
        return ratings.stream()
                .map(rating -> RatingMapper.toDTO(rating))
                .collect(Collectors.toList());
    }
}
