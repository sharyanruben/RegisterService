package com.springbootexercise.springbootexercise.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "AFFILIATE_TRANSACTIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffilateTransaction {

    @Id
    @Column(name = "conversion_id")
    private int conversionId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "order_id")
    private String orderId;

    private String currency;

    @Column(name = "order_amount")
    private String orderAmount;

    @Column(name = "conversion_amount")
    private String conversionmount;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "creation_date")
    private Timestamp creationDate;
}
