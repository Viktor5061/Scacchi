/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scacchi;

import java.awt.event.ActionEvent;

/**
 *
 * @author Viktor
 */
public class Torre extends Pedina{
    private int x,y;
    private boolean dead;
    private int colore;
    
    public Torre(int x,int y,int colore){
        super(x,y,colore);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  

 
}
