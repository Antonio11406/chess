package rules;

import domain.Board;
import domain.Move;
import interfaces.PieceMove;

public class PawnValidator implements PieceMove {
    @Override
    public boolean isValid(Move move, Board board) {

        return false;
    }
}
