import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ChartFrame {

	private JFrame frmGraphToolkit;
	private JComboBox regionPick;
	private JCheckBox chckbxTotal;
	private JCheckBox chckbxHealed;
	private JCheckBox chckbxDead;
	private JCheckBox chckbxSwab;
	private JCheckBox chckbxIntCare;
	private JCheckBox chckbxNew;
	private JCheckBox chckbxHospitalized;
	private JButton btnEsporta;
	private JTextField txtLarghezza;
	private JTextField txtAltezza;
	private JTextField txtNomeFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChartFrame window = new ChartFrame();
					window.frmGraphToolkit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChartFrame() {
		initialize();
		frmGraphToolkit.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGraphToolkit = new JFrame();
		frmGraphToolkit.setResizable(false);
		frmGraphToolkit.setTitle("Graph Toolkit");
		frmGraphToolkit.setBounds(100, 100, 351, 149);
		frmGraphToolkit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGraphToolkit.getContentPane().setLayout(null);
		
		regionPick = new JComboBox();
		regionPick.setRequestFocusEnabled(false);
		regionPick.setLightWeightPopupEnabled(false);
		regionPick.setFocusable(false);
		regionPick.setAutoscrolls(true);
		regionPick.setAlignmentX(Component.RIGHT_ALIGNMENT);
		regionPick.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		regionPick.setToolTipText("Seleziona una regione");
		regionPick.setModel(new DefaultComboBoxModel(new String[] {"Italia", "Abruzzo", "Basilicata", "Calabria", "Campania", "Emilia Romagna", "Friuli Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche", "Molise", "P.A. Bolzano", "P.A. Trento", "Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana", "Umbria", "Valle d'Aosta", "Veneto"}));
		regionPick.setSelectedIndex(0);
		regionPick.setBounds(10, 11, 123, 20);
		frmGraphToolkit.getContentPane().add(regionPick);
		
		chckbxTotal = new JCheckBox("Casi totali");
		chckbxTotal.setBounds(139, 10, 92, 23);
		frmGraphToolkit.getContentPane().add(chckbxTotal);
		
		chckbxHealed = new JCheckBox("Guariti");
		chckbxHealed.setBounds(139, 36, 82, 23);
		frmGraphToolkit.getContentPane().add(chckbxHealed);
		
		chckbxDead = new JCheckBox("Deceduti");
		chckbxDead.setBounds(139, 62, 82, 23);
		frmGraphToolkit.getContentPane().add(chckbxDead);
		
		chckbxSwab = new JCheckBox("Tamponi");
		chckbxSwab.setBounds(139, 88, 97, 23);
		frmGraphToolkit.getContentPane().add(chckbxSwab);
		
		JButton btnCalcola = new JButton("Calcola");
		btnCalcola.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (!chckbxTotal.isSelected() && !chckbxDead.isSelected() && !chckbxHealed.isSelected() && !chckbxSwab.isSelected() && !chckbxIntCare.isSelected() && !chckbxHospitalized.isSelected() && !chckbxNew.isSelected())
				{
					JOptionPane.showMessageDialog(null, "Seleziona almeno una casistica", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				ArrayList <DailyData> plottedData = new ArrayList<DailyData> ();
				DataFinder dataFinder= new DataFinder ();
				TimeSeriesCollection collectionData = new TimeSeriesCollection();
				String place="";
				String what="";
				

				
				//guardiamo la regione selezionata
				String region=regionPick.getSelectedItem().toString();
				if (region.equals("Italia"))
				{
					DataFinder.setRegion("");
					plottedData=dataFinder.getData();
					place =" in Italia";
				}
				else
				{
					DataFinder.setRegion(region);
					plottedData=dataFinder.getRegionData(region);
					place = " nella regione " + region;
				}
			//	DataFinder.setRegion(region);
		//		plottedData=dataFinder.getRegionData(region);
				
				if (chckbxTotal.isSelected())
				{
					what+=" infetti";
					TimeSeries positivi = new TimeSeries("Positivi");
					for (DailyData d : plottedData)
					{
						positivi.add(d.getStructuredDay(), d.getTotalCases());
					}
					
					collectionData.addSeries(positivi);
					
				}
				if (chckbxHealed.isSelected())
				{
					what+=" guariti";
					TimeSeries guariti = new TimeSeries("Guariti");
					for (DailyData d : plottedData)
					{
						guariti.add(d.getStructuredDay(), d.getHealed());
					}
					collectionData.addSeries(guariti);					
				}
				if (chckbxDead.isSelected())
				{
					what+=" deceduti";
					TimeSeries deceduti = new TimeSeries("Deceduti");
					for (DailyData d : plottedData)
					{
						deceduti.add(d.getStructuredDay(), d.getDead());
					}
					collectionData.addSeries(deceduti);					
				}
				//1
				if (chckbxSwab.isSelected())
				{
					what+=" tamponi";
					TimeSeries tamponi = new TimeSeries("Tamponi");
					for (DailyData d : plottedData)
					{
						tamponi.add(d.getStructuredDay(), d.getSwabs());
					}
					collectionData.addSeries(tamponi);					
				}
				//2
				if (chckbxIntCare.isSelected())
				{
					what+=" terapie Intensive";
					TimeSeries terapie = new TimeSeries("Terapie Intensive");
					for (DailyData d : plottedData)
					{
						terapie.add(d.getStructuredDay(), d.getIntensiveCare());
					}
					collectionData.addSeries(terapie);					
				}
				//3
				if (chckbxHospitalized.isSelected())
				{
					what+=" ricoverati";
					TimeSeries ricoverati = new TimeSeries("Ricoverati con sintomi");
					for (DailyData d : plottedData)
					{
						ricoverati.add(d.getStructuredDay(), d.getTotalHospitalized());
					}
					collectionData.addSeries(ricoverati);					
				}
				//4
				if (chckbxNew.isSelected())
				{
					what+=" nuovi casi";
					TimeSeries newCases = new TimeSeries("Nuovi Casi");
					for (DailyData d : plottedData)
					{
						newCases.add(d.getStructuredDay(), d.getNewPositive());
					}
					collectionData.addSeries(newCases);					
				}


			    JFreeChart chart = ChartFactory.createTimeSeriesChart(
			            "Andamento"+what+place, // Chart
			            "Giorni", // X-Axis Label
			            "Unità", // Y-Axis Label
			            collectionData, true, true, true);
			    chart.getTitle().setFont(new Font("Agency FB", Font.BOLD, 40));
	
			    ChartPanel panel = new ChartPanel(chart);
			    XYPlot plot = (XYPlot)chart.getPlot();
			    XYItemRenderer xyir = plot.getRenderer();
			    xyir.setSeriesPaint(5, Color.cyan);
			    xyir.setSeriesPaint(5, Color.black);
			    xyir.setSeriesPaint(4, Color.pink);
			    xyir.setSeriesPaint(3, Color.orange);
			    xyir.setSeriesPaint(2, Color.red);
			    xyir.setSeriesPaint(1, Color.green);
			    xyir.setSeriesPaint(0, Color.blue);

			    plot.setBackgroundPaint(new Color(255,228,196));
			    JFrame jf = new JFrame();
			    jf.setTitle(  "Andamento"+what+place);
			    jf.setBounds(100, 100, 800, 450);
			    jf.setContentPane(panel);
			    jf.setVisible(true);

				
			}
		});
		btnCalcola.setBounds(246, 86, 89, 23);
		frmGraphToolkit.getContentPane().add(btnCalcola);
		
		chckbxIntCare = new JCheckBox("Terapie Intensive");
		chckbxIntCare.setBounds(6, 36, 123, 23);
		frmGraphToolkit.getContentPane().add(chckbxIntCare);
		
		chckbxNew = new JCheckBox("Nuovi Casi");
		chckbxNew.setBounds(6, 62, 97, 23);
		frmGraphToolkit.getContentPane().add(chckbxNew);
		
		chckbxHospitalized = new JCheckBox("Ricoverati");
		chckbxHospitalized.setBounds(6, 88, 97, 23);
		frmGraphToolkit.getContentPane().add(chckbxHospitalized);
		
		btnEsporta = new JButton("Esporta");
		btnEsporta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!chckbxTotal.isSelected() && !chckbxDead.isSelected() && !chckbxHealed.isSelected() && !chckbxSwab.isSelected() && !chckbxIntCare.isSelected() && !chckbxHospitalized.isSelected() && !chckbxNew.isSelected())
				{
					JOptionPane.showMessageDialog(null, "Seleziona almeno una casistica", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				ArrayList <DailyData> plottedData = new ArrayList<DailyData> ();
				DataFinder dataFinder= new DataFinder ();
				TimeSeriesCollection collectionData = new TimeSeriesCollection();
				String place="";
				String what="";
				

				
				//guardiamo la regione selezionata
				String region=regionPick.getSelectedItem().toString();
				if (region.equals("Italia"))
				{
					DataFinder.setRegion("");
					plottedData=dataFinder.getData();
					place =" in Italia";
				}
				else
				{
					DataFinder.setRegion(region);
					plottedData=dataFinder.getRegionData(region);
					place = " nella regione " + region;
				}
			//	DataFinder.setRegion(region);
		//		plottedData=dataFinder.getRegionData(region);
				
				if (chckbxTotal.isSelected())
				{
					what+=" infetti";
					TimeSeries positivi = new TimeSeries("Positivi");
					for (DailyData d : plottedData)
					{
						positivi.add(d.getStructuredDay(), d.getTotalCases());
					}
					
					collectionData.addSeries(positivi);
					
				}
				if (chckbxHealed.isSelected())
				{
					what+=" guariti";
					TimeSeries guariti = new TimeSeries("Guariti");
					for (DailyData d : plottedData)
					{
						guariti.add(d.getStructuredDay(), d.getHealed());
					}
					collectionData.addSeries(guariti);					
				}
				if (chckbxDead.isSelected())
				{
					what+=" deceduti";
					TimeSeries deceduti = new TimeSeries("Deceduti");
					for (DailyData d : plottedData)
					{
						deceduti.add(d.getStructuredDay(), d.getDead());
					}
					collectionData.addSeries(deceduti);					
				}
				//1
				if (chckbxSwab.isSelected())
				{
					what+=" tamponi";
					TimeSeries tamponi = new TimeSeries("Tamponi");
					for (DailyData d : plottedData)
					{
						tamponi.add(d.getStructuredDay(), d.getSwabs());
					}
					collectionData.addSeries(tamponi);					
				}
				//2
				if (chckbxIntCare.isSelected())
				{
					what+=" terapie Intensive";
					TimeSeries terapie = new TimeSeries("Terapie Intensive");
					for (DailyData d : plottedData)
					{
						terapie.add(d.getStructuredDay(), d.getIntensiveCare());
					}
					collectionData.addSeries(terapie);					
				}
				//3
				if (chckbxHospitalized.isSelected())
				{
					what+=" ricoverati";
					TimeSeries ricoverati = new TimeSeries("Ricoverati con sintomi");
					for (DailyData d : plottedData)
					{
						ricoverati.add(d.getStructuredDay(), d.getTotalHospitalized());
					}
					collectionData.addSeries(ricoverati);					
				}
				//4
				if (chckbxNew.isSelected())
				{
					what+=" nuovi casi";
					TimeSeries newCases = new TimeSeries("Nuovi Casi");
					for (DailyData d : plottedData)
					{
						newCases.add(d.getStructuredDay(), d.getNewPositive());
					}
					collectionData.addSeries(newCases);					
				}


			    JFreeChart chart = ChartFactory.createTimeSeriesChart(
			            "Andamento"+what+place, // Chart
			            "Giorni", // X-Axis Label
			            "Unità", // Y-Axis Label
			            collectionData, true, true, true);
			    chart.getTitle().setFont(new Font("Agency FB", Font.BOLD, 40));
	
			    ChartPanel panel = new ChartPanel(chart);
			    XYPlot plot = (XYPlot)chart.getPlot();
			    XYItemRenderer xyir = plot.getRenderer();
			    xyir.setSeriesPaint(5, Color.cyan);
			    xyir.setSeriesPaint(5, Color.black);
			    xyir.setSeriesPaint(4, Color.pink);
			    xyir.setSeriesPaint(3, Color.orange);
			    xyir.setSeriesPaint(2, Color.red);
			    xyir.setSeriesPaint(1, Color.green);
			    xyir.setSeriesPaint(0, Color.blue);

			    plot.setBackgroundPaint(new Color(255,228,196));
			    
			    // Sanifico i dati
			    String nomeFile="";
			    int height=0;
			    int width=0;
			    
			    if (txtNomeFile.getText()==null || txtNomeFile.getText().isEmpty())
			    	nomeFile="img";
			   	else
			   		nomeFile=txtNomeFile.getText();
			    nomeFile+=".png";
			    try
			    {
			    	height=Integer.parseInt(txtAltezza.getText());
			    }	
			    catch (NumberFormatException ne)
			    {
			    	height=800;
			    }
			    try
			    {
			    	width=Integer.parseInt(txtLarghezza.getText());
			    }	
			    catch (NumberFormatException ne)
			    {
			    	width=600;
			    }
			    
			    File file = new File(nomeFile);
			    
			    try
			    {
			    BufferedImage chartImage = chart.createBufferedImage(height, width, null);
			    ImageIO.write( chartImage, "png", file );
			    }
			    catch (Exception e23)
			    {
			    	;
			    }
			    
				Desktop desktop = Desktop.getDesktop();
				
				try {
					desktop.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				
			}
		});
		btnEsporta.setBounds(246, 10, 89, 23);
		frmGraphToolkit.getContentPane().add(btnEsporta);
		
		txtLarghezza = new JTextField();
		txtLarghezza.setText("600");
		txtLarghezza.setBounds(293, 37, 42, 20);
		frmGraphToolkit.getContentPane().add(txtLarghezza);
		txtLarghezza.setColumns(10);
		
		txtAltezza = new JTextField();
		txtAltezza.setText("800");
		txtAltezza.setBounds(246, 37, 42, 20);
		frmGraphToolkit.getContentPane().add(txtAltezza);
		txtAltezza.setColumns(10);
		
		txtNomeFile = new JTextField();
		txtNomeFile.setText("Nome File");
		txtNomeFile.setBounds(246, 63, 89, 20);
		frmGraphToolkit.getContentPane().add(txtNomeFile);
		txtNomeFile.setColumns(10);
		

	}
}
