package gov.traffic.vehicle.deviation.notifier.exception;

public class VehicleApiException extends RuntimeException {
    private ApiErrors apiError;

    public ApiErrors getApiError() {
        return apiError;
    }

    public VehicleApiException(ApiErrors apiError, String message) {
        super(message);
        this.apiError = apiError;
    }

    public VehicleApiException(Throwable cause, ApiErrors apiError, String message) {
        super(message, cause);
        this.apiError = apiError;
    }
}
