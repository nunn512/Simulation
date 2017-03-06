import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;



public class AgentNode extends AgentAbstract {
	    RunTime rt;
	    public int positionX;
	    public int positionY;
	    public String ID;
	    public double theta;
	    public boolean isActive;
	    public double radius;
	    int cycles;
	
	    Color color;
	    
	    ArrayList<Packet> packets = new ArrayList<Packet>();
	    ArrayList<Packet> sentPackets = new ArrayList<Packet>();
	    
	    //HashMap<Packet, Integer> sentPackets = new HashMap<Packet, Integer>();
	    //Set<RoutingNode> routingTable = new HashSet<RoutingNode>();
	    
	    HashMap<AgentNode,Packet> MessageQuery = new HashMap<AgentNode,Packet>(); 
	    
	    
	    public AgentNode(RunTime rt, int x, int y, String id, double theta, double radius, int cycles, Color color){
	    	Random random = new Random();
	    	this.rt = rt;
	        positionX = x;
	        positionY = y;
	        ID = id;	        
	        this.theta = theta;
	        this.radius = radius;
	        isActive = false;
	        this.cycles = cycles;
	        this.color = color;
	    }
	    
	    public void initializeQuerry(){
	    	Iterator it = rt.agents.iterator();
	    	
	    	while(it.hasNext()){
	    		AgentAbstract t = (AgentAbstract) it.next();
	    		if(t.getClass() == AgentNode.class){
	    			if( !t.equals(this)){
	    				MessageQuery.put( (AgentNode) t, null );
	    			}
	    		}
	    	}
	    }
	    
	    /*
	    public void initializeRoutingTable(){
	    	Iterator it = rt.agents.iterator();
	    	
	    	while(it.hasNext()){
	    		AgentAbstract t = (AgentAbstract) it.next();
	    		if(t.getClass() == AgentNode.class){
	    			if( !t.equals(this)){
	    				RoutingNode node = new RoutingNode((AgentNode) t, null, -1);
	    				routingTable.add(node);
	    			}
	    			
	    		}
	    	}
	    }
	    */
	    
	    /*
	    public void deployNetwork(){
	    	Iterator it = rt.agents.iterator();
	    	
	    	while(it.hasNext()){
	    		AgentAbstract t = (AgentAbstract) it.next();
	    		
	    		
	    		Iterator itr = ((AgentNode) t).routingTable.iterator(); 
	    		
	    		if(t.getClass() == AgentNode.class){
	    			if( !t.equals(this)){
	    				
	    			}
	    			
	    		}
	    	}
	    	
	    }
	    */
	    
	    public boolean overlap(double x, double y, double radius){
	    	if(Math.sqrt((this.positionX - x)*(this.positionX - x)+(this.positionY - y)*(this.positionY - y)) <= this.radius+radius)  
	    		return true;
	    	else return false;
	    }
	    
	    
	    public boolean overlaps(AgentNode a, AgentNode b){
	    	if(euclidian_distance(a.positionX, a.positionY, b.positionX, b.positionY) <= a.radius+b.radius)
	    		return true;
	    	else return false;
	    }
	    
	    public double euclidian_distance(double x, double y, double v, double w){
	    	return Math.sqrt((x - v)*(x - v)+(y - w)*(y - w));
	    }
	    	    
