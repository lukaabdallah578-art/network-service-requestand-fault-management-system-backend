package rw.ac.auca.network_service_requestand_fault_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.TicketStatus;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.FaultTicketRepository;

@Service
public class FaultTicketService {

  public static final String SAVED = "Fault ticket saved successfully";
  public static final String NOT_FOUND = "The fault ticket with that id does not exist";
  public static final String DELETED = "Fault ticket deleted successfully";
  public static final String UPDATED = "Fault ticket updated successfully";

  // D1
  public static final String PAST_DATE_MESSAGE = "Fault ticket date cannot be in the past";
  public static final String TECHNICIAN_BUSY_MESSAGE = "Technician already has a ticket assigned on that date";

  private final FaultTicketRepository faultTicketRepository;

  public FaultTicketService(FaultTicketRepository faultTicketRepository) {
    this.faultTicketRepository = faultTicketRepository;
  }

  // D1
  public String save(FaultTicket faultTicket) {

    if (faultTicket.getReportDate().isBefore(LocalDate.now())) {
      return PAST_DATE_MESSAGE;
    }

    boolean hasConflict = false;
    if (faultTicket.getTechnician() != null &&
        faultTicket.getTechnician().getId() != null) {
      hasConflict = faultTicketRepository.existsByTechnicianIdAndReportDateAndStatusNot(
          faultTicket.getTechnician().getId(),
          faultTicket.getReportDate(),
          TicketStatus.CANCELLED);
    }
    if (hasConflict) {
      return TECHNICIAN_BUSY_MESSAGE;
    }
    faultTicket.setStatus(TicketStatus.OPEN);

    faultTicketRepository.save(faultTicket);
    return SAVED;
  }

  public List<FaultTicket> findAll() {
    return faultTicketRepository.findAll();
  }

  public Optional<FaultTicket> findById(UUID id) {
    return faultTicketRepository.findById(id);
  }

  public String update(UUID id, FaultTicket faultTicket) {
    if (!faultTicketRepository.existsById(id)) {
      return NOT_FOUND;
    }
    faultTicket.setId(id);
    faultTicketRepository.save(faultTicket);
    return UPDATED;
  }

  public String delete(UUID id) {
    if (!faultTicketRepository.existsById(id)) {
      return NOT_FOUND;
    }
    faultTicketRepository.deleteById(id);
    return DELETED;
  }

  // B2
  public List<FaultTicket> findByStatus(TicketStatus status) {
    return faultTicketRepository.findByStatusOrderByReportDateAsc(status);
  }

  // B3
  public List<FaultTicket> findTicketsBetween(LocalDate start, LocalDate end) {
    return faultTicketRepository.findByReportDateBetweenOrderByReportDateAsc(start, end);
  }

}
