package ma.fath.Patients_mvc.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecuritController {
    @GetMapping("/403")
    public String notAuthorized(){
        return "403";

    }
}
