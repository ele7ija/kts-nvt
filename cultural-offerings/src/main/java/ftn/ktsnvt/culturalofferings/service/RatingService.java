package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import ftn.ktsnvt.culturalofferings.mapper.RatingMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Rating;
import ftn.ktsnvt.culturalofferings.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private RatingRepository ratingRepository;


    private UserService userService;
    private CulturalOfferingService culturalOfferingService;

    @Autowired
    public RatingService(RatingRepository ratingRepository, UserService userService, CulturalOfferingService culturalOfferingService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
        this.culturalOfferingService = culturalOfferingService;
    }

    public List<RatingDTO> findAll() {
        var ratings = ratingRepository.findAll();

        return RatingMapper.toDTOs(ratings);
    }

    public Page<RatingDTO> findAll(Pageable pageable) {
        var ratingsPage = ratingRepository.findAll(pageable);
        var ratingsList = ratingsPage.toList();
        var ratingDTOs = RatingMapper.toDTOs(ratingsList);

        return new PageImpl<>(ratingDTOs, ratingsPage.getPageable(), ratingsPage.getTotalElements());
    }

    public List<Rating> findAll(List<Long> ratingIds) {
        List<Rating> result = ratingIds.stream()
                .map(ratingId -> this.getEntityById(ratingId))
                .collect(Collectors.toList());

        return result;
    }


    private Rating getEntityById(Long id) {
        var ratingDTO = findOne(id);

        var culturalOffering = culturalOfferingService.findOne(
                ratingDTO.getCulturalOfferingId());
        var user = userService.findOne(ratingDTO.getUserId());

        return RatingMapper.toEntity(ratingDTO, culturalOffering, user);
    }

    public RatingDTO findOne(Long id) {
        Rating rating = ratingRepository.findById(id).orElse(null);

        if (rating == null) throw new EntityNotFoundException(id, Rating.class);

        return RatingMapper.toDTO(rating);
    }


    public RatingDTO create(RatingDTO dto, String userEmail) throws Exception {
        CulturalOffering culturalOffering = culturalOfferingService.findOne(dto.getCulturalOfferingId());
        User user = userService.findByEmail(userEmail);

        Rating entity = RatingMapper.toEntity(dto, culturalOffering, user);

        Rating newEntity = ratingRepository.save(entity);

        return RatingMapper.toDTO(newEntity);
    }

    public RatingDTO update(Long id, RatingDTO ratingUpdateDTO) throws Exception {
        // check if entity with given id exists
        findOne(id);

        var culturalOffering = culturalOfferingService.findOne(
                ratingUpdateDTO.getCulturalOfferingId());
        var user = userService.findOne(ratingUpdateDTO.getUserId());

        // update entity
        Rating newRating = RatingMapper.toEntity(ratingUpdateDTO, culturalOffering, user);
        newRating.setId(id);

        var updatedRating = ratingRepository.save(newRating);

        return RatingMapper.toDTO(updatedRating);
    }

    public void delete(Long id) throws Exception {
        Rating existingRating = ratingRepository.findById(id).orElse(null);

        if (existingRating == null)
            throw new EntityNotFoundException(id, Rating.class);

        ratingRepository.delete(existingRating);
    }


}
