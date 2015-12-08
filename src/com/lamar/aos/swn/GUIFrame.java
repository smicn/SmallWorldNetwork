/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Suresh Vadlakonda; Shaomin (Samuel) Zhang
 * 
 * Email : svadlakonda@lamar.edu
 *         smicn@foxmail.com
 * */
package com.lamar.aos.swn;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import com.lamar.aos.swn.Exceptions.ExceptionK;
import com.lamar.aos.swn.Exceptions.ExceptionN;
import com.lamar.aos.swn.Exceptions.ExceptionP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This file contains the GUI related code for the project.
 * All the classes related to GUI are written in this file and main() is located here.
 * Used Java Swings for controls and awt classes for drawings.
 *
 * Suresh draws the initial version of GUI frames and controls;
 * Samuel integrates the GUI with the Graph domain modules and makes them work.
 **/
public class GUIFrame {

	private JFrame frmSwnSimulation;
	MyPanel panel_1;
	JScrollPane scPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFrame window = new GUIFrame();
					window.frmSwnSimulation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSwnSimulation = new JFrame();
		frmSwnSimulation.getContentPane().setForeground(Color.WHITE);
		frmSwnSimulation.setTitle("SWN Simulation");
		frmSwnSimulation.setBounds(100, 100, 700, 700);
		frmSwnSimulation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSwnSimulation.getContentPane().setLayout(new BorderLayout());
		
		//spitpane is used to separate the drawing area and frame content area
		//so that buttons are placed in one pane and drawings are done in other
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.1);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBounds(10, 37, 646, 435);
		frmSwnSimulation.getContentPane().add(splitPane);
		
		//this panel is attached or added to the main frame
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNodes = new JLabel("Nodes(N)");
		lblNodes.setBounds(10, 15, 61, 14);
		panel.add(lblNodes);
		
		panel_1 = new MyPanel();
		
		JComboBox comboBoxNodes = new JComboBox();
		comboBoxNodes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					int nodeCount = Integer.parseInt((String)event.getItem());
					//panel_1.setPreferredSize();
					panel_1.setNodeCount(nodeCount);
					panel_1.repaint();
				}
			}
		});
		comboBoxNodes.setBounds(69, 12, 55, 20);
		panel.add(comboBoxNodes);
		comboBoxNodes.setModel(new DefaultComboBoxModel(new String[] {"20", "50", "100", "1000", "5000"}));
		comboBoxNodes.setToolTipText("");
		
		JLabel lblNeighbors = new JLabel("Neighbors(K)");
		lblNeighbors.setBounds(131, 15, 83, 14);
		panel.add(lblNeighbors);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(212, 12, 46, 20);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"3", "5", "8", "10", "20"}));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					int k = Integer.parseInt((String)event.getItem());
					//panel_1.setPreferredSize();
					panel_1.setNeighbors(k);
					//panel_1.repaint();
				}
			}
		});
		
		JLabel lblP = new JLabel("P");
		lblP.setBounds(295, 15, 26, 14);
		panel.add(lblP);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(318, 12, 66, 20);
		panel.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0.1","0.01", "0.02"}));
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					float p = Float.parseFloat((String)event.getItem());
					//panel_1.setPreferredSize();
					panel_1.setProbability(p);
					//panel_1.repaint();
				}
			}
		});
		
		JButton btnStep = new JButton("Step");
		btnStep.setBounds(402, 11, 89, 22);
		panel.add(btnStep);
		btnStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				panel_1.doSimulateStep();
			}
		});
		
		JButton button = new JButton("Analyze");
		button.setBounds(512, 11, 89, 22);
		panel.add(button);
		button.setHorizontalAlignment(SwingConstants.RIGHT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				panel_1.gotoAnalysis();
			}
		});
		
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
      
        //MyPanel is added below side for the split pane
		scPane = new JScrollPane(panel_1);
		scPane.setVerticalScrollBarPolicy(scPane.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setRightComponent(scPane);
		
		panel_1.setBounds(14, 121, 553, 408);
		//Spit pane is finally added to the frame
		frmSwnSimulation.getContentPane().add(splitPane);
	}
	
}

/*
 * this class has the ability to communicate with back and code and intializes 
 * graphics need to the GUI based on the back end operations.
 * it implements the SWNListener interface and defines the methods in that interface
 */
class MyPanel extends JPanel implements SWN.SWNListener {

	private int n = 20, k = 3;
	private float p = 0.1f;
	
	// these lists to store the points and connections to draw the nodes and lines.
	private ArrayList<Integer> coordX, coordY;
	public ArrayList<Integer> node1, node2;
	
	private SWN swn;
	private int stepCount;
	
