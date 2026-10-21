package domain;

import interfaces.PieceInterfaz;

public record Piece(PieceType type, Color color) implements PieceInterfaz{

}
