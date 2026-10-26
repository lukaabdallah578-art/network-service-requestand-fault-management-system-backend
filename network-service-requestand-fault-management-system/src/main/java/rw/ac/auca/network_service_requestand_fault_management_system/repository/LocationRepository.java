package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.List;
import java.util.UUID;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, UUID> {

  // C2
  @Query("SELECT l FROM Location l WHERE NOT EXISTS (SELECT t FROM Technician t WHERE t.location = l) ORDER BY l.locationNumber ASC")

  List<Location> searchFreeLocation();

}
