package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.FaultTicket;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.TicketStatus;
import rw.ac.auca.network_service_requestand_fault_management_system.service.FaultTicketService;

@RestController
@RequestMapping("/api/fault-tickets")
public class FaultTicketController {

  private final FaultTicketService faultTicketService;

  public FaultTicketController(FaultTicketService faultTicketService) {
    this.faultTicketService = faultTicketService;
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody FaultTicket faultTicket) {
    String message = faultTicketService.save(faultTicket);
    // D1
    if (message.equals(FaultTicketService.PAST_DATE_MESSAGE)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    } else if (message.equals(FaultTicketService.TECHNICIAN_BUSY_MESSAGE)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    } else {
      return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    List<FaultTicket> faultTickets = faultTicketService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(faultTickets);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<FaultTicket> faultTicketOptional = faultTicketService.findById(id);

    if (faultTicketOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(faultTicketOptional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FaultTicketService.NOT_FOUND);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody FaultTicket faultTicket) {
    String message = faultTicketService.update(id, faultTicket);

    if (message.equals(FaultTicketService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    String message = faultTicketService.delete(id);

    if (message.equals(FaultTicketService.NOT_FOUND)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
  }

  // B2
  @GetMapping("/by-status")
  public ResponseEntity<List<FaultTicket>> findTicketsByStatus(@RequestParam TicketStatus status) {
    List<FaultTicket> tickets = faultTicketService.findByStatus(status);
    return ResponseEntity.ok(tickets);
  }

  // B3
  @GetMapping("/between")
  public ResponseEntity<List<FaultTicket>> findTicketsBetween(@RequestParam String start, @RequestParam String end) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    List<FaultTicket> tickets = faultTicketService.findTicketsBetween(startDate, endDate);
    return ResponseEntity.ok(tickets);
  }

}