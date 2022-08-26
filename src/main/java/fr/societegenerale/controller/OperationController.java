package fr.societegenerale.controller;

import fr.societegenerale.dto.OperationDto;
import fr.societegenerale.exception.InvalidInputException;
import fr.societegenerale.model.OperationEntity;
import fr.societegenerale.service.OperationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping({"/bank/v1/operations"})
@CrossOrigin
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping(path = {"/client-account"})
    public Set<OperationEntity> getAllOperationByClientAccountId(@RequestParam("id") long id) throws InvalidInputException {
        return this.operationService.getAllOperationByClientAccountId(id);

    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OperationDto saveOperation(@RequestBody OperationDto operation) throws Exception {
        return this.operationService.saveOperation(operation);


    }
}
