package drools.controller;


import drools.model.Examination;
import drools.model.Medication;
import drools.model.dto.AddExaminationDTO;
import drools.service.MedicationService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @RequestMapping(value = "api/lijekovi/sviLijekovi", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDiseases() {
        try {
            List<Medication> medications = medicationService.findAllMedications();
            return new ResponseEntity<>(medications, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

}
