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
    
    @RequestMapping("/home")
    public String home(){
        return "home";
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
			edu.setUsername(newUsername);
			educationRepository.save(edu);
					count ++ ;
				return "redirect:/education";
				
			}
			edu.setUsername(newUsername);
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
		exp.setUsername(newUsername);
		experianceRepository.save(exp);
				count ++ ;
			return "redirect:/experience";
			
		}
		exp.setUsername(newUsername);
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
		skill.setUsername(newUsername);
		skillRepository.save(skill);
				count ++ ;
			return "redirect:/skill";
			
		}
		skillRepository.save(skill);
		return "redirect:/profile";
	
	}
			    
	@RequestMapping(value = "/profileDis", method = RequestMethod.GET)
    public String toSend( PersonModel person, Model model){

        Iterable<PersonModel> perVal = personRepository.findByUsername(newUsername);
        Iterable<EducationModel> eduVal = educationRepository.findByUsername(newUsername);
        Iterable<ExperianceModel> expVal = experianceRepository.findByUsername(newUsername);
        Iterable<SkillsModel> skillVal = skillRepository.findByUsername(newUsername);
        model.addAttribute("newValue1", perVal);
        model.addAttribute("newValue2", eduVal);
        model.addAttribute("newValue3", expVal);
        model.addAttribute("newValue4", skillVal);
        return "profile";
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String SearchByName(Model model){
        model.addAttribute(new PersonModel());
        return "search";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String SearchPostName(@ModelAttribute PersonModel person, Model model){
        String nameSearch = person.getFname();
        Iterable<PersonModel> newVal = personRepository.findByfname(nameSearch);
        model.addAttribute("newValue", newVal);
        return "display";
    }
   
    
}


