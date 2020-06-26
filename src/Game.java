package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private Thread thread;
    private boolean running = false;
    public Handler handler;
    public static final int WIDTH = 1080, HEIGHT = WIDTH/4 * 3;
    private Random random;

    public Game(){
        handler = new Handler();
        this.addMouseListener(new MouseInput(handler));
        this.addKeyListener(new KeyboardInput(handler));

        new Window(WIDTH, HEIGHT, "Conwey's Life", this);

        for(int i=0 ;i<HEIGHT/10; i++){
            for(int j=0;j<WIDTH/10; j++){
                Cell temp = new Cell(j*10,i*10,handler);
                handler.addObject(temp);
            }
        }

        handler.object.get(108+3).alive = true;
        handler.object.get(108*2+3).alive = true;
        handler.object.get(108*3+3).alive = true;
        for(int i=0 ; i<20;i++) {
            handler.object.get(108*4+i).alive = true;
        }

    }

    public static void main(String[] args) {
        new Game();
    }


    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis()-timer> 1000){
                timer +=1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void tick(){
        handler.tick();
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

}
