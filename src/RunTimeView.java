
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;

/**
 *
 * @author Nurzhan
 */
public class RunTimeView extends JComponent{
    public ArrayList<AgentAbstract> agents = new ArrayList<AgentAbstract>();
    final private Color[] colors = {Color.red, Color.blue, Color.yellow, Color.green, Color.black};
    

/*    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is broadcast graphics
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        
        Iterator it = agents.iterator();
        
        int j = 0;
        
        while (it.hasNext()) {
        	AgentAbstract t = (AgentAbstract) it.next();
        	if( t.getClass() == AgentNode.class)
        		{
        		AgentNode node = (AgentNode) t;
            	g2.setPaint(colors[j++]);
            	g2.fillRect( node.positionX - 6 , node.positionY - 6, 12, 12);
        		Shape theCircle = new Ellipse2D.Double(node.positionX - node.radius, node.positionY - node.radius, 2.0 * node.radius, 2.0 * node.radius);
        		g2.draw(theCircle);
        		
        		Set set = node.MessageQuery.entrySet();
        		Iterator it2 = set.iterator();
        		
        		while (it2.hasNext()) {		
        			Map.Entry mapEntry = (Map.Entry)it2.next();
        			Packet packet = (Packet) mapEntry.getValue(); 
        			
        			//System.out.println(packet.getpacket());
        			
        			if(packet != null && packet.getpacket() == "Simple"){
        				AgentNode nodet = (AgentNode) mapEntry.getKey();
        				
        				g2.drawLine(node.positionX, node.positionY, nodet.positionX, nodet.positionY);
        				
        				node.MessageQuery.put(nodet, null);
        			}
        		}
        		if( j > 4 )
        			j = 0;
        		}
			}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is broadcast graphics
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*/

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is flood graphics
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        
        Iterator it = agents.iterator();
        
        int j = 0;
        
        while (it.hasNext()) {
        	AgentAbstract t = (AgentAbstract) it.next();
        	if( t.getClass() == AgentNode.class)
        		{
        		AgentNode node = (AgentNode) t;
            	g2.setPaint(node.color);
            	g2.fillRect( node.positionX - 6 , node.positionY - 6, 12, 12);
        		Shape theCircle = new Ellipse2D.Double(node.positionX - node.radius, node.positionY - node.radius, 2.0 * node.radius, 2.0 * node.radius);
        		g2.draw(theCircle);
        		        		
        		Iterator it2 = node.packets.iterator();
        		
        		while(it2.hasNext()){
        			Packet p = (Packet) it2.next();
        			AgentNode tempo = (AgentNode) p.getSenderNodId();
        			
        			
        			g2.setPaint(p.getSenderNodId().color);
        		
        			int x = tempo.positionX;
        			int y =tempo.positionY;
        			System.out.println("RTV/Sent from "+tempo.ID+" to "+node.ID);
        				
        			g2.drawLine(node.positionX, node.positionY, x,y);

        		}
        	}
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is flood graphics
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    
    /*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is older graphics
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void paint(Graphics g){
Graphics2D g2 = (Graphics2D)g;

Iterator it = agents.iterator();

int j = 0;

while (it.hasNext()) {
AgentAbstract t = (AgentAbstract) it.next();
if( t.getClass() == AgentNode.class)
{AgentNode node = (AgentNode) t;

if( !node.isActive )    
g2.setPaint(colors[j++]);
else  g2.setPaint(Color.white);
g2.fillRect( node.positionX - 6 , node.positionY - 6, 12, 12);
Shape theCircle = new Ellipse2D.Double(node.positionX - node.radius, node.positionY - node.radius, 2.0 * node.radius, 2.0 * node.radius);
g2.draw(theCircle);
if( j > 4 )
j = 0;
}
}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is older graphics
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*/    
}
    