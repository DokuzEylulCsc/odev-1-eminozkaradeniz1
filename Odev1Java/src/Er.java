import java.awt.*;

public class Er extends Asker {
    @Override
    public void move() {
        super.move();
        boolean b;

        if(rnd_number < 0.40){
            b = rnd.nextBoolean();

            if(b){ //yukarı hareket
                setNext_pos(new Point(getPre_pos().x - 1, getPre_pos().y));
                if(getNext_pos().x == -1){
                    setNext_pos(new Point(getPre_pos().x,getPre_pos().y + 1));
                }
                setHasMoved(Meydan.changePosition(this, getPre_pos(), getNext_pos()));
            } else { // aşağı hareket
                setNext_pos(new Point(getPre_pos().x + 1, getPre_pos().y));
                if(getNext_pos().x == 16){
                    setNext_pos(new Point(getPre_pos().x,getPre_pos().y - 1));
                }
                setHasMoved(Meydan.changePosition(this, getPre_pos(), getNext_pos()));
            }

            if(getHasMoved()){
                System.out.println(this.getName() + " in Team " + this.getTeam() +
                        " has moved from " + "(" + getPre_pos().x + "," + getPre_pos().y +
                        ") to (" + getPos().x + "," + getPos().y + ")");
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
            System.out.println(this.getName() + " in Team " + this.getTeam() +
                    " (" + this.getPos().x + "," + this.getPos().y +
                    ") has opened fire to " + target.getName() +
                    " in Team " + target.getTeam() + " (" + target.getPos().x +
                    "," + target.getPos().y + ").");
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
            System.out.println(this.getName() + " in Team " + this.getTeam() +
                    " (" + this.getPos().x + "," + this.getPos().y +
                    ") couldn't find a target.");
        }
    }
}
