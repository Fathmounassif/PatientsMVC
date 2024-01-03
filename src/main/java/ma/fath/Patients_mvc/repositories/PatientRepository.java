package ma.fath.Patients_mvc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.fath.Patients_mvc.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
Page<Patient> findByNomContains(String kw,Pageable pageable);
}
