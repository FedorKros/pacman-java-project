package GameLogic;

import Common.Constants;
import GameStates.BaseState;
import GameStates.MainMenuState;


import javax.swing.*;
import java.awt.*;
import java.util.Stack;

import static Common.Constants.FRAME_LENGTH;
import static Common.Constants.WINDOW_SIZE;

public class PacmanGUI extends JFrame  {
    private Stack<BaseState> stateStack;
    private float time = 0.0f;



    public PacmanGUI() {

        super("Pacman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(Constants.WINDOW_SIZE);

        setResizable(false);
        setLayout(new BorderLayout());

        // Start from the main menu
        stateStack = new Stack<BaseState>();
        stateStack.push(new MainMenuState(this));
        add(stateStack.peek(), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        gameLoop();
    }

    public void gameLoop() {
        Timer gameLoop = new Timer(FRAME_LENGTH, e -> {
            if (!stateStack.isEmpty()) {
                BaseState curState = stateStack.peek();
                curState.repaint();
            }
        });
        gameLoop.start();
    }




    public void changeState(BaseState newState) {
        removeState();
        addState(newState);

    }

    public void addState(BaseState newState) {
        stateStack.push(newState);
        add(newState);
        revalidate();
        repaint();
        newState.requestFocus();
    }

    public void removeState() {
        if (!stateStack.isEmpty()) remove(stateStack.pop());
        revalidate();
        repaint();
    }

}
