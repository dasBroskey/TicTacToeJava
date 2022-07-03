import java.util.*;
import java.io.*;

public class tictactoe {

    public static void main(String args[]){
        save(new_board());
        String instructions = "To play Tic-Tac-Toe choose the square you want to play in by typing the number on the square when prompted. To save the game and quit type 'q'";
        System.out.println(instructions);
        List<String> board = readGame();
        displayGrid(board);
        while (!is_winner(board)){
            int space = get_square(board);
            board = update_board(board, space);
            displayGrid(board);
        }
    }
    public static List new_board(){
        //Creating a List of "blanks" for empty board
        List<String> newBoard = new ArrayList<String>();
        for (int i=0;i<9;i++){
            newBoard.add("BLANK");
        }
        return newBoard;
    }
    public void displayGrid(List board){
        for (int i=1;i<10;i++){
            if (i%3 != 0){
                if (!(board.get(i-1).equals("BLANK"))){
                    System.out.print(" "+ board.get(i-1)+ " |");
                }else{
                    System.out.print(" "+ i +" |");
                }
            }
            else if (i==8){
                if (!(board.get(i-1).equals("BLANK"))){
                    System.out.print(" "+ board.get(i-1));
                }else{
                    System.out.print(" "+ i);
                }
            }
            else{
                if (!(board.get(i-1).equals("BLANK"))){
                    System.out.println(" "+ board.get(i-1));
                    System.out.println("---+---+---");
                }else{
                    System.out.println(" "+ i );
                    System.out.println("---+---+---");
                }
            }
        }
    }
    public  boolean is_winner(List board){
        //Check rows
        for (int r=0;r<3;r++){
            if (!(board.get(r*3).equals("BLANK")) && ((board.get((r*3)+1).equals(board.get((r*3)))) && (board.get((r*3)+2).equals(board.get((r*3)))))){
                System.out.println("The game was won by "+board.get(r*3)+"'s player!row");
                return true;
            }
        }
        //Check columns
        for (int c=0;c<3;c++){
            if (!(board.get(c).equals("BLANK")) && ((board.get(c+3).equals(board.get((c)))) && (board.get(c+6).equals(board.get(c))))){
                System.out.println("The game was won by "+board.get(c)+"'s player!column");
                return true;
            }
        }
        //check diagonals
        if (!(board.get(4).equals("BLANK")) && (( (board.get(0).equals(board.get(4)))&& board.get(0).equals(board.get(8)) ) || (board.get(2).equals(board.get(4)) && board.get(4).equals(board.get(6))))){
            System.out.println("The game was won by "+board.get(4)+"'s player!diag");
            return true;
        }
        return false;
    }
    public String is_turn(List board){
        if (Collections.frequency(board,"BLANK")%2 == 0){
            return "O";
        }else{
            return "X";
        }
    }
    public boolean is_blank(List board,int square){
        if (board.get(square).equals("BLANK")){
            return true;
        }else{
            return false;
        }
    }
    public void save(List board){
        try{
            FileOutputStream writeData = new FileOutputStream("tictactoe.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        
            writeStream.writeObject(board);
            writeStream.flush();
            writeStream.close();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List readGame(){
        try{
            FileInputStream readData = new FileInputStream("tictactoe.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
        
            //ArrayList<String> board2 = new ArrayList<String>();
            List<String> board = (List<String>) readStream.readObject();
            readStream.close();
            //System.out.println(board.toString());
            //System.out.println(board.getClass().getSimpleName());
            return board;
            
        }catch (Exception e) {
            e.printStackTrace();
            return new_board();
        }
         
    }
    public int get_square(List board){
        Scanner listen = new Scanner(System.in);
        System.out.println("Which square will you place "+is_turn(board)+" in? ");
        int place = listen.nextInt();
        return (place-1);
    }
    public List<String> update_board(List board, int square){
        if (is_blank(board,square)){
            board.set(square,is_turn(board));
        }
        return board;
    }
    public static boolean saving(int keys){
        if (keys == 0){
            return false;
        }else{
            return true;
        }
    }
    public void keyReleased(int VK_ACCEPT){

    }
}
//Change scanner to nexline and return string then change later in cokde before used