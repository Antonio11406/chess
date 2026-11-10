package ulpgc.chess;

import ulpgc.chess.domain.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board tablero = new Board();
        Scanner scanner = new Scanner(System.in);

        System.out.println("♟️ ¡Bienvenido a tu Motor de Ajedrez en Java! ♟️");
        System.out.println("Formato normal: [fila_origen] [columna_origen] [fila_destino] [columna_destino]");
        System.out.println("Formato coronación: [origenX] [origenY] [destinoX] [destinoY] [Letra de pieza]");
        System.out.println("  (Q = Reina, T = Torre, A = Alfil, C = Caballo)");
        System.out.println("Escribe 'salir' para terminar el juego.\n");

        while (true) {

            System.out.println("\n===========================================");
            tablero.printBoard();
            System.out.print("\nIntroduce tu movimiento (o 'salir'): ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("salir") || input.equals("exit")) {
                System.out.println("¡Gracias por jugar! Hasta la próxima.");
                break;
            }

            try {
                String[] partes = input.split("\\s+");

                if (partes.length != 4 && partes.length != 5) {
                    System.out.println("❌ Formato incorrecto. Introduce 4 números (o 5 si coronas). Ejemplo: 6 4 7 4 q");
                    continue;
                }

                int fromX = Integer.parseInt(partes[0]);
                int fromY = Integer.parseInt(partes[1]);
                int toX = Integer.parseInt(partes[2]);
                int toY = Integer.parseInt(partes[3]);

                Move move;
                if (partes.length == 5) {
                    String promoInput = partes[4].toUpperCase();
                    PieceType promoType = switch (promoInput) {
                        case "T", "ROOK" -> PieceType.ROOK;
                        case "A", "BISHOP" -> PieceType.BISHOP;
                        case "C", "KNIGHT" -> PieceType.KNIGHT;
                        default -> PieceType.QUEEN;
                    };
                    move = new Move(new Square(fromX, fromY), new Square(toX, toY), promoType);
                } else {
                    move = new Move(new Square(fromX, fromY), new Square(toX, toY));
                }
                tablero = tablero.move(move);

            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, introduce solo números para las coordenadas.");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ ERROR: " + e.getMessage());
            } catch (CheckmateException e) {
                e.getFinalBoard().printBoard();
                System.out.println("\n🏆 " + e.getMessage());
                break;
            } catch (Exception e) {
                System.out.println("❌ Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }


}