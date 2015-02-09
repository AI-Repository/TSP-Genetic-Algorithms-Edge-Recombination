import java.util.*;

public class Sequence {

	private double FITNESS;
	private ArrayList<City> PATH = new ArrayList<City>();
	private ArrayList<Integer> SEQ = new ArrayList<Integer>();
	
	public Sequence () { }
	public Sequence (ArrayList<City> Cities){ setPath(Cities);  createSeq(Cities); };
	public Sequence (double fit){ setFitness(fit); };
	
	public double getFitness () {return FITNESS;}
	public ArrayList<City> getPath () {return PATH;}
	public ArrayList<Integer> getSeq () {
		SEQ.clear();
		for (int i=0; i<PATH.size(); i++){
			SEQ.add(PATH.get(i).getID());
		}
		return SEQ;
		
	}
	
	public void setPath(ArrayList<City> Cities) {
		PATH.clear();
		for (int i=0; i<Cities.size(); i++){
			PATH.add(Cities.get(i));
		}
	}
	
	public void addToPath(City a){
		PATH.add(a);
		SEQ.add(a.getID());
	}
	
	public void setFitness(double fit) {
		FITNESS = fit;
	}
	
	public void createSeq (ArrayList<City> Cities){
		
		Collections.shuffle(PATH);
		
		calcFitness();
		
	}
	
	public void calcFitness(){			
		
		double distance=0;
		
		for (int i=0; i<PATH.size()-1; i++){
			distance += Math.sqrt((Math.pow(PATH.get(i+1).getX() - PATH.get(i).getX(), 2)) + (Math.pow(PATH.get(i+1).getY() - PATH.get(i).getY(), 2)));
		}
		
		distance += Math.sqrt((Math.pow(PATH.get(0).getX() - PATH.get(PATH.size()-1).getX(), 2)) + (Math.pow(PATH.get(0).getY() - PATH.get(PATH.size()-1).getY(), 2)));
		
		FITNESS = distance;
	}
		
}
