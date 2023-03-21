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
		if(Math.round(Math.random())==1) {
			p1Start=true;
		}
		else {
			p1Start=false;
		}
		System.out.print("Moechtest du gegen eine KI spielen?");
		String  input=sc.nextLine();  //for testing purposes
//		String input="n";//nach dem Testen löschen
		input=input.toLowerCase();
		if(input.equals("ja")||input.equals("y")){
			System.out.println("Welchen Schwierigkeitsgrad moechtest du? Je groeßer die Zahl, desto schwieriger");
			try {
				schwierigkeitsgrad=Integer.parseInt(sc.nextLine());
			}
			catch(RuntimeException e){
				System.out.println("\n Der Schwierigkeitsgrad  muss eine Zahl sein! Der Schwierigkeitsgrad ist jetzt 42.");
				schwierigkeitsgrad=42;
			}
			hasKi=true;
		}
		else {
			hasKi=false;
		}
	}
	public void startGame() {
		if(hasKi) {
			spielenKi(eration.values()[schwierigkeitsgrad]);
		}
		else {
			spielen2p();
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
		boolean p1Turn;
		if(p1Start) {
			p1Turn=true;
		}
		else {
			p1Turn=false;
		}
		switch(difclty) {
		boolean p1Turn=p1Start;
		z=0;
		s=0;
		zahler=0;
			case leicht:
				ki0();
				break;
			case einfach:
				break;
			case mittel:
				break;
			case schwer:
				break;
			case perfekt:
				break;
		}
		while(!hasWon()) {
			if(p1Turn) {
				break;
			}
		}
	}
	public void ki0() {
		while(!hasWon()){
			
		}
	}
	public void spielen2p() {//curryntly in work
		boolean p1Turn=p1Start;
		z=0;
		s=0;
		zahler=0;
		while(!hasWon()) {
			if(p1Turn) {
				System.out.println("Spieler 1: Waehle die Zeile und die Spalte");
			}
			else {
				System.out.println("Spieler 2: Waehle die Zeile und die Spalte");
			}
			try {
				z=Integer.parseInt(sc.nextLine());
				s=Integer.parseInt(sc.nextLine());
			}
			catch(RuntimeException g) {
				z=felder.length;
				s=felder.length;
			}
			eingabe(z,s, p1Turn);
			p1Turn=!p1Turn;
			zahler++;
			printField();
			if(zahler>=9) {
				zahler=-1;
				break;
			}
		}
		if(zahler==-1) {
			System.out.println("Unentschieden!");
		}
		else {
			if(!p1Turn) {
				System.out.println("Spieler 1 hat gewonnen");
				score[0]++;
			}
			else {
				System.out.println("Spieler 2 hat gewonnen");
				score[1]++;
			}
		}
	}
	public void eingabe(int zeille,int spallte, boolean p1Zug) {
		if(spallte<felder.length && zeille<felder.length && zeille>=0 && spallte>=0 && istFrei(zeille,spallte)) {
			if(p1Zug) {
				felder[zeille][spallte]='X';
			}
			else {
				felder[zeille][spallte]='O';
			}
		}
		else {
			System.out.println("Deine Eingabe war Fehlerinhaltig! Try again");
			try {
				zeille=Integer.parseInt(sc.nextLine());
				spallte=Integer.parseInt(sc.nextLine());
			}
			catch(RuntimeException g) {
				zeille=felder.length;
				spallte=felder.length;
			}
			eingabe(zeille,spallte, p1Zug);
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
}