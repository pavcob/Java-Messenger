package com.comtrade.forma.caskanje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.transfer.TransferKlasa;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class FormaGrupnoCaskanje extends JFrame {

	private JPanel contentPane;
	private JList jListaPoruka;
	private JTextField textArea;
	private DefaultListModel<Object> dlmListaPoruka=new DefaultListModel<>();
	private JButton btnPosalji;
	private Profil profil;
	private Grupa izabranaGrupa;
	
	public Profil getProfil() {
		return profil;
	}


	public void setProfil(Profil profil) {
		this.profil = profil;
	}


	public Grupa getIzabranaGrupa() {
		return izabranaGrupa;
	}


	public void setIzabranaGrupa(Grupa izabranaGrupa) {
		this.izabranaGrupa = izabranaGrupa;
	}

	
	public FormaGrupnoCaskanje(List<GrupnaPoruka> listaGrupnihPoruka, Profil profil, Grupa izabranaGrupa) {
		this.profil=profil;
		this.izabranaGrupa=izabranaGrupa;
		for(GrupnaPoruka gp : listaGrupnihPoruka) {
			dlmListaPoruka.addElement(gp);
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Grupno dopisivanje: "+profil.getKorisnickoIme()+"->"+izabranaGrupa.getNazivGrupe());
		
		jListaPoruka = new JList(dlmListaPoruka);
		contentPane.add(jListaPoruka, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		textArea = new JTextField();
		textArea.setColumns(40);
		panel.add(textArea);
		
		btnPosalji = new JButton("Posalji");
		btnPosalji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.POSALJI_GRUPNU_PORUKU);
				GrupnaPoruka gp = new GrupnaPoruka(profil.getIdProfil(), izabranaGrupa.getIdGrupa());
				gp.setTekstPoruke(textArea.getText());
				gp.setKorisnickoImePosiljaoca(profil.getKorisnickoIme());
				gp.setImeGrupe(izabranaGrupa.getNazivGrupe());
				dlmListaPoruka.addElement(gp);
				textArea.setText("");
				tk.setKlijentObjekat(gp);
				Komunikacija.vratiKomunikaciju().posalji(tk);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				KontrolerKlijent.getInstanca().getListaGrupnihFormi().remove(e.getWindow());
			}		
			
		});
		panel.add(btnPosalji);
		
		
	}


	public void primiGrupnuPoruku(GrupnaPoruka poruka) {
			
			dlmListaPoruka.addElement(poruka);
			
		
	}

}
