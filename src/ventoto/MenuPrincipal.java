package ventoto;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	
	JTextArea textAreaVehicules = new JTextArea();
	static MenuPrincipal frame = new MenuPrincipal();
	
	String url = "jdbc:mysql://localhost:3306/voiture";
	String login = "root";
	String password = "";
	String resultat = "";
	Connection cn = null;
	Statement st = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		frame.lancementFenetrePrincipale();
	}
	
	public void lancementFenetrePrincipale() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.ActualiserListeVehicule();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVente = new JLabel("Les véhicules en vente");
		lblVente.setBounds(18, 41, 200, 13);
		contentPane.add(lblVente);
		
		JButton btnNouveauVehicule = new JButton("Nouveau Véhicule");
		btnNouveauVehicule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNouveauVehicule.setBounds(18, 10, 400, 21);
		contentPane.add(btnNouveauVehicule);
		
		JButton btnVerouiller = new JButton("Vérouiller");
		btnVerouiller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerouiller.setBounds(151, 232, 120, 21);
		contentPane.add(btnVerouiller);
		
		JButton btnVendre = new JButton("Vendre");
		btnVendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaisieVoiture nouvelleVoiture = new SaisieVoiture();
				nouvelleVoiture.lancementFenetreSaisie();
			}
		});
		btnVendre.setBounds(298, 232, 120, 21);
		contentPane.add(btnVendre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 64, 400, 158);
		contentPane.add(scrollPane);
		
		textAreaVehicules.setEditable(false);
		scrollPane.setViewportView(textAreaVehicules);
	}
	
	public void ActualiserListeVehicule() {
		textAreaVehicules.setText("");
		resultat = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, password);
			st = cn.createStatement();
			String sql = "select * from voiture order by Marque";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				resultat += rs.getString("Marque") + " " + rs.getString("Modele") + " " + rs.getString("Annee") + " " + rs.getString("PrixVente") + "€\n";
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		textAreaVehicules.setText(resultat);
	}
}
