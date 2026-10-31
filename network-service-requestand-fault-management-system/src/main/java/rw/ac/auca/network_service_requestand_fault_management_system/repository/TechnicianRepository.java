package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.List;
import java.util.UUID;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {
  // C1
  @Query("SELECT t FROM Technician t JOIN t.expertises e WHERE LOWER(e.name) = LOWER(:name)")
  List<Technician> findByExpertiseName(@Param("name") String name);

  // D4
  boolean existsByLocation(Location location);
}
