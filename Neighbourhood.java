import java.util.*;

public class Neighbourhood {
	
	private int ID;
	private City CITY = new City();
	private boolean USED;
	private ArrayList<Neighbour> NEI = new ArrayList<Neighbour>();
	
	public Neighbourhood(City city, boolean used, Sequence parent1, Sequence parent2){ setCity(city); setUsed(used); setNei(parent1, parent2); } 
	
	public City getCity() { return CITY; }
	public int getId() { return ID; }
	public boolean isUsed(){ return USED; }
	public ArrayList<Neighbour> getNei() { return NEI; }
	
	public void setCity(City city) { CITY = city; ID = city.getID(); }
	public void setUsed(boolean used){ USED = used; }
	
	public void setNei(Sequence parent1,  Sequence parent2) {

		int indice1=0, indice2=0;

		City indice1_prev = new City(), indice1_next = new City(), indice2_prev = new City(), indice2_next = new City();

		for (int i=0; i<parent1.getPath().size(); i++){

			if (parent1.getPath().get(i).getID() == ID){
				indice1 = i;
				break;
			}
		}

		for (int i=0; i<parent2.getPath().size(); i++){

			if (parent2.getPath().get(i).getID() == ID){
				indice2 = i;
				break;
			}
		}

		if (indice1 == 0){

			indice1_prev = parent1.getPath().get(parent1.getPath().size()-1);
			indice1_next = parent1.getPath().get(1);

		} else if (indice1 == parent1.getPath().size()-1) {

			indice1_prev = parent1.getPath().get(indice1-1);
			indice1_next = parent1.getPath().get(0);

		} else {

			indice1_prev = parent1.getPath().get(indice1-1);
			indice1_next = parent1.getPath().get(indice1+1);

		}

		Neighbour v1 = new Neighbour(indice1_prev, false);
		Neighbour v2 = new Neighbour(indice1_next, false);


		NEI.add(v1);
		NEI.add(v2);


		if (indice2 == 0){

			indice2_prev = parent2.getPath().get(parent2.getPath().size()-1);
			indice2_next = parent2.getPath().get(indice2+1);

		} else if (indice2 == parent2.getPath().size()-1) {

			indice2_prev = parent2.getPath().get(indice2-1);
			indice2_next = parent2.getPath().get(0);

		} else {

			indice2_prev = parent2.getPath().get(indice2-1);
			indice2_next = parent2.getPath().get(indice2+1);

		}


		if (NEI.get(0).getId() == indice2_prev.getID())
			NEI.get(0).setRep(true);

		else if (NEI.get(1).getId() == indice2_prev.getID())
			NEI.get(1).setRep(true);

		else {
			Neighbour v3 = new Neighbour(indice2_prev, false);
			NEI.add(v3);
		}


		if (NEI.get(0).getId() == indice2_next.getID())
			NEI.get(0).setRep(true);

		else if (NEI.get(1).getId() == indice2_next.getID())
			NEI.get(1).setRep(true);

		else {
			Neighbour v4 = new Neighbour(indice2_next, false);
			NEI.add(v4);
		}

	}
	
}
