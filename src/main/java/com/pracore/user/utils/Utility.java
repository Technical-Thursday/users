package com.pracore.user.utils;

import com.pracore.user.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;

public class Utility {

    private String logMessage = "init";

    public Utility(){
        System.out.println("Utility: In no arg constructor");
    }

    @Autowired
    public Utility(LogService logService){
        System.out.println("Utility: In arg constructor");
    }

    public void setLogMessage(String message) {
        this.logMessage = message;
    }

    public String log() {
        return this.logMessage;
    }

    @Autowired
    public void setLogMessage(Utility utility) {
        this.logMessage = "from setter";
        System.out.println("Utility: In setter");
    }
}
