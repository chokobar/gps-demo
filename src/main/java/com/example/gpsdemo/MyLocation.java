package com.example.gpsdemo;

import java.net.http.*;
import java.net.URI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyLocation {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://ip-api.com/json/")) // 무료 위치 API
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(res.body());

        double lat = json.get("lat").asDouble();
        double lon = json.get("lon").asDouble();
        String city = json.get("city").asText();
        String country = json.get("country").asText();

        System.out.println("현재 대략적 위치:");
        System.out.println("위도: " + lat);
        System.out.println("경도: " + lon);
        System.out.println("도시: " + city + ", 국가: " + country);
    }
}
