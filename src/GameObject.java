package com.company;

import java.awt.*;

public abstract class GameObject {

    protected int x, y;
    public boolean alive;
    public boolean pTick;
    public abstract void tick();
    public abstract void render(Graphics g);

    public GameObject(int x,int y){
        this.x = x;
        this.y = y;
    }

}
