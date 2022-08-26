package fr.societegenerale.service.impl;

import fr.societegenerale.dto.ClientAccountDto;
import fr.societegenerale.dto.OperationDto;
import fr.societegenerale.exception.AccountNotContainAmountException;
import fr.societegenerale.exception.InvalidInputException;
import fr.societegenerale.model.OperationEntity;
import fr.societegenerale.model.OperationTypeEnum;
import fr.societegenerale.repository.OperationRepository;
import fr.societegenerale.service.ClientAccountService;
import fr.societegenerale.service.OperationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class OperationServiceImpl implements OperationService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private OperationRepository operationRepo;
    @Autowired
    private ClientAccountService clientAccountService;

    @Override
    public Set<OperationEntity> getAllOperationByClientAccountId(Long id) throws InvalidInputException {
        if (id == null) {
            throw new InvalidInputException("The input cannot be null");
        }
        return operationRepo.findByAccountIdOrderByDateDesc(id);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OperationDto saveOperation(OperationDto operation) throws Exception {
        OperationEntity savedOperation;
        Double newBalance;
        ClientAccountDto clientAccountDto = clientAccountService.getAccountById(operation.getAccount().getId());
        if (operation.getType() == null) {
            throw new InvalidInputException("operation type");
        }

        if (OperationTypeEnum.WITHDRAWAL.equals(operation.getType())) {
            // us 1 : withdrawal
            if (clientAccountDto.getBalance() > operation.getAmount()) {
                newBalance = clientAccountDto.getBalance() - operation.getAmount();
            } else {
                throw new AccountNotContainAmountException(clientAccountDto.getAccountNumber(), operation.getAmount());
            }
        } else {
            // case Deposit
            newBalance = clientAccountDto.getBalance() + operation.getAmount();
        }
        // save operation
        operation.setBalance(newBalance);
        savedOperation = operationRepo.save(modelMapper.map(operation, OperationEntity.class));
        // update account balance
        clientAccountDto.setBalance(newBalance);
        clientAccountService.updateAccountBalance(clientAccountDto);
        return modelMapper.map(savedOperation, OperationDto.class);
    }

}
