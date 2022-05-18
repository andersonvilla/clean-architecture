package com.example.cleanarchitecture.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmptyDataException extends RuntimeException{

    public EmptyDataException(String message){
        super(message);
    }

}
