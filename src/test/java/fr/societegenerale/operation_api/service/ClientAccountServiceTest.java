package fr.societegenerale.operation_api.service;

import fr.societegenerale.dto.ClientAccountDto;
import fr.societegenerale.model.ClientAccountEntity;
import fr.societegenerale.repository.ClientAccountRepository;
import fr.societegenerale.service.ClientAccountService;
import fr.societegenerale.service.impl.ClientAccountServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestContextManager;

public class ClientAccountServiceTest {
    @Autowired
    ClientAccountService clientAccountService;
    @MockBean
    ClientAccountRepository clientAccountRepository;


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

    @Test
    void updateAccountBalanceTest() {
        ClientAccountDto clientAccountDto = ClientAccountDto.builder().id(2L).balance(900D)
                .accountNumber("5486").build();
        ClientAccountEntity clientAccountEntityTOsave = ClientAccountEntity.builder().id(2L).balance(800D)
                .accountNumber("5486").build();
        Mockito.when(clientAccountRepository.save(Mockito.any())).thenReturn(clientAccountEntityTOsave);

        Assert.assertEquals(clientAccountEntityTOsave.getBalance(), clientAccountService.updateAccountBalance(clientAccountDto).getBalance());

    }
}
