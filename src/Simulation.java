import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
/**
 *
 * @author Nurzhan
 */
public class Simulation extends JFrame{
    /**
     * @param args the command line arguments
     */
	JFrame frame = new JFrame();
	JLabel label= new JLabel();
	
	JMenuBar menuBar;
	JMenu editMenu;
	JMenu simMenu;
	JMenuItem startMenuItem;
	JMenuItem pauseMenuItem;
	JMenuItem stopMenuItem;
	JMenuItem editMenuItem;
	
	int nodes = 5;
	int cycles = 10;
	
	
	RunTime rt;
	RunTimeView rtv = new RunTimeView();
	
	//Worker worker;
	
	public Simulation(){
		createGUI();
	}
	
	private void createGUI(){
        //label.setText("");
        
        createMenuBar();
        frame.setSize(500,500);
        //frame.getContentPane().add(label, BorderLayout.BEFORE_FIRST_LINE);
        frame.getContentPane().add(rtv, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setTitle("WSN Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}
	
    public static void main(String[] args) {
        Simulation sim = new Simulation();
    }
    
    public void startSimulation(){
    	rt = new RunTime(nodes, cycles);
    	rt.isPaused = false;
        //worker = new Worker(rt);
    	//rtv = new RunTimeView();
    	
    	rt.setView(rtv);
        rt.label = label;
        rt.initRunTime();
        rt.execute();
        //worker.execute();
    }
    
    private void stopSimulation(){
    	rt.cancel(true);
    	
    }
    
    
    private void addDialog(){
    	 JTextField field1 = new JTextField(5);
         JTextField field2 = new JTextField(5);

         JPanel myPanel = new JPanel();
         myPanel.add(new JLabel("Nodes:"));
         myPanel.add(field1);
         
         
         myPanel.add(Box.createHorizontalStrut(15)); // a spacer
         myPanel.add(new JLabel("Time:"));
         myPanel.add(field2);
         
         int result = JOptionPane.showConfirmDialog(null, myPanel, 
                  "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
         if (result == JOptionPane.OK_OPTION) {
        	 
        	 try{
        		  nodes = Integer.parseInt(field1.getText());
        		  System.out.println(nodes);
        		  
        		  // is an integer!
        		} catch (NumberFormatException e) {
        		  // not an integer!
        		}
        	 
        	 try{
       		  cycles = Integer.parseInt(field2.getText());
       		  System.out.println(cycles);
       		  // is an integer!
       		} catch (NumberFormatException e) {
       		  // not an integer!
       		}
         }
    	
    }
    
    private void createMenuBar(){
    	menuBar =new JMenuBar(); 
        editMenu = new JMenu("Edit");
        simMenu = new JMenu("Simulation");
        
        editMenuItem = new JMenuItem("Edit");
        startMenuItem = new JMenuItem("Start");
        stopMenuItem = new JMenuItem("Stop");
        pauseMenuItem = new JMenuItem("Pause");
        
        startMenuItem.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e){
        				startSimulation();
        			}
        		});
        
        pauseMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				if(rt.isPaused){
					rt.isPaused = false;
					System.out.println("Running");
					}
				else {rt.isPaused = true;
				System.out.println("Paused");}
			}
		});
        
        stopMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
				stopSimulation();
			}
		});

        
        editMenuItem.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e){
        				addDialog();
        			}
        		}		
        );
        simMenu.add(startMenuItem);
        simMenu.add(stopMenuItem);
        simMenu.add(pauseMenuItem);
        
        editMenu.add(editMenuItem);
         
        menuBar.add(simMenu);
        menuBar.add(editMenu);
          
        menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
          
        frame.setJMenuBar(menuBar);
    }
  
 }	
    
    	


	
    	
    
    
    
 
    
	
