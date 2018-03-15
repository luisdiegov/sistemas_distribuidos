package proyectAalpha;

public class stressMoleGrid {

    /**
     * Creates new form NewJFrame
     */
    private static int UNIQUE_ID = 0;
    private int clientId;
    private int roundNo;
    private int answer;
    private Won won = new Won();

    public stressMoleGrid(int i) {
        clientId = i;
    }

    public void resetGrid() {

    }

    public void newGame() {
    }

    public void sendMessage(boolean b) {

        String message;
        TCPClientThread tcpct = new TCPClientThread(won);

        message = clientId + " " + roundNo + " " + b;//client_id, round, answer
        tcpct.setMessage(message);
//        System.out.println("se mando click");
//        
        tcpct.start();

        if (won.hasWon()) {
            System.out.println("GANASTE!");
        }
    }

    public void exitGame() {

        String message;
        TCPClientThread tcpct = new TCPClientThread(won);

        message = "END";
        tcpct.setMessage(message);
//        System.out.println("se mando click");
//        
        tcpct.start();

        System.out.println("El cliente [" + clientId + "] salió del juego");

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
    public void selectCell(int cell) {
        boolean b = (answer == (cell));
        System.out.println("Cliente: ["+clientId+"] Atinó: "+b+" Ronda ["+roundNo+"]");
        sendMessage(b);

    }

}
