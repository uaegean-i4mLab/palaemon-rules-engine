package gr.aegean.palaemon.rulesengine.service;

import gr.aegean.palaemon.rulesengine.model.MessageObject;
import gr.aegean.palaemon.rulesengine.model.PhaseTask;

public interface TaskRuleService {

    public MessageObject getMessageObject(PhaseTask phaseTask, MessageObject mo);
}
