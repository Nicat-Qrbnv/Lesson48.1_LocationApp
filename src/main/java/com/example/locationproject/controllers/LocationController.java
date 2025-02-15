package com.example.locationproject.controllers;

import com.example.locationproject.dtos.RequestDto;
import com.example.locationproject.dtos.ResponseDto;
import com.example.locationproject.enums.MarkerType;
import com.example.locationproject.repositories.MarkerRepository;
import com.example.locationproject.services.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/markers")
@Slf4j
public class LocationController {
    private final LocationService locationService;
    private final MarkerRepository markerRepo;
    private final ModelMapper mapper;


//    @PostMapping("/new")
//    public ResponseDto createLocation(@RequestBody RequestDto requestDto) {
//        log.info("controller received");
//        return locationService.createMarker(requestDto);
//    }

    @PostMapping("/new")
    public ResponseDto createLocation(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("markerType") MarkerType markerType,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {

//        log.info("controller received");
//        log.info("Title: " + title);
//        log.info("Description: " + description);
//        log.info("MarkerType: " + markerType);
//        log.info("Latitude: " + latitude);
//        log.info("Longitude: " + longitude);
        RequestDto requestDto = new RequestDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setMarkerType(markerType);
        requestDto.setLatitude(latitude);
        requestDto.setLongitude(longitude);

        return locationService.createMarker(requestDto);
    }


//    @GetMapping("/{id}")
//    public ResponseDto getLocation(@PathVariable Long id) {
//        return locationService.getMarker(id);
//    }

    @GetMapping
    public ResponseDto getLocation(@RequestParam Long id) {
        log.info("Fetching location with id: {}", id);
        return locationService.getMarker(id);
    }

    @GetMapping("/all")
    public List<ResponseDto> getAllLocations() {
        return locationService.getAllMarkers();
    }


    @PutMapping("/{id}")
    public ResponseDto updateLocation(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return locationService.updateMarker(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteLocation(@PathVariable Long id) {
        return locationService.deleteMarker(id);
    }
}
