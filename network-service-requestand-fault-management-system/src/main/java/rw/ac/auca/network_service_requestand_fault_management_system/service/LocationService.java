package rw.ac.auca.network_service_requestand_fault_management_system.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Location;

import rw.ac.auca.network_service_requestand_fault_management_system.repository.LocationRepository;

@Service
public class LocationService {

  public static final String SAVED = "Location saved successfully";
  public static final String NOT_FOUND = "The location with that id does not exist";
  public static final String DELETED = "Location deleted successfully";
  public static final String UPDATED = "Location updated successfully";

  private final LocationRepository locationRepository;

  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public String save(Location location) {
    locationRepository.save(location);
    return SAVED;
  }

  public List<Location> findAll() {
    return locationRepository.findAll();
  }

  public Optional<Location> findById(UUID id) {
    return locationRepository.findById(id);
  }

  public String update(UUID id, Location location) {
    if (!locationRepository.existsById(id)) {
      return NOT_FOUND;
    }
    location.setId(id);
    locationRepository.save(location);
    return UPDATED;
  }

  public String delete(UUID id) {
    if (!locationRepository.existsById(id)) {
      return NOT_FOUND;
    }
    locationRepository.deleteById(id);
    return DELETED;
  }

  // C2
  public List<Location> searchFreeLocations() {
    return locationRepository.searchFreeLocation();
  }

}
