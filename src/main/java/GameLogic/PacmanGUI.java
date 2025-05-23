package GameLogic;

import Common.Constants;
import GameStates.BaseState;
import GameStates.MainMenuState;

import javax.swing.*;
import java.util.Stack;

public class PacmanGUI extends JFrame  {
    private Stack<BaseState> stateStack;


    public PacmanGUI() {
        super("Pacman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(Constants.WINDOW_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Start from the main menu
        stateStack = new Stack<BaseState>();
        stateStack.push(new MainMenuState());
    }





}
