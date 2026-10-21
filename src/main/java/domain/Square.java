package domain;

public record Square(int x, int y) {
    public Square {
        if (x < 0 || x > 8 ||  y < 0 || y > 8){
            throw new IllegalArgumentException("Las coordenadas de la casilla deben estar entre 0 y 7.");
        }
    }
}
