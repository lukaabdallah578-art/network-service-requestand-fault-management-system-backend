package rw.ac.auca.network_service_requestand_fault_management_system.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.ac.auca.network_service_requestand_fault_management_system.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

  private final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping("/summary")
  public ResponseEntity<Map<String, Long>> summary() {
    return ResponseEntity.ok(dashboardService.summary());
  }
}