
public class Neighbour {

	private int ID;
	private City CITY = new City();
	private boolean REPEAT;

	public Neighbour(City city, boolean rep){ setCity(city); setRep(rep); }

	public void setCity(City city){ CITY = city; ID = city.getID();}
	public void setRep(boolean rep) { REPEAT = rep;	}

	public int getId() { return ID; }
	public City getCity() { return CITY; }
	public boolean isRep() { return REPEAT; }
}