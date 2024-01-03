package ma.fath.Patients_mvc.security.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fath.Patients_mvc.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
