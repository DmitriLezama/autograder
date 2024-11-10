package com.gophers;

import com.gophers.interfaces.Facade;
import com.gophers.services.handlers.GradingFacade;

public class App {
    public static void main(String[] args) {
        Facade facade = new GradingFacade();
        facade.processSubmissions("submissions.zip");
    }
}
