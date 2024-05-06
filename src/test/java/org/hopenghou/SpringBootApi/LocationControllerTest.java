package org.hopenghou.SpringBootApi;

import java.util.UUID;

import org.hopenghou.SpringBootApi.dto.CreateLocationDto;
import org.hopenghou.SpringBootApi.dto.CreateLocationDto.GeoPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

  @Test
  public void testWithoutApiKey() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/locations"))
       .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  public void testGetAllLocationsEndpoint() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders
        .get("/api/v1/locations")
        .header("X-API-KEY", apiKey)
      )
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testSaveLocationEndpoint() throws Exception {
    // Generate a unique name for this test
    String uniqueName = "Location_" + UUID.randomUUID().toString().substring(0, 8);
    boolean isFavourite = true;
    CreateLocationDto input = new CreateLocationDto(
      uniqueName, 
      new GeoPoint(1, 2), 
      new GeoPoint(3, 4), 
      new GeoPoint(5, 6), 
      isFavourite
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
      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(uniqueName))
      .andExpect(MockMvcResultMatchers.jsonPath("$.isFavourite").value(isFavourite))
      // lat is y
      .andExpect(MockMvcResultMatchers.jsonPath("$.center.x").value(2))
      // lng is x
      .andExpect(MockMvcResultMatchers.jsonPath("$.center.y").value(1));
  }
}

