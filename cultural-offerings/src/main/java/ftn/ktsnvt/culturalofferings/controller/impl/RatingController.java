package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.RatingApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import ftn.ktsnvt.culturalofferings.model.Rating;
import ftn.ktsnvt.culturalofferings.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class RatingController implements RatingApi {

    private static final Logger log = LoggerFactory.getLogger(RatingController.class);

    private final ObjectMapper objectMapper;

    private RatingService ratingService;

    @Autowired
    public RatingController(ObjectMapper objectMapper, RatingService ratingService) {
        this.objectMapper = objectMapper;
        this.ratingService = ratingService;
    }

    @Override
    public ResponseEntity<List<RatingDTO>> findAll() {
        var ratings = ratingService.findAll();

        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<RatingDTO>> findAll(Pageable pageable) {
//        return ratingService.findAll(pageable);
        return null;
    }

    @Override
    public ResponseEntity<RatingDTO> findOne(Long id) {
        RatingDTO result = ratingService.findOne(id);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RatingDTO> create(RatingDTO body, BindingResult bindingResult) throws Exception {
        String userEmail = this.getUserEmailFromToken();

        RatingDTO newRating = ratingService.create(body, userEmail);

        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RatingDTO> update(RatingDTO body, BindingResult bindingResult, Long id) throws Exception {
        RatingDTO updatedRating = ratingService.update(id, body);

        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) throws Exception {
        ratingService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getUserEmailFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();

        return userEmail;
    }
}
