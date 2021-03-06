package resume;

import java.security.Principal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import resume.EducationModel;
import resume.ExperianceModel;
import resume.PersonModel;
import resume.SkillsModel;


@Controller
public class MainController {
	@Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;
    
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
	@Autowired
    private JobRepository jobRepository;
	

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
   
    
    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logOut(HttpServletRequest request ,HttpServletResponse response){
    Authentication aut= SecurityContextHolder.getContext().getAuthentication();
    if(aut != null){
    	new SecurityContextLogoutHandler().logout(request, response, aut);
    }
        return "redirect:/login";
    }
    
    
    @RequestMapping(value ="/addPerson", method= RequestMethod.GET)
	public String registerForm(Model model ,PersonModel per, User user) {
    Authentication aut= SecurityContextHolder.getContext().getAuthentication();
    newUsername=aut.getName();
    model.addAttribute("per" , new PersonModel());
       		return "personForm";
    		
   	}
               
	@RequestMapping(value ="/addPerson", method= RequestMethod.POST)
	 public String saveRegister(@ModelAttribute PersonModel per,Principal p  ) {
		per.setUsername(newUsername);
	  personRepository.save(per);
	 	return "redirect:/addEducation";
	}
	
		
	@RequestMapping(value ="/addEducation", method= RequestMethod.GET)
	   public String eduForm(Model model) {
	   model.addAttribute("edu" , new EducationModel());
				return "educationForm";
	    }

	@RequestMapping(value ="/addEducationForm", method= RequestMethod.POST)
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
			return "redirect:/addExperience";
		
		}
	@RequestMapping(value ="/addExperience", method= RequestMethod.GET)
	   public String expForm(Model model) {
		 model.addAttribute("exp" , new ExperianceModel());
				return "experianceForm";
	    }

	@RequestMapping(value ="/addExperianceForm", method= RequestMethod.POST)
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
			    
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String toSend( PersonModel person, Model model, Principal p){
		newUsername= p.getName();
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
	
	/*@RequestMapping(value ="/update", method= RequestMethod.GET)
	   public String updateForm(Model model, PersonModel person , Principal p) {
		 Iterable<PersonModel> perVal = personRepository.findByUsername(p.getName());
		 for (PersonModel s:perVal)
		 {
			 person.setEmail(s.getEmail());
			 person.setFname(s.getFname());
			 person.setLname(s.getLname());
		 }
		 model.addAttribute("username", p.getName());
		 model.addAttribute("updatePer" , person);
				return "personFormUpdate";
	    }

		
	*/
	
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String SearchByName(Model model){
        model.addAttribute(new PersonModel());
        model.addAttribute(new ExperianceModel());
        return "search";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String SearchPostName(@ModelAttribute PersonModel person, Model model){
        String nameSearch = person.getFname();
        Iterable<PersonModel> perVal = personRepository.findByfname(nameSearch);
        model.addAttribute("newValue1", perVal);
       return "listNames";
    }
    
  
    
    @RequestMapping(value ="/jobpost", method= RequestMethod.GET)
	public String aaaForm(Model model ,JobModel job) {
    model.addAttribute("job" , new JobModel());
       		return "jobposting";
    		
   	}
               
	@RequestMapping(value ="/postjob", method= RequestMethod.POST)
	 public String jobPost(@ModelAttribute JobModel job,Principal p  ) {
		job.setUsername(p.getName());
		System.out.println(p.getName());
	   jobRepository.save(job);
	 	return "redirect:/joblist";
	}
	
	 @RequestMapping(value = "/joblist", method = RequestMethod.GET)
	    public String List(Model model, JobModel job,Principal p){
			newUsername= p.getName();
	        Iterable<JobModel> perVal = jobRepository.findByUsername(newUsername);
	         model.addAttribute("newValue", perVal);
	         return "joblist";
    	             
	}
  
    
    @RequestMapping(value = "/searchCompany", method = RequestMethod.POST)
    public String SearchPostName(@ModelAttribute ExperianceModel exp, Model model){
        String companySearch = exp.getCompany();
        Iterable<ExperianceModel> perVal = experianceRepository.findByCompany(companySearch);
        model.addAttribute("newValue1", perVal);
       return "listCompanies";
    }
   
       
    @RequestMapping(value = "/view/{username}", method = RequestMethod.GET)
    public String View(@PathVariable(value ="username") String username, ExperianceModel exp, Model model){
    	String UM = exp.getUsername();
    	System.out.println("profile" + username);
    	 Iterable<PersonModel> perVal = personRepository.findByUsername(UM);
         Iterable<EducationModel> eduVal = educationRepository.findByUsername(UM);
         Iterable<ExperianceModel> expVal = experianceRepository.findByUsername(UM);
         Iterable<SkillsModel> skillVal = skillRepository.findByUsername(UM);
         
         model.addAttribute("newValue1", perVal);
         model.addAttribute("newValue2", eduVal);
         model.addAttribute("newValue3", expVal);
         model.addAttribute("newValue4", skillVal);
         
         return "profile";

        
             
    }
    
    @RequestMapping(value = "/viewCompany/{username}", method = RequestMethod.GET)
    public String viewCompany(@PathVariable(value ="username") String username, PersonModel person, Model model){
    	String UM = person.getUsername();
    	System.out.println("profile" + username);
    	 Iterable<PersonModel> perVal = personRepository.findByUsername(UM);
         Iterable<EducationModel> eduVal = educationRepository.findByUsername(UM);
         Iterable<ExperianceModel> expVal = experianceRepository.findByUsername(UM);
         Iterable<SkillsModel> skillVal = skillRepository.findByUsername(UM);
         
         model.addAttribute("newValue1", perVal);
         model.addAttribute("newValue2", eduVal);
         model.addAttribute("newValue3", expVal);
         model.addAttribute("newValue4", skillVal);
         
         return "profile";

        
             
    }
    
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model, Model rmodel){
        model.addAttribute("user", new User());
        rmodel.addAttribute("role", new Role());
        return "registration";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){

        model.addAttribute("user", user);
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }

        return "home";
    }
    
    @RequestMapping(value="/registerRec", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registrationRec";
    }

    @RequestMapping(value="/registerRec", method = RequestMethod.POST)
    public String processRegistrationRec(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){

        model.addAttribute("user", user);
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveAdmin(user);
            model.addAttribute("message", "User Account Successfully Created");
        }

        return "home";
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

}