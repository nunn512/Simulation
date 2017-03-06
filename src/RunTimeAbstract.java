
/**
 *
 * @author Nurzhan
 */
import java.util.PriorityQueue;

import javax.swing.SwingWorker;

public abstract class RunTimeAbstract extends SwingWorker<Void, Void> {
	
	protected PriorityQueue<ScheduledSimple> pq;
	public int currentTime;
	
	
	public RunTimeAbstract() {
		this.pq = new PriorityQueue<>();
		this.currentTime = 0;
	}
	
	@Override
	protected Void doInBackground() throws Exception{
		return null;
	}
	
	
	/*
	public void execute() {
		while (pq.size() > 0) {
			ScheduledSimple temp = pq.poll();
			currentTime = temp.simTime;
			temp.step();
                        
                        //Worker worker = new Worker(this);
                        //worker.execute();
                        
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) { }
		}
		
		this.conclude();
		}
		*/
	
	
	public void addToSchedule(ScheduledSimple ss) {
		if (ss.simTime >= this.currentTime) {
			this.pq.offer(ss);
		}
	}
	
	public abstract void initRunTime(); 
	
	public abstract void conclude();

}

