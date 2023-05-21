import java.lang.Math;
import java.util.Random;

public class Position {
    private int x, y;
    Position(){
        Random rand = new Random();
        this.x = rand.nextInt(50);
        this.y = rand.nextInt(50);
    }
    Position (int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int manhattanDist(Position p){
        return Math.abs(this.x - p.getX()) +  Math.abs(this.y - p.getY());
    }
}
