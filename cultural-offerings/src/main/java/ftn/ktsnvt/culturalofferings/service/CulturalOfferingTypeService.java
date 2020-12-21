package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.SQLDeleteEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CulturalOfferingTypeService implements ServiceInterface<CulturalOfferingType> {

    @Autowired
    public CulturalOfferingTypeRepository culturalOfferingTypeRepository;

    @Autowired
    public CulturalOfferingService culturalOfferingService;

    public List<CulturalOfferingType> findAll() {
        return culturalOfferingTypeRepository.findAll();
    }

    @Override
    public Page<CulturalOfferingType> findAll(Pageable pageable) {
        return culturalOfferingTypeRepository.findAll(pageable);
    }

    @Override
    public CulturalOfferingType findOne(Long id) {
        Optional<CulturalOfferingType> optional = culturalOfferingTypeRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingType.class
            );
        return optional.get();
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
        Optional<CulturalOfferingType> optional =  culturalOfferingTypeRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingType.class
            );
        }
        entity.setId(id);
        return culturalOfferingTypeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<CulturalOfferingType> optional = culturalOfferingTypeRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingType.class
            );
        }

        List<CulturalOffering> culturalOfferings = culturalOfferingService.findByCulturalOfferingTypeId(id);
        if(!culturalOfferings.isEmpty())
            throw new SQLDeleteEntityException(
                    CulturalOfferingType.class,
                    CulturalOffering.class
            );
        culturalOfferingTypeRepository.delete(optional.get());
    }
}
