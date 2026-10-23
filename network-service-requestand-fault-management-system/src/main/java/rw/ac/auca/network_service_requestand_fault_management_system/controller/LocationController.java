package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Location;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.network_service_requestand_fault_management_system.service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody Location location) {
    String message = locationService.save(location);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

}
