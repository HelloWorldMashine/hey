package source;
import java.util.Scanner;

public class TicTacToe {
	char[][] felder= new char[3][3];
	int[] score={0,0};
	int schwierigkeitsgrad;
	int z;
	int s;
	int zahler;
	boolean p1Start;
	boolean p1Turn;
	boolean hasKi;
	Scanner sc=new Scanner(System.in);
	enum eration{
		leicht,
		einfach,
		mittel,
		schwer,
		perfekt
	}
	public void initialisieren() {
		System.out.print("Moechtest du gegen eine KI spielen?");
		String  input=sc.nextLine();
//		String input="y";//nach dem Testen löschen
		input=input.toLowerCase();
		if(input.equals("ja")||input.equals("y")){
			System.out.println("Welchen Schwierigkeitsgrad moechtest du? leicht, einfach, mittel ,schwer oder perfekt");
			try {
				eration.valueOf(sc.nextLine().toLowerCase());
			}
			catch(RuntimeException e){
				System.out.println("\n Den Schwierigkeitsgrad gibt es nicht! Der Schwierigkeitsgrad ist jetzt maximal schwer.");
				eration.valueOf("leicht");//soll eigentlich perfekt sein
			}
			hasKi=true;
		}
		else {
			hasKi=false;
		}
	}
	public void startGame() {
		zahler=0;
		if(Math.round(Math.random())==1) {
			p1Start=true;
		}
		else {
			p1Start=false;
		}
		p1Turn=p1Start;
		while(!hasWon()) {
			if(p1Turn) {
				printField();
				if(hasKi) {
					System.out.println("Waehle Zeile und Spalte");
				}
				else {
					System.out.println("Spieler 1: Waehle die Zeile und die Spalte");
				}
				for(boolean doRepeat=true;doRepeat;) {
					try {
						eingabe(true);
						doRepeat=false;
					}
					catch(illegalMoveException f){
						f.printStackTrace();
					}
				}
			}
			else if(hasKi) {
				spielenKi(eration.leicht/*values()[schwierigkeitsgrad]*/);
			}
			else {
				spielen2p();
			}
			changeTurn();
			if(zahler>=9) {//bendet das Spiel bei einem Unentschieden
				break;
			}
		}
		printField();
		if(!p1Turn) {
			score[0]++;
		}
		else {
			score[1]++;
		}
		printScore(hasKi);
	}
	public void printField() {
		System.out.println("");
		for(int i=0;i<felder.length;i++) {
			for(int j=0;j<felder.length;j++) {
				System.out.print(felder[i][j]);
				if(j<(felder[i].length-1)) {
					System.out.print("|");
				}
			}
			if(i<(felder[i].length)-1) {
				System.out.println("");
				for(int j=0;j<5;j++) {
					System.out.print("-");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
	public void spielenKi(eration difclty) {//gegen KI spielen
		switch(difclty) {
			case leicht:
				ki0();
				break;
			case einfach:
				ki1();
				break;
			case mittel:
				break;
			case schwer:
				break;
			case perfekt:
				break;
		}
	}
	public void ki1() {//wird gerade bearbeitet
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(felder[i][0]==felder[i][1] && istFrei(i, j)) {//Wenn 2 Felder gleich und !gleich 0 : auf 3. Feld
					felder[i][2]='O';
				}
			}
		}
	}
	public void ki0() {
		boolean doPlacement=true;
		while(doPlacement) {
			z=(int)Math.round(Math.random()*2);
			s=(int)Math.round(Math.random()*2);
			if(istFrei(z, s)) {
				felder[z][s]='O';
				doPlacement=false;
			}
		}
	}
	public void spielen2p() {
		printField();
		System.out.println("Spieler 2: Waehle die Zeile und die Spalte");
		for(boolean doRepeat=true;doRepeat;) {
			try {
				eingabe(true);
				doRepeat=false;
			}
			catch(illegalMoveException f){
				f.printStackTrace();
			}
		}
	}
	public void eingabe(boolean p1Zug) throws illegalMoveException {
		try {
			z=Integer.parseInt(sc.nextLine());
			s=Integer.parseInt(sc.nextLine());
		}
		catch(RuntimeException g) {
			z=felder.length;
			s=felder.length;
		}
		if(s<felder.length && z<felder.length && s>=0 && z>=0 && istFrei(z,s)) {//besser durch illegalMoveException
			if(p1Zug) {
				felder[z][s]='X';
			}
			else {
				felder[z][s]='O';
			}
		}
		else {
			throw new illegalMoveException();
//			eingabe(p1Zug);
		}
	}
	public void resetField() {
		for(int i=0;i<felder.length;i++) {
			for(int j=0;j<felder[i].length;j++) {
				felder[i][j]='0';
			}
		}
	}
	public boolean istFrei(int zeile, int spalte) {
		if(felder[zeile][spalte]=='0') {
			return true;
		}
		return false;
	}
	public boolean hasWon() {
		for(int i=0;i<felder.length;i++) {
			if(felder[i][0]==felder[i][1]&&felder[i][0]==felder[i][2]) {//Zeilen prüfen
				if(felder[i][0]!='0' && felder[i][1]!='0' && felder[i][2]!='0') {
					return true;
				}
			}
			if(felder[0][i]==felder[1][i]&&felder[0][i]==felder[2][i]) {//Spalten prüfen
				if(felder[0][i]!='0' && felder[1][i]!='0' && felder[2][i]!='0') {
					return true;
				}
			}
		}
		if(felder[0][0]==felder[2][2]&&felder[0][0]==felder[1][1]) {//Diagonale von Links oben prüfen
			if(felder[0][0]!='0' && felder[1][1]!='0' && felder[2][2]!='0') {
				return true;
			}
		}
		if(felder[0][2]==felder[1][1]&&felder[2][0]==felder[1][1]) {//Diagonale von Rechts oben prüfen
			if(felder[0][2]!='0' && felder[1][1]!='0' && felder[2][0]!='0') {
				return true;
			}
		}
		return false;
	}
	public void printScore(boolean mitKi) {
		if(mitKi) {
			System.out.println("Du hast "+Integer.toString(score[0])+" mal gewonnen und "+Integer.toString(score[1])+" mal verloren");
		}
		else{
			System.out.println("Spieler 1 hat "+Integer.toString(score[0])+" mal gewonnen, Spieler 2 hat "+Integer.toString(score[1])+" mal gewonnen");
		}
	}
	public void changeTurn() {
		p1Turn=!p1Turn;
		zahler++;
	}
}