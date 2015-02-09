import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		ArrayList<City> Cities = new ArrayList<City>();
		
		for (int i=0; i<N; i++){
			
			int id = sc.nextInt();
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			
			City city = new City(id, x, y);
			
			Cities.add(city);	
		}
		
		sc.close();
		
		long startTime = System.currentTimeMillis();
		
		ArrayList<Sequence> Sequences = new ArrayList<Sequence>();
		
		for (int i=0; i<180; i++){
			
			Sequence seq = new Sequence(Cities);
			
			Sequences.add(seq);
			
		}
		
		TSP tsp = new TSP(Sequences);
		
		tsp.run(startTime);
		

	}

}
