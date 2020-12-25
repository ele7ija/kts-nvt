package ftn.ktsnvt.culturalofferings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Location;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
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
        Optional<Location> location =  locationRepository.findById(id);
        if(location.isEmpty()){
    		throw new EntityNotFoundException(id, Location.class);
        }
        return location.get();
    }

    @Override
    public Location create(Location entity) {
        return locationRepository.save(entity);
    }

    @Override
    public Location update(Location entity, Long id) {
    	Location existingLocation =  locationRepository.findById(id).orElse(null);
        if(existingLocation == null){
    		throw new EntityNotFoundException(id, Location.class);
        }
        entity.setId(id);
        return locationRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
    	Location existingLocation = locationRepository.findById(id).orElse(null);
        if(existingLocation == null){
    		throw new EntityNotFoundException(id, Location.class);
        }
        locationRepository.delete(existingLocation);
    }

}
