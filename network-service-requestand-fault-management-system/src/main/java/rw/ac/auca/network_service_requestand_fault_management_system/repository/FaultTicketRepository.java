package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;

public interface FaultTicketRepository extends JpaRepository<FaultTicket, UUID> {

}
