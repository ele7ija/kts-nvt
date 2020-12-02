package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingTypeRepository;

import java.util.List;

@Service
public class CulturalOfferingTypeService implements ServiceInterface<CulturalOfferingType> {

    @Autowired
    public CulturalOfferingTypeRepository culturalOfferingTypeRepository;

    public List<CulturalOfferingType> findAll() {
        return culturalOfferingTypeRepository.findAll();
    }

    @Override
    public Page<CulturalOfferingType> findAll(Pageable pageable) {
        return culturalOfferingTypeRepository.findAll(pageable);
    }

    @Override
    public CulturalOfferingType findOne(Long id) {
        return culturalOfferingTypeRepository.findById(id).orElse(null);
    }
    
    public CulturalOfferingType findName(String name) {
    	return culturalOfferingTypeRepository.findByTypeName(name).orElse(null);
    }

    @Override
    public CulturalOfferingType create(CulturalOfferingType entity) {
        return culturalOfferingTypeRepository.save(entity);
    }

    @Override
    public CulturalOfferingType update(CulturalOfferingType entity, Long id) {
        CulturalOfferingType existingCulturalOfferingType =  culturalOfferingTypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingType == null){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingType.class
            );
        }
        return culturalOfferingTypeRepository.save(existingCulturalOfferingType);
    }

    @Override
    public void delete(Long id) {
        CulturalOfferingType existingCulturalOfferingType = culturalOfferingTypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingType == null){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingType.class
            );
        }
        culturalOfferingTypeRepository.delete(existingCulturalOfferingType);
    }
}
