package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.SubscriptionApi;
import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.helper.SubscriptionMapper;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.service.SubscriptionService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriptionController implements SubscriptionApi {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private SubscriptionMapper mapper;

    @Autowired
    private SubscriptionService subscriptionService;

    @org.springframework.beans.factory.annotation.Autowired
    public SubscriptionController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<SubscriptionDTO> createSubscription(SubscriptionDTO body) {
        Subscription subscription;
        try {
            subscription = subscriptionService.create(mapper.toEntity(body));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(mapper.toDto(subscription), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSubscription(Long id) {
        try {
            subscriptionService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SubscriptionDTO> getSubscriptionByID(Long id) {
        Subscription subscription;
        try {
            subscription = subscriptionService.findOne(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mapper.toDto(subscription), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<SubscriptionDTO>> getAllSubscriptions(Pageable pageable) {
        Page<Subscription> page;
        Page<SubscriptionDTO> dtopage;
        try {
            page = subscriptionService.findAll(pageable);
            List<SubscriptionDTO> dtos = toSubscriptionDTOList(page.toList());
            dtopage = new PageImpl<>(dtos,page.getPageable(),page.getTotalElements());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dtopage, HttpStatus.CREATED);
    }

    private List<SubscriptionDTO> toSubscriptionDTOList(List<Subscription> subscriptions) {
        List<SubscriptionDTO> subscriptionDTOS = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            subscriptionDTOS.add(mapper.toDto(subscription));
        }
        return subscriptionDTOS;
    }

    @Override
    public ResponseEntity<SubscriptionDTO> updateSubscription(SubscriptionDTO dto, Long id) {
        Subscription subscription = mapper.toEntity(dto);
        try {
            subscription = subscriptionService.update(subscription, id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mapper.toDto(subscription), HttpStatus.CREATED);
    }


}
