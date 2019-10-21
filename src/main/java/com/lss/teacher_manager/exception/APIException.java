package com.lss.teacher_manager.exception;

public class APIException extends RuntimeException {
    private APIError mAPIError;

    public APIException(APIError apiError){
//        super(apiError.getMessage());
        this.mAPIError=apiError;
    }

    public APIError getmAPIError(){
        return this.mAPIError;
    }

}
