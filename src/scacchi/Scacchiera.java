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
public class Scacchiera {
    public final int BIANCO=1;
    public final int NERO=-1;
    private Torre[] torri=new Torre[4];
    private Cavallo[] cavalli=new Cavallo[4];
    private Alfiere[] alfieri= new Alfiere[4];
    private Regina[] regine=new Regina[2];
    private Re[] re=new Re[2];
    private Pedone[] pedoni=new Pedone[16];
    
    public void initComponents(){
        initTorri();
        initCavalli();
        initAlfieri();
        initRegine();
        initRe();
        initPedoni();
    }
    
    public void initTorri(){
        torri[0]=new Torre(1,1,NERO);
        torri[1]=new Torre(8,1,NERO);
        torri[2]=new Torre(1,8,BIANCO);
        torri[3]=new Torre(8,8,BIANCO);
    }
    
    public void initCavalli(){
        cavalli[0]=new Cavallo(2,1,NERO);
        cavalli[1]=new Cavallo(7,1,NERO);
        cavalli[2]=new Cavallo(2,8,BIANCO);
        cavalli[3]=new Cavallo(7,8,BIANCO);
    }
    
    public void initAlfieri(){
        alfieri[0]=new Alfiere(3,1,NERO);
        alfieri[1]=new Alfiere(6,1,NERO);
        alfieri[2]=new Alfiere(3,8,BIANCO);
        alfieri[3]=new Alfiere(6,8,BIANCO);
    }
    
    public void initRegine(){
        regine[0]=new Regina(4,1,NERO);
        regine[1]=new Regina(4,8,BIANCO);
    }
    
    public void initRe(){
        re[0]=new Re(5,1,NERO);
        re[1]=new Re(5,8,BIANCO);
    }
    
    public void initPedoni(){
        for(int i=1;i<=8;i++){
            pedoni[i-1]=new Pedone(i,2,NERO);
            pedoni[i-1+8]=new Pedone(i,7,BIANCO);
        }
    }
}
