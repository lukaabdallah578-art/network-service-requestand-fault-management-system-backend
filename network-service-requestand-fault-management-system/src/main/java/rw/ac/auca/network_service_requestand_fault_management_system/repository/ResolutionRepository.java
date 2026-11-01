package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ResolutionRepository extends JpaRepository<Resolution, UUID> {

  // E2
  @Query("SELECT r FROM Resolution r WHERE r.faultTicket.requester.staffNumber = :staffNumber ORDER BY r.resolvedDate DESC")
  List<Resolution> searchByRequesterStaffNumber(@Param("staffNumber") String staffNumber);

  // E3
  boolean existsByFaultTicket(FaultTicket faultTicket);
}