import java.util.ArrayList;

public class CostTable {
	ArrayList<AgentNode> pathToNode = new ArrayList<AgentNode>();
	int cost;
	
	
	public CostTable(){
		
	}
	
	public CostTable(int cost, ArrayList<AgentNode> path){
		this.cost = cost;
		pathToNode = path;
	}
	
	
	public void add(AgentNode node){
		if(node != null){
			pathToNode.add(node);
		}
		else return;
		return;
	}
	
	public void remove(AgentNode node){
		if(node != null){
			pathToNode.remove(node);
		}
		else return;
		return;
	}
	
	
	
	/*
	public void deleteNode(AgentNode node){
		Iterator it = (Iterator) pathToNode.iterator();

	}
	*/
	
	
	
}
