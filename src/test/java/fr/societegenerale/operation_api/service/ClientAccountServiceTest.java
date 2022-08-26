package fr.societegenerale.operation_api.service;

import fr.societegenerale.service.ClientAccountService;
import fr.societegenerale.service.impl.ClientAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestContextManager;

public class ClientAccountServiceTest {
    @Autowired
    ClientAccountService clientAccountService;



    @TestConfiguration
    static class ClientServiceImplTestContextConfiguration {

        @Bean
        public ClientAccountService clientAccountService() {
            return new ClientAccountServiceImpl();
        }
    }

    @BeforeEach
    public void beforeEachTestDo() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }


}
