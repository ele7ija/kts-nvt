package ftn.ktsnvt.culturalofferings.service;

import org.hibernate.resource.beans.container.internal.ContainerManagedLifecycleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.dto.SearchFilterDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundByNameException;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.UniqueEntityConstraintViolationException;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingRepository;

import java.util.ArrayList;
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
            throw new EntityNotFoundException(id, CulturalOffering.class);
        }
        return optional.get();
    }
    
    public CulturalOffering findName(String name) {
        Optional<CulturalOffering> optional = culturalOfferingRepository.findByName(name);
        if(optional.isEmpty()){
            throw new EntityNotFoundByNameException(name, CulturalOffering.class);
        }
        return optional.get();
    }

    @Override
    public CulturalOffering create(CulturalOffering entity) {
    	Optional<CulturalOffering> optional = culturalOfferingRepository.findByName(entity.getName());
        if(!optional.isEmpty()){
            throw new UniqueEntityConstraintViolationException(entity.getName(), CulturalOffering.class);
        } 	
        return culturalOfferingRepository.save(entity);
    }

    @Override
    public CulturalOffering update(CulturalOffering entity, Long id) {
        Optional<CulturalOffering> optional =  culturalOfferingRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(id, CulturalOffering.class);
        }
        entity.setId(id);
        return culturalOfferingRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<CulturalOffering> optional =  culturalOfferingRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(id, CulturalOffering.class);
        }
        culturalOfferingRepository.delete(optional.get());
    }

    public List<CulturalOffering> findByCulturalOfferingTypeId(Long id) {
        return culturalOfferingRepository.findAllByCulturalOfferingTypeId(id);
    }

    public List<CulturalOffering> findByCulturalOfferingSubTypeId(Long id) {
        return culturalOfferingRepository.findAllByCulturalOfferingSubTypeId(id);
    }

	public Page<CulturalOffering> searchFilter(Pageable pageable, SearchFilterDTO searchFilterDTO) {
        List<CulturalOffering> all = culturalOfferingRepository.findAll();
        List<CulturalOffering> searchFilterApplied = applySearchFilter(all, searchFilterDTO);
        Page<CulturalOffering> p = new PageImpl<CulturalOffering>(searchFilterApplied, pageable, all.size());
        return p;   
    }
    
    private List<CulturalOffering> applySearchFilter(List<CulturalOffering> all, SearchFilterDTO searchFilterDTO) {
        List<CulturalOffering> retval = new ArrayList<CulturalOffering>();
        for (CulturalOffering co : all) {
            // Filter type
            if (searchFilterDTO.getCulturalOfferingTypeIds().contains(co.getCulturalOfferingType().getId())) {
                if (searchFilterDTO.getCulturalOfferingSubtypeIds().contains(co.getCulturalOfferingSubType().getId())) {
                    // filter is okay
                }
                else {
                    // filter not okay
                    continue;
                }
            }
            else {
                // filter not okay
                continue;
            }

            // Name, description
            if (stringMatch(searchFilterDTO.getTerm(), co.getName()) || stringMatch(searchFilterDTO.getTerm(), co.getDescription())) {
                retval.add(co);
            }
        }
        return retval;
    }

    private boolean stringMatch(String term, String content) {
        String[] termWords = term.split(" ");
        String[] contentWords = content.split(" ");
        boolean flag;
        for (String termWord : termWords) {
            flag = false;
            for (String contentWord : contentWords) {
                System.out.println(termWord + " <-> " + contentWord);
                if (contentWord.toLowerCase().contains(termWord.toLowerCase())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}
