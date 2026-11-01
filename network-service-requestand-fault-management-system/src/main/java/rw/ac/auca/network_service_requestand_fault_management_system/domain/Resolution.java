package rw.ac.auca.network_service_requestand_fault_management_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "resolution")
public class Resolution {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String fixType;

  private String description;

  private int minutesSpent;

  private LocalDate resolvedDate;

  @ManyToOne
  @JoinColumn(name = "fault_ticket_id")
  private FaultTicket faultTicket;

  public Resolution() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFixType() {
    return fixType;
  }

  public void setFixType(String fixType) {
    this.fixType = fixType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getMinutesSpent() {
    return minutesSpent;
  }

  public void setMinutesSpent(int minutesSpent) {
    this.minutesSpent = minutesSpent;
  }

  public LocalDate getResolvedDate() {
    return resolvedDate;
  }

  public void setResolvedDate(LocalDate resolvedDate) {
    this.resolvedDate = resolvedDate;
  }

  public FaultTicket getFaultTicket() {
    return faultTicket;
  }

  public void setFaultTicket(FaultTicket faultTicket) {
    this.faultTicket = faultTicket;
  }
}
