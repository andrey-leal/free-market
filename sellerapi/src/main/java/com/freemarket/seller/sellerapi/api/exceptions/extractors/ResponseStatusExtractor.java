package com.freemarket.seller.sellerapi.api.exceptions.extractors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Component
public class ResponseStatusExtractor {

    private static final Logger LOG = LoggerFactory
            .getLogger(ResponseStatusExtractor.class);

    public Optional<HttpStatus> extract(Exception ex) {
        LOG.debug("Discovering Http Status...");
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        ResponseStatus parentResponseStatus = ex.getClass().getSuperclass()
                .getAnnotation(ResponseStatus.class);

        if (responseStatus != null) {
            LOG.debug("Exception has a response status of {}.", responseStatus.value());
            return Optional.of(responseStatus.value());
        }

        if (parentResponseStatus != null) {
            LOG.debug("Parent exception has a response status of {}.", parentResponseStatus.value());
            return Optional.of(parentResponseStatus.value());
        }

        LOG.debug("Exception has no response status annotation.");
        return Optional.empty();
    }
}
