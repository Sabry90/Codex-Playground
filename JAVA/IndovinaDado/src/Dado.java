import java.util.Random;
import java.util.Scanner;

public class Dado {
	
	// METODO PROCEDURALE (ossia che non restituisce un valore)
	public static void test()
	{
		System.out.println("test");
	}
	
	
	// METODO FUNZIONALE (ossia che restituisce un valore)
	public static int numerocasuale() {
		// Restituisce un numero casuale compreso tra 0 e 100
		Random R = new Random();
		int n = R.nextInt(101);
		return n;
	}
	
	public static int dado()
	{
		// Restituisce un numero casuale compreso tra 1 e 6
		Random R = new Random();
		// R.nextInt(6) genera un numero compreso tra 0 e 5
		// +1 per farlo partire da 1 e quando dovesse essere 5 diventa 6
		int n = R.nextInt(6) + 1;		
		return n;
	}
	
	

	public static void main(String[] args) 
	{	
		String scelta_ripeti;
		Scanner input = new Scanner(System.in);
		
		int partite = 0;
		int vittorie = 0;
		int sconfitte = 0;
		
		
		do
		{
			int estrazione = dado();
			partite++;
			int tentativi = 3;
            boolean indovinato = false;
			
			int numerotentato;
			System.out.println("🎲 IL DADO E' TRATTO! 🎲");
			System.out.println("INDOVINA IL NUMERO CHE E' STATO ESTRATTO (1-6): ");
			
			
			do {
				
				numerotentato = input.nextInt();
				input.nextLine(); 
				
				if (numerotentato < 1 || numerotentato > 6)
                {
                    System.out.println("❌ Numero non valido, riprova.");
                }
				else if (numerotentato == estrazione)
                {
                    System.out.println("✅ Hai indovinato! Il numero era " + estrazione);
                    vittorie++;
                }
                else
                {
                    System.out.println("❎ Hai sbagliato! Il numero era " + estrazione);
                    sconfitte++;
                }
               
			} while (numerotentato < 1 || numerotentato > 6);
			
			
			do
			{
				System.out.println("Vuoi ripetere? (si/no)");
				scelta_ripeti = input.nextLine().toLowerCase();
				if (!scelta_ripeti.equals("si") && !scelta_ripeti.equals("no"))
				{
					System.out.println("Scelta non valida, riprova.");
				}
			} while (!scelta_ripeti.equals("si") && !scelta_ripeti.equals("no"));
			
			
			
		} while (scelta_ripeti.equals("si"));
		System.out.println("Hai scelto di non ripetere.");
		System.out.println("Il programma termina.");
		
		
        System.out.println(" STATISTICHE FINALI:");
        System.out.println("Partite giocate: " + partite);
        System.out.println("Vittorie: " + vittorie);
        System.out.println("Sconfitte: " + sconfitte);
        System.out.println("Grazie per aver giocato!");

		
	}

}
