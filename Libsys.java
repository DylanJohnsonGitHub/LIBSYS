import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.sql.*;

public class Libsys {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//-----------------------SQL Stuff----------------------------------//
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","CSC331");   
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from books");  
		//while(rs.next())  
			//System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3));
		}catch(Exception e){ System.out.println(e);}
		
//-----------------------Set Up Frame-------------------------------//
		
		JFrame libFrame = new JFrame("LIBSYS Library Management System");
		
		libFrame.setLocation(300, 300);
		
//-----------MENU SET UP | NEW INFORMATION --------------------------//
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("File Area");
		menuBar.add(fileMenu);
		
		JMenuItem fileMenuItem = new JMenuItem("File Away", KeyEvent.VK_T);
		fileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		fileMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		fileMenu.add(fileMenuItem);
		
		JMenu booksMenu = new JMenu("Books");
		booksMenu.setMnemonic(KeyEvent.VK_B);
		booksMenu.getAccessibleContext().setAccessibleDescription("Book Area");
		menuBar.add(booksMenu);
		
		JMenuItem booksMenuItem = new JMenuItem("Books a Boy", KeyEvent.VK_U);
		booksMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		booksMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		booksMenu.add(booksMenuItem);
		
		JMenu membersMenu = new JMenu("Members");
		membersMenu.setMnemonic(KeyEvent.VK_M);
		membersMenu.getAccessibleContext().setAccessibleDescription("Member Area");
		menuBar.add(membersMenu);
		
		JMenuItem membersMenuItem = new JMenuItem("Member Access", KeyEvent.VK_V);
		membersMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		membersMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		membersMenu.add(membersMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription("Help Area");
		menuBar.add(helpMenu);
		
		JMenuItem helpMenuItem = new JMenuItem("Help Us", KeyEvent.VK_W);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		helpMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		helpMenu.add(helpMenuItem);
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		aboutMenu.getAccessibleContext().setAccessibleDescription("About Area");
		menuBar.add(aboutMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About Page", KeyEvent.VK_X);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		aboutMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		aboutMenu.add(aboutMenuItem);

//------------------END MENU SET UP --------------------------------------------------------------------//
		
		JTextArea textArea = new JTextArea(30,30);
		textArea.setEditable(false);
		
		JLabel dateLabel = new JLabel("System Date: ");
		JTextField dateField = new JTextField(5);
		dateField.setEditable(false);
		
		JLabel timeLabel = new JLabel("System Time: ");
		JTextField timeField = new JTextField(5);
		timeField.setEditable(false);
		
		JButton textButton = new JButton("Update Text Area");
		
		textButton.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent e) {
					
					//Sets the text within the text area according to the button
					try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","CSC331");  
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from books");
					String query = "";
					while (rs.next()) {	
					query = query + rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3) + "\n";
					textArea.setText(query);
					}
					}catch(Exception f){ System.out.println(f);}
					
				}
			
			});
		
		JPanel displayPanel = new JPanel();
		JPanel footPanel = new JPanel();
		
		displayPanel.add(textArea);
		
		footPanel.add(BorderLayout.WEST, timeLabel);
		footPanel.add(BorderLayout.WEST, timeField);
		footPanel.add(BorderLayout.CENTER, textButton);
		footPanel.add(BorderLayout.EAST, dateLabel);
		footPanel.add(BorderLayout.EAST, dateField);
		
		libFrame.setJMenuBar(menuBar);
		libFrame.getContentPane().add(BorderLayout.CENTER, displayPanel);
		libFrame.getContentPane().add(BorderLayout.SOUTH, footPanel);
		
		libFrame.pack();
		libFrame.setVisible(true);

	}

}
