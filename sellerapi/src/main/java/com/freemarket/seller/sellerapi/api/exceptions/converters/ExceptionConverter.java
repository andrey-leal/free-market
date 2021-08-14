package com.freemarket.seller.sellerapi.api.exceptions.converters;


import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ExceptionConverter {

    private static final Logger LOG = LoggerFactory
            .getLogger(ExceptionConverter.class);

    private final List<ExceptionConverterSupports> exceptionConverterList;
    private final GenericExceptionConverter genericExceptionConverter;

    public ExceptionConverter(
            List<ExceptionConverterSupports> exceptionConverterList,
            GenericExceptionConverter genericExceptionConverter) {
        this.exceptionConverterList = exceptionConverterList;
        this.genericExceptionConverter = genericExceptionConverter;
    }

    public ExceptionResponse convert(Exception e, int value) {
        LOG.debug("Converting exception", e);

        List<IExceptionConverter> converterSupportedList =
                exceptionConverterList.stream().filter(converter -> converter.supports(e))
                        .collect(toList());

        if (converterSupportedList.size() > 1) {
            throw new IllegalStateException(
                    String.format(
                            "More than one converter found for exception %s: %s", e.getClass(),
                            converterSupportedList));
        }

        IExceptionConverter exceptionConverter = converterSupportedList.stream().findFirst()
                .orElse(genericExceptionConverter);

        LOG.debug("Using converter {}", exceptionConverter.getClass());

        return exceptionConverter.convert(e, value);
    }


}
