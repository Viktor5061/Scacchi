
package scacchi;

/**
 *
 * @author Viktor
 */
public abstract class Pedina {
    private int x;
    private int y;
    private boolean bianco; //colore pedina -> true=bianco, false=nero
    private boolean dead;   //true -> pedina distrutta
    
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
    
    public boolean isWhite(){
        return colore;
    }
}
