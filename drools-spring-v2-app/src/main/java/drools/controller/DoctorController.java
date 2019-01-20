package drools.controller;


import drools.model.Symptom;
import drools.model.dto.DiagnosePatientDTO;
import drools.service.DiseaseService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Scope;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
@CrossOrigin
public class DoctorController {

    @Autowired
    private DiseaseService diseaseService;


    @RequestMapping(value = "api/doktor/dijagnozaPacijenta", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosePatient(@RequestBody DiagnosePatientDTO diagnosePatientDTO, HttpServletRequest request) {
        try {

            diseaseService.diagnose(diagnosePatientDTO,(KieSession)request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "api/doktor/potencijalneBolesti", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> potentialDiseases(@RequestBody List<Symptom> symptomList, HttpServletRequest request) {
        try {

            diseaseService.findPotentialDiseasesSorted(symptomList,(KieSession)request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

}
