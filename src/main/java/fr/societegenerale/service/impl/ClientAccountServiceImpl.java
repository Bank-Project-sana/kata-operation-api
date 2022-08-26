package fr.societegenerale.service.impl;

import fr.societegenerale.dto.ClientAccountDto;
import fr.societegenerale.exception.NotFoundException;
import fr.societegenerale.model.ClientAccountEntity;
import fr.societegenerale.repository.ClientAccountRepository;
import fr.societegenerale.service.ClientAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientAccountServiceImpl implements ClientAccountService {
    @Autowired
    ClientAccountRepository clientAccountRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ClientAccountDto getAccountById(Long id) throws Exception {
        Optional<ClientAccountEntity> clientAccountOptional = clientAccountRepository.findById(id);
        return modelMapper.map(clientAccountOptional.orElseThrow(() ->
                new NotFoundException("account")), ClientAccountDto.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)

    public ClientAccountDto updateAccountBalance(ClientAccountDto clientAccount) {

        return modelMapper.map(clientAccountRepository.save(modelMapper.map(clientAccount,
                ClientAccountEntity.class)), ClientAccountDto.class);
    }
}
