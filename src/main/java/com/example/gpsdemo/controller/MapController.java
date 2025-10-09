package com.example.gpsdemo.controller;

import com.example.gpsdemo.service.IpLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MapController {

    private final IpLocationService ipLocationService;

    @GetMapping("/map")
    public String showMap(Model model) throws Exception {
        IpLocationService.Location loc = ipLocationService.getMyIpLocation();

        String label;

        if (loc.getCity() == null || loc.getCity().isBlank()) {
            if (loc.getCountry() == null) {
                label = "My Location";
            } else {
                label = loc.getCountry();
            }
        } else {
            label = loc.getCity() + ", " + loc.getCountry();
        }

        model.addAttribute("lat", loc.getLat());
        model.addAttribute("lon", loc.getLon());
        model.addAttribute("label", label);

        return "map";
    }

}
