package com.example.cleanarchitecture.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoDataFoundException extends RuntimeException{

    public NoDataFoundException(String message){
        super(message);
    }

}
