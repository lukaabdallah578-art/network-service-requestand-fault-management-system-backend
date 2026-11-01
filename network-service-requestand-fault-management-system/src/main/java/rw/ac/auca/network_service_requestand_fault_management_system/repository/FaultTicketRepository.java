package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.TicketStatus;

public interface FaultTicketRepository extends JpaRepository<FaultTicket, UUID> {

  // B2
  List<FaultTicket> findByStatusOrderByReportDateAsc(TicketStatus status);

  // B3
  List<FaultTicket> findByReportDateBetweenOrderByReportDateAsc(LocalDate start, LocalDate end);

  // D1
  boolean existsByTechnicianIdAndReportDateAndStatusNot(UUID technicianId, LocalDate reportDate, TicketStatus status);

  // F1
  @Query("SELECT f.status, COUNT(f) FROM FaultTicket f GROUP BY f.status")
  List<Object[]> countTicketsByStatus();

  // F2
  @Modifying
  @Query("UPDATE FaultTicket f SET f.status = :cancelled WHERE f.technician.id = :technicianId AND f.reportDate = :date AND f.status <> :resolved")
  int cancelDay(@Param("technicianId") UUID technicianId, @Param("date") LocalDate date,
      @Param("cancelled") TicketStatus cancelled, @Param("resolved") TicketStatus resolved);

  // Bonus
  long countByReportDate(LocalDate reportDate);

  long countByStatus(TicketStatus status);

}
