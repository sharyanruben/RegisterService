package com.springbootexercise.springbootexercise.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "AFFILIATE_CLIENT_MAP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffilateClientMap {

    @Id
    @Column(name = "client_id")
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "click_id")
    private String clickId;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "creation_date")
    private Timestamp creationDate;

}
