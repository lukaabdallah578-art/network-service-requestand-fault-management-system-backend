package rw.ac.auca.network_service_requestand_fault_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Expertise;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.Technician;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.ExpertiseRepository;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.TechnicianRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TechnicianService {

  public static final String SAVED = "Technician saved successfully";
  public static final String NOT_FOUND = "The technician with that id does not exist";
  public static final String DELETED = "Technician deleted successfully";
  public static final String UPDATED = "Technician updated successfully";

  // D3
  public static final String EXPERTISE_NOT_FOUND = "That expertise does not exist";
  public static final String ALREADY_HAS_EXPERTISE = "The technician already has that expertise";
  public static final String EXPERTISE_ADDED = "Expertise added to the technician";

  private final TechnicianRepository technicianRepository;
  // D3
  private final ExpertiseRepository expertiseRepository;

  public TechnicianService(TechnicianRepository technicianRepository, ExpertiseRepository expertiseRepository) {
    this.technicianRepository = technicianRepository;
    this.expertiseRepository = expertiseRepository;
  }

  public String save(Technician technician) {
    technicianRepository.save(technician);
    return SAVED;
  }

  public List<Technician> findAll() {
    return technicianRepository.findAll();
  }

  public Optional<Technician> findById(UUID id) {
    return technicianRepository.findById(id);
  }

  public String update(UUID id, Technician technician) {
    if (!technicianRepository.existsById(id)) {
      return NOT_FOUND;
    }
    technician.setId(id);
    technicianRepository.save(technician);
    return UPDATED;
  }

  public String delete(UUID id) {
    if (!technicianRepository.existsById(id)) {
      return NOT_FOUND;
    }
    technicianRepository.deleteById(id);
    return DELETED;
  }

  // C1
  public List<Technician> findTechniciansByExpertise(String name) {
    return technicianRepository.findByExpertiseName(name);
  }

  // D3
  @Transactional
  public String addExpertiseToTechnician(UUID technicianId, UUID expertiseId) {
    Optional<Technician> technicianOptional = technicianRepository.findById(technicianId);
    if (technicianOptional.isEmpty()) {
      return NOT_FOUND;
    }
    Optional<Expertise> expertiseOptional = expertiseRepository.findById(expertiseId);
    if (expertiseOptional.isEmpty()) {
      return EXPERTISE_NOT_FOUND;
    }

    Technician technician = technicianOptional.get();
    Expertise expertise = expertiseOptional.get();

    boolean alreadyHas = false;
    for (Expertise e : technician.getExpertises()) {
      if (e.getId().equals(expertiseId)) {
        alreadyHas = true;
      }
    }
    if (alreadyHas) {
      return ALREADY_HAS_EXPERTISE;
    }

    technician.getExpertises().add(expertise);
    technicianRepository.save(technician);
    return EXPERTISE_ADDED;
  }

}
