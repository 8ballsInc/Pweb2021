/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.disi.giarre.game;

/**
 *
 * @author giarr
 */
public class Board {
    Cella[][] griglia;
    
    public Board(){
        griglia = new Cella[9][9];
        for(int i=0; i<9;i++){
            for(int j=0; j<9; j++){
                griglia[i][j]=new Cella();
            }
        }
        bombafra();
        contabombe();
    }
    public void bombafra(){
         for(int i=0; i<9;i++){
            for(int j=0; j<9; j++){
                if(i==j){
                    griglia[i][j].value=-1;
                }
            }
        }
    }
    public void contabombe(){
        for(int i=0; i<9;i++){
            for(int j=0; j<9; j++){
                conta(i,j);
            }
        }
    }
    public void conta(int i, int j){
        
        Cella c = griglia[i][j];
        if(c.value==-1){return;}
        if(i>0){if(griglia[i-1][j].value == -1) c.value++;}
        if(i<8){if(griglia[i+1][j].value == -1) c.value++;}
        if(j>0){if(griglia[i][j-1].value == -1) c.value++;}
        if(j<8){if(griglia[i][j+1].value == -1) c.value++;}
        if(i>0 && j<8){if(griglia[i-1][j+1].value == -1) c.value++;}
        if(i<8 && j<8){if(griglia[i+1][j+1].value == -1) c.value++;}
        if(i>0 && j>0){if(griglia[i-1][j-1].value == -1) c.value++;}
        if(i<8 && j>0){if(griglia[i+1][j-1].value == -1) c.value++;}
        
        
    }
}
