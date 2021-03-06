package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import funcionalities.ButtonAction;

public class Frame {
	
	JFrame frame;
	
	private int manualCounterFN;
	private int manualCounterFP;
	private int automaticCounterFN;
	private int automaticCounterFP;
	//private JLabel manualLabelFN;
	//private JLabel manualLabelFP;
	
	/**
	 * Construtor da frame
	 *
	 * @return ....
	 */
	public Frame(){
		
		frame = new JFrame("Software ES");
		
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		addComponents();
		
	}

	/**
	 * Cria a frame com os JTextField, JButton, JScrollPane, JLabel, ...
	 *
	 * @return ....
	 */
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
		fileDirectory.setName("rules.cf");
		
		JTextField spamDirectory = new JTextField();
		spamDirectory.setName("spam.log");
		
		JTextField hamDirectory = new JTextField();
		hamDirectory.setName("ham.log");
		
		JButton searchButton = new JButton("Procurar...");
		JButton spamButton = new JButton("Procurar...");
		JButton hamButton = new JButton("Procurar...");
		
		JScrollPane manual = new JScrollPane();
		JScrollPane automatic = new JScrollPane();
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				buttonAction.searchFile(fileDirectory);
				
				buttonAction.setManualTable(buttonAction.getManualTable());
				buttonAction.setAutomaticTable(buttonAction.getAutomaticTable());
				
				manual.setViewportView(buttonAction.getManualTable());
				automatic.setViewportView(buttonAction.getAutomaticTable());
				
				buttonAction.getManualTable().addMouseListener(new MouseListener() {
					
					@Override
					public void mousePressed(MouseEvent e) {
						
						int c = buttonAction.getManualTable().getSelectedColumn();
						int l = buttonAction.getManualTable().getSelectedRow();
						String valor = null;
						
						if(buttonAction.getManualTable().isColumnSelected(1)){
							valor = new JOptionPane().showInputDialog("Digite um valor (entre -5.0 e 5.0): ", 0.0);

							if(valor != null){
								try {
									while(Double.parseDouble(valor) < -5.0 || Double.parseDouble(valor) > 5.0){
										valor = new JOptionPane().showInputDialog("Digite um valor (entre -5.0 e 5.0): ", 0.0);
									}
									
									buttonAction.getManualTable().setValueAt(Double.parseDouble(valor), l, c);
								} catch (Exception e2) {
									System.out.println("Nao � um double");
								}
							}
						}
					}
					
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub	
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
					}
				});
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
		
		manualPanel.add(manualLabel, BorderLayout.NORTH);
		manualPanel.add(manual, BorderLayout.CENTER);
		
		//Manual Information
		
		JPanel manualInformation = new JPanel();
		manualInformation.setLayout(new GridLayout(2,2,10,0));
		manualPanel.add(manualInformation, BorderLayout.SOUTH);
		
		manualCounterFN = 0;
		manualCounterFP = 0;

		JLabel manualLabelFN = new JLabel("False Negative: " + manualCounterFN);
		JLabel manualLabelFP = new JLabel("False Positive: " + manualCounterFP);
		
		JButton counter = new JButton("Avaliar Qualidade");
		JButton manualSave = new JButton("Gravar");
		
		counter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				buttonAction.gerar(buttonAction.getManualTable(), counter);
				
				manualCounterFN = buttonAction.getCounterFN();
				manualCounterFP = buttonAction.getCounterFP();
			
				manualLabelFN.setText("False Negative: " + manualCounterFN);
				manualLabelFP.setText("False Positive: " + manualCounterFP);
				
			}

		});
		
		manualSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonAction.save(fileDirectory, buttonAction.getManualTable());	
			}
		});
		
		manualInformation.add(manualLabelFN);
		manualInformation.add(manualLabelFP);
		manualInformation.add(counter);
		manualInformation.add(manualSave);
		
		//Automatic
		
		JPanel automaticPanel = new JPanel();
		automaticPanel.setLayout(new BorderLayout());
		rulesPanel.add(automaticPanel);
		
		JLabel automaticLabel = new JLabel("Automatic Configuration");

		automaticPanel.add(automaticLabel, BorderLayout.NORTH);
		automaticPanel.add(automatic, BorderLayout.CENTER);
		
		//Automatic Information
		
		JPanel automaticInformation = new JPanel();
		automaticInformation.setLayout(new GridLayout(2,2,10,0));
		automaticPanel.add(automaticInformation, BorderLayout.SOUTH);
		
		automaticCounterFN = 0;
		automaticCounterFP = 0;
		
		JLabel automaticFN = new JLabel("False Negative: " + String.valueOf(automaticCounterFN));
		JLabel automaticFP = new JLabel("False Positive: " + String.valueOf(automaticCounterFP));
		
		JButton automaticGenerate = new JButton("Gerar");
		JButton automaticSave = new JButton("Gravar");
		
		automaticGenerate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				buttonAction.gerar(buttonAction.getAutomaticTable(), automaticGenerate);
				
				automaticCounterFN = buttonAction.getCounterFN();
				automaticCounterFP = buttonAction.getCounterFP();
			
				System.out.println("FN: " + buttonAction.getCounterFN() + " FP: " + buttonAction.getCounterFP());
				
				automaticFN.setText("False Negative: " + automaticCounterFN);
				automaticFP.setText("False Positive: " + automaticCounterFP);
				
				
			}
		});
		
		automaticSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				buttonAction.save(fileDirectory, buttonAction.getAutomaticTable());
			}
		});
		
		automaticInformation.add(automaticFN);
		automaticInformation.add(automaticFP);
		automaticInformation.add(automaticGenerate);
		automaticInformation.add(automaticSave);
		
		//-----//
		
	}

	/**
	 * Inicia a frame
	 *
	 * @return ....
	 */
	public void init() {
		frame.setSize(800, 500);
		frame.setVisible(true);
	}
	
	/**
	 * Retorna a frame
	 *
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}

}