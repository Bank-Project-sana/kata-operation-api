package fr.societegenerale.operation_api.service;

import fr.societegenerale.dto.ClientAccountDto;
import fr.societegenerale.dto.OperationDto;
import fr.societegenerale.exception.AccountNotContainAmountException;
import fr.societegenerale.model.ClientAccountEntity;
import fr.societegenerale.model.OperationEntity;
import fr.societegenerale.model.OperationTypeEnum;
import fr.societegenerale.repository.OperationRepository;
import fr.societegenerale.service.ClientAccountService;
import fr.societegenerale.service.OperationService;
import fr.societegenerale.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestContextManager;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class OperationServiceTest {
    @Autowired
    private OperationService operationService;

    @MockBean
    private OperationRepository operationRepository;

    @MockBean
    private ClientAccountService clientAccountService;

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

    @Test
    void getAllOperationByClientAccountIdTest() throws Exception {
        Set<OperationEntity> operations = new HashSet<>();
        operations.add(OperationEntity.builder().id(1L).type(OperationTypeEnum.WITHDRAWAL).build());
        operations.add(OperationEntity.builder().id(2L).type(OperationTypeEnum.WITHDRAWAL).build());

        Mockito.when(operationRepository.findByAccountIdOrderByDateDesc(Mockito.any())).thenReturn(operations);

        assertEquals(operations.size(), operationService.getAllOperationByClientAccountId(1L).size());

    }

    @Test
    void saveOperationTest_when_type_withdrawal() throws Exception {
        OperationDto operationTosave = OperationDto.builder().type(OperationTypeEnum.WITHDRAWAL).account(ClientAccountDto.builder().id(2L).balance(900D)
                .accountNumber("5488").build()).amount(50D).date("02-05-2022").build();
        OperationEntity EntityTOReturn = OperationEntity.builder().id(1L).type(OperationTypeEnum.WITHDRAWAL).account(ClientAccountEntity.builder().id(2L).balance(900D)
                .accountNumber("5488").build()).amount(50D).date("02-05-2022").build();
        Mockito.when(clientAccountService.getAccountById(Mockito.any())).thenReturn(ClientAccountDto.builder().id(2L).balance(900D)
                .accountNumber("5488").build());

        Mockito.when(operationRepository.save(Mockito.any())).thenReturn(EntityTOReturn);
        assertEquals(EntityTOReturn.getId(), operationService.saveOperation(operationTosave).getId());


    }

    @Test
    void saveOperationTest_when_type_deposit() throws Exception {
        OperationDto operationTosave = OperationDto.builder().type(OperationTypeEnum.DEPOSIT).account(ClientAccountDto.builder().id(2L).balance(900D)
                .accountNumber("5486").build()).amount(50D).date("02-05-2022").build();
        OperationEntity EntityTOReturn = OperationEntity.builder().id(1L).type(OperationTypeEnum.DEPOSIT).account(ClientAccountEntity.builder().id(2L).balance(900D)
                .accountNumber("5486").build()).amount(50D).date("02-05-2022").build();
        Mockito.when(clientAccountService.getAccountById(Mockito.any())).thenReturn(ClientAccountDto.builder().id(2L).balance(900D)
                .accountNumber("5486").build());

        Mockito.when(operationRepository.save(Mockito.any())).thenReturn(EntityTOReturn);
        assertEquals(EntityTOReturn.getId(), operationService.saveOperation(operationTosave).getId());


    }


    @Test
    void saveOperationTest_when_type_withdrawal_throw_AccountNotContainAmountException() throws Exception {
        OperationDto operationTosave = OperationDto.builder().type(OperationTypeEnum.WITHDRAWAL).account(ClientAccountDto.builder().id(2L).balance(400D)
                .accountNumber("5486").build()).amount(500D).date("02-05-2022").build();
        Mockito.when(clientAccountService.getAccountById(Mockito.any())).thenReturn(ClientAccountDto.builder().id(2L).balance(400D)
                .accountNumber("5486").build());
        Exception exception = assertThrows(AccountNotContainAmountException.class, () -> operationService.saveOperation(operationTosave));
        assertEquals("The account 5486 not contain the amount 500", exception.getMessage());

    }


}
