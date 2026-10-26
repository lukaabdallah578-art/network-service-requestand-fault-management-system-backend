package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Requester;

public interface RequesterRepository extends JpaRepository<Requester, UUID> {

  // B1
  List<Requester> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

  // C4
  @Query("SELECT DISTINCT f.requester FROM FaultTicket f WHERE f.technician.id = :technicianId")
  List<Requester> searchRequestersOfTechnician(@Param("technicianId") UUID technicianId);

}
