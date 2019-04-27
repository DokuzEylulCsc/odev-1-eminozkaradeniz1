import java.awt.*;

public class Yuzbasi extends Asker{

    @Override
    protected void move() {
        super.move();
        if(rnd_number < 0.40){
            if(rnd_number < 0.10){ //yukarı yürüme
                int y = rnd.nextInt(3) - 1;
                setNext_pos(new Point(getPre_pos().x - 1, getPre_pos().y + y));
                setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
            }
            else if(rnd_number < 0.20){ //aşağı yürüme
                int y = rnd.nextInt(3) - 1;
                setNext_pos(new Point(getPre_pos().x + 1, getPre_pos().y + y));
                setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
            }
            else if(rnd_number < 0.30){ //sola yürüme
                int x = rnd.nextInt(3) - 1;
                setNext_pos(new Point(getPre_pos().x + x, getPre_pos().y - 1));
                setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
            }
            else if (rnd_number < 0.40){ //sağa yürüme
                int x = rnd.nextInt(3) - 1;
                setNext_pos(new Point(getPre_pos().x + x, getPre_pos().y + 1));
                setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
            }

            if(getHasMoved()){
                System.out.println(this.getName() + " in Team " + this.getName() +
                        " has moved from " + "(" + getPre_pos().x + "," + getPre_pos().y +
                        ") to (" + getPos().x + "," + getPos().y + ")");
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
            System.out.println(this.getName() + " in Team " + this.getTeam() +
                    " (" + this.getPos().x + "," + this.getPos().y +
                    ") has opened fire to " + target.getName() +
                    " in Team " + target.getTeam() + " (" + target.getPos().x +
                    "," + target.getPos().y + ").");
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
            System.out.println(this.getName() + " in Team " +
                    this.getTeam() + " (" + this.getPos().x +
                    "," + this.getPos().y +
                    ") couldn't find a target.");
        }
    }
}
