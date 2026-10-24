package domain;

import engine.PieceInterfaz;

public record Piece(PieceType type, Color color) implements PieceInterfaz{

}
