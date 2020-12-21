package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.NewsApi;
import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.mapper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import ftn.ktsnvt.culturalofferings.service.NewsService;

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
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController implements NewsApi {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsService newsService;

    @org.springframework.beans.factory.annotation.Autowired
    public NewsController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
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
    public ResponseEntity<Void> deleteNews(Long id) {
        newsService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewsDTO> getNewsByID(Long id) {
        News news = newsService.findOne(id);
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<NewsDTO>> getAllNews(Pageable pageable) {
        Page<News> page;
        Page<NewsDTO> dtopage;
        page = newsService.findAll(pageable);
        List<NewsDTO> dtos = toNewsDTOList(page.toList());
        dtopage = new PageImpl<>(dtos,page.getPageable(),page.getTotalElements());
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

    
}
