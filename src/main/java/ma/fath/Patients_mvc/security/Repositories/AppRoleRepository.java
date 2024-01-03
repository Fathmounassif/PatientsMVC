package ma.fath.Patients_mvc.security.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fath.Patients_mvc.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRolename(String roleName);
}
