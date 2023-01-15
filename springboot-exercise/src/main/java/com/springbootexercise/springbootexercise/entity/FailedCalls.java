package com.springbootexercise.springbootexercise.entity;

import com.springbootexercise.springbootexercise.enumuration.RequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id ;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "FAILED_CALLS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailedCalls {

    @Id
    @Column(name = "client_id")
    @Type(type = "uuid-char")
    private UUID uuid;

    private String requestType = RequestType.CREATECLICK.getValue();

    private String payload;

    private String reason;

    private Timestamp errorTime;

    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "PROCESSED")
    private boolean processed;
}
