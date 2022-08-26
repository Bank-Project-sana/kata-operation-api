package fr.societegenerale.operation_api.service;

import fr.societegenerale.service.OperationService;
import fr.societegenerale.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestContextManager;


public class OperationServiceTest {
    @Autowired
    private OperationService operationService;



    @TestConfiguration
    static class OperationServiceImplTestContextConfiguration {
        @Bean
        public OperationService operationService() {
            return new OperationServiceImpl();
        }
    }

    @BeforeEach
    public void beforeEachTestDo() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

}
