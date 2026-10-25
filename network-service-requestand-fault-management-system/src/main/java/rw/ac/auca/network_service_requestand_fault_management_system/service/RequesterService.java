package rw.ac.auca.network_service_requestand_fault_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Requester;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.RequesterRepository;

@Service
public class RequesterService {

  public static final String SAVED = "Requester saved successfully";
  public static final String NOT_FOUND = "The requester with that id does not exist";
  public static final String DELETED = "Requester deleted successfully";
  public static final String UPDATED = "Requester updated successfully";

  private final RequesterRepository requesterRepository;

  public RequesterService(RequesterRepository requesterRepository) {
    this.requesterRepository = requesterRepository;
  }

  public String save(Requester requester) {
    requesterRepository.save(requester);
    return SAVED;
  }

  public List<Requester> findAll() {
    return requesterRepository.findAll();
  }

  public Optional<Requester> findById(UUID id) {
    return requesterRepository.findById(id);
  }

  public String update(UUID id, Requester requester) {
    if (!requesterRepository.existsById(id)) {
      return NOT_FOUND;
    }
    requester.setId(id);
    requesterRepository.save(requester);
    return UPDATED;
  }

  public String delete(UUID id) {
    if (!requesterRepository.existsById(id)) {
      return NOT_FOUND;
    }
    requesterRepository.deleteById(id);
    return DELETED;
  }

  // B1
  public List<Requester> findByLastName(String lastName) {
    return requesterRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
  }

}
