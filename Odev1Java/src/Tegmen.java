import java.awt.*;

public class Tegmen extends Asker{

    @Override
    protected void move() {
        super.move();
        double random_move;

        if(rnd_number < 0.55){
            if(rnd_number < 0.25){ //yatay hareket
                random_move = rnd.nextDouble();
                if(random_move < 0.50){
                    setNext_pos(new Point(getPre_pos().x, getPre_pos().y + 1));
                    setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
                } else {
                    setNext_pos(new Point(getPre_pos().x, getPre_pos().y - 1));
                    setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
                }
            }
            else if(rnd_number < 0.55){ //dikey hareket
                random_move = rnd.nextDouble();
                if(random_move < 0.50){
                    setNext_pos(new Point(getPre_pos().x - 1, getPre_pos().y));
                    setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
                } else {
                    setNext_pos(new Point(getPre_pos().x + 1, getPre_pos().y - 1));
                    setHasMoved(Meydan.changePosition(this,getPre_pos(),getNext_pos()));
                }
            }

            if(getHasMoved()){
                System.out.println(this.getName() + " in Team " + this.getTeam() +
                        " has moved from " + "(" + getPre_pos().x + "," + getPre_pos().y +
                        ") to (" + getPos().x + "," + getPos().y + ")");
            }else {
                await();
            }
        }
        else if(rnd_number < 0.80){
            fire();
        } else{
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
                damage = 10;
            }else if(rnd_damage == 1){
                damage = 20;
            }else{
                damage = 25;
            }
            target.takeDamage(damage);

        }else{
            System.out.println(this.getName() + " in Team " + this.getTeam() +
                    " (" + this.getPos().x + "," + this.getPos().y +
                    ") couldn't find a target.");
        }
    }
}
