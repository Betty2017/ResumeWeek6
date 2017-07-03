package resume;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import resume.EducationModel;
import resume.ExperianceModel;
import resume.PersonModel;
import resume.SkillsModel;


@Controller
public class MainController {
	int count=0;
	String newUsername;
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
    private EducationRepository educationRepository;
	@Autowired
    private ExperianceRepository experianceRepository;
	@Autowired
    private SkillRepository skillRepository;
	

    @RequestMapping("/")
    public String index(){
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    
    @RequestMapping(value ="/personForm", method= RequestMethod.GET)
	public String registerForm(Model model ,PersonModel per) {
    Authentication aut= SecurityContextHolder.getContext().getAuthentication();
    newUsername=aut.getName();
    model.addAttribute("per" , new PersonModel());
    System.out.println(newUsername);
   			return "personForm";
	}
    
            
	@RequestMapping(value ="/personForm", method= RequestMethod.POST)
	 public String saveRegister(@ModelAttribute PersonModel per,Principal p  ) {
	per.setUsername(newUsername);
	  personRepository.save(per);
	 	return "redirect:/education";
	}
	
		
	@RequestMapping(value ="/education", method= RequestMethod.GET)
	   public String eduForm(Model model) {
	   model.addAttribute("edu" , new EducationModel());
				return "educationForm";
	    }

	@RequestMapping(value ="/educationForm", method= RequestMethod.POST)
	   public String saveEdu(@RequestParam String action, @ModelAttribute EducationModel edu ) {
			
			if (action.equals("continue") && count < 10)
			{
			educationRepository.save(edu);
					count ++ ;
				return "redirect:/education";
				
			}
			educationRepository.save(edu);
			return "redirect:/experience";
		
		}
	@RequestMapping(value ="/experience", method= RequestMethod.GET)
	   public String expForm(Model model) {
		 model.addAttribute("exp" , new ExperianceModel());
				return "experianceForm";
	    }

	@RequestMapping(value ="/experianceForm", method= RequestMethod.POST)
	   public String saveExp(@RequestParam String action, @ModelAttribute ExperianceModel exp ) {
		
		if (action.equals("continue") && count < 10)
		{
		experianceRepository.save(exp);
				count ++ ;
			return "redirect:/experience";
			
		}
		experianceRepository.save(exp);
		return "redirect:/skill";
	
	}
		
	@RequestMapping(value ="/skill", method= RequestMethod.GET)
	   public String skillForm(Model model) {
		 model.addAttribute("skill" , new SkillsModel());
				return "skillsForm";
	    }

	@RequestMapping(value ="/skill", method= RequestMethod.POST)
	   public String saveExp(@RequestParam String action,@ModelAttribute SkillsModel skill ,  Model Pmodel) {
		if (action.equals("continue") && count < 20)
		{
		skillRepository.save(skill);
				count ++ ;
			return "redirect:/skill";
			
		}
		skillRepository.save(skill);
		return "redirect:/display";
	
	}
			    
/* @RequestMapping(value = "/display", method = RequestMethod.GET)
 	public String DisplayAll( Model model)
 {
     Iterable<PersonModel> person = personRepository.findAll();
     Iterable<EducationModel> education = educationRepository.findAll();
     Iterable<ExperianceModel> experiance = experianceRepository.findAll();
     Iterable<SkillsModel> skills = skillRepository.findAll();
     model.addAttribute("list", person);
     model.addAttribute("education", education);
     model.addAttribute("experiance", experiance);
     model.addAttribute("skills", skills);
     return "display";
 }
 
 */
   
    
}


