package drools.controller;


import drools.model.Disease;
import drools.model.Examination;
import drools.model.Symptom;
import drools.model.User;
import drools.model.dto.AddExaminationDTO;
import drools.model.dto.CheckAllergiesResponseDTO;
import drools.model.dto.DiagnosePatientDTO;
import drools.model.dto.PotentialDiseasesResponseDTO;
import drools.service.DiseaseService;
import drools.service.ExaminationService;
import drools.service.MedicationService;
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

    @Autowired
    MedicationService medicationService;

    @Autowired
    ExaminationService examinationService;

    @RequestMapping(value = "api/doktor/dijagnozaPacijenta", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosePatient(@RequestBody DiagnosePatientDTO diagnosePatientDTO, HttpServletRequest request) {
        try {

            Disease d = diseaseService.diagnose(diagnosePatientDTO,(KieSession)request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(d, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "api/doktor/potencijalneBolesti", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> potentialDiseases(@RequestBody List<Symptom> symptomList, HttpServletRequest request) {
        try {
            List<PotentialDiseasesResponseDTO> dto = diseaseService.findPotentialDiseasesSorted(symptomList,(KieSession)request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "api/pacijent/{patientId}/lijek/{medicationId}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkPatientAlergies(@PathVariable("patientId") Integer patientId,
                                  @PathVariable("medicationId") Integer medicationId,HttpServletRequest request) {
        try {
            System.out.println(request.getSession().getAttribute("kieSession"));
            CheckAllergiesResponseDTO carDTO = medicationService.checkAlergiest(
                    patientId,medicationId,(KieSession) request.getSession().getAttribute("kieSession"));
            return new ResponseEntity<>(carDTO, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "api/pregled/noviPregled", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addExamination(@RequestBody AddExaminationDTO addExaminationDTO,HttpServletRequest request) {
        try {
            Examination examination = examinationService.saveExamination(
                    addExaminationDTO,(User) request.getSession().getAttribute("currentUser"));
            return new ResponseEntity<>(examination, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


}
