package cs319;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JTabbedPane;

import java.awt.Panel;

import javax.swing.JList;

import java.awt.ScrollPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Color;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTree;

public class Lab3ServerClient extends JFrame {

	private JPanel contentPane;
	private static Lab3ServerClient frame;
	private static JList<String> list;
	private DataModel model;
	private Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Lab3ServerClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws FileNotFoundException
	 */
	public Lab3ServerClient() throws FileNotFoundException, UnknownHostException,
	IOException, InterruptedException{
		setTitle("Tabbed Swing Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(10, 11, 414, 250);
		contentPane.add(tabbedPane);

		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("List", null, panel_1, null);
		panel_1.setLayout(null);
		list = new JList<String>();
		list.setSelectionBackground(Color.YELLOW);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		list.setBorder(null);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 405, 177);
		scrollPane.setPreferredSize(new Dimension(400, 182));
		panel_1.add(scrollPane);
		model = new DataModel();
		list.setModel(model);
		panel_1.add(scrollPane);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = (String) JOptionPane.showInputDialog(frame,
						"What is the new company?", "Enter new company name",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if ((input != null) && (input.length() > 0)) {
					try {
						socket = new Socket("localhost", 4444);
						Thread.sleep(1000);
						PrintWriter out = new PrintWriter(socket.getOutputStream());
						out.println(input);
						out.flush();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						list.setModel(new DataModel());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAdd.setBounds(95, 188, 89, 23);
		panel_1.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = list.getSelectedValue();
				model.removeComp(item);
				
				try {
					list.setModel(new DataModel());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnRemove.setBounds(213, 188, 89, 23);
		panel_1.add(btnRemove);
		Panel panel = new Panel();
		tabbedPane.addTab("Tree", null, panel, null);
		panel.setLayout(null);

		Panel panel_2 = new Panel();
		tabbedPane.addTab("Table", null, panel_2, null);
	}

	class ServerHandler implements Runnable{
		Socket s;
		int num;
		
		public ServerHandler(Socket s, int n) {
			this.s = s;
			num = n;
		}

		@Override
		public void run() {
			Scanner in;
			
			try{
				in = new Scanner(s.getInputStream());
				
			}
			
			catch(Exception e1){
				
			}
			
		}
		
	}
}
