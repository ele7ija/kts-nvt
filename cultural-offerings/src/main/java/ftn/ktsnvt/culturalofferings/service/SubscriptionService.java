package ftn.ktsnvt.culturalofferings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.repository.SubscriptionRepository;

import java.util.List;

@Service
public class SubscriptionService implements ServiceInterface<Subscription> {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public Page<Subscription> findAll(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    @Override
    public Subscription findOne(Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    @Override
    public Subscription create(Subscription entity) throws Exception {
        return subscriptionRepository.save(entity);
    }

    @Override
    public Subscription update(Subscription entity, Long id) throws Exception {
        Subscription existingSubscription =  subscriptionRepository.findById(id).orElse(null);
        if(existingSubscription == null){
            throw new Exception("Cultural content category with given id doesn't exist");
        }
        return subscriptionRepository.save(existingSubscription);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) throws Exception {
        Subscription existingSubscription = subscriptionRepository.findById(id).orElse(null);
        if(existingSubscription == null){
            throw new Exception("Cultural content category with given id doesn't exist");
        }
        subscriptionRepository.delete(existingSubscription);
    }
}
