package vml.travelagency.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ApiException {

    private final String statusCode;
    private final String message;
    private final LocalDateTime timestamp;
}
