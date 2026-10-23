package rw.ac.auca.network_service_requestand_fault_management_system.repository;

import java.util.UUID;

import rw.ac.auca.network_service_requestand_fault_management_system.domain.Location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, UUID> {

}
