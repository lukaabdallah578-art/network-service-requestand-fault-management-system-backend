package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.network_service_requestand_fault_management_system.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    List<Location> locations = locationService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(locations);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<Location> locationOptional = locationService.findById(id);

    if (locationOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(locationOptional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LocationService.NOT_FOUND);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Location location) {
    String message = locationService.update(id, location);

    if (message.equals(LocationService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    String message = locationService.delete(id);

    if (message.equals(LocationService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  // C2
  @GetMapping("/free")
  public ResponseEntity<List<Location>> findFreeLocations() {
    List<Location> freeLocations = locationService.searchFreeLocations();
    return ResponseEntity.ok(freeLocations);
  }
}
