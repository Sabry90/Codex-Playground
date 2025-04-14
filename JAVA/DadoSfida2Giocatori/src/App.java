
import java.util.Random;

import java.util.Scanner;


public class App {
	
	public static int dado() {
        Random R = new Random();
        return R.nextInt(6) + 1;
    }

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
        String scelta_ripeti;

        int partite = 0;
        int puntiGiocatore1 = 0;
        int puntiGiocatore2 = 0;

        System.out.println("MODALITÀ DUE GIOCATORI CON CLASSIFICA ");

        // Prendi i nomi dei giocatori
        System.out.print("Inserisci il nome del Giocatore 1: ");
        String nome1 = input.nextLine();

        System.out.print("Inserisci il nome del Giocatore 2: ");
        String nome2 = input.nextLine();

        do {
            partite++;
            int estrazione = dado();
            System.out.println("Partita " + partite);
            System.out.println("Un numero è stato estratto (da 1 a 6).");

            // Turno del Giocatore 1
            System.out.println("Turno di " + nome1);
            int punti1 = turnoGiocatore(nome1, estrazione, input);
            puntiGiocatore1 += punti1;
            if (punti1 > 0) {
                System.out.println("" + nome1 + " ha guadagnato " + punti1 + " punti!");
            } else {
                System.out.println("" + nome1 + " non ha guadagnato punti.");
            }

            // Turno del Giocatore 2
            System.out.println("Turno di " + nome2);
            int punti2 = turnoGiocatore(nome2, estrazione, input);
            puntiGiocatore2 += punti2;
            if (punti2 > 0) {
                System.out.println("" + nome2 + " ha guadagnato " + punti2 + " punti!");
            } else {
                System.out.println("" + nome2 + " non ha guadagnato punti.");
            }

            // Vuoi ripetere?
            do {
                System.out.println("Vuoi giocare un'altra partita? (si/no)");
                scelta_ripeti = input.nextLine().toLowerCase();
                if (!scelta_ripeti.equals("si") && !scelta_ripeti.equals("no")) {
                    System.out.println("Scelta non valida, riprova.");
                }
            } while (!scelta_ripeti.equals("si") && !scelta_ripeti.equals("no"));

        } while (scelta_ripeti.equals("si"));

        // Classifica finale
        System.out.println("CLASSIFICA FINALE:");
        System.out.println(nome1 + " = " + puntiGiocatore1 + " punti");
        System.out.println(nome2 + " = " + puntiGiocatore2 + " punti");

        if (puntiGiocatore1 > puntiGiocatore2) {
            System.out.println("Vincitore: " + nome1 + "!");
        } else if (puntiGiocatore2 > puntiGiocatore1) {
            System.out.println("Vincitore: " + nome2 + "!");
        } else {
            System.out.println("È un pareggio!");
        }

        input.close();
    }

    public static int turnoGiocatore(String nome, int numeroSegreto, Scanner input) {
        int tentativi = 3;

        for (int i = 1; i <= 3; i++) {
            System.out.print(nome + ", tentativo " + i + ": Inserisci un numero (1-6): ");
            int tentativo = input.nextInt();
            input.nextLine(); // pulizia buffer

            if (tentativo < 1 || tentativo > 6) {
                System.out.println("Numero non valido. Deve essere tra 1 e 6.");
                i--; // non conta come tentativo valido
                continue;
            }

            if (tentativo == numeroSegreto) {
                switch (i) {
                    case 1: return 3;
                    case 2: return 2;
                    case 3: return 1;
                }
            } else {
                System.out.println("Sbagliato.");
            }
        }
        System.out.println("Hai esaurito i tentativi. Il numero era: " + numeroSegreto);
        return 0;
    }

	}


