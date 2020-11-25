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
    private CulturalOfferingTypeRepository culturalOfferingSubtypeRepository;

    @Override
    public List<CulturalOfferingType> findAll() {
        return culturalOfferingSubtypeRepository.findAll();
    }

    public Page<CulturalOfferingType> findAll(Pageable pageable) {
        return culturalOfferingSubtypeRepository.findAll(pageable);
    }

    @Override
    public CulturalOfferingType findOne(Long id) {
        return culturalOfferingSubtypeRepository.findById(id).orElse(null);
    }

    @Override
    public CulturalOfferingType create(CulturalOfferingType entity) throws Exception {
        return culturalOfferingSubtypeRepository.save(entity);
    }

    @Override
    public CulturalOfferingType update(CulturalOfferingType entity, Long id) throws Exception {
        CulturalOfferingType existingCulturalOfferingType =  culturalOfferingSubtypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingType == null){
            throw new Exception("Cultural content category with given id doesn't exist");
        }
        return culturalOfferingSubtypeRepository.save(existingCulturalOfferingType);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) throws Exception {
        CulturalOfferingType existingCulturalOfferingType = culturalOfferingSubtypeRepository.findById(id).orElse(null);
        if(existingCulturalOfferingType == null){
            throw new Exception("Cultural content category with given id doesn't exist");
        }
        culturalOfferingSubtypeRepository.delete(existingCulturalOfferingType);
    }
}
