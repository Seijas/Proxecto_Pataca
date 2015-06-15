package proxectopataca05;

/**
 * @version v0.5
 * 
 * @author Daniel Seijas
 * @author Micael Remi
 */
public class Config {
    
    private int speed = 100;
    
    private int width = 500, height = 500;

    private int xSachador = 10, ySachador = 10;
    private int tileSize = 10;
    private int sizeSachador = 3;
    
    private int heightScore = 25;
    private int widthtScore = width;

    private String dificulty = "medium";
    private int intDificulty = 1;

    public int getIntDificulty() {
        return intDificulty;
    }

    public void setIntDificulty(int intDificulty) {
        this.intDificulty = intDificulty;
    }
    
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }
    
    public int getHeightScore() {
        return heightScore;
    }

    public void setHeightScore(int heightScore) {
        this.heightScore = heightScore;
    }

    public int getWidthtScore() {
        return widthtScore;
    }

    public void setWidthtScore(int widthtScore) {
        this.widthtScore = widthtScore;
    }
    
    public int getSizeSachador() {
        return sizeSachador;
    }

    public void setSizeSachador(int sizeSachador) {
        this.sizeSachador = sizeSachador;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getxSachador() {
        return xSachador;
    }

    public void setxSachador(int xSachador) {
        this.xSachador = xSachador;
    }

    public int getySachador() {
        return ySachador;
    }

    public void setySachador(int ySachador) {
        this.ySachador = ySachador;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
