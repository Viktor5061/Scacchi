/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scacchi;

/**
 *
 * @author Viktor
 */
public abstract class Pedina {
    private int x;
    private int y;
    private boolean dead;
    
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
    
    public void setDead(boolean b){
        dead=b;
    }
}
