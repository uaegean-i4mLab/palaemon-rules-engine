package gr.aegean.palaemon.rulesengine.config;


import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.IOException;

public class DroolsConfig {

    private static final String RULES_PATH = "rules/";
    private final KieServices kieServices=KieServices.Factory.get();

    private  KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
////        List<String> rules= Arrays.asList("BackwardChaining.drl","SuggestApplicant.drl","Product_rules.xls");
//        List<String> rules= Arrays.asList("Applicant.drl", "Product_rules.xls");
//        for(String rule:rules){
//            kieFileSystem.write(ResourceFactory.newClassPathResource(rule));
//        }
        return kieFileSystem;

    }

    public KieContainer getKieContainer() throws IOException {
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kContainer;

    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

    public KieSession getKieSession(){
        getKieRepository();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/Phase_Task_Rules.xls"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/Phase_Rules.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/MessageBodyRules.drl"));
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kContainer.newKieSession();
    }

    public KieSession getKieSession(Resource dt) {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem()
                .write(dt);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem)
                .buildAll();
        KieRepository kieRepository = kieServices.getRepository();
        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
        KieSession ksession = kieContainer.newKieSession();
        return ksession;
    }

    /*
     * Can be used for debugging
     * Input excelFile example: com/baeldung/drools/rules/Discount.xls
     */
    public String getDrlFromExcel(String excelFile) {
        DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        configuration.setInputType(DecisionTableInputType.XLS);
        Resource dt = ResourceFactory.newClassPathResource(excelFile, getClass());
        DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();
        String drl = decisionTableProvider.loadFromResource(dt, null);

        return drl;
    }

}

