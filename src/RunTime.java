
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JLabel;

/**
 *
 * @author Nurzhan
 */
public class RunTime extends RunTimeAbstract {
    ArrayList<AgentAbstract> agents = new ArrayList<AgentAbstract>();
    RunTimeView rtv;
    final public double theta = 25.0;
    AgentFusionCenter fusionCenter;
    public JLabel label = new JLabel();
    private int agentCount;
    private int cycles;
    
    public StringBuilder b;
    
    public boolean isPaused;
    
    public final Color[] colorPallette = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.RED};
    public final String[] colorsID = {"Black", "Blue", "Cyan", "Green", "Red"}; 
    
    
    public void setView(RunTimeView rtv){
        this.rtv = rtv;
    }
    
    public RunTime(int nodes, int time){
    	agentCount = nodes;
    	cycles = time;
    }
    
    
    
    public Color[] generateColors(int n)
    {
    	Color[] cols = new Color[n];
    	for(int i = 0; i < n; i++)
    	{
    		cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
    	}
    	return cols;
    }
    
    
    
    @Override
    public void initRunTime() {
       //final double radius = 100.0;
        fusionCenter = new AgentFusionCenter(this, theta,cycles);
        Color[] cols;
        
        int i = 0;
        int j = 0;
        
        cols = generateColors(agentCount);
        
        while( agentCount-- > 0 ){
        	Random random = new Random();       	
        	agents.add(new AgentNode(this, random.nextInt(500 - 20), 
        	        random.nextInt(500 - 20), "Node_"+i+"_"+(j+1), theta,
        	        random.nextDouble()*60.0+40.0, cycles, cols[i++]));
        	
        	if(j > 4){
        		j = 0;
        	}
        }
        
        agents.add(fusionCenter);
        
        
        
        Iterator it = agents.iterator();
         
        i = 1;
        
        while(it.hasNext()){
        	AgentAbstract t= (AgentAbstract) it.next();
        	
        	if(t.getClass() == AgentNode.class)
        	{
        		AgentNode node = (AgentNode) t;
        		node.initializeQuerry();
        		pq.offer(new ScheduledSimple(node,i));
        		i++;// +=10; //+= agents.size();
        		}
        	
        	else {
        		pq.offer(new ScheduledSimple((AgentFusionCenter)t,i));
        		i++; // += 10; //+= agents.size();}
        	}
        	
        }
        
        rtv.agents = agents;
    }

    @Override
    protected Void doInBackground() throws Exception{
    	while (pq.size() > 0 && !isCancelled()) {
    		if(!isPaused){
    			ScheduledSimple temp = pq.poll();
				currentTime = temp.simTime;
				temp.step();
		                        
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
		                        
			}
    		else {
    			System.out.println("RT Paused");
    			try {
                    synchronized(this){
                        wait(1000);
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Background interrupted");
                }
                }
			} 
    		
    	
	this.conclude();
			
	return null;
    }
    
    @Override
    public void conclude() {
    	System.out.println(theta);
    	System.out.println(fusionCenter.getAverageTheta());
    	label.setText(b.toString());
    	System.out.println("End of this process");
  }
        
}
