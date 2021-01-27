package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.RatingApi;

import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity findAll(Long culturalOfferingId, Pageable pageable) {
        Page<RatingDTO> ratingPage = ratingService.findAll(pageable, culturalOfferingId);

        return ok(ratingPage);
    }


    @Override
    public ResponseEntity findOne(Long id) {
        RatingDTO result = ratingService.findOne(id);

        return ok(result);
    }

    @Override
    @PreAuthorize("hasAuthority('REVIEW:write')")
    public ResponseEntity create(@Valid RatingDTO body, BindingResult bindingResult) throws Exception {
        DTOValidationHelper.validateDTO(bindingResult);

        String userEmail = credentialsHelper.getUserEmailFromToken();

        RatingDTO newRating = ratingService.create(body, userEmail);

        return created(newRating);
    }

    @Override
    @PreAuthorize("hasAuthority('REVIEW:write')")
    public ResponseEntity update(RatingDTO body, BindingResult bindingResult, Long id) throws Exception {
        DTOValidationHelper.validateDTO(bindingResult);

        RatingDTO updatedRating = ratingService.update(id, body);

        return ok(updatedRating);
    }

    @Override
    @PreAuthorize("hasAuthority('REVIEW:write')")
    public ResponseEntity delete(Long id) throws Exception {
        ratingService.delete(id);

        return ok();
    }
}
