package com.example.liujishun.mysamples.bean;

import javax.inject.Inject;

/**
 * Created by liujishun on 2017/2/14.
 */

public class Cat {
    private String name;
    @Inject
    public Cat(String name){
        this.name = name;
    }

    public Cat(){

    }
    public String getName() {
        return name;
    }

}
