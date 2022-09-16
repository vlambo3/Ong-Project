package com.alkemy.ong.utils.messenger;

public interface IMessenger {

    String OK = "asd";

    default String getMessage(int code){
        switch (code) {
            case 200:
                return OK;        
            default:
                break;
        }
        return OK;
    }
    
}
