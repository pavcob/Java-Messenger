package com.comtrade.forma.caskanje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class FormaCaskanje extends JFrame {

	private JPanel contentPane;
	private JList jListaPoruka;
	private Profil mojProfil;
	private Profil profilPrijatelj;
	private JTextField textArea;
	private DefaultListModel  dlmListaPoruka = new DefaultListModel<>(); // default cuva podate a jList ih prikazujeeeee
	

	public Profil getMojProfil() {
		return mojProfil;
	}


	public void setMojProfil(Profil mojProfil) {
		this.mojProfil = mojProfil;
	}


	public Profil getProfilPrijatelj() {
		return profilPrijatelj;
	}


	public void setProfilPrijatelj(Profil profilPrijatelj) {
		this.profilPrijatelj = profilPrijatelj;
	}
	
	public FormaCaskanje(List<PrivatnaPoruka> listaPoruka, Profil mojProfil2, Profil profilPrijatelj2) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 775);
		setTitle("Privatno dopisivanje: "+mojProfil2.getKorisnickoIme()+"->"+profilPrijatelj2.getKorisnickoIme());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		for(PrivatnaPoruka pp : listaPoruka) {
			dlmListaPoruka.addElement(pp);
		}
		jListaPoruka = new JList(dlmListaPoruka);
		contentPane.add(jListaPoruka, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		this.mojProfil = mojProfil2;
		this.profilPrijatelj = profilPrijatelj2;
		
		textArea = new JTextField();
		textArea.setColumns(40);
		panel.add(textArea);
		
		JButton btnPosalji = new JButton("Posalji");
		btnPosalji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.POSALJI_PORUKU);
				PrivatnaPoruka pp = new PrivatnaPoruka(mojProfil.getIdProfil(), profilPrijatelj.getIdProfil(), mojProfil.getKorisnickoIme());
				pp.setTekstPoruke(textArea.getText());
				pp.setKorisnickoImePrimaoca(profilPrijatelj.getKorisnickoIme());
				pp.setKorisnickoImePosiljaoca(mojProfil.getKorisnickoIme());
				dlmListaPoruka.addElement(pp);
				textArea.setText("");
				tk.setKlijentObjekat(pp);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				
				
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				KontrolerKlijent.getInstanca().getListaOtvorenihFormi().remove(e.getWindow());
			}		
			
		});
		
		panel.add(btnPosalji);
	}


	public void primiPoruku(PrivatnaPoruka pp) {
		
		dlmListaPoruka.addElement(pp);
		
	}

}
