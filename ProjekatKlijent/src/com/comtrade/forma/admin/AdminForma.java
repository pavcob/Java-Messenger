package com.comtrade.forma.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.Grupa;
import com.comtrade.domen.Profil;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.nit.NitObradaZahtevaServera;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminForma extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Object>dlmListaProfila = new DefaultListModel<>();
	private DefaultListModel<Object>dlmListaGrupa = new DefaultListModel<>();
	private JList jListaGrupa ;
	private JList jListaProfila;
	private List<Profil> sviProfili=new ArrayList<>();
	private Map<String, List<Profil>> clanoviGrupe=new HashMap<>();
	private JTextArea taPodaciProfila;
	private JTextArea taPodaciGrupa;
	String izabranaGrupa;
	
	

	public AdminForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelProfili = new JPanel();
		panelProfili.setBounds(12, 13, 444, 658);
		contentPane.add(panelProfili);
		panelProfili.setLayout(null);
		
		JScrollPane spListaProfila = new JScrollPane();
		spListaProfila.setBounds(83, 34, 269, 198);
		panelProfili.add(spListaProfila);
		
		jListaProfila = new JList(dlmListaProfila);
		jListaProfila.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				taPodaciProfila.setText("");
				Profil p=(Profil) jListaProfila.getSelectedValue();
				prikaziProfil(p);
			}

			
		});
		spListaProfila.setViewportView(jListaProfila);
		
		JLabel lblListaProfila = new JLabel("Lista Profila");
		lblListaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaProfila.setBounds(83, 0, 269, 31);
		panelProfili.add(lblListaProfila);
		
		taPodaciProfila = new JTextArea();
		taPodaciProfila.setFont(new Font("Arial", Font.PLAIN, 17));
		taPodaciProfila.setBounds(83, 262, 269, 241);
		panelProfili.add(taPodaciProfila);
		
		JPanel panelGrupe = new JPanel();
		panelGrupe.setBounds(477, 13, 444, 658);
		contentPane.add(panelGrupe);
		panelGrupe.setLayout(null);
		
		JScrollPane spListaGrupa = new JScrollPane();
		spListaGrupa.setBounds(54, 41, 269, 194);
		panelGrupe.add(spListaGrupa);
		
		jListaGrupa = new JList(dlmListaGrupa);
		jListaGrupa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				taPodaciGrupa.setText("");
				
				izabranaGrupa=(String) jListaGrupa.getSelectedValue();
				
				List<Profil> clanovi= clanoviGrupe.get(izabranaGrupa);
				prikaziGrupu(clanovi);
				
			}

			
		});
		spListaGrupa.setViewportView(jListaGrupa);
		
		JLabel lblListaGrupa = new JLabel("Lista Grupa");
		lblListaGrupa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaGrupa.setBounds(54, 13, 288, 16);
		panelGrupe.add(lblListaGrupa);
		
		taPodaciGrupa = new JTextArea();
		taPodaciGrupa.setFont(new Font("Arial", Font.PLAIN, 17));
		
		taPodaciGrupa.setBounds(54, 267, 269, 239);
		panelGrupe.add(taPodaciGrupa);
		
		KontrolerKlijent.getInstanca().postaviAdminFormu(this);
		NitObradaZahtevaServera nit=new NitObradaZahtevaServera();
		nit.start();
		
		vratiSveGrupeIProfile();
		
	}



	private void vratiSveGrupeIProfile() {
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_PODATKE_ZA_ADMINA);
		Komunikacija.vratiKomunikaciju().posalji(tk);
		
		
	}



	public void postaviPodatke(Map<String, Object> podaci) {
		this.sviProfili=(List<Profil>) podaci.get("sviProfili");
		this.clanoviGrupe=(Map<String, List<Profil>>) podaci.get("sveGrupe");
		
		ubaciULIste();
		
	}



	private void ubaciULIste() {
		for(Profil p:sviProfili) {
			dlmListaProfila.addElement(p);
		}
		
		for(Entry<String, List<Profil>> map:clanoviGrupe.entrySet()) {
			
			dlmListaGrupa.addElement(map.getKey());
			
		}
		
	}
	
	private void prikaziProfil(Profil p) {
		taPodaciProfila.removeAll();
		
		
		taPodaciProfila.append("Podaci profila: \n");
		taPodaciProfila.append("Korisnicko ime: "+p.getKorisnickoIme()+"\n");
		taPodaciProfila.append("Ime: "+p.getIme()+"\n");
		taPodaciProfila.append("Prezime: "+p.getPrezime()+"\n");
		taPodaciProfila.append("Datum rodjenja: "+p.getDatumRodjenja()+"\n");
		taPodaciProfila.append("Pol: "+p.getPol()+"\n");
		taPodaciProfila.append("Zanimanje: "+p.getZanimanje()+"\n");
		taPodaciProfila.append("E-mail: "+p.getMail()+"\n");
		
	}
	
	private void prikaziGrupu(List<Profil> clanovi) {
		
		
		taPodaciGrupa.append("Podaci grupe: \n");
		taPodaciGrupa.append("Naziv grupe:"+izabranaGrupa+"\n");
		taPodaciGrupa.append("Clanovi grupe: \n");
		for(Profil p:clanovi) {
			taPodaciGrupa.append("- "+p.getKorisnickoIme()+"\n");
		}
		
	}
}
