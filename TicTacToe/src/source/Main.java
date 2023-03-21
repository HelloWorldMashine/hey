package source;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		TicTacToe ttt=new TicTacToe();
		ttt.initialisieren();
		boolean nochmal;
		try(Scanner scan=new Scanner(System.in)){
			do {
				ttt.resetField();
				ttt.startGame();
				System.out.println("\n Moechtest du nochmal spielen?");
				String nput=scan.nextLine().toLowerCase();
				if(nput.equals("ja")||nput.equals("y")) {
					nochmal=true;
				}
				else {
					nochmal=false;
				}
			}
			while(nochmal);
		}
	}
}