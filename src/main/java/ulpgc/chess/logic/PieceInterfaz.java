package ulpgc.chess.logic;

import ulpgc.chess.domain.Color;
import ulpgc.chess.domain.PieceType;

public interface PieceInterfaz {
    PieceType type();
    Color color();
}