	// below methods are to responsible for set or add values to member variables and getter methods
	public void addX(int x, int index)
	{
		coordX.add(index, x); 
	}
	public void addY(int y, int index)
	{
		coordY.add(index, y);
	}
	
	public int getX(int index)
	{
		return coordX.get(index);
	}
	
	public int getY(int index)
	{
		return coordY.get(index);
	}
	
	
	//this method sets the nodes count(N) to local and SWN algorithm as well
	public void setNodeCount(int nCount)
	{
		coordX.clear();
		coordY.clear();
		node1.clear();
		node2.clear();
		n = nCount;
		try {
			swn.setN(nCount);
		} catch (ExceptionN e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stepCount = 0;
	}
	
	//it sets K value in local and also calls a method related to SWN to set K
	public void setNeighbors(int k)
	{
		node1.clear();
		node2.clear();
		this.k = k;
		try {
			swn.setK(k);
		} catch (ExceptionN e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionK e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stepCount = 0;
	}
	
	
	// it sets Probability value in local and also in SWN
	public void setProbability(float p)
	{
		this.p = p;
		try {
			swn.setP(p);
		} catch (ExceptionN e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionK e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionP e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stepCount = 0;
	}
	
	// this method to call to simulate the communication nodes for algorithm
	public void doSimulateStep() {
		if (0 == stepCount) {
			swn.startSimulate();
		}
		else if (1 <= stepCount && stepCount <= 4) {
			try {
				swn.stepSimulate();
			} catch (ExceptionN e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExceptionK e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExceptionP e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
			JOptionPane.showMessageDialog(null,"Computing end, please see data analysis.");
		}
		stepCount++;
	}
	
	//this method defines and calls the AnalyzerFrame Class to display the graph.
	public void gotoAnalysis() {
		//TODO:
		if(stepCount<5)
		{
			JOptionPane.showMessageDialog(null,"Please collect 5 samples to view analysis");
		}
		else
		{
			new AnalyzeFrame(getNodeCount(), getNeighbors(), getProbability());
		}
	}
	
	public int getNeighbors()
	{
		return k;
	}
	public int getNodeCount()
	{
		return n;
	}
	
	public float getProbability()
	{
		return p;
	}
	
	//constructor to MyPanel class. Basic needed initialization is defined here.
    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        coordX = new ArrayList<Integer>();
        coordY = new ArrayList<Integer>();
        node1 = new ArrayList<Integer>();
        node2 = new ArrayList<Integer>();
        
        // creating object to WattsStrogatz to begin the simulation
        swn = new WattsStrogatz();
        //registering the listener so that it can call the methods related to GUI
		swn.registerListener(this);
		stepCount = 0;
		
		//initialization with some default values.
		setNodeCount(20);
		setNeighbors(3);
		setProbability(0.1f);
		
    }
    
    public void setPreferredSize(Dimension preferredSize)
	{
		 super.setPreferredSize(preferredSize);
	}
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
		
        new Circle(this).drawCircle(g);
    }
    
    //below methods will be called by application
	@Override
	public void onPointCreated(int src) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onPointsConnected(int src, int dst) {
		node1.add(src);
		node2.add(dst);
		repaint();
	}
	
	@Override
	public void onPointsDisconnected(int src, int dst) {
		node1.remove(src);
		node2.remove(dst);
		repaint();
	}
}

/* 
 * this class is responsible for
 * to create ring network
 * and defining connections between them.
 */
class Circle
{
	int iNodes, iNeighbors;
	int sizeX, sizeY;
	int centerX, centerY;
	int radius;
	private MyPanel localPanel;
	
	public Circle(MyPanel panel)
	{
		localPanel = panel;
		iNodes = panel.getNodeCount();
		iNeighbors = panel.getNeighbors();
	}
	
	//network is drawn here.
	public void drawCircle(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		localPanel.setPreferredSize(new Dimension(15*iNodes, 15*iNodes));
		 g2d.setRenderingHint(
		 RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);
		        g2d.setColor(Color.white);
		        
		        centerX = localPanel.getWidth() / 2;
		        centerY = localPanel.getHeight() / 2;
		        int imin = Math.max(centerX, centerX);
		        radius = 4 * imin / 5;
		        int r2 = 5;
		        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
		        g2d.setColor(Color.blue);
		        
		        for (int i = 0; i < iNodes; i++) {
		            double t = 2 * Math.PI * i / iNodes;
		            int x = (int) Math.round(centerX + radius * Math.cos(t));
		            int y = (int) Math.round(centerY + radius * Math.sin(t));
		            localPanel.addX(x,i);
		            localPanel.addY(y,i);
		            g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
		        }
		        
		        g2d.setColor(Color.black);
		        for(int index = 0; index <localPanel.node1.size(); index++)
		        g.drawLine(localPanel.getX(localPanel.node1.get(index)), localPanel.getY(localPanel.node1.get(index)),
		        		localPanel.getX(localPanel.node2.get(index)), localPanel.getY(localPanel.node2.get(index)));
		       
	}
	
}

/*
 * this class is to create a frame to draw the analysis graph
 * when ever analyze button is clicked it will be appeared
 */
class AnalyzeFrame extends JFrame
{
	AnalyzeFrame(int N, int K, float P )
	{
		
		GraphPanel gPanel = new GraphPanel(N,K,P);
		setTitle("Simulation Result Analyzation");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		getContentPane().add(gPanel);
		
	}
}

/*
 * this class is to draw graph on GraphPanel and attach 
 * this panel to AnalyzeFrame frame
 */

 class GraphPanel extends JPanel {

    private int padding = 30;
    private int labelPadding = 25;
    private Color pointColor = new Color(100, 100, 100, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private List<Integer> maxscores;
    private List<Integer> meanscores;
    Analyzer  analyzer;

    public GraphPanel(int N, int K, float P) {
    	super();
        analyzer =  Analyzer.getInstance();
        maxscores = new ArrayList<Integer>();
        meanscores = new ArrayList<Integer>();
        
        //add N, K, P values here:
        addLabel(3*getWidth()/4, labelPadding, "N "+N+" K "+K+" P "+P);
        
        fillScores();
    }

    @Override
    //draw the lines and point related to analyzer graphs
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (analyzer.getSampleCount());
        double yScale = (double) getHeight() - 2 * padding - labelPadding;

        List<Point> graphPoints = new ArrayList<Point>();
        List<Point> graphPoints1 = new ArrayList<Point>();
        for (int i = 0; i < maxscores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) (analyzer.getMaxDiameter(i) * yScale)/ analyzer.getMaxY();
            graphPoints.add(new Point(x1, y1));
            
            
            //for mean scores
            int x2 = (int) (i * xScale + padding + labelPadding);
            int y2 = (int) (analyzer.getMeanDiameter(i) * yScale)/ analyzer.getMaxY();
            graphPoints1.add(new Point(x2, y2));
            
            addLabel((int)((i+1) * xScale + padding + labelPadding),(int) yScale+labelPadding, String.valueOf((float)(Math.pow(2,i+1)*(1f/16))));
        }
        
        addLabel((int)padding,(int)(yScale+50),"X-Axis:Probability, Y-Axis:Diameter");

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);


        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        //g2.setColor(lineColor);
        //draw the lines 
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
        	g2.setColor(Color.MAGENTA);
            int x1 = graphPoints.get(i).x;
            int y1 = getHeight() - padding-labelPadding -graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = getHeight() - padding-labelPadding -graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
            
            
            g2.setColor(Color.BLUE);
            int x3 = graphPoints1.get(i).x;
            int y3 = getHeight() - padding-labelPadding -graphPoints1.get(i).y;
            int x4 = graphPoints1.get(i + 1).x;
            int y4 = getHeight() - padding-labelPadding -graphPoints1.get(i + 1).y;
            g2.drawLine(x3, y3, x4, y4);
            
        }

