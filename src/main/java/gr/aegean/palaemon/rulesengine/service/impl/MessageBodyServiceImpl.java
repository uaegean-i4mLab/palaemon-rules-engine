package gr.aegean.palaemon.rulesengine.service.impl;

import gr.aegean.palaemon.rulesengine.config.DroolsConfig;
import gr.aegean.palaemon.rulesengine.model.MessageBodyRequest;
import gr.aegean.palaemon.rulesengine.model.MessageBodyResponse;
import gr.aegean.palaemon.rulesengine.service.MessageBodyService;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class MessageBodyServiceImpl implements MessageBodyService {

    private KieSession kieSession=new DroolsConfig().getKieSession();

    public String getMessageBody(MessageBodyRequest req, MessageBodyResponse res){
        kieSession.insert(req);
        kieSession.setGlobal("messageBodyRes",res);
        kieSession.fireAllRules();
        System.out.println(res.getContent());
        return  res.getContent();
    };
}
