package ventoto;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaisieVoiture extends JFrame {

	private JPanel contentPane;
	private JTextField txtMarque;
	private JTextField txtModele;
	private JTextField txtKilometrage;
	private JTextField txtAnnee;
	private JTextField txtPrixDeVente;
	JScrollPane scrollPane = new JScrollPane();
	JTextPane txtpnDescription = new JTextPane();
	JComboBox comboBoxBoite = new JComboBox();
	JComboBox comboBoxEnergie = new JComboBox();
	
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
	public void lancementFenetreSaisie() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaisieVoiture frame = new SaisieVoiture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaisieVoiture() {
		setBounds(100, 100, 300, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMarque = new JTextField();
		txtMarque.setText("Marque");
		txtMarque.setBounds(18, 30, 250, 19);
		contentPane.add(txtMarque);
		txtMarque.setColumns(10);
		
		txtModele = new JTextField();
		txtModele.setText("Modèle");
		txtModele.setColumns(10);
		txtModele.setBounds(18, 80, 250, 19);
		contentPane.add(txtModele);
		
		txtKilometrage = new JTextField();
		txtKilometrage.setText("Kilométrage");
		txtKilometrage.setColumns(10);
		txtKilometrage.setBounds(18, 130, 250, 19);
		contentPane.add(txtKilometrage);
		
		txtAnnee = new JTextField();
		txtAnnee.setText("Année");
		txtAnnee.setColumns(10);
		txtAnnee.setBounds(18, 280, 250, 19);
		contentPane.add(txtAnnee);
		
		scrollPane.setBounds(18, 380, 250, 100);
		contentPane.add(scrollPane);
		
		txtpnDescription.setText("Description");
		scrollPane.setViewportView(txtpnDescription);
		
		comboBoxBoite.setBounds(18, 180, 250, 21);
		comboBoxBoite.addItem("Mécanique");
		comboBoxBoite.addItem("Automatique");
		contentPane.add(comboBoxBoite);
		
		comboBoxEnergie.setToolTipText("");
		comboBoxEnergie.setBounds(18, 230, 250, 21);
		comboBoxEnergie.addItem("Essence");
		comboBoxEnergie.addItem("Diesel");
		comboBoxEnergie.addItem("Hybride");
		comboBoxEnergie.addItem("Electrique");

		contentPane.add(comboBoxEnergie);
		
		JButton btnPhoto = new JButton("Ajouter photo");
		btnPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPhoto.setBounds(63, 511, 160, 21);
		contentPane.add(btnPhoto);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutVoiture();
				MenuPrincipal.frame.ActualiserListeVehicule();
				
			}
		});
		btnEnregistrer.setBounds(63, 542, 160, 21);
		contentPane.add(btnEnregistrer);
		
		txtPrixDeVente = new JTextField();
		txtPrixDeVente.setText("Prix de vente");
		txtPrixDeVente.setColumns(10);
		txtPrixDeVente.setBounds(18, 330, 250, 19);
		contentPane.add(txtPrixDeVente);
	}
	
	public void AjoutVoiture() {
		String marque = txtMarque.getText();
		String modele = txtModele.getText();
		String kilometre = txtKilometrage.getText();
		String boite = comboBoxBoite.getSelectedItem().toString();
		String energie = comboBoxEnergie.getSelectedItem().toString();
		String annee = txtAnnee.getText();
		String prix = txtPrixDeVente.getText();
		String description = txtpnDescription.getText();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, password);
			st = cn.createStatement();
			String sql = "insert into voiture values" + " (null,null,'" + marque + "','" + modele + "','" + kilometre + "','" + boite + "','" + energie + "','" + annee + "','" + prix + "','" + description + "')";
			st.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
