package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.RatingApi;

import org.springframework.stereotype.Controller;

import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import ftn.ktsnvt.culturalofferings.helper.CredentialsHelper;
import ftn.ktsnvt.culturalofferings.helper.DTOValidationHelper;
import ftn.ktsnvt.culturalofferings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import static ftn.ktsnvt.culturalofferings.helper.ResponseHelper.created;
import static ftn.ktsnvt.culturalofferings.helper.ResponseHelper.ok;

@Controller
public class RatingController implements RatingApi {

    private RatingService ratingService;
    private CredentialsHelper credentialsHelper;

    @Autowired
    public RatingController(RatingService ratingService, CredentialsHelper credentialsHelper) {
        this.ratingService = ratingService;
        this.credentialsHelper = credentialsHelper;
    }

    @Override
    public ResponseEntity findAll() {
        var ratings = ratingService.findAll();

        return ok(ratings);
    }

    @Override
    public ResponseEntity findAll(Pageable pageable) {
        var ratings = ratingService.findAll(pageable);

        return ok(ratings);
    }

    @Override
    public ResponseEntity findOne(Long id) {
        RatingDTO result = ratingService.findOne(id);

        return ok(result);
    }

    @Override
    public ResponseEntity create(@Valid RatingDTO body, BindingResult bindingResult) throws Exception {
        DTOValidationHelper.validateDTO(bindingResult);

        String userEmail = credentialsHelper.getUserEmailFromToken();

        RatingDTO newRating = ratingService.create(body, userEmail);

        return created(newRating);
    }

    @Override
    public ResponseEntity update(RatingDTO body, BindingResult bindingResult, Long id) throws Exception {
        DTOValidationHelper.validateDTO(bindingResult);

        RatingDTO updatedRating = ratingService.update(id, body);

        return ok(updatedRating);
    }

    @Override
    public ResponseEntity delete(Long id) throws Exception {
        ratingService.delete(id);

        return ok();
    }
}
