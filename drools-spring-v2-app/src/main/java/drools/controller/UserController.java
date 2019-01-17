package drools.controller;


import drools.model.User;
import drools.model.dto.LoginRequestDTO;
import drools.model.dto.LoginResponseDTO;
import drools.service.UserService;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController
{
    @Autowired
    UserService userService;


    @Autowired
    private KieContainer kieContainer;


    @RequestMapping(value = "/api/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO, HttpServletRequest request) {
        System.out.println("Username: " + loginDTO.getUsername());
        System.out.println("Password: " + loginDTO.getPassword());
        try {
            User user = this.userService.validateUser(loginDTO);
            if (user != null) {
                request.getSession().setAttribute("currentUser", user);

                /*
                KieServices ks = KieServices.Factory.get();
                KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
                kbconf.setOption(EventProcessingOption.STREAM);
                KieBase kbase = kieContainer.newKieBase(kbconf);
                KieSession kieSession = kbase.newKieSession();
                request.getSession().setAttribute("kieSession", kieSession);
                */
            }
            System.out.println(user);
            return new ResponseEntity<>(new LoginResponseDTO(null, user.getId(),loginDTO.getUsername(),user.getUserAuthority().toString()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

}
