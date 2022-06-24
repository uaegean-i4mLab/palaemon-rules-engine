package gr.aegean.palaemon.rulesengine.service.impl;

import gr.aegean.palaemon.rulesengine.config.DroolsConfig;
import gr.aegean.palaemon.rulesengine.model.MessageObject;
import gr.aegean.palaemon.rulesengine.model.PassengerAssignment;
import gr.aegean.palaemon.rulesengine.model.PhaseTask;
import gr.aegean.palaemon.rulesengine.service.TaskRuleService;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;

@Service
public class TaskRuleServiceImpl implements TaskRuleService {

    private KieSession kieSession=new DroolsConfig().getKieSession();


    @Override
    public MessageObject getMessageObject(PhaseTask phaseTask, MessageObject mo) {
        //ensure the messageObject is set
        phaseTask.setMessageObject(new MessageObject());

        //
        FactHandle phaseFact = kieSession.insert(phaseTask);
        kieSession.setGlobal("messageObject",mo);
        kieSession.fireAllRules();
//        System.out.println(phaseTask.getMessageObject());
        kieSession.delete(phaseFact);
        return  phaseTask.getMessageObject();
    }
}
