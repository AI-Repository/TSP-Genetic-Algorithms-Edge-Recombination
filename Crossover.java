import java.util.*;

public class Crossover {
	
	private ArrayList<Neighbourhood> AdjList = new ArrayList<Neighbourhood>();
	private Sequence Child = new Sequence();
	
	public Crossover (Sequence parent1, Sequence parent2) { createAdjList(parent1, parent2); } 
	
	
	public Sequence getChild() {return Child; }
	
	public void createAdjList(Sequence parent1, Sequence parent2){
		
		AdjList.clear();
		
		int id = 0;
		
		while (id < parent1.getPath().size()){
		
			for (int i=0; i<parent1.getPath().size(); i++){
				
				if (parent1.getPath().get(i).getID()-1 == id){
				
					Neighbourhood nei = new Neighbourhood(parent1.getPath().get(i), false, parent1, parent2);
				
					AdjList.add(nei);
					
					break;
				}
			}
			
			id++;
			
		}
				
	}
	
	public void runCrossover() {
		
		Random random = new Random();
		
		int atual = random.nextInt(AdjList.size());
		
		while (Child.getPath().size() < AdjList.size()){
			
			int next = addToChild(atual);
			
			atual = next;
			
			if (next == -1) break;
			
		}
		
	}
	
	public int checkNext(ArrayList<Neighbour> nei){
		
		int next=0;
		
		if (nei.isEmpty() == true){
			for (int j=0; j<AdjList.size(); j++)
				if (AdjList.get(j).isUsed() == false){
					next = j;
					break;
				} else {
					next = -1;
				}
			
		} else if (nei.size() == 1){
			next = nei.get(0).getId()-1;
			
		} else if (nei.size() == 2){
			ArrayList<Integer> idsRep = new ArrayList<Integer>();
			ArrayList<Integer> idsSin = new ArrayList<Integer>();
			for ( int i=0; i<nei.size(); i++)
				if (nei.get(i).isRep() == true){
					idsRep.add(i);
				} else {
					idsSin.add(i);
				}
			if (idsRep.size() == 1) //se so um vizinho for repetido entao o proximo e esse
				next = nei.get(idsRep.get(0)).getId()-1;
			else if (idsRep.size() == 2) { //se os dois vizinhos forem repetidos ou nenhum for, vai ver qual tem a lista mais pequena de vizinhos
				next = checkSmaller(nei, idsRep);
			} else {
				next = checkSmaller(nei, idsSin);
			}
			
		} else if (nei.size() == 3) {
			ArrayList<Integer> idsRep = new ArrayList<Integer>();
			ArrayList<Integer> idsSin = new ArrayList<Integer>();
			for (int i=0; i<nei.size(); i++){
				if (nei.get(i).isRep() == true){
					idsRep.add(i);
				} else {
					idsSin.add(i);
				}
			}
			if (idsRep.size() == 1){
				next = nei.get(idsRep.get(0)).getId()-1;
			} else if (idsRep.size() == 2){
				next = checkSmaller(nei, idsRep);
			} else {
				next = checkSmaller(nei, idsSin);
			}
			
				
		} else {
			ArrayList<Integer> idsSin = new ArrayList<Integer>();
			for (int i=0; i<nei.size(); i++)
				idsSin.add(i);
				
			next = checkSmaller(nei, idsSin);
		}
		return next;
	}
	
	public int checkSmaller (ArrayList<Neighbour> nei, ArrayList<Integer> ids){
		
		int min = Integer.MAX_VALUE;
		int id = Integer.MAX_VALUE;
		
		for (int i=0; i<ids.size(); i++){
			
			if(AdjList.get(nei.get(ids.get(i)).getId()-1).getNei().size() < min && AdjList.get(nei.get(ids.get(i)).getId()-1).getNei().size() > 0){
				
				min = AdjList.get(nei.get(ids.get(i)).getId()-1).getNei().size();
				id = AdjList.get(nei.get(ids.get(i)).getId()-1).getId()-1;
				
			}
			
		}
		
		if (id == Integer.MAX_VALUE){
			
			for (int j=0; j<AdjList.size(); j++)
				if (AdjList.get(j).isUsed() == false){
					id = j;
					break;
				}
			
		}
			
		
		return id;
	}

	
	public boolean isAlreadyChild (int id) {
		
		boolean check = false;
			
		if (AdjList.get(id).isUsed() == true) //se o vizinho ja esta na lista child entao check passa true
				
			check = true;	
		
		return check;
	}
	
	public int addToChild(int id){
		
		if (isAlreadyChild(id) == false) {
			
			Child.addToPath(AdjList.get(id).getCity());
			
			AdjList.get(id).setUsed(true); //marca o id como usado
				
			for (int i=0; i<AdjList.size(); i++){ //percorre a lista de vizinhos
				
				for (int j=0; j<AdjList.get(i).getNei().size(); j++){
					
					if (AdjList.get(i).getNei().get(j).getId()-1 == id){ //se o id do vizinho atual for igual ao id entao marca o vizinho como usado
							
						AdjList.get(i).getNei().remove(j);
					}
				}
			}	
				
			return checkNext(AdjList.get(id).getNei());
			
		} else { //se ja estiver na lista Child entao inverte a lista
			
			Collections.reverse(Child.getPath());
			
			int last = Child.getPath().size()-1;
			
			return checkNext(AdjList.get(Child.getPath().get(last).getID()-1).getNei());
			
		}
	}
}
