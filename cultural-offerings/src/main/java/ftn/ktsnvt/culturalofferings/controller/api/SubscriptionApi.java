package ftn.ktsnvt.culturalofferings.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.model.Subscription;

@RequestMapping(value = "/subscriptions")
public interface SubscriptionApi {
    @RequestMapping(value = "",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO body);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteSubscription(@PathVariable("id") Long id);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<SubscriptionDTO> getSubscriptionByID(@PathVariable("id") Long id);

    @RequestMapping(value = "",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Page<SubscriptionDTO>> getAllSubscriptions(Pageable pageable);

    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<SubscriptionDTO> updateSubscription(@RequestBody SubscriptionDTO body, @PathVariable("id") Long id);
}
