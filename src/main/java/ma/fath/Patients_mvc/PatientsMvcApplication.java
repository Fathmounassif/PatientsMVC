package ma.fath.Patients_mvc;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.fath.Patients_mvc.entities.Patient;
import ma.fath.Patients_mvc.repositories.PatientRepository;
import ma.fath.Patients_mvc.security.sevice.SecurityService;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder(){

		return new BCryptPasswordEncoder();
	}
	//@Bean
	CommandLineRunner saveUsers(SecurityService securityService){
		return args -> {
			securityService.saveNewUser(null,"Fath","1234","1234");
			securityService.saveNewUser(null,"Sara","1234","1234");
			


			securityService.saveNewRole("USER","");
			securityService.saveNewRole("ADMIN","");
			




			securityService.addRoleToUser("Fath","USER");
			securityService.addRoleToUser("Fath","ADMIN");
			securityService.addRoleToUser("Sara","USER");



		};
	}
//@Bean //pour executer auto lors de démarage
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args ->{
			patientRepository.save(new Patient(null, "fath", new Date(), false, 12));
			patientRepository.save(new Patient(null, "Alii", new Date(), true, 120));
			patientRepository.save(new Patient(null, "Brahim", new Date(), true, 23));
			patientRepository.save(new Patient(null, "Omar", new Date(), false, 18));
			patientRepository.save(new Patient(null, "Rachid", new Date(), false, 32));
			patientRepository.findAll().forEach(p->{
				System.out.println(p.getNom());
				});
			};
					
		};
		
	}
