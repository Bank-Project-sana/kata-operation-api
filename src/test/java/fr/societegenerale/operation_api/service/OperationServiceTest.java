package fr.societegenerale.operation_api.service;

import fr.societegenerale.model.OperationEntity;
import fr.societegenerale.model.OperationTypeEnum;
import fr.societegenerale.repository.OperationRepository;
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


public class OperationServiceTest {
    @Autowired
    private OperationService operationService;

    @MockBean
    private OperationRepository operationRepository;



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


}
