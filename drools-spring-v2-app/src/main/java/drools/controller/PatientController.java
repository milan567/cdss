package drools.controller;

import drools.model.Patient;
import drools.model.dto.DiagnosePatientDTO;
import drools.service.DiseaseService;
import drools.service.PatientService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;


    @RequestMapping(value = "api/sviPacijenti", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAll();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/pacijentiSaHronicnimOboljenjima", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPatientsWithChronicDiseases(HttpServletRequest request) {
        try {
            List<Patient> patients = patientService.getPatientsWithChronicDiseases(
                    (KieSession) request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/potencijalniZavisnici", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPotentialAddicts(HttpServletRequest request) {
        try {
            List<Patient> patients = patientService.getPotentialAddicts(
                    (KieSession) request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/pacijentiSaSlabimImunitetom", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLowImmunityPatients(HttpServletRequest request) {
        try {
            List<Patient> patients = patientService.getLowImmunityPatients(
                    (KieSession) request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "api/pacijent/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDisease(@PathVariable("id") Integer id) {
        try {
            Patient patient = patientService.findPatient(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }
}
