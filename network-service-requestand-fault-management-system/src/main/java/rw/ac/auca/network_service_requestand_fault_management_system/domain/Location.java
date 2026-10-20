package rw.ac.auca.network_service_requestand_fault_management_system.domain;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "location_number")
  private int locationNumber;

  @Column(name = "name")
  private String name;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getLocationNumber() {
    return locationNumber;
  }

  public void setLocationNumber(int locationNumber) {
    this.locationNumber = locationNumber;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
