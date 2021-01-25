package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.NewsApi;
import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.mapper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import ftn.ktsnvt.culturalofferings.service.NewsService;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController implements NewsApi {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsService newsService;

    @org.springframework.beans.factory.annotation.Autowired
    public NewsController() {

    }

    @Override
    @PreAuthorize("hasAuthority('NEWS:write')")
    public ResponseEntity<NewsDTO> createNews(@Valid NewsDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    News.class
            );
        }
        News news = newsService.create(newsMapper.toEntity(body));   
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('NEWS:write')")
    public ResponseEntity<Void> deleteNews(Long id) {
        newsService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('NEWS:read')")
    public ResponseEntity<NewsDTO> getNewsByID(Long id) {
        News news = newsService.findOne(id);
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('NEWS:read')")
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<News> news = newsService.findAll();
        List<NewsDTO> dtos = toNewsDTOList(news);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('NEWS:read')")
    public ResponseEntity<Page<NewsDTO>> getAllNewsByPage(int pageIndex, int pageSize) {
        Page<News> page;
        Page<NewsDTO> dtopage;
        page = newsService.findAll(PageRequest.of(pageIndex, pageSize));
        List<NewsDTO> dtos = toNewsDTOList(page.toList());
        dtopage = new PageImpl<>(dtos,PageRequest.of(pageIndex, pageSize),page.getTotalElements());
        return new ResponseEntity<>(dtopage, HttpStatus.OK);
    }

    private List<NewsDTO> toNewsDTOList(List<News> news) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        for (News newsOne : news) {
            newsDTOS.add(newsMapper.toDto(newsOne));
        }
        return newsDTOS;
    }

    @Override
    @PreAuthorize("hasAuthority('NEWS:write')")
    public ResponseEntity<NewsDTO> updateNews(@Valid NewsDTO body, BindingResult bindingResult, Long id) {
        if (bindingResult.hasErrors()) {
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    News.class
            );
        }
        News news = newsService.update(newsMapper.toEntity(body), id);
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('NEWS:write')")
    public ResponseEntity<Boolean> notify(Long id) {
        newsService.notifyNews(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Page<NewsDTO>> findAll(@RequestParam(value="id") Long id, Pageable pageable){
    	Page<NewsDTO> page = newsService.findAllNewsById(pageable, id);
    	return new ResponseEntity<>(page, HttpStatus.OK);
    }


}
