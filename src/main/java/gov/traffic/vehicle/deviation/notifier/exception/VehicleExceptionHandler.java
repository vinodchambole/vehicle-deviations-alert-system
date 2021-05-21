package gov.traffic.vehicle.deviation.notifier.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class VehicleExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handle(VehicleApiException exception) {
        return ResponseEntity.status(exception.getApiError().getHttpStatus())
                .body(exception.getLocalizedMessage());
    }
}
