package GameLogic;

import Common.Constants;
import GameStates.BaseState;
import GameStates.MainMenuState;
import Common.Constants;

import javax.swing.*;
import java.util.Stack;

import static Common.Constants.FRAME_LENGTH;

public class PacmanGUI extends JFrame  {
    private Stack<BaseState> stateStack;
    private float time = 0.0f;



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

        gameLoop();
    }

    public void gameLoop() {

        while (this.isVisible()) {

            new Timer(FRAME_LENGTH, e -> {

            BaseState curState = stateStack.peek();
            curState.processInput();
            curState.update();
            curState.draw();
            });
        }

    }


    public void changeState(BaseState newState) {
        removeState();
        addState(newState);
    }

    public void addState(BaseState newState) {
        stateStack.push(newState);
    }

    public void removeState() {
        stateStack.pop();
    }


    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
