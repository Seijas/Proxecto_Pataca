package proxectopataca05;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @version v0.5
 * 
 * @author Daniel Seijas
 * @author Micael Remi
 */
public class Game extends JPanel implements KeyListener, Runnable {
    
    private final Random rnd = new Random();
    
    Config data;
    
    private final Thread thread;
    
    private final int widthPanel;
    private final int heightPanel;
    
    private final int widthScore;
    private final int heightScore;
    
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = true;
    
    private Sachador manolo;
    private final ArrayList <Sachador> sachador;
    
    private int xCoord;
    private int yCoord;
    private int sizeManolo;
    
    private Pataca potato;
    private final ArrayList <Pataca> patacas;
    
    private boolean running = false;
    private final int speed;
    private final int tileSize;
    private int patacaEat = 0;
    
    public Game(Config data){
        setFocusable(true);
        addKeyListener(this);
        
        this.data = data;
        tileSize = data.getTileSize();
        widthPanel = data.getWidth();
        heightPanel = data.getHeight();
        xCoord = data.getxSachador();
        yCoord = data.getySachador();
        sizeManolo = data.getSizeSachador();
        speed = data.getSpeed();
        
        widthScore = data.getWidthtScore();
        heightScore = data.getHeightScore();
        
        //scor = new JLabel("Score: 0");
        //scor.setForeground(Color.BLACK);
        //add(scor).setBounds(5, widthPanel, widthScore, heightScore);
        
        setPreferredSize(new Dimension(widthPanel, heightPanel+heightScore));
        
        
        patacas = new ArrayList<>();
        sachador = new ArrayList<>();
        
        running = true;
        thread = new Thread(this, "Game loop");
        thread.start();
    }
    
    @Override
    public void run() {
        
        manolo = new Sachador(xCoord-2, yCoord);
        sachador.add(manolo);
        manolo = new Sachador(xCoord-1, yCoord);
        sachador.add(manolo);
        manolo = new Sachador(xCoord, yCoord);
        sachador.add(manolo);
        
        while(running){
            
            /**
             * Generate Potato if ArrayList is empty
             */
            if(patacas.isEmpty()){
                boolean valide = false;
                
                int xPataca = 0;
                int yPataca = 0;
                
                do{
                    xPataca = rnd.nextInt((widthPanel/tileSize)-1);
                    yPataca = rnd.nextInt((heightPanel/tileSize)-1);
                    
                    valide = false;
                    for(int i=0; i<sachador.size(); i++){
                        if(xPataca == sachador.get(i).getxCoor() && yPataca == sachador.get(i).getyCoor()){
                            valide = true;
                        }
                    }
                }while(valide);
                
                potato = new Pataca(xPataca, yPataca);
                patacas.add(potato);
            }
            
            /**
             * Comprobate if manolo eat de potato and plus a potatoEat
             */
            for(int i=0; i<patacas.size(); i++){
                if(manolo.getxCoor() == patacas.get(i).getxCoor() && 
                        manolo.getyCoor() == patacas.get(i).getyCoor()){
                    //sizeManolo++;
                    patacas.remove(i);
                    i--;
                    patacaEat++;
                    manolo.setPataca(true);
                }
            }
            
            /**
             * running stop y manolo cross him back
             */
            for(int i=0; i<sachador.size(); i++){
                if(xCoord == sachador.get(i).getxCoor() && 
                        yCoord == sachador.get(i).getyCoor()){
                    if(i != sachador.size() - 1){
                        stop();
                    }
                }
            }
            
            /**
             * movements depends of key pressed
             */
            if(right){
                xCoord++;
            }
            if(left){
                xCoord--;
            }
            if(up){
                yCoord--;
            }
            if(down){
                yCoord++;
            }
            
            
            /**
             * generate new manolo in the actualized coords
             */
            manolo = new Sachador(xCoord, yCoord);
            sachador.add(manolo);
            
            
            /**
             * if manolo no eat a potato, lose him last part
             */
            //if(sachador.size() > sizeManolo){
            //    sachador.remove(0);
            //}
            if(sachador.get(0).isPataca()){
                sizeManolo++;
                sachador.get(0).setPataca(false);
            }else{
                sachador.remove(0);
            }
            
            
            /**
             * if manolo cross the limits, runnig stop
             * else repaint the new positions
             */
            if(xCoord < 0 || xCoord > data.getWidth()/tileSize-1 || 
                    yCoord < 0 || yCoord > data.getHeight()/tileSize-1){
                stop();
            }else{
                
                repaint();
            }
            
            try{
                thread.sleep(speed);
            }catch(InterruptedException e){
                
            }
        }
    }
    
    
    Image img;
    
    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, widthPanel, heightPanel);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, widthPanel, heightPanel);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, widthPanel, widthScore, heightScore);
        
        g.setColor(Color.WHITE);
        g.drawString("Score: " + patacaEat * 150, 5, heightPanel + 17);
        
        for (Sachador sachador1 : sachador) {
            if(!sachador1.isPataca()){
                g.setColor(Color.BLACK);
                g.fillRect(sachador1.getxCoor()*tileSize+1, sachador1.getyCoor()*tileSize+1, 8, 8);
            }
            if(sachador1.isPataca()){
                g.setColor(Color.BLACK);
                g.fillRect(sachador1.getxCoor()*tileSize, sachador1.getyCoor()*tileSize, 10, 10);
            }
        }
        
        for (Pataca pataca : patacas) {
            
            g.setColor(Color.YELLOW);
            g.fillRect(pataca.getxCoor()*10, pataca.getyCoor()*10, 10, 10);
            
            //img = getToolkit().getImage("images/prueba.jpg");
            //g.drawImage(img, pataca.getxCoor()*10, pataca.getyCoor()*10, 10, 10, this);
            
        }
    }
    
    public void stop(){
        
        running = false;
        
        data.setScore(patacaEat * 150);
        
        ScoreOne dialog = new ScoreOne(new javax.swing.JFrame(), true, data);
        addKeyListener(dialog);
        dialog.setVisible(true);
        
        repaint();
        
        try{
            thread.join();
        }catch(InterruptedException e){
            System.out.println("error al parrar el hilo");
        }
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        if ( (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT ) && !left) {
            up = false;
            down = false;
            right = true;
        }

        if ( (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT ) && !right) {
            up = false;
            down = false;
            left = true;
        }

        if ( (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP ) && !down) {
            left = false;
            right = false;
            up = true;
        }

        if ( (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN ) && !up) {
            left = false;
            right = false;
            down = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    
}
