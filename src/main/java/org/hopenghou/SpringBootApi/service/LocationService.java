package org.hopenghou.SpringBootApi.service;

import org.hopenghou.SpringBootApi.dto.CreateLocationDto;
import org.hopenghou.SpringBootApi.entity.Location;
import org.hopenghou.SpringBootApi.repository.LocationRepository;
import org.springframework.data.geo.Point;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
 
/**
 * Service class for managing Location entities.
 */
@Service
public class LocationService {
 
    private final LocationRepository locationRepository;
 
    // @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
 
    /**
     * Save a location.
     *
     * @param location the entity to save
     * @return the persisted entity
     */
    public Location saveLocation(CreateLocationDto request) {
        Location location = new Location();
        location.setName(request.getName());
        location.setIsFavourite(request.getIsFavourite());
        location.setCenter(new Point(request.getCenter().getLng(), request.getCenter().getLat()));
        location.setNorthEast(new Point(request.getNorthEast().getLng(), request.getNorthEast().getLat()));
        location.setSouthWest(new Point(request.getSouthWest().getLng(), request.getSouthWest().getLat()));
        return locationRepository.save(location);
    }
 
    /**
     * Get all the location.
     *
     * @return the list of entities
     */
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
 
    /**
     * Get one location by ID.
     *
     * @param id the ID of the entity
     * @return the entity
     */
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }
 
    /**
     * Update a location.
     *
     * @param id the ID of the entity
     * @param updatedLocation the updated entity
     * @return the updated entity
     */
    public Location updateLocation(Long id, Location updatedLocation) {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            location.setIsFavourite(updatedLocation.getIsFavourite());
            return locationRepository.save(location);
        } else {
            throw new RuntimeException("Location not found");
        }
    }
 
    /**
     * Delete the location by ID.
     *
     * @param id the ID of the entity
     */
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
