import java.awt.*;
import java.util.Random;

public abstract class Asker {
    private String name;
    private boolean isAlive;
    private char team;
    private int health;
    private Point pos;
    private Point pre_pos;
    private Point next_pos;
    protected static Random rnd = new Random();
    protected double rnd_number;
    protected int rnd_damage;
    private boolean hasMoved;

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

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected char getTeam() {
        return team;
    }

    protected void setTeam(char team) {
        this.team = team;
    }

    protected Point getPos() {
        return pos;
    }

    protected void setPos(Point pos) {
        this.pos = pos;
    }

    protected Point getPre_pos() {
        return pre_pos;
    }

    protected Point getNext_pos() {
        return next_pos;
    }

    protected void setNext_pos(Point next_pos) {
        this.next_pos = next_pos;
    }

    protected boolean getHasMoved() {
        return hasMoved;
    }

    protected void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
