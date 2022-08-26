package fr.societegenerale.service;

import fr.societegenerale.dto.ClientAccountDto;

public interface ClientAccountService {
    ClientAccountDto getAccountById(Long id) throws Exception;

    ClientAccountDto updateAccountBalance(ClientAccountDto clientAccount);
}
