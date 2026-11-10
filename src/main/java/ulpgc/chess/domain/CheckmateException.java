package ulpgc.chess.domain;

public class CheckmateException extends RuntimeException{
    private final Board finalBoard;

    public CheckmateException(String message, Board finalBoard) {
        super(message);
        this.finalBoard = finalBoard;
    }

    public Board getFinalBoard() {
        return finalBoard;
    }
}
