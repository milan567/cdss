package monitoring;

import drools.event.HeartBeatEvent;
import drools.event.UrinationEvent;
import drools.model.*;
import org.drools.core.ClassObjectFilter;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class UrgentDialysisTest {

    @Test
    @SuppressWarnings("Duplicates")
    public void testRapidHeartRate() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices
                .newKieContainer(kieServices.newReleaseId("drools-spring-v2", "drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));

        KieBaseConfiguration kconf = kieServices.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);

        KieSessionConfiguration kconfig1 = kieServices.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession kSession1 = kieBase.newKieSession(kconfig1, null);

        KieSessionConfiguration kconfig2 = kieServices.newKieSessionConfiguration();
        kconfig2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession kSession2 = kieBase.newKieSession(kconfig2, null);

        positiveTest(kSession1);
        negativeTest(kSession2);
    }

    private void positiveTest(KieSession kieSession) {
        SessionPseudoClock clock = kieSession.getSessionClock();

        Disease disease = new Disease();
        disease.setDisease("Hronicna bubrezna bolest");
        kieSession.insert(disease);
        Patient p = new Patient();
        p.setId(1);
        p.setPatientName("Marko");
        Examination examination = new Examination();
        examination.setDisease(disease);
        p.getExaminations().add(examination);
        kieSession.insert(p);
        for (int i = 0; i < 20; i++) {
            HeartBeatEvent heartbeatEvent = new HeartBeatEvent();
            heartbeatEvent.setPatientId(p.getId() );
            kieSession.insert(heartbeatEvent);
        }
        for (int i = 0; i < 5; i++) {
            UrinationEvent ue = new UrinationEvent();
            ue.setPatientId(p.getId());
            ue.setAmount(13);
            kieSession.insert(ue);
            clock.advanceTime(1, TimeUnit.HOURS);
        }

        kieSession.getAgenda().getAgendaGroup("dijaliza").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(1, firedRules);
        Collection<?> newIssues = kieSession.getObjects(new ClassObjectFilter(Issue.class));
        assertEquals(1, newIssues.size());

        Issue issue = (Issue) newIssues.iterator().next();
        Integer i = 1;
        assertEquals(issue.getPatientId(), i);
        assertEquals(issue.getIssueType(), IssueType.URGENT_DIALYSIS);
    }

    private void negativeTest(KieSession kieSession) {
        SessionPseudoClock clock = kieSession.getSessionClock();

        Disease disease = new Disease();
        disease.setDisease("Hronicna bubrezna bolest");
        kieSession.insert(disease);
        Patient p = new Patient();
        p.setId(1);
        p.setPatientName("Marko");
        Examination examination = new Examination();
        examination.setDisease(disease);
        p.getExaminations().add(examination);
        for (int i = 0; i < 20; i++) {
            HeartBeatEvent heartbeatEvent = new HeartBeatEvent();
            heartbeatEvent.setPatientId(p.getId());
            kieSession.insert(heartbeatEvent);
        }
        for (int i = 0; i < 5; i++) {
            UrinationEvent ue = new UrinationEvent();
            ue.setPatientId(p.getId());
            ue.setAmount(20);
            kieSession.insert(ue);
            clock.advanceTime(1, TimeUnit.HOURS);
        }

        kieSession.getAgenda().getAgendaGroup("dijaliza").setFocus();
        kieSession.fireAllRules();
        int firedRules = kieSession.fireAllRules();
        assertEquals(0, firedRules);
        Collection<?> newIssues = kieSession.getObjects(new ClassObjectFilter(Issue.class));
        assertEquals(0, newIssues.size());
    }



}
