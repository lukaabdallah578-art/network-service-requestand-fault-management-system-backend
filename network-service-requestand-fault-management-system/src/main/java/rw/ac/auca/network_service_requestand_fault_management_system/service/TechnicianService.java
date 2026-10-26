package rw.ac.auca.network_service_requestand_fault_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Technician;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.TechnicianRepository;

@Service
public class TechnicianService {

  public static final String SAVED = "Technician saved successfully";
  public static final String NOT_FOUND = "The technician with that id does not exist";
  public static final String DELETED = "Technician deleted successfully";
  public static final String UPDATED = "Technician updated successfully";

  private final TechnicianRepository technicianRepository;

  public TechnicianService(TechnicianRepository technicianRepository) {
    this.technicianRepository = technicianRepository;
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

}
