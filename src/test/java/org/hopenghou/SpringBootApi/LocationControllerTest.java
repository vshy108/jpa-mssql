package org.hopenghou.SpringBootApi;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hopenghou.SpringBootApi.dto.CreateLocationDto;
import org.hopenghou.SpringBootApi.dto.CreateLocationDto.GeoPoint;
import org.hopenghou.SpringBootApi.dto.UpdateLocationDto;
import org.hopenghou.SpringBootApi.entity.Location;
import org.hopenghou.SpringBootApi.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {
    
  @Autowired
  private MockMvc mockMvc;

  @Value("${api.key}")
  private String apiKey;

  @MockBean
  private LocationRepository locationRepository;

  private Location generateTestLocation(boolean isFavourite) {
    // Generate unique name
    String uniqueName = "Location_" + UUID.randomUUID().toString().substring(0, 8);
    double centerX = 2;
    double centerY = 1;
    double northEastX = 4;
    double northEastY = 3;
    double southWestX = 6;
    double southWestY = 5;

    Location location = new Location();
    location.setName(uniqueName);
    location.setIsFavourite(isFavourite);
    location.setCenter(new Point(centerX, centerY));
    location.setNorthEast(new Point(northEastX, northEastY));
    location.setSouthWest(new Point(southWestX, southWestY));
    
    return location;
  }

  @Test
  public void testWithoutApiKey() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/locations"))
       .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  public void testSaveLocationEndpoint() throws Exception {
    Location location = generateTestLocation(true);

    when(locationRepository.save(isA(Location.class))).thenReturn(location);

    CreateLocationDto input = new CreateLocationDto(
      location.getName(), 
      new GeoPoint(location.getCenter().getY(), location.getCenter().getX()), 
      new GeoPoint(location.getNorthEast().getY(), location.getNorthEast().getX()), 
      new GeoPoint(location.getSouthWest().getY(), location.getSouthWest().getX()), 
      location.getIsFavourite()
    );

    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc
      .perform(
        MockMvcRequestBuilders
        .post("/api/v1/location")
        .header("X-API-KEY", apiKey)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(input))
        .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(location.getName()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.isFavourite").value(location.getIsFavourite()))
      // lat is y
      .andExpect(MockMvcResultMatchers.jsonPath("$.center.x").value(location.getCenter().getX()))
      // lng is x
      .andExpect(MockMvcResultMatchers.jsonPath("$.center.y").value(location.getCenter().getY()));
  }

  @Test
  public void testGetAllLocationsEndpoint() throws Exception {
    List<Location> locationList = new ArrayList<>();
    locationList.add(generateTestLocation(true));
    Page<Location> page = new PageImpl<>(locationList, Pageable.unpaged(), locationList.size());
    when(locationRepository.findAll(isA(PageRequest.class))).thenReturn(page);
    mockMvc
      .perform(
        MockMvcRequestBuilders
        .get("/api/v1/locations")
        .header("X-API-KEY", apiKey)
      )
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfElements").value(locationList.size()))
      // Page<Location> puts the list at content
      .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].isFavourite").value(true));
  }

  @Test
  public void testUpdateLocationEndpoint() throws Exception {
    Location location = generateTestLocation(true);
    Location updatedLocation = generateTestLocation(false);
    UpdateLocationDto input = new UpdateLocationDto(false);

    ObjectMapper objectMapper = new ObjectMapper();

    when(locationRepository.findById(anyLong())).thenReturn(Optional.of(location));
    when(locationRepository.save(isA(Location.class))).thenReturn(updatedLocation);

    mockMvc.perform(MockMvcRequestBuilders
      .put("/api/v1/locations/" + 1)
      .header("X-API-KEY", apiKey)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(input))
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.isFavourite").value(updatedLocation.getIsFavourite()));
  }

  @Test
  public void testDeleteLocationEndpoint() throws Exception {
    doNothing().when(locationRepository).deleteById(anyLong());

    mockMvc.perform(MockMvcRequestBuilders
      .delete("/api/v1/locations/" + 1)
      .header("X-API-KEY", apiKey)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

