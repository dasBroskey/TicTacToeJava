import java.util.*;
import java.io.*;

public class titactoe {
    public static void main(String args[]){  

          
 
    }
    public static List<String> new_board(){
                 //Creating a List  
                 List<String> newBoard=new ArrayList<String>();  
                 //Adding elements in the List  
                 for (int i=0;i<9;i++){
                     newBoard.add("X");
                 }
                 System.out.println(newBoard.getClass().getSimpleName());
                return newBoard;
    }
    public static void displayGrid(List<String> squares){
        for (int i=1; i<10;i++){
            if ((i)%3 != 0){
                // The left 2 boxes need lines between themselves
                if (squares.get(i-1) != " "){
                    System.out.print(" " + squares.get(i-1) + " |");
                }
                else{
                    System.out.print(" " + (i) + " |");
                }
            //The last box only needs no borders
            }else if(i==9){
                if (squares.get(i-1) != " "){
                    System.out.println(" " + squares.get(i-1) );
                }
                else{
                    System.out.println(" " + (i));
                }
            //the right boxes need a horizontal border
            }else{
                if (squares.get(i-1) != " "){
                    System.out.println(" " + squares.get(i-1));
                    System.out.println("---+---+---");
                }
                else{
                    System.out.println(" " + (i));
                    System.out.println("---+---+---");
                }
            }
        }
    }
    public static List<String> is_winner(List<String> squares){
        //Check rows
        for (int r=0; r<3;r++){
            if ((squares.get(r*3) != " ") && (squares.get(r*3) == squares.get((r*3)+1))&&(squares.get(r*3) == squares.get((r*3)+2))){
                System.out.println("The game was won by " + squares.get(r*3) + "'s player!");
                List<String> board=new_board();
                return board;
            }
        }
        //Check columns
        for(int c=0;c<3;c++){
            if ((squares.get(c*3) != " ") && (squares.get(c*3) == squares.get((c*3)+3))&&(squares.get(c*3) == squares.get((c*3)+6))){
                System.out.println("The game was won by " + squares.get(c*3) + "'s player!");
                List<String> board=new_board();
                return board;
                }
        }
        //Check diagonals
        if ((squares.get(4)!= " ")&& (squares.get(0)==squares.get(4)&&squares.get(0)==squares.get(8))||
                            (squares.get(2)==squares.get(4)&&squares.get(6)==squares.get(8))){
            System.out.println("The game was won by " + squares.get(4) + "'s player!");
            List<String> board=new_board();
            return board;
        }
        //Cat's game
        if (Collections.frequency(squares, " ") == 0){
            System.out.print("(=ΦｴΦ=) Cat's Game (=ΦｴΦ=)");
            List<String> board=new_board();
            return board;
        }
        return squares;   
    }
    public static String is_turn(List<String> squares){
        //Even blanks is O's turn
        if (Collections.frequency(squares, " ") % 2 == 0){
            return "O";
        }else{
            return "X";
        }
    }
    public static boolean is_blank(List<String> board, int square){
        if(board.get(square)==" "){
            return true;
        }else{
            return false;
        }
    }
    public static void save(List<String> board){
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
    public static List<String> readGame(){
        try{
            FileInputStream readData = new FileInputStream("tictactoe.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
        
            //ArrayList<String> board2 = new ArrayList<String>();
            List<String> board = (List<String>) readStream.readObject();
            readStream.close();
            System.out.println(board.toString());
            System.out.println(board.getClass().getSimpleName());
            return board;
            
        }catch (Exception e) {
            e.printStackTrace();
            return new_board();
        }
         
    }
    public static int get_square(List<String> board){
        Scanner listen = new Scanner(System.in);
        System.out.println("Which square will you place "+is_turn(board)+" in? ");
        int place = listen.nextInt();
        listen.close();
        return (place-1); 
        }
    public static List<String> update_board(List<String> board,int square){
        board.set(square,is_turn(board));
        return board;
    }
}
