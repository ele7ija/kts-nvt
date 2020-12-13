package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.repository.SubscriptionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService implements ServiceInterface<Subscription> {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Page<Subscription> findAll(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    public List<Subscription> findAll(List<Long> subscriptionIds){
        return subscriptionIds.stream().map((Long subscriptionId) -> {
            Optional<Subscription> optional = subscriptionRepository.findById(subscriptionId);
            if(optional.isEmpty()){
                throw new EntityNotFoundException(subscriptionId, Subscription.class);
            }
            return optional.get();
        }).collect(Collectors.toList());
    }

    @Override
    public Subscription findOne(Long id) {
        Optional<Subscription> optional = subscriptionRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    Subscription.class
            );
        return optional.get();
    }

    @Override
    public Subscription create(Subscription entity) {
        return subscriptionRepository.save(entity);
    }

    @Override
    public Subscription update(Subscription entity, Long id) {
        Optional<Subscription> optional = subscriptionRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    Subscription.class
            );
        entity.setId(id);
        return subscriptionRepository.save(entity);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) {
        Optional<Subscription> optional = subscriptionRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    Subscription.class
            );
        subscriptionRepository.delete(optional.get());
    }
}
