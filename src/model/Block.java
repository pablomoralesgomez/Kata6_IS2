package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Pablo
 */
public class Block {
    private int x;
    private int y;
    public static final int MAX = 7;
    private Timer timer;
    private List<Observer> observers;
    
    public Block() {
        this.x = 3;
        this.y = 3;
        this.timer = new Timer();
        this.timer.schedule(task(), 1000, 500);
        observers = new ArrayList<>();
    }   
    
    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }
    
    public void left() {
        if (x == 1) return;
        x--;
        changed();
    }
    
    public void right() {
        if (x == MAX) return;
        x++;
        changed();
    }
    
    public void up() {
        if (y == MAX) return;
        y++;
        changed();
    }
    
    public void down() {
        if (y == 1) return;
        y--;
        changed();
    }

    private TimerTask task() {
        return new TimerTask(){
            @Override
            public void run(){
                
                double r = Math.random();
                if (r >= 0.20) return;
                
                if (r >= 0.15) left();
                else if (r >= 0.10) right();
                else if (r >= 0.05) up();
                else down();
            }
        };
    }
    
    private void changed() {
        for (Observer observer : observers) 
            observer.changed();
    }
    
    public void register(Observer observer) {
        observers.add(observer);
    }
    
    public interface Observer {
        void changed();
    }
}
