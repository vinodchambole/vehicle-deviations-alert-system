package gov.traffic.vehicle.deviation.notifier.exception;

import org.springframework.http.HttpStatus;


public enum ApiErrors {

    //******************************** Client Errors *************************
    BAD_REQUEST(400000, HttpStatus.BAD_REQUEST),
    INVALID_ARGUMENTS(400001, HttpStatus.BAD_REQUEST),
    //******************************** Application Errors *********************

    //******************************** Server Errors *************************
    INTERNAL_SERVER_ERROR(500000, HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private HttpStatus httpStatus;

    ApiErrors(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }


    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return "ApiErrors{"
                + "code=" + code
                + ", httpStatus=" + httpStatus
                + '}';
    }

}

