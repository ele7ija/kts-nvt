package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.NewsApi;
import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.helper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.News;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public ResponseEntity<NewsDTO> createNews(NewsDTO body) {
        News news;
        try {
            news = newsService.create(newsMapper.toEntity(body));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteNews(Long id) {
        try {
            newsService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewsDTO> getNewsByID(Long id) {
        News news;
        try {
            news = newsService.findOne(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<NewsDTO>> getAllNews(Pageable pageable) {
        Page<News> page;
        Page<NewsDTO> dtopage;
        try {
            page = newsService.findAll(pageable);
            List<NewsDTO> dtos = toNewsDTOList(page.toList());
            dtopage = new PageImpl<>(dtos,page.getPageable(),page.getTotalElements());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dtopage, HttpStatus.CREATED);
    }

    private List<NewsDTO> toNewsDTOList(List<News> news) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        for (News newsOne : news) {
            newsDTOS.add(newsMapper.toDto(newsOne));
        }
        return newsDTOS;
    }

    @Override
    public ResponseEntity<NewsDTO> updateNews(NewsDTO body, Long id) {
        News news = newsMapper.toEntity(body);
        try {
            news = newsService.update(news, id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newsMapper.toDto(news), HttpStatus.CREATED);
    }

    
}