        // plot the points
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = getHeight() - padding-labelPadding - graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
            addLabel(x, y-30,String.valueOf(analyzer.getMaxDiameter(i)));
            
            int x1 = graphPoints1.get(i).x - pointWidth / 2;
            int y1 = getHeight() - padding-labelPadding - graphPoints1.get(i).y - pointWidth / 2;
            int ovalW1 = pointWidth;
            int ovalH1 = pointWidth;
            g2.fillOval(x1, y1, ovalW1, ovalH1);
            addLabel(x1, y1-30,String.valueOf(analyzer.getMeanDiameter(i)));
        }
    }

    // get the scores from the analyzer class and store them in local
    private void fillScores()
    {
    	maxscores.clear();
    	meanscores.clear();
    	for(int index = 0; index <analyzer.getSampleCount(); index++)
    	{
    		maxscores.add(analyzer.getMaxDiameter(index));
    		//System.out.println(analyzer.getMaxDiameter(index)+" "+analyzer.getMeanDiameter(index));
    		meanscores.add(analyzer.getMeanDiameter(index));
    	}
    }
    
    // this function is to add labels to graphical window
    private void addLabel(int x, int y, String str)
    {
    	JLabel label = new JLabel();
    	label.setBounds(x, y,25*str.length(), 30);
    	label.setText(str);
    	add(label);
    }
}