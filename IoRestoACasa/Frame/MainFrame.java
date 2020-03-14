/*
 * Copiright 2020 Lorenzo Ganci © 
 * This file is part of SARS-CoV-2 Italian Monitor.

    SARS-CoV-2 Italian Monitor is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SARS-CoV-2 Italian Monitor is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SARS-CoV-2 Italian Monitor.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class MainFrame {

	private JFrame frmIoRestoACasa;
	private JLabel totalNumber;
	private JLabel deadNumber;
	private JLabel healed;
	private JLabel healedNumber;
	private JLabel dateLbl;
	private DataFinder dataFinder;
	private DailyData todayData;
	private JComboBox regionPick;
	private DailyData yesterdayData;
	private JLabel deltaHealLbl;
	private JLabel deltaTotalLbl;
	private JLabel deltaDeadLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmIoRestoACasa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		dataFinder = new DataFinder();
		todayData = dataFinder.getLastDayInfo();
		yesterdayData = dataFinder.getYesterdayInfo();
		frmIoRestoACasa = new JFrame();
		//frmRestiamoUniti.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/ico/virus (1).png")));
		frmIoRestoACasa.setTitle("SARS-CoV-2 Italian Monitor");
		frmIoRestoACasa.setForeground(new Color(0, 0, 0));
		frmIoRestoACasa.setResizable(false);
		frmIoRestoACasa.setBounds(100, 100, 600, 330);
		frmIoRestoACasa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIoRestoACasa.getContentPane().setLayout(null);
		
		JPanel healPanel = new JPanel();
		healPanel.setBounds(10, 93, 180, 161);
		frmIoRestoACasa.getContentPane().add(healPanel);
		
		healed = new JLabel("Guariti");
		healed.setFont(new Font("Agency FB", Font.BOLD, 50));
		healPanel.add(healed);
		
		healedNumber = new JLabel(String.valueOf(todayData.getHealed()));
		healedNumber.setHorizontalAlignment(SwingConstants.CENTER);
		healedNumber.setPreferredSize(new Dimension(150, 65));
		healedNumber.setFont(new Font("Agency FB", Font.PLAIN, 50));
		healedNumber.setForeground(new Color(0, 100, 0));
		healPanel.add(healedNumber);
		String deltaHealed = String.valueOf(todayData.getHealed()-yesterdayData.getHealed());
			if (!deltaHealed.contains("-"))
				deltaHealed="+" + deltaHealed;
			
		deltaHealLbl = new JLabel(deltaHealed);
		deltaHealLbl.setHorizontalAlignment(SwingConstants.CENTER);
		deltaHealLbl.setForeground(Color.GREEN);
		deltaHealLbl.setFont(new Font("Agency FB", Font.BOLD, 20));
		healPanel.add(deltaHealLbl);
		
		JPanel totalPanel = new JPanel();
		totalPanel.setBounds(200, 93, 194, 161);
		frmIoRestoACasa.getContentPane().add(totalPanel);
		
		JLabel total = new JLabel("  Totali  ");
		total.setFont(new Font("Agency FB", Font.BOLD, 50));
		totalPanel.add(total);
		
		totalNumber = new JLabel(String.valueOf(todayData.getTotalCases()));
		totalNumber.setPreferredSize(new Dimension(150, 65));
		totalNumber.setHorizontalAlignment(SwingConstants.CENTER);
		totalNumber.setForeground(new Color(0, 0, 139));
		totalNumber.setFont(new Font("Agency FB", Font.PLAIN, 50));
		totalPanel.add(totalNumber);
		
		String deltaTotal = String.valueOf(todayData.getNewPositive());
		if (!deltaTotal.contains("-"))
			deltaTotal="+" + deltaTotal;

		
		deltaTotalLbl = new JLabel(deltaTotal);
		deltaTotalLbl.setHorizontalAlignment(SwingConstants.CENTER);
		deltaTotalLbl.setFont(new Font("Agency FB", Font.BOLD, 20));
		deltaTotalLbl.setForeground(new Color(30, 144, 255));
		totalPanel.add(deltaTotalLbl);
		
		JPanel deadPanel = new JPanel();
		deadPanel.setBounds(404, 93, 180, 161);
		frmIoRestoACasa.getContentPane().add(deadPanel);
		
		JLabel dead = new JLabel("Deceduti");
		dead.setFont(new Font("Agency FB", Font.BOLD, 50));
		deadPanel.add(dead);
		
		deadNumber = new JLabel(String.valueOf(todayData.getDead()));
		deadNumber.setHorizontalAlignment(SwingConstants.CENTER);
		deadNumber.setPreferredSize(new Dimension(150, 65));
		deadNumber.setForeground(new Color(139, 0, 0));
		deadNumber.setFont(new Font("Agency FB", Font.PLAIN, 50));
		deadPanel.add(deadNumber);
		
		String deltaDead = String.valueOf(todayData.getDead()-yesterdayData.getDead());
		if (!deltaDead.contains("-"))
			deltaDead="+" + deltaDead;

		
		deltaDeadLbl = new JLabel(deltaDead);
		deltaDeadLbl.setHorizontalAlignment(SwingConstants.CENTER);
		deltaDeadLbl.setForeground(new Color(255, 0, 0));
		deltaDeadLbl.setFont(new Font("Agency FB", Font.BOLD, 20));
		deadPanel.add(deltaDeadLbl);
		
		JPanel datePanel = new JPanel();
		datePanel.setBounds(404, 28, 180, 48);
		frmIoRestoACasa.getContentPane().add(datePanel);
		
		JLabel updateLbl = new JLabel("Aggiornamento del:");
		updateLbl.setFont(new Font("Agency FB", Font.BOLD, 15));
		datePanel.add(updateLbl);
		
		dateLbl = new JLabel(todayData.getData());
		dateLbl.setFont(new Font("Agency FB", Font.BOLD, 15));
		datePanel.add(dateLbl);
		
		JButton btnAggiorna = new JButton("Aggiorna");
		btnAggiorna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dataFinder = new DataFinder();
				todayData = dataFinder.getLastDayInfo();
				yesterdayData = dataFinder.getYesterdayInfo();
				
				healedNumber.setText(String.valueOf(todayData.getHealed()));

				totalNumber.setText(String.valueOf(todayData.getTotalCases()));
				
				deadNumber.setText(String.valueOf(todayData.getDead()));

				dateLbl.setText(todayData.getData());
				
				String deltaHealed = String.valueOf(todayData.getHealed()-yesterdayData.getHealed());
				if (!deltaHealed.contains("-"))
					deltaHealed="+" + deltaHealed;
				
				deltaHealLbl.setText(deltaHealed);
				
				String deltaTotal = String.valueOf(todayData.getNewPositive());
				if (!deltaTotal.contains("-"))
					deltaTotal="+" + deltaTotal;
				
				deltaTotalLbl.setText(deltaTotal);
				
				
				String deltaDead = String.valueOf(todayData.getDead()-yesterdayData.getDead());
				if (!deltaDead.contains("-"))
					deltaDead="+" + deltaDead;

				
				deltaDeadLbl.setText(deltaDead);
				

				
			}
		});
		btnAggiorna.setBounds(143, 265, 89, 23);
		frmIoRestoACasa.getContentPane().add(btnAggiorna);
		
		JButton btnVaiAlDettaglio = new JButton("Vai al Dettaglio");
		btnVaiAlDettaglio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataFinder.setRegion("");
				DetailFrame detailFrame = new DetailFrame();
				detailFrame.setRegion("TotaleItalia");
				detailFrame.setVisible(true);
			}
		});
		btnVaiAlDettaglio.setBounds(10, 265, 123, 23);
		frmIoRestoACasa.getContentPane().add(btnVaiAlDettaglio);
		
		JButton btnCrediti = new JButton("Crediti");
		btnCrediti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Questo programma è stato scritto totalmente da Lorenzo Ganci "
						+ " sotto licenza generale pubblica GNU. \nSi ringrazia il dipartimento della protezione"
						+ "civile italiana nella persona"
						+ "di Umberto Rosini per la pubblicazione dei dati che\nvengono utilizzati in questo programma.\n"
						+ "Per eventuali contatti scrivere a lorenzo.ganci@yahoo.it");
			}
		});
		btnCrediti.setBounds(242, 265, 89, 23);
		frmIoRestoACasa.getContentPane().add(btnCrediti);
		
		regionPick = new JComboBox();
		regionPick.setRequestFocusEnabled(false);
		regionPick.setLightWeightPopupEnabled(false);
		regionPick.setFocusable(false);
		regionPick.setAutoscrolls(true);
		regionPick.setAlignmentX(Component.RIGHT_ALIGNMENT);
		regionPick.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		regionPick.setToolTipText("Seleziona una regione");
		regionPick.setModel(new DefaultComboBoxModel(new String[] {"Abruzzo", "Basilicata", "Calabria", "Campania", "Emilia Romagna", "Friuli Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche", "Molise", "P.A. Bolzano", "P.A. Trento", "Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana", "Umbria", "Valle d'Aosta", "Veneto"}));
		regionPick.setSelectedIndex(0);
		regionPick.setBounds(10, 28, 123, 20);
		frmIoRestoACasa.getContentPane().add(regionPick);
		
		JButton btnVaiAllaRegione = new JButton("Vai alla Regione");
		btnVaiAllaRegione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String region=regionPick.getSelectedItem().toString();
				DataFinder.setRegion(region);
				DetailFrame detailFrame = new DetailFrame();
				detailFrame.setRegion(region);
				detailFrame.setVisible(true);
			}
		});
		btnVaiAllaRegione.setBounds(10, 53, 125, 23);
		frmIoRestoACasa.getContentPane().add(btnVaiAllaRegione);
	}
}
