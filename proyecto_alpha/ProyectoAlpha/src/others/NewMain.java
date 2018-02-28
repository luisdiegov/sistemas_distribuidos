/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

/**
 *
 * @author user
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void add1(numero n){
        n.num = 2;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        numero a = new numero();
        a.num = 1;
        add1(a);
        System.out.println(a.num);
        
    }
    
}

class numero{
    public int num;
    
    public numero(){
        
    }
}
