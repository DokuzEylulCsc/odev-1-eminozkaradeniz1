import java.awt.*;

public class Yuzbasi extends Asker{

    @Override
    protected void move() {
        super.move();
        if(rnd_number < 0.40){
            if(rnd_number < 0.10){ //yukarı yürüme
                int y = rnd.nextInt(3) - 1;
                next_pos = new Point(pre_pos.x - 1, pre_pos.y + y);
                hasMoved = Meydan.changePosition(this,pre_pos,next_pos);
            }
            else if(rnd_number < 0.20){ //aşağı yürüme
                int y = rnd.nextInt(3) - 1;
                next_pos = new Point(pre_pos.x + 1, pre_pos.y + y);
                hasMoved = Meydan.changePosition(this,pre_pos,next_pos);
            }
            else if(rnd_number < 0.30){ //sola yürüme
                int x = rnd.nextInt(3) - 1;
                next_pos = new Point(pre_pos.x + x, pre_pos.y - 1);
                hasMoved = Meydan.changePosition(this,pre_pos,next_pos);
            }
            else if (rnd_number < 0.40){ //sağa yürüme
                int x = rnd.nextInt(3) - 1;
                next_pos = new Point(pre_pos.x + x, pre_pos.y + 1);
                hasMoved = Meydan.changePosition(this,pre_pos,next_pos);
            }

            if(hasMoved == true){
                System.out.println(this.name + " in Team " + this.team +
                        " has moved from " + "(" + pre_pos.x + "," + pre_pos.y +
                        ") to (" + pos.x + "," + pos.y + ")");
            }else {
                await();
            }

        }
        else if(rnd_number < 0.80){
            fire();
        }
        else{
            await();
        }

    }

    @Override
    protected void await() {
        super.await();
    }

    @Override
    protected void fire() {
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
                damage = 15;
            }else if(rnd_damage == 1){
                damage = 25;
            }else{
                damage = 40;
            }
            target.takeDamage(damage);

        }else{
            System.out.println(this.name + " in Team " +
                    this.team + " (" + this.pos.x +
                    "," + this.pos.y +
                    ") couldn't find a target.");
        }
    }
}
