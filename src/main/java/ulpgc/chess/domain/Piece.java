package ulpgc.chess.domain;

import ulpgc.chess.logic.PieceInterfaz;

public record Piece(PieceType type, Color color) implements PieceInterfaz{

}
