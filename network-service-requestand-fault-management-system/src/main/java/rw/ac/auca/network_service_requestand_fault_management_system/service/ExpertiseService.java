package rw.ac.auca.network_service_requestand_fault_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Expertise;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.ExpertiseRepository;

@Service
public class ExpertiseService {

  public static final String SAVED = "Expertise saved successfully";
  public static final String NOT_FOUND = "The expertise with that id does not exist";
  public static final String DELETED = "Expertise deleted successfully";
  public static final String UPDATED = "Expertise updated successfully";

  private final ExpertiseRepository expertiseRepository;

  public ExpertiseService(ExpertiseRepository expertiseRepository) {
    this.expertiseRepository = expertiseRepository;
  }

  public String save(Expertise expertise) {
    expertiseRepository.save(expertise);
    return SAVED;
  }

  public List<Expertise> findAll() {
    return expertiseRepository.findAll();
  }

  public Optional<Expertise> findById(UUID id) {
    return expertiseRepository.findById(id);
  }

  public String update(UUID id, Expertise expertise) {
    if (!expertiseRepository.existsById(id)) {
      return NOT_FOUND;
    }
    expertise.setId(id);
    expertiseRepository.save(expertise);
    return UPDATED;
  }

  public String delete(UUID id) {
    if (!expertiseRepository.existsById(id)) {
      return NOT_FOUND;
    }
    expertiseRepository.deleteById(id);
    return DELETED;
  }

}
