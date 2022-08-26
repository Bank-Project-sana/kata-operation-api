package fr.societegenerale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientAccountDto {
    private Long id;

    private String accountNumber;

    private Double balance;


}
