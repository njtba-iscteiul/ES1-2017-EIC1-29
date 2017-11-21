package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import funcionalities.ButtonAction;

public class Frame {
	
	JFrame frame;
	
	public Frame(){
		
		frame = new JFrame("Software ES");
		
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		addComponents();
		
	}

	private void addComponents() {
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(3,3,0,10));
		frame.add(searchPanel, BorderLayout.NORTH);
		
		ButtonAction buttonAction = new ButtonAction();
		
		//Search Files
		
		JLabel fileLabel = new JLabel("Ficheiro: ");
		JLabel spamLabel = new JLabel("Spam: ");
		JLabel hamLabel = new JLabel("Ham: ");
		
		JTextField fileDirectory = new JTextField();
		
		JTextField spamDirectory = new JTextField();
		
		JTextField hamDirectory = new JTextField();
		
		JButton searchButton = new JButton("Procurar...");
		JButton spamButton = new JButton("Procurar...");
		JButton hamButton = new JButton("Procurar...");
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction.searchFile(fileDirectory);
			}
		});
		
		spamButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction.searchFile(spamDirectory);
			}
		});
		
		hamButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction.searchFile(hamDirectory);
			}
		});
		
		searchPanel.add(fileLabel);
		searchPanel.add(fileDirectory);
		searchPanel.add(searchButton);
		searchPanel.add(spamLabel);
		searchPanel.add(spamDirectory);
		searchPanel.add(spamButton);
		searchPanel.add(hamLabel);
		searchPanel.add(hamDirectory);
		searchPanel.add(hamButton);	
		
		JPanel rulesPanel = new JPanel();
		rulesPanel.setLayout(new GridLayout(1,0,10,0));
		frame.add(rulesPanel, BorderLayout.CENTER);
		
		//Manual
		
		JPanel manualPanel = new JPanel();
		manualPanel.setLayout(new BorderLayout());
		rulesPanel.add(manualPanel);
		
		JLabel manualLabel = new JLabel("Manual Configuration");
		
		JScrollPane manual = new JScrollPane();
		
		JScrollPane manualRules = new JScrollPane();
		manualRules.setPreferredSize(new Dimension(40,0));
		
		manualPanel.add(manualLabel, BorderLayout.NORTH);
		manualPanel.add(manual, BorderLayout.CENTER);
		manualPanel.add(manualRules, BorderLayout.EAST);
		
		//Manual Information
		
		JPanel manualInformation = new JPanel();
		manualInformation.setLayout(new GridLayout(2,2,10,0));
		manualPanel.add(manualInformation, BorderLayout.SOUTH);
		
		int manualCounterFN = 0;
		int manualCounterFS = 0;
		
		JLabel manualLabelFN = new JLabel("False Negative: " + String.valueOf(manualCounterFN));
		JLabel manualLabelFS = new JLabel("False Positive: " + String.valueOf(manualCounterFS));
		
		JButton counter = new JButton("Avaliar Qualidade");
		JButton manualSave = new JButton("Gravar");
		
		manualInformation.add(manualLabelFN);
		manualInformation.add(manualLabelFS);
		manualInformation.add(counter);
		manualInformation.add(manualSave);
	}

	public void init() {
		frame.setSize(800, 500);
		frame.setVisible(true);
	}

}