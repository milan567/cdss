package drools.controller;


import drools.model.Symptom;
import drools.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SymptomController {

    @Autowired
    private SymptomService symptomService;



    @RequestMapping(value = "/simptomi/sviSimptomi", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSymptoms() {
        try {
            List<Symptom> symptoms = symptomService.findAllSymptoms();
            return new ResponseEntity<>(symptoms, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }
}
