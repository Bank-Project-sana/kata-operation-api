package fr.societegenerale.service;

import fr.societegenerale.dto.OperationDto;
import fr.societegenerale.exception.InvalidInputException;
import fr.societegenerale.model.OperationEntity;

import java.util.Set;

public interface OperationService {
    Set<OperationEntity> getAllOperationByClientAccountId(Long id) throws InvalidInputException;

    OperationDto saveOperation(OperationDto operation) throws Exception;
}
