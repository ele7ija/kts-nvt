package ftn.ktsnvt.culturalofferings.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.dto.NewsDTO;

import java.util.List;

@RequestMapping(value = "/news")
public interface NewsApi {
    @RequestMapping(value = "",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO body, BindingResult bindingResult);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteNews(@PathVariable("id") Long id);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<NewsDTO> getNewsByID(@PathVariable("id") Long id);

    @RequestMapping(value = "/all",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<NewsDTO>> getAllNews();

    @RequestMapping(value = "/all/by-page",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Page<NewsDTO>> getAllNewsByPage(@RequestParam("page") int pageIndex,
                                                   @RequestParam("size") int pageSize);

    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<NewsDTO> updateNews(@RequestBody NewsDTO body, BindingResult bindingResult, @PathVariable("id") Long id);

    @RequestMapping(value = "/notify/{id}",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
    ResponseEntity<Boolean> notify(@PathVariable("id") Long id);
    
    // get all newsletters from one cultural offering
    @RequestMapping(value = "all/by-page", 
    		method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<NewsDTO>> findAll(@RequestParam(value="id") Long id, Pageable pageable);

}
