
import javax.swing.SwingWorker;
/**
 *
 * @author Nurzhan
 */
public class Worker extends SwingWorker<Void,Void>{
    RunTime rt;
    
    public Worker(RunTime rt){
        this.rt = rt;
    }
    
    
    @Override
    protected Void doInBackground(){
        rt.execute();
        return null;
    }
    
}
