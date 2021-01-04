package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.SQLDeleteEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingTypeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public CulturalOfferingType create(CulturalOfferingType entity) {
        CulturalOfferingType culturalOfferingType = culturalOfferingTypeRepository.save(entity);
        entity.getCulturalOfferingSubTypes().forEach(x -> {
            x.setCulturalOfferingType(culturalOfferingType);
        });
        return culturalOfferingType;
    }

    @Override
    @Transactional
    public CulturalOfferingType update(CulturalOfferingType entity, Long id) {
        Optional<CulturalOfferingType> optional =  culturalOfferingTypeRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(
                    id,
                    CulturalOfferingType.class
            );
        }
        entity.setId(id);

        /*
            Check if there is a cultural offering with this sub type
            prevent removal of subtype in that case
         */
        List<CulturalOfferingSubType> culturalOfferingSubTypes = optional.get().getCulturalOfferingSubTypes()
                .stream().filter(x -> !entity.getCulturalOfferingSubTypes().contains(x)).collect(Collectors.toList());
        culturalOfferingSubTypes.forEach(x-> {
            List<CulturalOffering> culturalOfferings = culturalOfferingService.findByCulturalOfferingSubTypeId(x.getId());
            if(!culturalOfferings.isEmpty())
                throw new SQLDeleteEntityException(
                        CulturalOfferingSubType.class,
                        CulturalOffering.class
                );
        });
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

    public List<CulturalOfferingType> findAllByTypeName(String typeName) {
        return this.culturalOfferingTypeRepository.findAllByTypeName(typeName);
    }
}
