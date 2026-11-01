package rw.ac.auca.network_service_requestand_fault_management_system.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.TicketStatus;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.FaultTicketRepository;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.LocationRepository;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.RequesterRepository;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.ResolutionRepository;
import rw.ac.auca.network_service_requestand_fault_management_system.repository.TechnicianRepository;

@Service
public class DashboardService {

  private final RequesterRepository requesterRepository;
  private final TechnicianRepository technicianRepository;
  private final LocationRepository locationRepository;
  private final FaultTicketRepository faultTicketRepository;
  private final ResolutionRepository resolutionRepository;

  public DashboardService(RequesterRepository requesterRepository,
      TechnicianRepository technicianRepository,
      LocationRepository locationRepository,
      FaultTicketRepository faultTicketRepository,
      ResolutionRepository resolutionRepository) {
    this.requesterRepository = requesterRepository;
    this.technicianRepository = technicianRepository;
    this.locationRepository = locationRepository;
    this.faultTicketRepository = faultTicketRepository;
    this.resolutionRepository = resolutionRepository;
  }

  public Map<String, Long> summary() {
    Map<String, Long> result = new LinkedHashMap<>();
    result.put("requesters", requesterRepository.count());
    result.put("technicians", technicianRepository.count());
    result.put("freeLocations", locationRepository.count());
    result.put("ticketsToday", faultTicketRepository.countByReportDate(LocalDate.now()));
    result.put("open", faultTicketRepository.countByStatus(TicketStatus.OPEN));
    result.put("resolutions", resolutionRepository.count());
    return result;
  }
}