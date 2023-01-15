package com.springbootexercise.springbootexercise.service;

import com.springbootexercise.springbootexercise.request.ClickRequest;
import com.springbootexercise.springbootexercise.dto.ClientRequestDto;
import com.springbootexercise.springbootexercise.dto.ConversionRequestDto;
import com.springbootexercise.springbootexercise.request.ExternalConversionRequest;
import com.springbootexercise.springbootexercise.entity.AffilateClientMap;
import com.springbootexercise.springbootexercise.entity.AffilateTransaction;
import com.springbootexercise.springbootexercise.entity.FailedCalls;
import com.springbootexercise.springbootexercise.enumuration.RequestType;
import com.springbootexercise.springbootexercise.exception.ClickServiceException;
import com.springbootexercise.springbootexercise.repository.AffilateClientRepository;
import com.springbootexercise.springbootexercise.repository.AffilateTransactionRepository;
import com.springbootexercise.springbootexercise.repository.FailedCallsRepository;
import com.springbootexercise.springbootexercise.response.ConversionResponse;
import com.springbootexercise.springbootexercise.response.UuidResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class RegisterService {

    private static final Logger LOG = LogManager.getLogger(RegisterService.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    private AffilateClientRepository clientRepository;

    @Autowired
    private FailedCallsRepository failedCallsRepository;

    @Autowired
    private AffilateTransactionRepository affilateTransactionRepository;

    @Value("${vendorservice.base.url}")
    private String baseUrl;

    @Value("${currency.config.value}")
    private String currency;

    public UUID clickToExternalVendor(ClientRequestDto clientRequest) {
        UUID uuid;
        try {
            uuid = getUUIDFromExternalVendor(clientRequest);
        } catch (RuntimeException ex) {
            saveFailedCalls(clientRequest.getClientId(), ex.getMessage());
            throw new ClickServiceException("The Click External Vendor was failed");
        }
        return uuid;
    }

    public int conversionToExternalVendor(ConversionRequestDto conversionRequest) {
        int conversionid;
        try {
            conversionid = getConversionFromExternalVendor(conversionRequest);
        } catch (RuntimeException ex) {
            saveFailedCalls(conversionRequest.getClientId(), ex.getMessage());
            throw new ClickServiceException("The Conversion External Vendor was failed");
        }

        return conversionid;
    }

    @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(3000))
    public UUID getUUIDFromExternalVendor(ClientRequestDto clientRequest) {
        LOG.info("in getUUIDFromExternalVendor method ");
        ClickRequest clickRequest = getMappedClickRequest(clientRequest);

        UuidResponse responseUUID = webClient
                .post()
                .uri(baseUrl + "/clicks")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(clickRequest), ClickRequest.class)
                .retrieve()
                .bodyToMono(UuidResponse.class)
                .block();
        saveAffilateClient(clientRequest, responseUUID);

        return responseUUID.getUuid();
    }

    @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(3000))
    public int getConversionFromExternalVendor(ConversionRequestDto conversionRequest) {
        LOG.info("in getConversionFromExternalVendor method ");
        AffilateClientMap affilateClientMap = clientRepository.getById(conversionRequest.getClientId());
        ExternalConversionRequest externalConversionRequest = getMappedExternalConversionRequest(conversionRequest, affilateClientMap);
        ConversionResponse conversionResponse = webClient
                .post()
                .uri(baseUrl + "/conversions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(externalConversionRequest), ExternalConversionRequest.class)
                .retrieve()
                .bodyToMono(ConversionResponse.class)
                .block();
        saveAffilateTransaction(conversionRequest, conversionResponse, affilateClientMap.getReferralCode());
        return conversionResponse.getId();
    }

    public void saveAffilateClient(ClientRequestDto clientRequest, UuidResponse responseUUID) {
        AffilateClientMap affilateClientMap = AffilateClientMap
                .builder()
                .uuid(clientRequest.getClientId())
                .referralCode(clientRequest.getReferalCode())
                .clickId(responseUUID.getUuid().toString())
                .userAgent(clientRequest.getUserAgent())
                .creationDate(Timestamp.from(ZonedDateTime.now().toInstant()))
                .build();
        clientRepository.save(affilateClientMap);
    }

    public void saveAffilateTransaction(ConversionRequestDto conversionRequest, ConversionResponse conversionResponse, String referalCode) {
        AffilateTransaction affilateTransaction = AffilateTransaction
                .builder()
                .conversionId(conversionResponse.getId())
                .clientId(conversionRequest.getClientId().toString())
                .referralCode(referalCode)
                .orderId(conversionRequest.getOrderId().toString())
                .currency(currency)
                .orderAmount(Double.toString(conversionResponse.getAmount()))
                .conversionmount(Double.toString(conversionResponse.getCommisions().get(0).getAmount()))
                .transactionType(conversionRequest.getTransactionType().getValue())
                .creationDate(Timestamp.from(ZonedDateTime.now().toInstant()))
                .build();

        affilateTransactionRepository.save(affilateTransaction);
    }

    public void saveFailedCalls(UUID clientId, String payload) {
        FailedCalls failedCalls = FailedCalls
                .builder()
                .uuid(clientId)
                .requestType(RequestType.CREATECLICK.getValue())
                .processed(false)
                .errorTime(Timestamp.from(ZonedDateTime.now().toInstant()))
                .payload(payload)
                .reason(payload)
                .build();
        failedCallsRepository.save(failedCalls);
    }

    private ClickRequest getMappedClickRequest(ClientRequestDto clientRequest) {
        return ClickRequest
                .builder()
                .landingPage(clientRequest.getLandingPage())
                .referalCode(clientRequest.getReferalCode())
                .ip(clientRequest.getIp())
                .userAgent(clientRequest.getUserAgent()).build();
    }

    private ExternalConversionRequest getMappedExternalConversionRequest(ConversionRequestDto conversionRequest, AffilateClientMap affilateClientMap) {
        return ExternalConversionRequest
                .builder()
                .externalId(conversionRequest.getOrderId())
                .amount(conversionRequest.getTotalPrice())
                .clickId(affilateClientMap.getClickId())
                .userAgent(affilateClientMap.getUserAgent())
                .currency(currency)
                .build();
    }
}
