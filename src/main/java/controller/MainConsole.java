package controller;


import model.GameFacade;
import viewConsole.ViewConsole;

import java.util.Map;

public class MainConsole {

    public static void main(String[] args) {
        ViewConsole viewConsole = new ViewConsole(new GameFacade());
        viewConsole.startGame();
    }
}
