/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

/**
 *
 * @author user
 */
public class Counter {
    private int[] counter = new int[100];
    private boolean[] roundWon = new boolean[1000];
    private static final int POINTS_TO_WIN = 5;

    public Counter() {
        
    }  

    public int[] getCounter() {
        return counter;
    }

    public void setCounter(int[] counter) {
        this.counter = counter;
    }
    
    public boolean verifyWinner(int client, int round){
        boolean res = false;
        if(roundWon[round] == false){
            res=true;
            roundWon[round] = res;
            counter[client]++;
            
        }
        return res;
    }
    
    public boolean hasWon(int client){
        return counter[client] == POINTS_TO_WIN;
    }

    public boolean[] getRoundWon() {
        return roundWon;
    }

    public void setRound(boolean[] round) {
        this.roundWon = round;
    }


    
    
    
}
