//vecchia versione della superclasse di tutte le pedine

package scacchi;


import java.awt.event.ActionEvent;
import javax.swing.JLabel;
//import javax.swing.ImageIcon;
/**
 *
 * @author Viktor
 * 
 */
public abstract class Pedina extends JLabel {
    private int x;
    private int y;
    private int colore;      
    private boolean dead;   //true -> pedina distrutta
    private final int BIANCO=1;
    private final int NERO=-1;
    
    public Pedina(int x,int y,int colore){
        this.x=x;
        this.y=y;
        dead=false;
        this.colore=colore;
    }
    
    public int getPosizioneX(){
        return x;
    }
    
    public int getPosizioneY(){
        return y;
    }
    
    public boolean isDead(){
        return dead;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public void setDead(boolean dead){
        this.dead=dead;
    }
    
    public int getColore(){
        return colore;
    }
    
    public void setColore(int color){
        colore=color;
    }
    
    public abstract void actionPerformed(ActionEvent e);
}
