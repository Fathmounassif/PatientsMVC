package ma.fath.Patients_mvc.security.sevice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.fath.Patients_mvc.security.Repositories.AppRoleRepository;
import ma.fath.Patients_mvc.security.Repositories.AppUserRepository;
import ma.fath.Patients_mvc.security.entities.AppRole;
import ma.fath.Patients_mvc.security.entities.AppUser;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;





    @Override
    public  AppUser saveNewUser( Long id, String username,  String password, String rePassword) {
        if(!password.equals(rePassword)) throw new  RuntimeException("Password not match");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(id);//genere id automatiquement.
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setRepassword(hashedPWD);
       AppUser savedAppUser= appUserRepository.save(appUser);
        return savedAppUser;
    }



    @Override
    public AppRole saveNewRole(@Valid String roleName, @Valid String description) {
       AppRole appRole=appRoleRepository.findByRolename(roleName);
       if (appRole!=null) throw new RuntimeException("Role"+roleName+"already exist");
       appRole =new  AppRole();
       appRole.setRolename(roleName);
       appRole.setDescription(description);
       AppRole savedAppRole=appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException(" User not found");
    AppRole appRole=appRoleRepository.findByRolename(roleName);
        if (appRole==null) throw new RuntimeException(" Role not found");
    appUser.getAppRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException(" User not found");
        AppRole appRole=appRoleRepository.findByRolename(roleName);
        if (appRole==null) throw new RuntimeException(" Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username)
    {
        return appUserRepository.findByUsername(username);
    }
}
