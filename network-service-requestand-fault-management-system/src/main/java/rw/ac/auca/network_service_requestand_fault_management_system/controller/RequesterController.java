package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Requester;
import rw.ac.auca.network_service_requestand_fault_management_system.service.RequesterService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/requesters")
public class RequesterController {

  private final RequesterService requesterService;

  public RequesterController(RequesterService requesterService) {
    this.requesterService = requesterService;
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody Requester requester) {
    String message = requesterService.save(requester);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    List<Requester> requesters = requesterService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(requesters);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<Requester> requesterOptional = requesterService.findById(id);

    if (requesterOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(requesterOptional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequesterService.NOT_FOUND);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Requester requester) {
    String message = requesterService.update(id, requester);

    if (message.equals(RequesterService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    String message = requesterService.delete(id);

    if (message.equals(RequesterService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  // B1
  @GetMapping("/bylast-name")

  public ResponseEntity<?> findByLastName(@RequestParam String lastName) {
    List<Requester> requesters = requesterService.findByLastName(lastName);
    return ResponseEntity.ok(requesters);
  }

}
