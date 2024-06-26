package org.hopenghou.SpringBootApi.service;

import org.hopenghou.SpringBootApi.dto.CreateLocationDto;
import org.hopenghou.SpringBootApi.dto.ListLocationDto;
import org.hopenghou.SpringBootApi.dto.UpdateLocationDto;
import org.hopenghou.SpringBootApi.entity.Location;
import org.hopenghou.SpringBootApi.repository.LocationRepository;
import org.hopenghou.SpringBootApi.utility.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

import java.util.Optional;
 
/**
 * Service class for managing Location entities.
 */
@Transactional
@Service
public class LocationService {
 
    private final LocationRepository locationRepository;
 
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
    public Page<Location> getAllLocations(ListLocationDto params) {
        PageRequest pageRequest;
        int pageIndexZero = params.getPageIndexZero();
        int pageSize = params.getPageSize();
        String columnName =  params.getColumnName();
        boolean isAscending = params.getIsAscending();

        boolean isValidColumnName = false;

        for (String sortableColumnName : Constant.GetAllLocationsSortableColumnNames) {
            // NOTE: never compare strings with == in Java
            if (columnName.equals(sortableColumnName)) {
                isValidColumnName = true;
            }
        }

        // Assume default columnName if the input columnName is invalid
        if (!isValidColumnName) {
            columnName = "id";
        }

        pageRequest = isAscending ? 
            PageRequest.of(pageIndexZero, pageSize, Sort.by(columnName)) : 
            PageRequest.of(pageIndexZero, pageSize, Sort.by(columnName).descending());

        return locationRepository.findAll(pageRequest);
    }
 
    // /**
    //  * Get one location by ID.
    //  *
    //  * @param id the ID of the entity
    //  * @return the entity
    //  */
    // public Optional<Location> getLocationById(Long id) {
    //     return locationRepository.findById(id);
    // }
 
    /**
     * Update a location.
     *
     * @param id the ID of the entity
     * @param updatedLocation the updated entity
     * @return the updated entity
     */
    public Location updateLocation(Long id, UpdateLocationDto request) {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            location.setIsFavourite(request.getIsFavourite());
            return locationRepository.save(location);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
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
