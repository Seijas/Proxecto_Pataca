package proxectopataca05;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * @version v0.5
 * 
 * @author Daniel Seijas
 * @author Micael Remi
 */
public class Windows extends JFrame {

    JMenuBar menu;
    JMenu opGames;
    JMenu opDificulty;
    
    JMenuItem opGame_Start;
    JMenuItem opGame_Restart;
    JMenuItem opGame_Exit;
    
    JCheckBoxMenuItem opDificulty_Easy;
    JCheckBoxMenuItem opDificulty_Medium;
    JCheckBoxMenuItem opDificulty_Hard;
    JCheckBoxMenuItem opDificulty_Veteran;
    
    JPanel panel;
    JPanel panelGame;
    
    Game game;
    
    Config data = new Config();
    
    public Windows(){
        menu = new JMenuBar();
        panel = new JPanel();
        panelGame = new JPanel();
        opGames = new JMenu();
        opDificulty = new JMenu();
        
        opGames.setText("Game");
        opDificulty.setText("Dificulty");
        
        opGame_Start = new JMenuItem("Start");
        opGame_Restart = new JMenuItem("Restart");
        opGame_Exit = new JMenuItem("Exit");
        
        opGame_Start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
        opGame_Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                startGameActionPerformed(evt);
            }
        });
        opGame_Restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0));
        opGame_Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reStartGameActionPerformed(evt);
            }
        });
        opGame_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        opGame_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        
        opGames.add(opGame_Start);
        opGames.add(opGame_Restart);
        opGames.add(opGame_Exit);
        
        opDificulty_Easy = new JCheckBoxMenuItem("Easy");
        opDificulty_Easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                checkMenuDificulty(0, evt);
            }
        });
        opDificulty_Medium = new JCheckBoxMenuItem("Medium");
        opDificulty_Medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                checkMenuDificulty(1, evt);
            }
        });
        opDificulty_Hard = new JCheckBoxMenuItem("Hard");
        opDificulty_Hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                checkMenuDificulty(2, evt);
            }
        });
        opDificulty_Veteran = new JCheckBoxMenuItem("Jaimmie Fucking Lannister (*Seijas*)");
        opDificulty_Veteran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                checkMenuDificulty(3, evt);
            }
        });
        opDificulty_Medium.setSelected(true);
        
        opDificulty.add(opDificulty_Easy);
        opDificulty.add(opDificulty_Medium);
        opDificulty.add(opDificulty_Hard);
        opDificulty.add(opDificulty_Veteran);
        
        menu.add(opGames);
        menu.add(opDificulty);
        
        JLabel label = new JLabel(new ImageIcon(getClass().
                getResource("/images/portada500.jpg")));
        label.setBounds(0, 0, 500, 500);
        panel.add(label);
        add(panel);
        
        setJMenuBar(menu);
        setSize(520, 575);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 100);
        pack();
    }
    
    private void checkMenuDificulty(int type, ActionEvent evt){
        if(type == 0){
            opDificulty_Easy.setSelected(true);
            opDificulty_Medium.setSelected(false);
            opDificulty_Hard.setSelected(false);
            opDificulty_Veteran.setSelected(false);
            
            data.setSpeed(100);
            data.setDificulty("easy");
            data.setIntDificulty(type);
        }else{
            if(type == 1){
                opDificulty_Easy.setSelected(false);
                opDificulty_Medium.setSelected(true);
                opDificulty_Hard.setSelected(false);
                opDificulty_Veteran.setSelected(false);
                
                data.setSpeed(55);
                data.setDificulty("medium");
                data.setIntDificulty(type);
            }else{
                if(type == 2){
                    opDificulty_Easy.setSelected(false);
                    opDificulty_Medium.setSelected(false);
                    opDificulty_Hard.setSelected(true);
                    opDificulty_Veteran.setSelected(false);
                    
                    data.setSpeed(20);
                    data.setDificulty("hard");
                    data.setIntDificulty(type);
                }else{
                    opDificulty_Easy.setSelected(false);
                    opDificulty_Medium.setSelected(false);
                    opDificulty_Hard.setSelected(false);
                    opDificulty_Veteran.setSelected(true);
                    
                    data.setSpeed(15);
                    data.setDificulty("jaimie");
                    data.setIntDificulty(type);
                }
            }
        }
    }
    
    private void startGameActionPerformed(ActionEvent evt){
        
        if(game == null){
            game = new Game(data);
            addKeyListener(game);
            add(game).setBounds(0, 0, 500, 500);
            pack();
        }
        
    }
    private void reStartGameActionPerformed(ActionEvent evt){
        if(game != null){
            data.setRunning(false);
            game.setVisible(false);
            remove(game);
            game = null;
        }
        game = new Game(data);
        data.setRunning(true);
        addKeyListener(game);
        add(game).setBounds(0, 0, 500, 500);
        pack();
    }
    private void exitActionPerformed(ActionEvent evt){
        if(game == null){
            System.exit(0);
        }else{
            data.setRunning(false);
            game.setVisible(false);
            remove(game);
            game = null;
        }
    }
}