	    public boolean messageComplete(){
	    	return true;
	    }
	    
	    

	    
	    
/*	    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is broadcast routing
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    
	    public void broadcast(){
	    	Set set = this.MessageQuery.entrySet();
	        Iterator it = set.iterator();
	        Random random = new Random();
	        int msgId = random.nextInt(1000);
	        
	    
	        //if(!packets.isEmpty()){
	        //	boolean isUnique;
	        	
	        	//if(msgId == packet.getpacketId())
	        	//	isUnique = false;
	        	
	        	//while(true){
	        		//msgId = random.nextInt(1000);
	        		//isUnique = true;
	        		//Iterator iter = packets.iterator();
	        	//while(it.hasNext()){
	        		//Packet packet = (Packet) it.next();
        			//if(msgId == packet.getpacketId()){
        				//isUnique = false;
        				//break;
        				//}
        			//}
        			//if(isUnique)
        				//break;
        		
	        	//}
	        	
	        //}
	    
	        
	        while(it.hasNext()) {
	        	Map.Entry me = (Map.Entry) it.next();
	        	AgentNode node = (AgentNode) me.getKey();
	        	if(overlap(node.positionX,node.positionY, node.radius)){
	        		Random rand = new Random();
 					if(rand.nextInt(100) > 0 ){
 						Packet packet = new Packet(msgId, "" , "Simple", this, node,  node );
 						packets.add(packet);
 						MessageQuery.put(node, packet);}
	             }
	        }
	    }
	    
	    @Override
		public void step() {
	        Random random = new Random();
	        
	        
	    	theta = rt.theta+random.nextGaussian()/3;
	    	
	    	broadcast();
	    	
	    	System.out.println("End of the step of "+this.ID);
	    	
	    	if(--cycles > 0)
	            rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+10));//rt.agents.size()));//+1));
	        
			rt.rtv.repaint();
	    }
	    
	    
	    public void listen(){
	    	Set set = this.MessageQuery.entrySet();
	        Iterator it = set.iterator();
	        Random random = new Random();
	        int msgId = random.nextInt(1000);
	        
	        while(it.hasNext()) {
	        	Map.Entry me = (Map.Entry) it.next();
	        	AgentNode node = (AgentNode) me.getKey();
	        	if(overlap(node.positionX,node.positionY, node.radius)){
	        		Random rand = new Random();
 					if(rand.nextInt(100) > 0 ){
 						Packet packet = new Packet(msgId, "" , "Simple", this, node,  node );
 						packets.add(packet);
 						MessageQuery.put(node, packet);}
	             }
	        }
	    }
	    
	    	    public void printHash(){
	    	  Set set = this.MessageQuery.entrySet();
	          Iterator i = set.iterator();
	          
	          StringBuilder b = new StringBuilder() ;
	          
	          while(i.hasNext()) {
	             Map.Entry me = (Map.Entry)i.next();
	             AgentNode node = (AgentNode) me.getKey();
	             b.append("From "+ID+" to"+node.ID + ": ");
	             System.out.print("From "+ID);
	             System.out.print(" to"+node.ID + ": ");
	             Packet packet = (Packet) me.getValue();
	             if(packet != null){
	            	 b.append(packet.getpacketId());
	            	 System.out.println(packet.getpacketId());}
	             else {System.out.println("Null");
	             	b.append("Null");
	             }
	          }

	          
	          //b.append("--------------------------------------");
	          //if(cycles == 1){
	        	//rt.b.append(b.toString());  
	          //}
	          
	    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is broadcast routing
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    
*/
	    
	    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is flood routing
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    
	    public void sendMessageToAll(Packet p){
	    	//Looping mechanism
	    	//Packet drop on success
	    	Iterator it = rt.agents.iterator();
	    	Random random = new Random();
	    	
	    	while(it.hasNext()){
	    		
	    		AgentAbstract node =(AgentAbstract)it.next();
	    		
	    		
	    		if(node.getClass() == AgentNode.class){
	    			AgentNode t = (AgentNode) node;
	    			
	    			if(this != t)//if this node is not the same as iterating node 
	    				{
	    				if(overlap(t.positionX, t.positionY, t.radius)){
	    					int msgID = random.nextInt(10000);
	    					
	    				
	    					if(p == null){
	    						Packet packet = new Packet(msgID, "Hello", "Simple" ,this, t, t);
	    						//System.out.println(packet.getpacketId()+packet.getpacket());
	    						System.out.println("Generating a new packet"+msgID+" "+packet.getSenderNodId().ID+" "+packet.getSenderHopId().ID);
	    						t.packets.add(packet);
	    					}
	    					
	    					else {
	    						//System.out.println("Generating a new packet"+msgID+" "+p.getSenderNodId().ID+" "+p.getSenderHopId().ID);
	    						if(t != p.getSenderNodId()){
	    							
	    							Packet packet = new Packet(p.getpacketId(), "Hello", "Simple" ,p.getSenderNodId(), t, t);
	    							System.out.println("Packet "+p.getpacketId()+" was retransmitted from "+this.ID+" to "+t.ID);
	    							t.packets.add(p);
	    						}
	    						else{
	    							System.out.println("Packet "+p.getpacketId()+" was omitted due to loop avoidance");
	    						}	    						
	    					}
	    				}
	    			}
	    			
	    		}
	    		
	    	}	
	    	    	
	    	return;
	    	
	    }
	    
