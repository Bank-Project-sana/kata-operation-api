package fr.societegenerale.operation_api.controller;

import fr.societegenerale.controller.OperationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc


public class OperationControllerTest {
    @Autowired
    private MockMvc mockMvc;
@MockBean
    OperationController operationController;

    @Test
    public void getAllOperationByClientAccountIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/v1/operations/client-account").param("id","1")).andExpect(status().isOk());
    }



}
