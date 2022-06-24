package gr.aegean.palaemon.rulesengine.service.impl;

import gr.aegean.palaemon.rulesengine.config.DroolsConfig;
import gr.aegean.palaemon.rulesengine.model.MessageBodyRequest;
import gr.aegean.palaemon.rulesengine.model.MessageBodyResponse;
import gr.aegean.palaemon.rulesengine.service.MessageBodyService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageBodyServiceImpl implements MessageBodyService {

    private KieSession kieSession=new DroolsConfig().getKieSession();

    public String getMessageBody(MessageBodyRequest req, MessageBodyResponse res){

        FactHandle addedFacts = kieSession.insert(req);
        kieSession.setGlobal("messageBodyRes",res);
        kieSession.fireAllRules();
//        System.out.println(res.getContent());
        log.info("The message obj request is {}", req.toString());
        log.info("The message obj body returned from the rules is  is {}", res.getContent());
        kieSession.delete(addedFacts);
        return  res.getContent();
    };
}
