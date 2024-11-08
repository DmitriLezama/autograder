package com.gophers;

import com.gophers.services.helpers.GradingFacade;
import com.gophers.interfaces.Facade;

public class App {
    public static void main(String[] args) {
        Facade facade = new GradingFacade();
        facade.processSubmissions("submissions.zip");
    }
}
