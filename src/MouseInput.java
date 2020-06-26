package com.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter implements MouseListener {

    public Handler handler;
    public MouseInput(Handler handler){
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        Point p = e.getPoint();
        System.out.println("myszka: ");
        if(e.getButton() == MouseEvent.BUTTON1)
            handler.object.get(p.x/10 + (p.y/10)*108).alive = true;
    }

}
