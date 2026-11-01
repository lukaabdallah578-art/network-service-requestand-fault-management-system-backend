package rw.ac.auca.network_service_requestand_fault_management_system.service;

import org.springframework.stereotype.Service;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.Resolution;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.TicketStatus; // check this path
import rw.ac.auca.network_service_requestand_fault_management_system.repository.FaultTicketRepository;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.ResolutionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResolutionService {

  public static final String SAVED = "Resolution saved successfully";
  public static final String TICKET_NOT_FOUND = "The fault ticket with that id does not exist";
  public static final String NOT_RESOLVED = "Resolutions are only for RESOLVED fault tickets";
  public static final String NOT_FOUND = "The resolution with that id does not exist";
  public static final String DELETED = "Resolution deleted successfully";

  private final ResolutionRepository resolutionRepository;
  private final FaultTicketRepository faultTicketRepository;

  public ResolutionService(ResolutionRepository resolutionRepository,
      FaultTicketRepository faultTicketRepository) {
    this.resolutionRepository = resolutionRepository;
    this.faultTicketRepository = faultTicketRepository;
  }

  // E1
  public String saveResolution(UUID faultTicketId, Resolution resolution) {
    Optional<FaultTicket> found = faultTicketRepository.findById(faultTicketId);
    if (found.isEmpty()) {
      return TICKET_NOT_FOUND;
    }
    FaultTicket ticket = found.get();
    if (ticket.getStatus() != TicketStatus.RESOLVED) {
      return NOT_RESOLVED;
    }
    resolution.setResolvedDate(LocalDate.now());
    resolution.setFaultTicket(ticket);
    resolutionRepository.save(resolution);
    return SAVED;
  }

  // E2
  public List<Resolution> findByRequester(String staffNumber) {
    return resolutionRepository.searchByRequesterStaffNumber(staffNumber);
  }

  // E3
  public String delete(UUID id) {
    if (!resolutionRepository.existsById(id)) {
      return NOT_FOUND;
    }
    resolutionRepository.deleteById(id);
    return DELETED;
  }
}