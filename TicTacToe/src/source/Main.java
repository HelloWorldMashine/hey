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
			if(ttt.hasKi) {
				
			}
			else {
				System.out.println("Endergebnis: Spieler1 : "+Integer.toString(ttt.score[0])+" Siege, Spieler2: "+Integer.toString(ttt.score[1])+" Siege");
			}
		}
	}
}