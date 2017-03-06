
/**
 *
 * @author Nurzhan
 */
import java.util.ArrayList;

public abstract class AgentAbstract implements Steppable {
	public ArrayList<AgentAbstract> agents;
	
		
	public abstract void step();

}