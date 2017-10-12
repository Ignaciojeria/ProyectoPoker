import java.util.Scanner;

public class Menu {
	
	
	private void play(Mesa mesa) {
		for (int i = 0; i < mesa.getJugadores().size(); i++) {
			mesa.getJugadores().get(i).showHand();
			System.out.println("El jugador: "+
							   mesa.getJugadores().get(i).getNickname()+" Obtuvo: " +
							   mesa.getJugadores().get(i).getMano()+". Su carta más grande vale: "+
							   mesa.getJugadores().get(i).cartaAlta());
		}

	}
	
	public void menu() {
		Mesa mesa=new Mesa();
		String nickName;
		System.out.println("Opciones");
		System.out.println("1) Nuevo jugador.");
		System.out.println("2) Barajar y repartir.");
		System.out.println("3) Apostar!");
		System.out.println("4) Terminar con el juego");
		while(true) {
			Scanner entrada= new Scanner(System.in);
		try {
		int opcion=entrada.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("Ingresa un nombre de Jugador");
			entrada= new Scanner(System.in);
			nickName=entrada.nextLine();
			mesa.addJugador(new Jugador(nickName));
			break;
		case 2:
			System.out.println(" ");
			mesa.deal();
			break;
		case 3:
			play(mesa);
			mesa.compareAllScores(mesa.getJugadores());
			mesa.ganadores();
			mesa.resetGame(mesa.getJugadores());
			break;
		case 4:
			System.out.println("fin del juego");
			return;
		default:
			}
		}catch(Exception e) {
			System.out.println("Opción no válida.Recuerda barajar bien tus cartas antes de jugar ;)");
		}
		System.out.println("Opciones");
		System.out.println("1) Nuevo jugador.");
		System.out.println("2) Barajar y repartir.");
		System.out.println("3) Apostar!");
		System.out.println("4) Terminar con el juego");
		}
	}

}
