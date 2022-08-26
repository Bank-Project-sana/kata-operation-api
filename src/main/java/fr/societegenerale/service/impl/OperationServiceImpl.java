package fr.societegenerale.service.impl;

import fr.societegenerale.exception.InvalidInputException;
import fr.societegenerale.model.OperationEntity;
import fr.societegenerale.service.OperationService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OperationServiceImpl implements OperationService {


    @Override
    public Set<OperationEntity> getAllOperationByClientAccountId(Long id) throws InvalidInputException {
        return null;
    }
}
