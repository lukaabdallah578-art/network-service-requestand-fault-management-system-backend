package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.Technician;
import rw.ac.auca.network_service_requestand_fault_management_system.service.TechnicianService;

@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

  private final TechnicianService technicianService;

  public TechnicianController(TechnicianService technicianService) {
    this.technicianService = technicianService;
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody Technician technician) {
    String message = technicianService.save(technician);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    List<Technician> technicians = technicianService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(technicians);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<Technician> technicianOptional = technicianService.findById(id);

    if (technicianOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(technicianOptional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TechnicianService.NOT_FOUND);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Technician technician) {
    String message = technicianService.update(id, technician);

    if (message.equals(TechnicianService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    String message = technicianService.delete(id);

    if (message.equals(TechnicianService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  // C1
  @GetMapping("/by-expertise")
  public ResponseEntity<List<Technician>> findTechniciansByExpertise(@RequestParam String name) {
    List<Technician> technicians = technicianService.findTechniciansByExpertise(name);
    return ResponseEntity.ok(technicians);
  }

  // D3
  @PutMapping("/{technicianId}/expertises/{expertiseId}")
  public ResponseEntity<?> addExpertiseToTechnician(@PathVariable UUID technicianId, @PathVariable UUID expertiseId) {
    String message = technicianService.addExpertiseToTechnician(technicianId, expertiseId);

    if (message.equals(TechnicianService.NOT_FOUND)) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    else if (message.equals(TechnicianService.EXPERTISE_NOT_FOUND)) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    else if (message.equals(TechnicianService.ALREADY_HAS_EXPERTISE)) {

      return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    else {

      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }
}
