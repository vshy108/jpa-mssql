package org.hopenghou.SpringBootApi.controller;

import org.hopenghou.SpringBootApi.dto.CreateLocationDto;
import org.hopenghou.SpringBootApi.dto.ListLocationDto;
import org.hopenghou.SpringBootApi.entity.Location;
import org.hopenghou.SpringBootApi.service.LocationService;
import org.springframework.data.domain.Page;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
 
@RestController
@RequestMapping("/api/v1")
public class LocationController {
 
    private final LocationService locationService;
 
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
 
    /**
     * Create a new location.
     *
     * @param location the location to create
     * @return the ResponseEntity with status 200 (OK) and with body of the new location
     */
    @PostMapping("/location")
    public ResponseEntity<Location> saveLocation(@RequestBody CreateLocationDto request) {
        Location newLocation = locationService.saveLocation(request);
        return ResponseEntity.ok(newLocation);
    }
 
    /**
     * Get all locations.
     *
     * @return the ResponseEntity with status 200 (OK) and with body of the page of locations
     */
    @GetMapping("/locations")
    public Page<Location> getAllLocations(
        @RequestParam(defaultValue = "0", required = true) Integer pageIndexZero,
        @RequestParam(defaultValue = "10", required = true) Integer pageSize,
        @RequestParam(defaultValue = "id", required = true) String columnName,
        @RequestParam(defaultValue = "true", required = true) Boolean isAscending) {
        ListLocationDto dto = new ListLocationDto(pageIndexZero, pageSize, columnName, isAscending);
        return locationService.getAllLocations(dto);
    }
 
    /**
     * Get a location by ID.
     *
     * @param id the ID of the location to get
     * @return the ResponseEntity with status 200 (OK) and with body of the location, or with status 404 (Not Found) if the location does not exist
     */
    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationService.getLocationById(id);
        return location.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    // TODO: Update only isFavourite
    /**
     * Update a location by ID.
     *
     * @param id the ID of the location to update
     * @param location the updated location
     * @return the ResponseEntity with status 200 (OK) and with body of the updated location, or with status 404 (Not Found) if the location does not exist
     */
    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Location updatedLocation = locationService.updateLocation(id, location);
        return ResponseEntity.ok(updatedLocation);
    }
 
    /**
     * Delete a location by ID.
     *
     * @param id the ID of the location to delete
     * @return the ResponseEntity with status 200 (OK) and with body of the message "Location deleted successfully"
     */
    @DeleteMapping("/locations/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok("Location deleted successfully");
    }
}
