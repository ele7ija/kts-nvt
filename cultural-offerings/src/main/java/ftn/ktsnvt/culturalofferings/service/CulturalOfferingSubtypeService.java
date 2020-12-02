package ftn.ktsnvt.culturalofferings.service;

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

    @Override
    public CulturalOfferingSubType create(CulturalOfferingSubType entity) throws Exception {
        return culturalOfferingSubtypeRepository.save(entity);
    }

    @Override
    public CulturalOfferingSubType update(CulturalOfferingSubType entity, Long id) throws Exception {
        CulturalOfferingSubType existingCulturalOfferingSubType =  culturalOfferingSubtypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingSubType == null){
            throw new Exception("Cultural offering subtype with given id doesn't exist");
        }
        return culturalOfferingSubtypeRepository.save(existingCulturalOfferingSubType);
    }

    @Override
    public void delete(Long id) throws Exception {
        CulturalOfferingSubType existingCulturalOfferingSubType = culturalOfferingSubtypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingSubType == null){
            throw new Exception("Cultural offering subtype with given id doesn't exist");
        }
        culturalOfferingSubtypeRepository.delete(existingCulturalOfferingSubType);
    }
}
