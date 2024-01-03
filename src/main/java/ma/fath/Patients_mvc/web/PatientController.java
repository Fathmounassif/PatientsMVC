package ma.fath.Patients_mvc.web;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import ma.fath.Patients_mvc.entities.Patient;
import ma.fath.Patients_mvc.repositories.PatientRepository;

@Controller
@AllArgsConstructor
public class PatientController {
	private PatientRepository patientRepository;
	 @GetMapping("/login")
	    public String login(){
	        return "login";
	 }
	@GetMapping(path = "/user/index")
	public String patients(Model model,
			@RequestParam(name = "page",defaultValue = "0")int page,
			@RequestParam(name = "size",defaultValue = "5")int size,
			@RequestParam(name = "keyword",defaultValue = "")String keyword){
		Page<Patient> pagepatients=patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
		model.addAttribute("listePatients",pagepatients);
		model.addAttribute("pages", new int[pagepatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", keyword);
		return "patients";}
	@GetMapping(path="/admin/delete")	
	public String delete( Long id,String keyword,int page) {
		patientRepository.deleteById(id);
		return "redirect:/user/index?page="+page+"&keyword="+keyword;
	}
	@GetMapping(path="/")	
	public String home() {
		return "home";
	}
	@GetMapping(path="/user/patients")
	@ResponseBody
	public List<Patient> listPatients() {
		return patientRepository.findAll();
	}
	@GetMapping(path = "/admin/formPatients")
	public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		return "formPatients";
	}
	@PostMapping(path = "/admin/save")
	public String save(Model model,@javax.validation.Valid Patient patient,BindingResult bindingResult,
		@RequestParam(defaultValue = "0")int page,
		@RequestParam(defaultValue = "")String keyword) {
		if(bindingResult.hasErrors()) return "formPatients";
		patientRepository.save(patient);
		return "redirect:/user/index?page="+page+"&keyword="+keyword;
	}
	@GetMapping(path = "/admin/editPatient")
	public String editPatient(Model model,Long id,String keyword,int page) {
		Patient patient=patientRepository.findById(id).orElse(null);
		if (patient==null) throw new RuntimeException("Patient introuvable");
		model.addAttribute("patient", patient);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		return "editPatient";
	}
	

}
