package proxectopataca05;

/**
 * @version v0.5
 * 
 * @author Daniel Seijas
 * @author Micael Remi
 */
public class Sachador {
    
    private int xCoor, yCoor;
    private boolean pataca = false;

    public Sachador(int xCoor, int yCoor) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
    
    public boolean isPataca() {
        return pataca;
    }

    public void setPataca(boolean pataca) {
        this.pataca = pataca;
    }
}
