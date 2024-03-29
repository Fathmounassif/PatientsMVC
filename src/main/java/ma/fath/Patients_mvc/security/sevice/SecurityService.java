package ma.fath.Patients_mvc.security.sevice;

import ma.fath.Patients_mvc.security.entities.AppRole;
import ma.fath.Patients_mvc.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(Long id,String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
}
