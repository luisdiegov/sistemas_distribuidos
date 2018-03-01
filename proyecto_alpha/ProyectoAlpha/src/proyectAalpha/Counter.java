/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

/**
 *
 * @author user
 * This class allows communication between TCP threads
 */
public class Counter {
    private int[] counter = new int[100]; //score counter for each client
    private boolean[] roundWon = new boolean[1000]; //registers if someone has won that round
    private static final int POINTS_TO_WIN = 3;

    public Counter() {
        
    }  

//    public int[] getCounter() {
//        return counter;
//    }

    public void setCounter(int[] counter) {
        this.counter = counter;
    }
    
    private void reset(){
        for(int i = 0; i<counter.length ; i++){
            counter[i] = 0;
        }
        
        for(int i = 0; i<roundWon.length; i++){
            roundWon[i] = false;
        }
    }
    
    //Verifies if someone has won that round
    public synchronized boolean verifyWinner(int client, int round){
        boolean res = false;
        if(roundWon[round] == false){ //If no one has one the round, increase score
//            System.out.println("CLIENTE " + client);            
            res=true;
            roundWon[round] = res;
//           System.out.println("Cliente antes" + counter[client]);
            counter[client]++;
//            System.out.println("Cliente despues" + counter[client]);
            
        }
        return res;
    }
    
    //Check if a client won 
    public synchronized boolean hasWon(int client){
//        System.out.println("counter sdfdf " + counter[client]);
        boolean bool = counter[client] >= POINTS_TO_WIN;
//        System.out.println("    BBOOOOOOOL" + bool);
        return bool;
    }

    public boolean[] getRoundWon() {
        return roundWon;
    }

    public void setRound(boolean[] round) {
        this.roundWon = round;
    }


    
    
    
}
