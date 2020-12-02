package ftn.ktsnvt.culturalofferings.service;

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
    public CulturalOfferingType create(CulturalOfferingType entity) throws Exception {
    	if(culturalOfferingTypeRepository.findByTypeName(entity.getTypeName()) != null){
            throw new Exception("Cultural offering type name already exists");
        }
        return culturalOfferingTypeRepository.save(entity);
    }

    @Override
    public CulturalOfferingType update(CulturalOfferingType entity, Long id) throws Exception {
        CulturalOfferingType existingCulturalOfferingType =  culturalOfferingTypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingType == null){
            throw new Exception("Cultural offering type with given id doesn't exist");
        }
        return culturalOfferingTypeRepository.save(existingCulturalOfferingType);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) throws Exception {
        CulturalOfferingType existingCulturalOfferingType = culturalOfferingTypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingType == null){
            throw new Exception("Cultural offering type with given id doesn't exist");
        }
        culturalOfferingTypeRepository.delete(existingCulturalOfferingType);
    }
}
