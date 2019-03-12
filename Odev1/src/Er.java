import java.awt.*;

public class Er extends Asker {
    @Override
    public void move() {
        super.move();
        boolean b;

        if(rnd_number < 0.40){
            b = rnd.nextBoolean();

            if(b){ //yukarı hareket
                next_pos = new Point(pre_pos.x - 1, pre_pos.y);
                if(next_pos.x == -1){
                    next_pos.x = pre_pos.x;
                    next_pos.y = pre_pos.y + 1;
                }
                hasMoved = Meydan.changePosition(this, pre_pos, next_pos);
            } else { // aşağı hareket
                next_pos = new Point(pre_pos.x + 1, pre_pos.y);
                if(next_pos.x == 16){
                    next_pos.x = pre_pos.x;
                    next_pos.y = pre_pos.y - 1;
                }
                hasMoved = Meydan.changePosition(this, pre_pos, next_pos);
            }

            if(hasMoved == true){
                System.out.println(this.name + " in Team " + this.team +
                        " has moved from " + "(" + pre_pos.x + "," + pre_pos.y +
                        ") to (" + pos.x + "," + pos.y + ")");
            }else {
                await();
            }
        }
        else if(rnd_number < 0.70){ //fire
            fire();
        }
        else {
            await();
        }

    }

    @Override
    public void await() {
        super.await();
    }

    @Override
    public void fire() {
        super.fire();
        Asker target;
        target = Meydan.checkAround(this);

        if(target != null){
            System.out.println(this.name + " in Team " + this.team +
                    " (" + this.pos.x + "," + this.pos.y +
                    ") has opened fire to " + target.name +
                    " in Team " + target.team + " (" + target.pos.x +
                    "," + target.pos.y + ").");
            int damage;
            if(rnd_damage == 0){
                damage = 5;
            }else if(rnd_damage == 1){
                damage = 10;
            }else{
                damage = 15;
            }
            target.takeDamage(damage);
        }else{
            System.out.println(this.name + " in Team " + this.team +
                    " (" + this.pos.x + "," + this.pos.y +
                    ") couldn't find a target.");
        }
    }
}
