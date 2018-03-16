package proyectAalpha;

import java.io.BufferedWriter;
import java.io.IOException;

public class stressMoleGrid {

    /**
     * Creates new form NewJFrame
     */
    public BufferedWriter bufferedWriter;
    private static int UNIQUE_ID = 0;
    private int clientId;
    private int roundNo;
    private int answer;
    private Won won = new Won();

    public stressMoleGrid(int i,BufferedWriter bufferedWriter) {
        clientId = i;
        this.bufferedWriter=bufferedWriter;
    }

    public void resetGrid() {

    }

    public void newGame() {
    }

    public void sendMessage(boolean b) throws IOException {

        String message;
        TCPClientThread tcpct = new TCPClientThread(won,bufferedWriter);

        message = clientId + " " + roundNo + " " + b;//client_id, round, answer
        tcpct.setMessage(message);
//        //System.out.println("se mando click");
//        
        tcpct.start();

        if (won.hasWon()) {
            //System.out.println("GANASTE!");
        }
    }

    public void exitGame() throws IOException {

        String message;
        TCPClientThread tcpct = new TCPClientThread(won,bufferedWriter);

        message = "END";
        tcpct.setMessage(message);
//        //System.out.println("se mando click");
//        
        tcpct.start();

        //System.out.println("El cliente [" + clientId + "] salió del juego");

    }

    public int getId() {
        return clientId;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    //simulate a click
    public void selectCell(int cell) throws IOException {
        boolean b = (answer == (cell));
        //System.out.println("Cliente: ["+clientId+"] Atinó: "+b+" Ronda ["+roundNo+"]");
        sendMessage(b);

    }

}
