package drools.controller;


import drools.model.Disease;
import drools.model.Symptom;
import drools.model.dto.AddDiseaseDTO;
import drools.service.DiseaseService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;


@RestController
@CrossOrigin
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @RequestMapping(value = "/bolesti/sveBolesti", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDiseases() {
        try {
            List<Disease> diseases = diseaseService.findAllDiseases();
            return new ResponseEntity<>(diseases, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/bolest/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDisease(@PathVariable("id") Integer id) {
        try {
            Disease disease = diseaseService.findDisease(id);
            return new ResponseEntity<>(disease, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/bolest/{disease}/simptom/{symptom}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteSymptomFromDisease(@PathVariable("disease") Integer disease,
                                                      @PathVariable("symptom") Integer symptom) {
        try {
            Disease d = diseaseService.deleteSymptomFromDisease(disease,symptom);
            return new ResponseEntity<>(disease, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/bolest/{disease}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDisease(@PathVariable("disease") Integer disease) {
        try {
            List<Disease> d = diseaseService.deleteDisease(disease);
            return new ResponseEntity<>(disease, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "api/bolestSaSimptomima/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiseaseWithSortedSymptoms(@PathVariable("id") Integer id,HttpServletRequest request) {
        try {
            Enumeration stringEnumeration =request.getSession().getAttributeNames();
            List<String> sel=Collections.list(stringEnumeration);

            KieSession kieSession =(KieSession)request.getSession().getAttribute("kieSession");
            List<Symptom> symptoms = diseaseService.findDiseaseWithSortedSymptoms(id,kieSession);
            return new ResponseEntity<>(symptoms, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/bolest/novaBolest", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDisease(@RequestBody AddDiseaseDTO addDiseaseDTO) {
        try {
            Disease disease = diseaseService.addDisease(addDiseaseDTO);
            System.out.println("A");
            return new ResponseEntity<>(disease, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }




    @RequestMapping(value = "/bolest/izmjeniBolest", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editDisease(@RequestBody AddDiseaseDTO addDiseaseDTO) {
        try {
            Disease disease = diseaseService.editDisease(addDiseaseDTO);
            return new ResponseEntity<>(disease, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


}
