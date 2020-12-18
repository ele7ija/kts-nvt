package ftn.ktsnvt.culturalofferings.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.ktsnvt.culturalofferings.dto.NewsDTO;

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

    @RequestMapping(value = "",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Page<NewsDTO>> getAllNews(Pageable pageable);

    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<NewsDTO> updateNews(@RequestBody NewsDTO body, BindingResult bindingResult, @PathVariable("id") Long id);

}
