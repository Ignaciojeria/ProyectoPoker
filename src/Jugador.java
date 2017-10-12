import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Jugador {

	private String nickname;
	private Carta[] cards=new Carta[5];
	private Mano mano;
	private int points;
	private int manoScore;
	private int casoBordePoints;
	private boolean win=true;
	
	public void setManoScore(int manoScore) {
		this.manoScore=manoScore;
	}

	public void setCasoBordePoints(int points) {
		this.casoBordePoints=points;
	}
	
	public int getCasoBordePoints() {
		return this.casoBordePoints;
	}
	
	public int getManoScore() {
		return manoScore;
	}
	public Mano getMano() {
		return this.mano;
	}
	
	public Jugador(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Carta[] getCartas() {
		return cards;
	}
	public void setCartas(Carta[] cartas) {
		this.cards = cartas;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	public Mano showHand(){
		int pares=pares(),trio=trio(),escalera=escalera();
		Pinta color=color();
		if(escalera==5 && color!=null) {this.manoScore=100; return this.mano=Mano.EscaleraColor;}
		if(escalera==5) {this.manoScore=90; return this.mano=Mano.Escalera;}
		if(pares==2 && trio==1) {this.manoScore=80; return this.mano=Mano.Full;}
		if(pares==2) {this.manoScore=70; return this.mano=Mano.Doblepar;}
		if(trio==1) {this.manoScore=60; return this.mano=Mano.Trio;}
		if(pares==1) {this.manoScore=50; return this.mano=Mano.Unpar;}
		else {this.manoScore=40; return this.mano=Mano.CartaAlta;}
	}
	
	//Comprueba si tiene un trio 
	public int trio() {
		int[] trio=new int[1];
		trio[0]=0;
		List<Integer> numerosRepetidos=new ArrayList<Integer>();
		List <Carta> cartas=Arrays.asList(cards);
		cartas.forEach(item->{ numerosRepetidos.add(item.getNumero());});
		numerosRepetidos.stream().filter(i -> Collections.frequency(numerosRepetidos, i) >2)
        .collect(Collectors.toSet()).forEach(repetidos->{trio[0]++; this.points+=repetidos; System.out.println(nickname+" obtuvo+1 trío de " +" puntos!");});
		return trio[0];
	}

	//
	
	public int pares(){
		int[] par=new int[1];
		par[0]=0;
		List<Integer> numerosRepetidos=new ArrayList<Integer>();
		List <Carta> cartas=Arrays.asList(cards);
		cartas.forEach(item->{ numerosRepetidos.add(item.getNumero());});
		numerosRepetidos.stream().filter(i -> Collections.frequency(numerosRepetidos, i) >1)
        .collect(Collectors.toSet())
        .forEach((repetidos)->{par[0]++; this.points+=repetidos; System.out.println(nickname+" obtuvo+"+par[0]+" par de "+ points +" puntos!");});
		return par[0];
	}
	public void poker(){
		List<Integer> numerosRepetidos=new ArrayList<Integer>();
		List <Carta> cartas=Arrays.asList(cards);
		cartas.forEach(item->{ numerosRepetidos.add(item.getNumero());});
		numerosRepetidos.stream().filter(i -> Collections.frequency(numerosRepetidos, i) >3)
        .collect(Collectors.toSet()).forEach(repetidos->{this.points+=repetidos; System.out.println(nickname+" obtuvo+1 póker de "+ points +" puntos!");});
	}
	
	public int escalera() {
		List<Integer> numeros=new ArrayList<Integer>();
		List <Carta> cartas=Arrays.asList(cards);
		cartas.forEach(item->{ numeros.add(item.getNumero()); /*System.out.println(numeros);*/});	
		if(longestConsecutive(numeros)==5)
		this.points+=30;
		return longestConsecutive(numeros);
	}
	
	public int cartaAlta() {
		List<Integer> numeros=new ArrayList<Integer>();
		Arrays.asList(cards).forEach(item->{numeros.add(item.getNumero());});
		int puntos=numeros.stream().mapToInt(i -> i).max().getAsInt();
		this.points+=puntos;
		if(puntos==1) puntos*=14;
		return puntos;
	}
	
	//Si las 5 cartas son del mismo color entonces el jugador tendrá color.
	public Pinta color() {
		Pinta pinta[]=new Pinta[1];
		List <Carta> cartas=Arrays.asList(cards);
		List<Pinta> pintas=new ArrayList<Pinta>();
		cartas.forEach(item->{ pintas.add(item.getPinta());});
		pintas.stream().filter(i -> Collections.frequency(pintas, i) >4)
        .collect(Collectors.toSet()).forEach(repetidos->{System.out.println(repetidos); pinta[0]=repetidos;});
		return pinta[0];
	}

	//Este código fue investigado en la web.
	private static int longestConsecutive(List<Integer> num) {
		// if array is empty, return 0
		if (num.size() == 0) {
			return 0;
		}
	 
		Set<Integer> set = new HashSet<Integer>();
		int max = 1;
	 
		for (int e : num)
			set.add(e);
	 
		for (int e : num) {
			int left = e - 1;
			int right = e + 1;
			int count = 1;
	 
			while (set.contains(left)) {
				count++;
				set.remove(left);
				left--;
			}
	 
			while (set.contains(right)) {
				count++;
				set.remove(right);
				right++;
			}
	 
			max = Math.max(count, max);
		}
	 
		return max;
	}
	
	
}
