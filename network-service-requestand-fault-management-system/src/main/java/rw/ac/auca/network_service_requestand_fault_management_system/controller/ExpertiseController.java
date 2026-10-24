package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.Expertise;
import rw.ac.auca.network_service_requestand_fault_management_system.service.ExpertiseService;

@RestController
@RequestMapping("/api/expertises")
public class ExpertiseController {

  private final ExpertiseService expertiseService;

  public ExpertiseController(ExpertiseService expertiseService) {
    this.expertiseService = expertiseService;
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody Expertise expertise) {
    String message = expertiseService.save(expertise);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    List<Expertise> expertises = expertiseService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(expertises);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<Expertise> expertiseOptional = expertiseService.findById(id);

    if (expertiseOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(expertiseOptional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExpertiseService.NOT_FOUND);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Expertise expertise) {
    String message = expertiseService.update(id, expertise);

    if (message.equals(ExpertiseService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    String message = expertiseService.delete(id);

    if (message.equals(ExpertiseService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }
}
