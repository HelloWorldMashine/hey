package source;

public class illegalMoveException extends Exception {
	public illegalMoveException(/*String s*/) {
		super("Diese Eingabe enthielt einen Fehler!!");
	}
}