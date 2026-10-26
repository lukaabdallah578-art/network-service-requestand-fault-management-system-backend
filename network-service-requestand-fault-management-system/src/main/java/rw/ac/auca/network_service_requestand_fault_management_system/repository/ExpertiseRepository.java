package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Expertise;

public interface ExpertiseRepository extends JpaRepository<Expertise, UUID> {
  // C3
  @Query("SELECT e FROM Expertise e WHERE e.technicians IS EMPTY")
  List<Expertise> searchUnusedExpertises();

}