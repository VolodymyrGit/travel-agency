package vml.travelagency.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

    private List<ValidationViolation> violations;
    private LocalDateTime timestamp;
}
