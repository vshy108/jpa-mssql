package org.hopenghou.SpringBootApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/external")
public class ExternalApiController {
    @GetMapping("/github-lookup")
    public ResponseEntity<Object> nestedCall(@RequestParam(defaultValue = "vshy108", required = true) String userName) {
        // URL of the third-party API
        String apiUrl = "https://api.github.com/users/" + userName;

        // Create a RestTemplate instance to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Make a GET request to the third-party API
            ResponseEntity<Object> response = restTemplate.getForEntity(apiUrl, Object.class);

            Object responseBody = response.getBody();

            return ResponseEntity.ok(responseBody);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during nested call");
        }
    }
}
