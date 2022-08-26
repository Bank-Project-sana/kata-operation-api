package fr.societegenerale.dto;

import fr.societegenerale.model.OperationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationDto {
    private Long id;


    private OperationTypeEnum type;

    private Double amount;

    private String date;

    private Double balance;


    private ClientAccountDto account;


}

