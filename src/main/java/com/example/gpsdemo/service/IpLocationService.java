package com.example.gpsdemo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class IpLocationService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public Location getMyIpLocation() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://ip-api.com/json/"))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        JsonNode json = mapper.readTree(res.body());

        // status == "success" 체크
        if (!"success".equals(json.path("status").asText())) {
            throw new IllegalStateException("IP 위치 조회 실패: " + json.path("message").asText());
        }

        Location loc = new Location();
        loc.setLat(json.get("lat").asDouble());
        loc.setLon(json.get("lon").asDouble());
        loc.setCity(json.get("city").asText(""));
        loc.setCountry(json.get("country").asText(""));
        return loc;
    }

    @Data
    public static class Location {
        private double lat;
        private double lon;
        private String city;
        private String country;
    }

}
