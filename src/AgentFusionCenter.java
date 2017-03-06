import java.util.ArrayList;
import java.util.Iterator;

public class AgentFusionCenter extends AgentAbstract{
	private double theta; 
	RunTime rt;
	int cycles;
	
	public ArrayList<Double> averageReads = new ArrayList<Double>();
	
	
	public AgentFusionCenter(RunTime rt,double theta,int cycles){
		this.rt = rt;
		this.theta = theta;
		this.cycles = cycles;
	}
	
	public double getAverageTheta(){
		Iterator it = averageReads.iterator();
	
		double average = 0;
		
		while(it.hasNext()){
			average +=(double) it.next();
		}
		
		return average /= averageReads.size();
	
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		Iterator it = rt.agents.iterator();
		
		double averageTheta = 0;
		
		while(it.hasNext()){
			
			AgentAbstract t = (AgentAbstract) it.next();
			
			if( t.getClass() == AgentNode.class){
				AgentNode node = (AgentNode) t;
				averageTheta += node.theta;
				averageReads.add(node.theta);
			}
			
		}
		
		averageTheta /=  (rt.agents.size() - 1);
		System.out.println("Average Theta of this step is "+averageTheta);
		
		//int a = (int) averageTheta;
		//rt.label.setText("The average temperature is "+a);
		
		
		
		//Warning!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
		//rt.isPaused = true;
		//Warning!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		if(--cycles > 0)
            rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+10));//rt.agents.size()));//+1));
	}

}
