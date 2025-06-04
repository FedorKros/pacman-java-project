package game_logic;

import common.Constants;
import game_states.BaseState;
import game_states.MainMenuState;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

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
        gameThread();
    }


    public void gameThread() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(Constants.GAME_FRAME_LENGTH);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (!stateStack.isEmpty()) {
                    BaseState curState = stateStack.peek();
                    curState.repaint();
                }
            }
        }).start();
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
