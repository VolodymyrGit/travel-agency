package vml.travelagency.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationViolation {

    private final String fieldName;
    private final String message;
}
