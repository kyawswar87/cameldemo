package com.camel.dbimport.example;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String acctId;
    private int advanceBalance;
    private String currency;
    private String customCode;
    private String invoiceNo;
}
