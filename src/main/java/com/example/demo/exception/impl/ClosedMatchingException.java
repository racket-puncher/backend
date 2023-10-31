package com.example.demo.exception.impl;

import com.example.demo.exception.AbsctractException;
import com.example.demo.response.ResponseStatus;
import org.springframework.http.HttpStatus;

public class ClosedMatchingException extends AbsctractException {
    @Override
    public ResponseStatus getStatus() {
        return ResponseStatus.ERROR;
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "신청 마감된 경기입니다.";
    }
}
