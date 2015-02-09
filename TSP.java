import java.util.*;


public class TSP {
	
	private ArrayList<Sequence> TOURNAMENT = new ArrayList<Sequence>();
	private ArrayList<Sequence> CROSSOVER = new ArrayList<Sequence>();
	private ArrayList<Sequence> MUTATION = new ArrayList<Sequence>();
	private ArrayList<Sequence> NEWGEN = new ArrayList<Sequence>();
	
	private long StartTime;
	private int PC = 30;
	private double PM = 0.9;
	private double BEST = Double.MAX_VALUE;
	private ArrayList<Integer> BESTSEQ = new ArrayList<Integer>();
	
	public TSP (ArrayList<Sequence> Sequences){ setNewgen(Sequences); }
	
	public ArrayList<Sequence> getTORNEIO(){ return TOURNAMENT; }
	
	public void setNewgen(ArrayList<Sequence> Sequences) { 
		for (int i=0; i<Sequences.size(); i++)
			NEWGEN.add(Sequences.get(i));
	}
	
	public void createTournament (){
		
		Random random = new Random();
		
		
		
		for (int i=0; i<NEWGEN.size(); i++){
			
			int best=random.nextInt(NEWGEN.size());
			
			int K = 40; //K
			
			int perc = (int) ((NEWGEN.size() * K) / 100);
			
			for (int j=0; j<perc; j++){ 
				
				int id1 = random.nextInt(NEWGEN.size());
				
				if (NEWGEN.get(id1).getFitness() < NEWGEN.get(best).getFitness())
					best=id1;
			}
			
			TOURNAMENT.add(NEWGEN.get(best));

			if (NEWGEN.get(best).getFitness() < BEST){
				BESTSEQ.clear();
				BEST = NEWGEN.get(best).getFitness();
				System.out.println("PATH: "+NEWGEN.get(best).getSeq());
				System.out.println("DISTANCE: "+NEWGEN.get(best).getFitness());
				System.out.println("TIME(ms): "+(System.currentTimeMillis() - StartTime));
				System.out.println();
			}
			
			
			
		}
	}
	
	public void createCrossover (){
		
		Random random = new Random();
		
		for (int i=0; i<TOURNAMENT.size(); i++){
			
	        Sequence child = new Sequence();
	        
	        int x = random.nextInt(TOURNAMENT.size());
	        int y = random.nextInt(TOURNAMENT.size());
	        
	        int k = random.nextInt(101);
	        
	        if (k <= PC){
	        
		        Crossover cross = new Crossover(TOURNAMENT.get(x), TOURNAMENT.get(y));
		        
		        cross.runCrossover();
		        
		        child = cross.getChild();
		        
		        child.calcFitness();
	        
	        } else {
	        	
	        	int p = random.nextInt(2);
	        	
	        	if (p == 0)
	        		child = TOURNAMENT.get(x);
	        	else
	        		child = TOURNAMENT.get(y);
	        	
	        }
	        
	        CROSSOVER.add(child);
	        
	        if (child.getFitness() < BEST){
	        	BEST = child.getFitness();
	        	System.out.println("Caminho: "+child.getSeq());
	        	System.out.println("Distancia: "+child.getFitness());
	        	System.out.println("Tempo(ms): "+(System.currentTimeMillis() - StartTime));
	        	System.out.println();
	        }
	
		}		
	}
	
	public void createMutation (){
		
		Random random = new Random();
		
		for (int i=0; i<CROSSOVER.size(); i++){
			
			Sequence atual = CROSSOVER.get(i);
			
			double k = random.nextFloat();
			
			if (k <= PM){
				int id1 = random.nextInt(atual.getPath().size());
				int id2 = random.nextInt(atual.getPath().size());
				
				Collections.swap(atual.getPath(), id1, id2);
				
				atual.calcFitness();
			}
			
			MUTATION.add(atual);
			
			if (atual.getFitness() < BEST){
				BEST = atual.getFitness();
				System.out.println("PATH: "+atual.getSeq());
				System.out.println("DISTANCE: "+atual.getFitness());
				System.out.println("TIME(ms): "+(System.currentTimeMillis() - StartTime));
				System.out.println();
			}
			
		}
	}
	
	public void createNewgen (){
		
		NEWGEN.clear();
		
		double k = 80; //percentagem troca
		
		double k1 = k/100; 	//percentagem array mutacao		
		
		int size = MUTATION.size();
		
		int sizeMUT = (int) (size * k1);
		int sizeTOR = size - sizeMUT;
		
		for (int i=0; i<sizeMUT; i++){
			
			NEWGEN.add(MUTATION.get(i));
			
		}
		
		for (int i=0; i<sizeTOR; i++){
			
			NEWGEN.add(TOURNAMENT.get(i));
			
		}
		
		TOURNAMENT.clear();
		MUTATION.clear();
		CROSSOVER.clear();		
		
	}
	
	public void run (long startTime) {
		
		StartTime = startTime;
		
		long timeSolution = System.currentTimeMillis() - StartTime;
		
		while (timeSolution < 60000){
			
			createTournament();
			createCrossover();
			createMutation();
			createNewgen();
			
			timeSolution = System.currentTimeMillis() - startTime;
		}
		
		System.out.println("Time Limit Reached");
	}

}
