import java.awt.*;
import java.util.Random;

public class Asker {
    protected String name;
    protected boolean isAlive;
    protected char team;
    protected int health;
    protected Point pos;
    protected Point pre_pos;
    protected Point next_pos;
    protected static Random rnd = new Random();
    protected boolean hasMoved;
    protected double rnd_number;
    protected int rnd_damage;

    public Asker(){
        health = 100;
        isAlive = true;
    }

    protected void kill(){
        System.out.println(this.name + " in Team " + this.team + " is dead.");
        isAlive = false;
        Meydan.kill(this);
    }

    protected void takeDamage(int damage){
        health -= damage;
        if(health <= 0){
            kill();
        }else {
            System.out.println(this.name + " in Team " + this.team + " (" +
                    this.pos.x + "," + this.pos.y + ") has left " + this.health + "hp.");
        }
    }

    protected void setPos(Point p){
        this.pos = p;
    }

    protected void move(){
        pre_pos = pos;
        rnd_number = rnd.nextDouble();
        hasMoved = false;
        System.out.println(this.name + " in Team " + this.team +
                " (" + this.pos.x + "," + this.pos.y + "):");
    }

    protected void await(){
        System.out.println(this.name + " in Team " + this.team +
                " has stayed in position (" + pos.x + "," + pos.y + ").");
    }

    protected void fire(){
        rnd_damage = rnd.nextInt(3);
    }

}
