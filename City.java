
public class City {

	private int ID;
	private double X, Y;
	
	public City () { }
	public City (int id, double x, double y) {setID(id); setX(x); setY(y);}
	
	public int getID() { return ID; }			//ID cidade
	public double getX() { return X; } 			//posicao XX
	public double getY() { return Y; } 			//posicao YY
	
	public void setID(int id) {assert id<=200; ID = id; } //define o ID (nao pode ser maior que 200)
	public void setX(double x) {assert x>=0; X =x; }		//define a posicao X
	public void setY(double y) {assert y>=0; Y =y; }		//define a posicao Y
	
	
}
