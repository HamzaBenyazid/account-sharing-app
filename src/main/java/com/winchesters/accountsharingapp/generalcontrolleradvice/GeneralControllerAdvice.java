package com.winchesters.accountsharingapp.generalcontrolleradvice;

import com.winchesters.accountsharingapp.dto.ErrorResponseDto;
import com.winchesters.accountsharingapp.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public interface GeneralControllerAdvice {
    Logger LOG = LoggerFactory.getLogger(GeneralControllerAdvice.class);

    default ResponseEntity<ErrorResponseDto> handleException(
            int statusCode, Exception exception) {

        LOG.debug("error response {}", exception.getMessage());
        if (exception instanceof GeneralException) {
            return ResponseEntity
                    .status(statusCode)
                    .body(
                            new ErrorResponseDto(
                                    HttpStatus.resolve(statusCode),
                                    Instant.now(),
                                    true,
                                    exception
                            )
                    );
        }
        return ResponseEntity
                .status(statusCode)
                .body(
                        new ErrorResponseDto(
                                HttpStatus.resolve(statusCode),
                                Instant.now(),
                                exception
                        )
                );

    }
}
