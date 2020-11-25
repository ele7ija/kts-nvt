package ftn.ktsnvt.culturalofferings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingRepository;

import java.util.List;

@Service
public class CulturalOfferingService implements ServiceInterface<CulturalOffering> {

    @Autowired
    private CulturalOfferingRepository culturalOfferingRepository;

    @Override
    public List<CulturalOffering> findAll() {
        return culturalOfferingRepository.findAll();
    }

    public Page<CulturalOffering> findAll(Pageable pageable) {
        return culturalOfferingRepository.findAll(pageable);
    }

    @Override
    public CulturalOffering findOne(Long id) {
        return culturalOfferingRepository.findById(id).orElse(null);
    }

    @Override
    public CulturalOffering create(CulturalOffering entity) throws Exception {
        return culturalOfferingRepository.save(entity);
    }

    @Override
    public CulturalOffering update(CulturalOffering entity, Long id) throws Exception {
        CulturalOffering existingCulturalOffering =  culturalOfferingRepository.findById(id).orElse(null);
        if(existingCulturalOffering == null){
            throw new Exception("Cultural content category with given id doesn't exist");
        }
        return culturalOfferingRepository.save(existingCulturalOffering);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) throws Exception {
        CulturalOffering existingCulturalOffering = culturalOfferingRepository.findById(id).orElse(null);
        if(existingCulturalOffering == null){
            throw new Exception("Cultural content category with given id doesn't exist");
        }
        culturalOfferingRepository.delete(existingCulturalOffering);
    }
}
