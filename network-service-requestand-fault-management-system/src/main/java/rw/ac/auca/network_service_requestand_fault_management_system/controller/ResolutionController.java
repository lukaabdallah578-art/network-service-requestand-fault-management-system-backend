package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.network_service_requestand_fault_management_system.domain.Resolution;
import rw.ac.auca.network_service_requestand_fault_management_system.service.ResolutionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resolutions")
public class ResolutionController {

    private final ResolutionService resolutionService;

    public ResolutionController(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    // E1
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID faultTicketId,
            @RequestBody Resolution resolution) {
        String message = resolutionService.saveResolution(faultTicketId, resolution);
        if (message.equals(ResolutionService.TICKET_NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals(ResolutionService.NOT_RESOLVED)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    // E2
    @GetMapping("/requester/{staffNumber}")
    public ResponseEntity<List<Resolution>> byRequester(@PathVariable String staffNumber) {
        return ResponseEntity.ok(resolutionService.findByRequester(staffNumber));
    }

    // E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = resolutionService.delete(id);
        if (message.equals(ResolutionService.NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
}