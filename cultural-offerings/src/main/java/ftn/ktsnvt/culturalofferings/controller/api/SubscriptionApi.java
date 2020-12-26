package ftn.ktsnvt.culturalofferings.controller.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;

@RequestMapping(value = "/subscriptions")
public interface SubscriptionApi {
    @RequestMapping(value = "",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO body, BindingResult bindingResult);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteSubscription(@PathVariable("id") Long id);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<SubscriptionDTO> getSubscriptionByID(@PathVariable("id") Long id);

    @RequestMapping(value = "/all",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions();

    @RequestMapping(value = "/all/by-page",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<SubscriptionDTO>> getAllSubscriptionsByPage(@RequestParam("page") int pageIndex,
                                                                    @RequestParam("size") int pageSize);

    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<SubscriptionDTO> updateSubscription(@RequestBody SubscriptionDTO body, BindingResult bindingResult, @PathVariable("id") Long id);
}
