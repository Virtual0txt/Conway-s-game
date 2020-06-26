package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput extends KeyAdapter implements KeyListener {
    private Handler handler;

    public KeyboardInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(handler.run) handler.run = false;
            else handler.run = true;
        }
    }
}
