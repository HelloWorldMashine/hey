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
				if(ttt.hasKi) {
					System.out.println("\n Moechtest du nochmal spielen?");
				}
				else {
					System.out.println("\n Moechtet ihr nochmal spielen?");
				}
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
				System.out.println("Endergebnis: "+ttt.score[0]+" mal gewonnen und "+ttt.score[1]+" mal verloren");
			}
			else {
				System.out.println("Endergebnis: Spieler1: "+Integer.toString(ttt.score[0])+" Siege, Spieler2: "+Integer.toString(ttt.score[1])+" Siege");
			}
		}
	}
}