import java.util.Random;

import java.util.Scanner;
public class App {
	// Definisce un metodo statico chiamato 'numerocasuale' che accetta due interi (min e max) come input
	public static int numerocasuale(int min, int max) {
		// Crea un nuovo oggetto della classe Random per generare numeri casuali
		Random R = new Random();
		int n = R.nextInt(max - min + 1) + min;
		return n;
	}
	public static void main(String[] args) {
		int scelta; // Dichiara una variabile intera 'scelta' per memorizzare la scelta dell'utente dal menu
		Scanner input = new Scanner(System.in);// Crea un nuovo oggetto della classe Scanner per leggere l'input dell'utente dalla console (System.in)
		
		do // Inizia un ciclo 'do-while' che continuerà fino a quando l'utente non sceglie l'opzione 2 (Esci)
		{
			// Stampa le opzioni del menu
			System.out.println("1. Genera password");
			System.out.println("2. Esci.");
				
		do // Inizia un altro ciclo 'do-while' per assicurarsi che l'utente inserisca un'opzione valida (1 o 2)
			
		{
					System.out.println("Scegli un opzione:");                
	                scelta = input.nextInt();
	                input.nextLine(); // Consuma il newline rimasto nel buffer
	                if (scelta != 1 && scelta != 2) // Verifica se la scelta dell'utente non è né 1 né 2
                    {
                        System.out.println("Opzione non valida, riprova."); // Se la scelta non è valida, stampa un messaggio di errore
                    }
	            } while (scelta != 1 && scelta != 2); // Il ciclo continua finché la scelta dell'utente non è 1 o 2
			if (scelta==1) // Se l'utente ha scelto l'opzione 1 (Genera password)
			{
				int lunghezza; // Dichiara una variabile intera 'lunghezza' per memorizzare la lunghezza desiderata della password
				
			do // Inizia un ciclo 'do-while' per assicurarsi che la lunghezza della password sia almeno 8 caratteri
				{
					System.out.println("Inserisci la lunghezza della password di almeno 8 caratteri:");
					lunghezza = input.nextInt();
					input.nextLine(); // Consuma il newline rimasto nel buffer
					if (lunghezza < 8) // Verifica se la lunghezza inserita è inferiore a 8
                    {
                        System.out.println("Lunghezza non valida, riprova."); // Se la lunghezza non è valida, stampa un messaggio di errore
                    }
				} while (lunghezza < 8);// Il ciclo continua finché la lunghezza inserita è inferiore a 8
				
				String alfabeto = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
				String password = "";
				int da = 0;
				int a = alfabeto.length() - 1;
				for (int i=0;i<lunghezza;i++) // Inizia un ciclo 'for' che itererà 'lunghezza' volte (il numero di caratteri desiderati nella password)
				{
					int posizionecasuale = numerocasuale(da, a);// Genera una posizione casuale all'interno della stringa 'alfabeto' utilizzando il metodo 'numerocasuale'
					char ch = alfabeto.charAt(posizionecasuale); // Ottiene il carattere presente nella stringa 'alfabeto' alla posizione casuale generata
					password += ch; // Aggiunge il carattere casuale alla stringa 'password'
				}
				System.out.println("Password creata: " + password);
				
			    
			}
			
			if (scelta !=2) // Se l'utente non ha scelto l'opzione 2 (Esci)
			{
				System.out.println("Premi invio per tornare al menù.");// Stampa un messaggio per chiedere all'utente di premere invio per tornare al menu
				input.nextLine(); // Aspetta che l'utente prema INVIO
			}
			
		} while (scelta != 2); // Il ciclo 'do-while' principale continua finché la scelta dell'utente non è 2
		System.out.println("Grazie per averci scelto. Alla prossima.");
	}

}
		
	


