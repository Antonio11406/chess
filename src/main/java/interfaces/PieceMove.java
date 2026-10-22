package interfaces;

import domain.Board;
import domain.Move;

public interface PieceMove {
    boolean isValid(Move move, Board board);
}
