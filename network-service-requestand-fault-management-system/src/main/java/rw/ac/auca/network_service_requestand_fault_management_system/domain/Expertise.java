package rw.ac.auca.network_service_requestand_fault_management_system.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "expertise")
public class Expertise {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "expertises")
  private List<Technician> technicians = new ArrayList<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Technician> getTechnicians() {
    return technicians;
  }

  public void setTechnicians(List<Technician> technicians) {
    this.technicians = technicians;
  }

}