	    public void recieveMessage(){
	    	Iterator it = this.packets.iterator();
	    	
	    	if(packets.isEmpty()){
	    		System.out.println("Packet stack is empty");
	    		return;
	    	
	    	}
	    	
	    	
	    	while(it.hasNext()){
	    		Packet p = (Packet) it.next();
	    		
	    		if(p.getTtl() <= 1){
	    			//this.packets.remove(p);
	    			System.out.println("Packet "+p.getpacketId()+" was removed from packet stack due to ttl timeout");
	    		}
	    		
	    		
	    		//else if(this == p.getSenderHopId()){
	    			//this.packets.remove(p);
	    			//System.out.println("Packet "+p.getpacketId()+" was omitted due to loop avoidance");
	    		//}
	    		
	    		else{
	    			p.setTtl(p.getTtl()-1);
	    			sendMessageToAll(p);
	    		}
	    		//this.packets.remove(p);
	    		it.remove();
	    		
	    		//System.out.println("Hello From "+this.ID+" "+p.getTtl());
	    		}
	    		
	    	
	    	return;
	    }

	    
	    @Override
		public void step() {
	        Random random = new Random();
	        
	        
	    	theta = rt.theta+random.nextGaussian()*5;
	    	
	    	System.out.println("Generated theta is "+theta);
	    	
	    	
	    	System.out.println(this.ID+" is checking its buffer");
	    	recieveMessage();
	    	
	    	System.out.println(this.ID+" is flooding");
	    	if(Math.abs(random.nextInt(100)) > 0)
	    		sendMessageToAll(null);
	        
	    	
	    	System.out.println("End of the step of "+this.ID);
	    	
	    	if(--cycles > 0)
	            rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+10));//rt.agents.size()));//+1));
	        
			rt.rtv.repaint();
	    }    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is flood routing
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    
	    
	    

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// This is older versions	    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    /*
	    @Override
		public void step() {
	    	Iterator it = rt.agents.iterator();
	    	
	    	if(it.hasNext()){
	    		AgentAbstract t = (AgentAbstract) it.next();
	    		if(t.getClass() == AgentNode.class){
	    			AgentNode node = (AgentNode) t;
	    			if( !node.equals(this) )
	    			{	
	    				if(overlap(node.positionX,node.positionY, node.radius)){
	    					//Random random = new Random();
	    					//if(random.nextInt(100) > 50){
	    						if(MessageQuery.containsKey(node))
	    							MessageQuery.put(node, "Y");//}
	    			}
	    			}
	    		}
	    	}
	    	System.out.println(this.ID);
	    	printHash();
	        System.out.println();
	    	
	    	if(--count > 0)
	            rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+rt.agents.size()));//+1));
	        
			rt.rtv.repaint();
	    }
		*/
	    
	    /*
		@Override
		public void step() {
			Random random = new Random();
			
			theta = rt.theta + rt.theta*random.nextGaussian()/3;
			
			if(theta > rt.theta)
				isActive = true;
			else isActive = false;
			
	        if(--cycles > 0)
	            rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+10));
	        
			rt.rtv.repaint();			
		}
	    */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is older versions	    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    
	    
}
