package ftn.ktsnvt.culturalofferings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Location;
import ftn.ktsnvt.culturalofferings.repository.LocationRepository;

@Service
public class LocationService implements ServiceInterface<Location>{
	
	@Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Page<Location> findAll(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }

    @Override
    public Location findOne(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Location create(Location entity) throws Exception {
        return locationRepository.save(entity);
    }

    @Override
    public Location update(Location entity, Long id) throws Exception {
    	Location existingLocation =  locationRepository.findById(id).orElse(null);
        if(existingLocation == null){
            throw new Exception("Location with given id doesn't exist");
        }
        entity.setId(id);
        return locationRepository.save(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
    	Location existingLocation = locationRepository.findById(id).orElse(null);
        if(existingLocation == null){
            throw new Exception("Location with given id doesn't exist");
        }
        locationRepository.delete(existingLocation);
    }

}
