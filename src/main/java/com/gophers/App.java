package com.gophers;

import com.gophers.interfaces.Facade;
import com.gophers.services.handlers.GradingFacade;
import com.gophers.utilities.Constants;

public class App {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Facade facade = new GradingFacade();
        facade.processSubmissions(Constants.SUBMISSIONS);
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1_000_000;
        System.out.println("Execution Time " + elapsedTime + " ms");
    }
}
