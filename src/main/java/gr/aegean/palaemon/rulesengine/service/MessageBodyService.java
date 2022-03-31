package gr.aegean.palaemon.rulesengine.service;

import gr.aegean.palaemon.rulesengine.model.MessageBodyRequest;
import gr.aegean.palaemon.rulesengine.model.MessageBodyResponse;
import gr.aegean.palaemon.rulesengine.model.MessageObject;
import gr.aegean.palaemon.rulesengine.model.PhaseTask;

public interface MessageBodyService {

    public String getMessageBody(MessageBodyRequest req, MessageBodyResponse res);
}
