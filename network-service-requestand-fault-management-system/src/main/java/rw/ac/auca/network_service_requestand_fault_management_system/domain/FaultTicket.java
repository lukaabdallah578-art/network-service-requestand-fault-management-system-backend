package rw.ac.auca.network_service_requestand_fault_management_system.domain;

import java.util.UUID;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fault_ticket")
public class FaultTicket {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "reportDate")
  private LocalDate reportDate;

  @Enumerated(EnumType.STRING)
  private TicketStatus status;

  @Column(name = "description")
  private String description;

  @Column(name = "priority")
  private String priority;

  @ManyToOne
  @JoinColumn(name = "requester_id")
  private Requester requester;

  @ManyToOne
  @JoinColumn(name = "technician_id")
  private Technician technician;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDate getReportDate() {
    return reportDate;
  }

  public void setReportDate(LocalDate reportDate) {
    this.reportDate = reportDate;
  }

  public TicketStatus getStatus() {
    return status;
  }

  public void setStatus(TicketStatus status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public Requester getRequester() {
    return requester;
  }

  public void setRequester(Requester requester) {
    this.requester = requester;
  }

  public Technician getTechnician() {
    return technician;
  }

  public void setTechnician(Technician technician) {
    this.technician = technician;
  }

}
