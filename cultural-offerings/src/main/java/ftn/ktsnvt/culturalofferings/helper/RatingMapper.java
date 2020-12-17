package ftn.ktsnvt.culturalofferings.helper;

import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.Rating;
import ftn.ktsnvt.culturalofferings.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingMapper {

    public RatingDTO toDTO(Rating newEntity) {
        return new RatingDTO(
                newEntity.getId(),
                newEntity.getValue(),
                newEntity.getCulturalOffering().getId(),
                newEntity.getUser().getId()
        );
    }

    public Rating toEntity(RatingDTO dto, CulturalOffering culturalOffering, User user) {
        return new Rating(
                dto.getId(),
                dto.getValue(),
                culturalOffering,
                user
        );
    }

    public List<RatingDTO> toDTOs(List<Rating> ratings) {
        return ratings.stream()
                .map(rating -> this.toDTO(rating))
                .collect(Collectors.toList());
    }
}
