package com.gophers;

import com.gophers.interfaces.Facade;
import com.gophers.services.handlers.GradingFacade;
import com.gophers.utilities.Constants;

public class App {
    public static void main(String[] args) {
        Facade facade = new GradingFacade();
        facade.processSubmissions(Constants.SUBMISSIONS);
    }
}
