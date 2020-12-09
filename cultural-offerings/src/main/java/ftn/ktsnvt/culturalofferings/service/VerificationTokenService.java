package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.model.VerificationToken;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.ModelConstraintViolationException;
import ftn.ktsnvt.culturalofferings.model.exceptions.VerificationTokenNotFoundException;
import ftn.ktsnvt.culturalofferings.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.UUID;

@Service
@ConfigurationProperties(prefix = "application.verification-token")
public class VerificationTokenService implements ServiceInterface<VerificationToken>{

    private int expiryTimeInMinutes;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public Page<VerificationToken> findAll(Pageable pageable) {
        return verificationTokenRepository.findAll(pageable);
    }

    @Override
    public VerificationToken findOne(Long id) {
        Optional<VerificationToken> optional = verificationTokenRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    VerificationToken.class
            );
        return optional.get();
    }

    public VerificationToken findOne(String token) {
        Optional<VerificationToken> optional = verificationTokenRepository.findByToken(token);
        if(optional.isEmpty())
            throw new VerificationTokenNotFoundException(token);
        return optional.get();
    }

    @Override
    public VerificationToken create(VerificationToken entity) {
        entity.setToken(UUID.randomUUID().toString());
        entity.setExpiryDate(entity.calculateExpiryDate(this.expiryTimeInMinutes));
        try {
            return this.verificationTokenRepository.save(entity);
        }
        catch(DataIntegrityViolationException exc){
            //rethrow the exception so you can catch it in your advice
            //you must do this because your advice catches only exceptions from controllers and services, not repositories
            throw new ModelConstraintViolationException("Email " + entity.getUser().getEmail() + " already exists", User.class);
        }

    }

    @Override
    public VerificationToken update(VerificationToken entity, Long id) throws Exception {
        Optional<VerificationToken> optional =  verificationTokenRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    VerificationToken.class
            );
        }
        entity.setId(id);
        return verificationTokenRepository.save(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<VerificationToken> optional = verificationTokenRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    VerificationToken.class
            );
        }
        verificationTokenRepository.delete(optional.get());
    }
}
