package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.dto.NewsDTO;
import ftn.ktsnvt.culturalofferings.mapper.NewsMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.repository.NewsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService implements ServiceInterface<News> {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private EmailServiceImpl emailService;
    
    @Autowired
    private CulturalOfferingService culturalOfferingService;
    
    @Autowired
    private NewsMapper newsMapper;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public List<News> findAll(List<Long> newsIds){
        return newsIds.stream().map((Long newsId) -> {
            Optional<News> optional = newsRepository.findById(newsId);
            if(optional.isEmpty()){
                throw new EntityNotFoundException(newsId, News.class);
            }
            return optional.get();
        }).collect(Collectors.toList());
    }

    @Override
    public News findOne(Long id) {
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    News.class
            );
        return optional.get();
    }

    @Override
    public News create(News entity) {
        return newsRepository.save(entity);
    }

    @Override
    public News update(News entity, Long id) {
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    News.class
            );
        entity.setId(id);
        return newsRepository.save(entity);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) {
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    News.class
            );
        newsRepository.delete(optional.get());
    }

	public void notifyNews(Long id) {
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isEmpty()) {
            throw new EntityNotFoundException(id, News.class);
        }
        
        News newsletter = optional.get();
        emailService.sendNewsLetter(newsletter);        
	}
	
	public Page<NewsDTO> findAllNewsById(Pageable pageable, Long id) {
		CulturalOffering offering = culturalOfferingService.findOne(id);		
		
		List<NewsDTO> list = offering.getNews()
				.stream()
				.map(x -> newsMapper.toDto(x))
				.collect(Collectors.toList());

		Page<NewsDTO> page = new PageImpl<NewsDTO>(list, pageable, list.size());

		return page;
	}
}
