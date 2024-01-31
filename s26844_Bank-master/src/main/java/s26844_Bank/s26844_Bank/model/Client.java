package s26844_Bank.s26844_Bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Client {
    private Integer ID;
    private String name;
    private String surname;
    private Double saldo;
    private String currency;
}

