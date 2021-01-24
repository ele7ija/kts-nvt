package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.SubscriptionApi;
import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.mapper.SubscriptionMapper;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import ftn.ktsnvt.culturalofferings.service.SubscriptionService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.print.attribute.standard.PageRanges;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class SubscriptionController implements SubscriptionApi {

    // private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    // private final ObjectMapper objectMapper;

    // private final HttpServletRequest request;

    @Autowired
    private SubscriptionMapper mapper;

    @Autowired
    private SubscriptionService subscriptionService;

    @org.springframework.beans.factory.annotation.Autowired
    public SubscriptionController() {
    }

    @Override
    @PreAuthorize("hasAuthority('SUBSCRIPTION:write')")
    public ResponseEntity<SubscriptionDTO> createSubscription(@Valid SubscriptionDTO body, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    Subscription.class
            );
        }
        Subscription subscription = subscriptionService.create(mapper.toEntity(body));
        return new ResponseEntity<>(mapper.toDto(subscription), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('SUBSCRIPTION:write')")
    public ResponseEntity<Void> deleteSubscription(Long id) {
        subscriptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('SUBSCRIPTION:read')")
    public ResponseEntity<SubscriptionDTO> getSubscriptionByID(Long id) {
        Subscription subscription = subscriptionService.findOne(id);
        return new ResponseEntity<>(mapper.toDto(subscription), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('SUBSCRIPTION:read')")
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        List<Subscription> ss = subscriptionService.findAll();
        List<SubscriptionDTO> dtos = ss.stream()
                                        .map(s -> mapper.toDto(s))
                                        .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('SUBSCRIPTION:read')")
    public ResponseEntity<Page<SubscriptionDTO>> getAllSubscriptionsByPage(int pageIndex, int pageSize) {
        Page<Subscription> page;
        Page<SubscriptionDTO> dtopage;
        page = subscriptionService.findAll(PageRequest.of(pageIndex, pageSize));
        List<SubscriptionDTO> dtos = toSubscriptionDTOList(page.getContent());
        dtopage = new PageImpl<>(dtos,PageRequest.of(pageIndex, pageSize),page.getTotalElements());
        return new ResponseEntity<>(dtopage, HttpStatus.OK);
    }

    private List<SubscriptionDTO> toSubscriptionDTOList(List<Subscription> subscriptions) {
        List<SubscriptionDTO> subscriptionDTOS = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            subscriptionDTOS.add(mapper.toDto(subscription));
        }
        return subscriptionDTOS;
    }

    @Override
    @PreAuthorize("hasAuthority('SUBSCRIPTION:write')")
    public ResponseEntity<SubscriptionDTO> updateSubscription(@Valid SubscriptionDTO dto, BindingResult bindingResult, Long id) {
        if(bindingResult.hasErrors()){
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    Subscription.class
            );
        }
        Subscription subscription = subscriptionService.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDto(subscription), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SubscriptionDTO>> getAllQuery(Long culturalOfferingId, Long userId) {
        System.out.println(culturalOfferingId + " " + userId);
        List<Subscription> subscriptions = subscriptionService.getAllQuery(culturalOfferingId, userId);
        return new ResponseEntity<>(toSubscriptionDTOList(subscriptions), HttpStatus.OK);
    }
}
