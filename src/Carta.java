
public class Carta {
	
	private int numero;
	private Mono mono;
	private Pinta pinta;
	
	public Carta(int numero, Pinta pinta) {
		setNumero(numero);
		setPinta(pinta);}

	public int getNumero() {
		return numero;
	}

	private void setNumero(int numero){
		if(numero>13 || numero<1)
		System.out.println("Error en la asignación del número");
		else { this.numero=numero;
		if(numero==1) {mono=Mono.A; this.numero*=14;}
		if(numero==11)mono=Mono.J;
		if(numero==12)mono=Mono.Q;
		if(numero==13)mono=Mono.K;}
		}

	public Pinta getPinta() {
		return pinta;
	}

	public void setPinta(Pinta pinta) {
		this.pinta = pinta;
	}
	
	public String showCard() {
		if(numero>10)return pinta+" "+mono;
		return pinta+" "+numero;
	}
	

}
