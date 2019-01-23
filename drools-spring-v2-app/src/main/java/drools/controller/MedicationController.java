package drools.controller;


import drools.model.Examination;
import drools.model.Ingredient;
import drools.model.Medication;
import drools.model.dto.AddExaminationDTO;
import drools.service.IngredientService;
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

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(value = "api/lijekovi/sviLijekovi", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMedications() {
        try {
            List<Medication> medications = medicationService.findAllMedications();
            return new ResponseEntity<>(medications, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "api/lijek/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMedications(@PathVariable("id") Integer id) {
        try {
            List<Medication> medications = medicationService.deleteMedication(id);
            return new ResponseEntity<>(medications, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "api/sastojci/sviSastojci", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredients() {
        try {
            List<Ingredient> ingredients = ingredientService.findAllIngredients();
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/sastojak/dodajNoviSastojak", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSymptom(@RequestBody Ingredient i) {
        try {
            ingredientService.save(i);
            List<Ingredient> ingredients = ingredientService.findAllIngredients();
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/api/noviLijek", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMedication(@RequestBody Medication m) {
        try {
            Medication medication = medicationService.save(m);
            return new ResponseEntity<>(medication, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/lijek/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMedication(@PathVariable("id") Integer id) {
        try {
            Medication medication = medicationService.getMedication(id);
            return new ResponseEntity<>(medication, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

}
