package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Rating;
import ftn.ktsnvt.culturalofferings.repository.RatingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService implements ServiceInterface<Rating> {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Page<Rating> findAll(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }

    public List<Rating> findAll(List<Long> ratingIds){
        return ratingIds.stream().map((Long ratingId) -> {
            Optional<Rating> optional = ratingRepository.findById(ratingId);
            if(optional.isEmpty()){
                throw new EntityNotFoundException(ratingId, Rating.class);
            }
            return optional.get();
        }).collect(Collectors.toList());
    }

    @Override
    public Rating findOne(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public Rating create(Rating entity) throws Exception {
        return ratingRepository.save(entity);
    }

    @Override
    public Rating update(Rating entity, Long id) throws Exception {
        Rating existingRating =  ratingRepository.findById(id).orElse(null);
        if(existingRating == null){
            throw new Exception("Rating with given id doesn't exist");
        }
        return ratingRepository.save(existingRating);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) throws Exception {
        Rating existingRating = ratingRepository.findById(id).orElse(null);
        if(existingRating == null){
            throw new Exception("Rating with given id doesn't exist");
        }
        ratingRepository.delete(existingRating);
    }
}
