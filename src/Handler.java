package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class Handler {

        public LinkedList<GameObject> object = new LinkedList<GameObject>();
        private long ns = System.currentTimeMillis();
        private long lasttime = 0;
        public boolean run = true;

        public void tick() {
            if(run){
                 if (lasttime > 30) {
                    lasttime = 0;
                    for (int i = 0; i < object.size(); i++) {
                        object.get(i).tick();
                    }
                } else lasttime++;
        }
        }

        public void render(Graphics g){
            for(GameObject a: object){
                a.render(g);
            }
        }

        public void addObject(GameObject object){
            this.object.add(object);
        }

        public void removeObject(GameObject object){
            this.object.remove(object);
        }

}

