package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.SQLDeleteEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingSubtypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CulturalOfferingSubtypeService implements ServiceInterface<CulturalOfferingSubType> {

    @Autowired
    private CulturalOfferingSubtypeRepository culturalOfferingSubtypeRepository;

    @Autowired
    public CulturalOfferingService culturalOfferingService;

    public List<CulturalOfferingSubType> findAll() {
        return culturalOfferingSubtypeRepository.findAll();
    }

    @Override
    public Page<CulturalOfferingSubType> findAll(Pageable pageable) {
        return culturalOfferingSubtypeRepository.findAll(pageable);
    }

    @Override
    public CulturalOfferingSubType findOne(Long id) {
        Optional<CulturalOfferingSubType> optional = culturalOfferingSubtypeRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingSubType.class
            );
        return optional.get();
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
        Optional<CulturalOfferingSubType> optional =  culturalOfferingSubtypeRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingSubType.class
            );
        }
        entity.setId(id);
        return culturalOfferingSubtypeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<CulturalOfferingSubType> optional = culturalOfferingSubtypeRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingSubType.class
            );
        }
        List<CulturalOffering> culturalOfferings = culturalOfferingService.findByCulturalOfferingSubTypeId(id);
        if(!culturalOfferings.isEmpty())
            throw new SQLDeleteEntityException(
                    CulturalOfferingSubType.class,
                    CulturalOffering.class
            );
        culturalOfferingSubtypeRepository.deleteById(optional.get().getId());
    }

    public List<CulturalOfferingSubType> getAllByTypeId(Long typeId) {
        return this.culturalOfferingSubtypeRepository.findAllByCulturalOfferingTypeId(typeId);
    }
}
