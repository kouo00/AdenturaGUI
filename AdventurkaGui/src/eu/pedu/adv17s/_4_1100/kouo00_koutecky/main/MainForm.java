package eu.pedu.adv17s._4_1100.kouo00_koutecky.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;

import eu.pedu.adv17s._4_1100.kouo00_koutecky.logika.Potion;
import eu.pedu.adv17s._4_1100.kouo00_koutecky.logika.PotionGame;
import eu.pedu.adv17s._4_1100.kouo00_koutecky.logika.Room;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class MainForm {

	private JFrame frame;
	PotionGame pg;
	private JTextField tbCommand;
	JTextArea taOutput;
	JFXPanel jfxPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 973, 747);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		pg = PotionGame.getInstance();
		
		
		taOutput = new JTextArea();
		//frame.getContentPane().add(taOutput);
		JScrollPane scroll = new JScrollPane(taOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(57, 88, 589, 224);
		frame.getContentPane().add(scroll);		
		
	
		jfxPanel = new JFXPanel();
		jfxPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		jfxPanel.setForeground(Color.DARK_GRAY);
		jfxPanel.setBounds(57, 351, 589, 224);
		frame.getContentPane().add(jfxPanel);
		
		JButton btnRunCommand = new JButton("Spustit");
		btnRunCommand.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			callCommand(tbCommand.getText());
			
			
			}
		});
		btnRunCommand.setBounds(419, 9, 142, 25);
		frame.getContentPane().add(btnRunCommand);
		
			
		tbCommand = new JTextField();
		tbCommand.setBounds(99, 10, 319, 22);
		frame.getContentPane().add(tbCommand);
		tbCommand.setColumns(10);
		
		JLabel lblPkaz = new JLabel("Zadejte p\u0159\u00EDkaz:");
		lblPkaz.setBounds(12, 13, 86, 16);
		frame.getContentPane().add(lblPkaz);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnHra = new JMenu("Hra");
		menuBar.add(mnHra);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		
		mntmHelp.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       callHelp(true);
		    }
		});
		
		JMenuItem mntmNovHra = new JMenuItem("Nov\u00E1 hra");
		mntmNovHra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				startGame();
			}
		});
		mnHra.add(mntmNovHra);
		mnHra.add(mntmHelp);
	}
	private void callHelp(boolean clearTextArea) {
		if(isStartGame() == false) return;
		if(clearTextArea == true ) taOutput.setText("");
	    String result = pg.executeCommand("Help");
		taOutput.setText(taOutput.getText() + result);
	}
	
	private boolean isStartGame() {
		if(pg.isAlive() != true) {
			JOptionPane.showMessageDialog(frame,  "Nen� spu�t�n� hra!\nMenu: Hra  => Nov� hra");
			return false;
		}
		return true;
	}
	private void callCommand(String Command) {
		if(isStartGame() == false) return;
		String result = pg.executeCommand(Command);
		String strNeighbors = "";
		for(Room room : pg.getWorld().getCurrentPlace().getNeighbors()) {
			strNeighbors += room.getName()+"\n";
		}
		pg.getWorld().getCurrentPlace().getNeighbors();
		
		taOutput.setText(taOutput.getText() + "\n"+result);
		
/*		String strHelp = readHelp("C:\\Users\\Old�ich\\eclipse-workspace\\Adventura\\src\\eu\\pedu\\adv17s\\_4_1100\\kouo00_koutecky\\DATA\\Dokumentace.htm");
		File f = new File("C:\\Users\\Old�ich\\eclipse-workspace\\Adventura\\src\\eu\\pedu\\adv17s\\_4_1100\\kouo00_koutecky\\DATA\\Dokumentace.htm");
		Platform.runLater(() -> {
		    WebView webView = new WebView();
		   
		    webView.getEngine().load(f.toURI().toString());
		});*/
		
		taOutput.setText(taOutput.getText() + strNeighbors);
	}
	
	
	private String readHelp(String pathFileName) {
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			br = new BufferedReader(new FileReader(pathFileName));
		
		    sb = new StringBuilder();
		    String line = br.readLine();
	
		    while (line != null) {
		        sb.append(line);
		       line = br.readLine();
		    }
		    String everything = sb.toString();
		    br.close();
		}
		catch(IOException ex) {
			JOptionPane.showMessageDialog(frame,  ex.getMessage());
			return null;
		}
		 finally {
		    
		}
		return sb.toString();
	
	}
	private void startGame() {
		taOutput.setText("");
		taOutput.setText(pg.executeCommand(""));
	}
}
