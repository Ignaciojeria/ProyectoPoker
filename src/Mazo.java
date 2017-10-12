
public class Mazo {
	private Carta[][] cartas=new Carta[4][13];
	
	public void buildCards() {
	    for (int row = 0; row < 4; row++) {
	    	Pinta pinta=null;
	    	int numero=1;
	    		for (int col = 0; col < 13; col++) {
	    			if(row==0) pinta=Pinta.Corazon;
	    			if(row==1) pinta=Pinta.Diamante;
	    			if(row==2) pinta=Pinta.Pica;
	    			if(row==3) pinta=Pinta.Trebol;
	    			cartas[row][col]=new Carta(numero,pinta);numero++;
	    		//	System.out.println(cartas[row][col].showCard());
	    	}
	    		}
	}
	
	public Carta[][] getCartas(){
		return cartas;
	}

}
