package monitoring;

import drools.event.OxygenLevelEvent;
import drools.model.Issue;
import drools.model.IssueType;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class OxygenProblemTests {

    @Test
    @SuppressWarnings("Duplicates")
    public void testOxygenProblem() {

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


        for (int i = 0; i < 60; i++) {
            OxygenLevelEvent ole = new OxygenLevelEvent();
            ole.setPatientId(1);
            ole.setOxygenLevel(65);
            kieSession.insert(ole);
            clock.advanceTime(15, TimeUnit.SECONDS);
            int firedRules = kieSession.fireAllRules();
            assertEquals(0, firedRules);
        }
        kieSession.getAgenda().getAgendaGroup("kiseonik").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(1, firedRules);
        Collection<?> newIssues = kieSession.getObjects(new ClassObjectFilter(Issue.class));
        assertEquals(1, newIssues.size());

        Issue issue = (Issue) newIssues.iterator().next();
        Integer i = 1;
        assertEquals(issue.getPatientId(), i);
        assertEquals(issue.getIssueType(), IssueType.OXYGEN_PROBLEM);
    }

    private void negativeTest(KieSession kieSession) {
        SessionPseudoClock clock = kieSession.getSessionClock();
        for (int i = 0; i < 60; i++) {
            OxygenLevelEvent ole = new OxygenLevelEvent();
            ole.setPatientId(1);
            ole.setOxygenLevel(65+i);
            kieSession.insert(ole);
            clock.advanceTime(15, TimeUnit.SECONDS);
            int firedRules = kieSession.fireAllRules();
            assertEquals(0, firedRules);
        }
        kieSession.getAgenda().getAgendaGroup("kiseonik").setFocus();
        int firedRules = kieSession.fireAllRules();
        assertEquals(0, firedRules);
        Collection<?> newIssues = kieSession.getObjects(new ClassObjectFilter(Issue.class));
        assertEquals(0, newIssues.size());
    }
}
