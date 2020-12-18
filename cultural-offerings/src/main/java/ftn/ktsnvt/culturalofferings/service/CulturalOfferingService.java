package ftn.ktsnvt.culturalofferings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundByNameException;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CulturalOfferingService implements ServiceInterface<CulturalOffering> {

    @Autowired
    private CulturalOfferingRepository culturalOfferingRepository;

    public List<CulturalOffering> findAll() {
        return culturalOfferingRepository.findAll();
    }

    @Override
    public Page<CulturalOffering> findAll(Pageable pageable) {
        return culturalOfferingRepository.findAll(pageable);
    }

    @Override
    public CulturalOffering findOne(Long id) {
        Optional<CulturalOffering> optional =  culturalOfferingRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOffering.class
            );
        }
        return optional.get();
    }
    
    public CulturalOffering findName(String name) {
        Optional<CulturalOffering> optional = culturalOfferingRepository.findByName(name);
        if(optional.isEmpty()){
            throw new EntityNotFoundByNameException(
                    name,
                    CulturalOffering.class
            );
        }
        return optional.get();
    }

    @Override
    public CulturalOffering create(CulturalOffering entity) {
        return culturalOfferingRepository.save(entity);
    }

    @Override
    public CulturalOffering update(CulturalOffering entity, Long id) {
        Optional<CulturalOffering> optional =  culturalOfferingRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOffering.class
            );
        }
        entity.setId(id);
        return culturalOfferingRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<CulturalOffering> optional =  culturalOfferingRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOffering.class
            );
        }
        culturalOfferingRepository.delete(optional.get());
    }

    public List<CulturalOffering> findByCulturalOfferingTypeId(Long id) {
        return culturalOfferingRepository.findAllByCulturalOfferingTypeId(id);
    }
}
