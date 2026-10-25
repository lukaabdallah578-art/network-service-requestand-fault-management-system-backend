package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.TicketStatus;

public interface FaultTicketRepository extends JpaRepository<FaultTicket, UUID> {

  // B2
  List<FaultTicket> findByStatusOrderByReportDateAsc(TicketStatus status);

}
