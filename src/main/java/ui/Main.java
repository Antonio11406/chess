package ui;

import domain.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board tablero = new Board();
        Scanner scanner = new Scanner(System.in);

        System.out.println("♟️ ¡Bienvenido a tu Motor de Ajedrez en Java! ♟️");
        System.out.println("Formato de movimiento: [fila_origen] [columna_origen] [fila_destino] [columna_destino]");
        System.out.println("Ejemplo para mover el peón blanco de e2 a e4: 1 4 3 4");
        System.out.println("Escribe 'salir' para terminar el juego.\n");

        // El famoso Bucle de Juego (Game Loop)
        while (true) {
            System.out.println("\n===========================================");
            tablero.printBoard();
            System.out.print("\nIntroduce tu movimiento (o 'salir'): ");

            String input = scanner.nextLine().trim().toLowerCase();

            // Condición para salir del juego
            if (input.equals("salir") || input.equals("exit")) {
                System.out.println("¡Gracias por jugar! Hasta la próxima.");
                break;
            }

            try {
                // Separamos el texto por los espacios
                String[] partes = input.split("\\s+");

                if (partes.length != 4) {
                    System.out.println("❌ Formato incorrecto. Debes introducir 4 números. Ejemplo: 1 4 3 4");
                    continue; // Vuelve al inicio del bucle
                }

                // Convertimos el texto a números
                int fromX = Integer.parseInt(partes[0]);
                int fromY = Integer.parseInt(partes[1]);
                int toX = Integer.parseInt(partes[2]);
                int toY = Integer.parseInt(partes[3]);

                Move move = new Move(new Square(fromX, fromY), new Square(toX, toY));

                // Aplicamos el movimiento al tablero
                tablero = tablero.move(move);

            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, introduce solo números (ej: 1 4 3 4).");
            } catch (IllegalArgumentException e) {
                // Captura tu excepción de "Le toca mover a las BLANCAS/NEGRAS"
                System.out.println("❌ ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }


}