package com.company;

import java.awt.*;

public class Cell extends GameObject{

    private Handler handler;
    public static int howManyCells = 0;
    private int index;

    public Cell(int x,int y,Handler handler){
        super(x,y);
        this.handler = handler;
        alive = false;
        pTick = false;
        index = howManyCells;
        howManyCells++;
    }

    public Cell(int x, int y, Handler handler, boolean alive){
        this(x,y,handler);
        this.alive = alive;
        this.pTick = true;
    }

    private int howManyNeighbours(){
        int howMany = 0;
        if(index - 108 >= 0)
            if(handler.object.get(index - 108).pTick){
                howMany++;
            }
        if(index - 108 - 1 >= 0 )
            if(handler.object.get(index-Game.WIDTH/10-1).pTick){
                howMany++;
            }
        if(index - 108 + 1 >= 0 )
            if(handler.object.get(index - Game.WIDTH/10 + 1).pTick){
                howMany++;
            }
        if(index % 108 != 0)
            if(handler.object.get(index-1).pTick){
                howMany++;
            }
        if(index % (108 - 1) != 0 && (index + 1) < 8748)
            if(handler.object.get(index + 1).alive){
                howMany++;
            }
        if(index + 108 < 8748 )
            if(handler.object.get(index + Game.WIDTH/10).alive){
                howMany++;
            }
        if(index + 108 - 1 < 8748 )
            if(handler.object.get(index + Game.WIDTH/10 - 1).alive){
                howMany++;
            }
        if(index + 108 + 1 < 8748 )
            if(handler.object.get(index + 109).alive){
                howMany++;
            }
    return howMany;
    }

    public void tick() {
        int howMany = howManyNeighbours();

        pTick = alive;

        if(alive == false && howMany == 3){
            alive = true;;
        }

        if(alive == true && (howMany == 3 || howMany == 2)){
            alive = true;
        }else if(alive == true && (howMany>3 || howMany<2)){
            alive = false;
        }


    }

    public void render(Graphics g) {
        if(alive){
            g.setColor(Color.GREEN);
            g.fillRect(x,y,10,10);
        }

    }
}
