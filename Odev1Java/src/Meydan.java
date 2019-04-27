import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Meydan {

    private static Asker Alan[][] = new Asker[16][16];
    private static List<Asker> TeamA = new ArrayList<Asker>();
    private static List<Asker> TeamB = new ArrayList<Asker>();
    private static Random random = new Random();

    private static boolean isOutOfBounds(Point pos){
        if(pos.x < 0 || pos.x > 15 || pos.y < 0 || pos.y > 15){
            return true;
        }
        return false;
    }

    private static boolean isFree(Point pos){
        if(Alan[pos.x][pos.y] == null){
            return true;
        }
        return false;
    }

    protected static boolean changePosition(Asker asker,Point pre_pos,Point new_pos){

        if(isOutOfBounds(new_pos) == false && isFree(new_pos) == true){
            Asker temp = Alan[pre_pos.x][pre_pos.y];
            Alan[new_pos.x][new_pos.y] = temp;
            Alan[pre_pos.x][pre_pos.y] = null;
            asker.setPos(new_pos);
            return true;
        }
        else {
            return false;
        }
    }

    private List<Asker> setTeams(List<Asker> Team){
        int rnd_number;

        rnd_number = random.nextInt(2);
        if(rnd_number == 1){
            Team.add(new Yuzbasi());
            Team.get(0).setName("yuzbasi1");
        }
        rnd_number = random.nextInt(2) + 1;

        if(Team.isEmpty()){
            for(int i = 0; i < rnd_number; i++){
                Team.add(new Tegmen());
                Team.get(i).setName("tegmen" + (i+1));
            }
        }else{
            for(int i = 0; i < rnd_number; i++){
                Team.add(new Tegmen());
                Team.get(i+1).setName("tegmen" + (i+1));
            }
        }

        int n = 7 - Team.size();
        int size = Team.size();

        for(int i = 0; i < n; i++){
            Team.add(new Er());
            Team.get(i + size).setName("er" + (i+1));
        }

        return Team;
    }

    private void setTeamNames(){
        for (Asker a:TeamA
             ) {
            a.setTeam('A');
        }
        for (Asker a:TeamB
        ) {
            a.setTeam('B');
        }
    }

    private void placeTeams(){
        int rndX, rndY, i = 0;
        while (i < 7){
            rndX = random.nextInt(5);
            rndY = random.nextInt(5);
            if(Alan[rndX][rndY] == null){
                Alan[rndX][rndY] = TeamA.get(i);
                TeamA.get(i).setPos(new Point(rndX,rndY));
                i++;
            }else {
                continue;
            }
        }
        i = 0;
        while (i < 7){
            rndX = random.nextInt(5) + 11;
            rndY = random.nextInt(5) + 11;
            if(Alan[rndX][rndY] == null){
                Alan[rndX][rndY] = TeamB.get(i);
                TeamB.get(i).setPos(new Point(rndX,rndY));
                i++;
            }else {
                continue;
            }
        }
    }

    protected static void kill(Asker target){
        Alan[target.getPos().x][target.getPos().y] = null;
        if(target.getTeam() == 'A'){
            TeamA.remove(target);
        }else {
            TeamB.remove(target);
        }
    }

    protected static Asker checkAround(Asker asker){
        Asker target;
        Point p;
        int n = 0;
        int s = 0;
        if(asker.getClass() == Er.class){
            p = new Point(asker.getPos().x-1,asker.getPos().y-1);
            n = 3;
            s = 1;
        } else if(asker.getClass() == Tegmen.class){
            p = new Point(asker.getPos().x-2,asker.getPos().y-2);
            n = 5;
            s = 2;
        }else {
            p = new Point(asker.getPos().x-3,asker.getPos().y-3);
            n = 7;
            s = 3;
        }

        for(int i = 0; i < n; i++){

            for(int j = 0; j < n; j++){
                if(isOutOfBounds(p) || Alan[p.x][p.y] == null){
                    p.y++;
                    continue;
                }
                else{
                    target = Alan[p.x][p.y];
                    if(target.getTeam() != asker.getTeam()){
                        return target;
                    }else {
                        p.y++;
                        continue;
                    }
                }
            }
            p.x++;
            p.y = asker.getPos().y - s;
        }
        return null;

    }

    private static void printTeams(){
        System.out.println("TeamA");
        for(int i=0;i<7;i++){
            System.out.println(TeamA.get(i).getName());
        }
        System.out.println();
        System.out.println("TeamB");
        for(int i=0;i<7;i++){

            System.out.println(TeamB.get(i).getName());
        }
        System.out.println();
    }

    protected void start(){

        setTeams(TeamA);
        setTeams(TeamB);
        setTeamNames();
        placeTeams();
        play();
    }

    private void play(){

        int rnd_number;
        boolean exit = false;
        printTeams();


        for(int i = 0; exit == false; i++){
            System.out.println("Round " + (i+1));
            if(i%2 == 0){
                rnd_number = random.nextInt(TeamA.size());
                TeamA.get(rnd_number).move();
            }else {
                rnd_number = random.nextInt(TeamB.size());
                TeamB.get(rnd_number).move();
            }

            if(TeamA.size() < 1){
                System.out.println("TeamB won!");
                exit = true;
            }else if(TeamB.size() < 1){
                System.out.println("TeamA won!");
                exit = true;
            }
            System.out.println();
        }
    }

}
