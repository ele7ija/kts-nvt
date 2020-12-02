package ftn.ktsnvt.culturalofferings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.repository.NewsRepository;

import java.util.List;

@Service
public class NewsService implements ServiceInterface<News> {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public News findOne(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public News create(News entity) throws Exception {
        return newsRepository.save(entity);
    }

    @Override
    public News update(News entity, Long id) throws Exception {
        News existingNews =  newsRepository.findById(id).orElse(null);
        if(existingNews == null){
            throw new Exception("News with given id doesn't exist");
        }
        return newsRepository.save(existingNews);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) throws Exception {
        News existingNews = newsRepository.findById(id).orElse(null);
        if(existingNews == null){
            throw new Exception("News with given id doesn't exist");
        }
        newsRepository.delete(existingNews);
    }
}
