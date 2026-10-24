package rw.ac.auca.network_service_requestand_fault_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.FaultTicketRepository;

@Service
public class FaultTicketService {

  public static final String SAVED = "Fault ticket saved successfully";
  public static final String NOT_FOUND = "The fault ticket with that id does not exist";
  public static final String DELETED = "Fault ticket deleted successfully";
  public static final String UPDATED = "Fault ticket updated successfully";

  private final FaultTicketRepository faultTicketRepository;

  public FaultTicketService(FaultTicketRepository faultTicketRepository) {
    this.faultTicketRepository = faultTicketRepository;
  }

  public String save(FaultTicket faultTicket) {
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

}
