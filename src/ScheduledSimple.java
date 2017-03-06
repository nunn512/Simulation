/**
 *
 * @author Nurzhan
 */
public class ScheduledSimple implements Steppable, Comparable {
	public AgentAbstract agentA;
	public int simTime;
	
	public ScheduledSimple(AgentAbstract a, int t) {
		agentA = a; 
		simTime = t;
	}
	
	public void step() {
		agentA.step();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		ScheduledSimple otherObject = (ScheduledSimple) o;
		return (this.simTime - otherObject.simTime);
	}

}

