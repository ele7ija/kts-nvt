package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingSubtypeRepository;

import java.util.List;

@Service
public class CulturalOfferingSubtypeService implements ServiceInterface<CulturalOfferingSubType> {

    @Autowired
    private CulturalOfferingSubtypeRepository culturalOfferingSubtypeRepository;

    public List<CulturalOfferingSubType> findAll() {
        return culturalOfferingSubtypeRepository.findAll();
    }

    @Override
    public Page<CulturalOfferingSubType> findAll(Pageable pageable) {
        return culturalOfferingSubtypeRepository.findAll(pageable);
    }

    @Override
    public CulturalOfferingSubType findOne(Long id) {
        return culturalOfferingSubtypeRepository.findById(id).orElse(null);
    }
    
    public CulturalOfferingSubType findName(String name) {
    	return culturalOfferingSubtypeRepository.findBySubTypeName(name).orElse(null);
    }

    @Override
    public CulturalOfferingSubType create(CulturalOfferingSubType entity) {
        return culturalOfferingSubtypeRepository.save(entity);
    }

    @Override
    public CulturalOfferingSubType update(CulturalOfferingSubType entity, Long id) {
        CulturalOfferingSubType existingCulturalOfferingSubType =  culturalOfferingSubtypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingSubType == null){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingSubType.class
            );
        }
        return culturalOfferingSubtypeRepository.save(existingCulturalOfferingSubType);
    }

    @Override
    public void delete(Long id) {
        CulturalOfferingSubType existingCulturalOfferingSubType = culturalOfferingSubtypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingSubType == null){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingSubType.class
            );
        }
        culturalOfferingSubtypeRepository.delete(existingCulturalOfferingSubType);
    }
}
