import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//La clase mesa tendrá la responsabilidad de repartir las cartas de forma aleatorea.
public class Mesa {
	//composición.
	private Mazo mazo=new Mazo();
	private boolean playersIsOk=false;
	private List<Jugador> jugadores;
	
	public Mesa() {
		mazo.buildCards();
		jugadores=new ArrayList<Jugador>();
	}
	
	public void ganadores() {

		for (int i = 0; i < jugadores.size(); i++) {
			if(jugadores.get(i).isWin()) {
			System.out.println("El jugador: "+jugadores.get(i).getNickname()+" ganó con: "
			+jugadores.get(i).getMano());
			setCasoBordePoints(jugadores.get(i));
			}
		}
	}
	
	public void resetGame(List <Jugador> jugadores) {
		jugadores.forEach(item->{item.setPoints(0);
		                         item.setCasoBordePoints(0);
		                         item.setWin(true);
		                         item.setManoScore(0);});
	}
	
	//Trabaja en este método
	public  void compareAllScores(List<Jugador> jugadores){
		for (int row = 0; row < jugadores.size(); row++) {
			for (int col = 0; col < jugadores.size(); col++) {
				if(jugadores.get(row).getManoScore()<jugadores.get(col).getManoScore()) {
					jugadores.get(row).setWin(false);
					//break;
				}
				if(jugadores.get(row).getManoScore()==jugadores.get(col).getManoScore()) {
					if(jugadores.get(row).getPoints()<jugadores.get(col).getPoints()) {
						jugadores.get(row).setWin(false);
						if(jugadores.get(row).getPoints()==jugadores.get(col).getPoints()) {
							if(jugadores.get(row).getCasoBordePoints()<jugadores.get(col).getCasoBordePoints()) {
								jugadores.get(row).setWin(false); System.out.print("Caso Borde");
							}
						}
					}
				}
				//System.out.println(jugadores.get(row).getNickname()+";" +jugadores.get(row).isWin());
			}
		}
	}
	
	private void setCasoBordePoints(Jugador jugador){
		  List <Carta> cartas=Arrays.asList(jugador.getCartas());
		  final Comparator<Carta> comp = (p1, p2) -> Integer.compare( p1.getNumero(), p2.getNumero());
		  Carta carta=cartas.stream().max(comp).get();
		  int points=0;
		  if(carta.getPinta().equals(Pinta.Pica))points=carta.getNumero()+100;
		  if(carta.getPinta().equals(Pinta.Corazon))points=carta.getNumero()+90;
		  if(carta.getPinta().equals(Pinta.Trebol))points=carta.getNumero()+80;
		  if(carta.getPinta().equals(Pinta.Diamante))points=carta.getNumero()+70;
		  jugador.setCasoBordePoints(points);
	}
	
	
	public void addJugador(Jugador jugador) {
		if(jugadores.size()<5)
		jugadores.add(jugador);
		else System.out.println("Has sobrepasado la cantidad de jugadores");
		if(jugadores.size()>1)playersIsOk=true; else playersIsOk=false;
	}
	//revuelve las cartas
	private Carta[] shuffle(Carta[][] cartas){
		Carta[][] matrix = cartas;
	    Carta newArray[] = new Carta[matrix.length*matrix[0].length];
	    for(int i = 0; i < matrix.length; i++) {
	        Carta[] row = matrix[i];
	        for(int j = 0; j < row.length; j++) {
	            Carta number = matrix[i][j];
	            newArray[i*row.length+j] = number;
	        }
	    }
	    Collections.shuffle(Arrays.asList(newArray));
	/* 
	    for(int i = 0; i < newArray.length; i++) {
	        System.out.println(newArray[i].showCard() + " ");
	    }*/
		return newArray;
	}
	
	public Mazo getMazo() {
		return mazo;
	}
	public List<Jugador> getJugadores(){
		return jugadores;
	}
	
	//reparte las cartas
	public void deal() {
		int z=0;
		Carta[]revueltas=new Carta[5];
		Carta[] cartas=new Carta[5];
		Carta[] mazorevuelto= shuffle(mazo.getCartas());
	if(playersIsOk) {
		System.out.println("Listo para Repartir");
		for (int row = 0; row < 7; row++) {
			if(row==jugadores.size()) 
			{/*System.out.println("Hemos terminado de repartir las cartas");*/return;}
		//	System.out.println("");
			System.out.println("Jugador"+row+": "+this.jugadores.get(row).getNickname());
			for (int col = 0; col < 5; col++) {
				System.out.println(mazorevuelto[z].showCard());
				cartas[col]=mazorevuelto[z]; z++;
			}
			//System.out.println("xd");
			revueltas=new Carta[5];
			for (int i = 0; i < cartas.length; i++) {
				revueltas[i]=cartas[i];
				//System.out.println(revueltas[i].showCard());
			}
			
			System.out.println("----------------");
			jugadores.get(row).setCartas(revueltas);
			//System.out.println(row);
		}}
	else { System.out.println("No se cumplen las condiciones para repartir las cartas");}
	}
	
	
}
