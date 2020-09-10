package com.pracore.user.utils;

import org.springframework.beans.factory.annotation.Autowired;

public class Test {
    @Autowired
    public Test(Utility utility) {
        utility.log();
    }
}
