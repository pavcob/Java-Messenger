package com.comtrade.forma.profil;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.ProfilGrupa;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.nit.NitObradaZahtevaServera;
import com.comtrade.transfer.TransferKlasa;

public class ProfilForma extends JFrame {

	private JPanel contentPane;
	private Profil profil;
	private Profil profilPretraga;
	private JTextField tfPretraga;
	private JPanel panelGrupe;
	private JPanel panelMojProfil;
	private JPanel panelPrijatelji;
	private JTextField tfIme;
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblMail;
	private JLabel lblPol;
	private JLabel lblZanimanje;
	private JLabel lblDatumRodjenja;
	private JLabel lblUlogovaniKorisnik;
	private JLabel lblUnosImena;
	private JLabel lblUnosPrezimena;
	private JLabel lblUnosEMaila;
	private JLabel lblUnosPola;
	private JLabel lblUnosZanimanja;
	private JLabel lblUnosDatuma;
	private JLabel lblUlogovaniSteKao;
	private List<Profil>listaProfila = new ArrayList<>();
	private JLabel lblPretraga;
	private DefaultListModel<Object>dlm = new DefaultListModel<>();
	private HashMap<String, Object>hm = new HashMap<>();
	private JList listaPretraga;
	private JScrollPane scrollPane;
	private DefaultListModel dlmListaPretraga= new DefaultListModel();
	private JPanel panelPrikazProfila;
	private JLabel lblUnosZanimanjaProfila;
	private JLabel lblUnosPolaProfila;
	private JLabel lblUnosEMailaProfila;
	private JLabel lblUnosPrezimenaProfila;
	private JLabel lblUnosImenaProfila;
	private JLabel lblUlogovaniKorisnikProfila;
	private JLabel lblDatumRodjenjaProfila;
	private JLabel lblZanimanjeProfila;
	private JLabel lblPolProfila;
	private JLabel lblMailProfila;
	private JLabel lblPrezimeProfila;
	private JLabel lblImeProfila;
	private JLabel lblUnosDatumaProfila;
	private JLabel lblUlogovaniSteKaoProfil;	
	private StatusPrijateljstva sp;
	private DefaultListModel dlmListaPrijatelja = new DefaultListModel<>();
	private  JList jListaZahteva;
	private DefaultListModel  dlmListaZahteva = new DefaultListModel<>();
	private  JComboBox cbZahtevi;
	private List<StatusPrijateljstva>listaPrimljenihZahteva = new ArrayList<>();
	private List<StatusPrijateljstva>listaPoslatihZahteva = new ArrayList<>();
	private List<Profil>listaPrijatelja = new ArrayList<>();
	private JButton btnPrijatelji;
	private JButton btnMojProfil;
	private JButton btnGrupe;
	private  JButton btnPrihvatiZahtev;
	private StatusPrijateljstva zahtev;
	private JList jListaPrijatelja;
	private String izabranaLista;
	private JButton btnDodaj;
	private JButton btnIzbrisiPrijateljaPretraga;
	private JButton btnIzbrisiPrimljenZahtevPretraga;
	private JButton btnPrihvatiZahtevPretraga;
	private JButton btnIzbrisiPoslatiZahtevPretraga;	
	private JButton btnKreirajGrupu;
	private JLabel lblUnesiImeGrupe;
	private JTextField tfImeNoveGrupe;
	private DefaultListModel<Object> dlmGrupe = new DefaultListModel<>();
	private JList jListGrupe;
	private List<Grupa> listaGrupa = new ArrayList<>();
	private Map<String, List<Profil>> clanoviGrupe = new HashMap<>();
	private JLabel lblNazivGrupe;
	private DefaultListModel<Object> dlmClanoviGrupe = new DefaultListModel<>();
	private JList jListaClanovaGrupe;
	private JScrollPane spClanoviGrupe;
	private JButton btnDodajUGrupu;
	private JScrollPane spListaPrijateljaGrupa;
	private JList jListaPrijateljaGrupa;
	private Profil izabraniPrijatelj;
	private Grupa grupa;
	private JButton btnPretraziGrupe;
	private List<Grupa> mojeGrupe=new ArrayList<>();
	private JButton btnUdjiUGrupu;
	private JButton btnNapustiGrupu;
	private JButton btnZapocniGrupnoCaskanje;
	
	
	public ProfilForma(Profil pr) {
		this.profil = pr;
		
		vratiSveProfile();
		vratiSveGrupe();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1182, 198);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfPretraga = new JTextField();
		tfPretraga.setBounds(44, 46, 321, 29);
		panel.add(tfPretraga);
		tfPretraga.setColumns(10);
		
		btnMojProfil = new JButton("Moj profil");
		btnMojProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				btnMojProfil.setBackground(new Color(240, 240, 240));
				lblIme.setText(profil.getIme());
				lblPrezime.setText(profil.getPrezime());
				lblMail.setText(profil.getMail());
				lblPol.setText(profil.getPol());
				lblZanimanje.setText(profil.getZanimanje());
				lblDatumRodjenja.setText(profil.getDatumRodjenja());
				lblUlogovaniKorisnik.setText(profil.getKorisnickoIme());
				lblUlogovaniSteKao.setText("Ulogovani ste kao");
				lblUnosImena.setText("Ime :");
				lblUnosPrezimena.setText("Prezime :");
				lblUnosEMaila.setText("E-mail :");
				lblUnosPola.setText("Pol :");
				lblUnosZanimanja.setText("Zanimanje :");
				lblUnosDatuma.setText("Datum rodjenja :");
				
				
				prikaziMojProfil();
				
				
				
				
			}
		});
		btnMojProfil.setBounds(546, 147, 97, 25);
		panel.add(btnMojProfil);
		
		btnPrijatelji = new JButton("Prijatelji");
		btnPrijatelji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrijatelji.setBackground(new Color(240, 240, 240));
				dlmListaPrijatelja.clear();
				for(Profil p:listaPrijatelja) {
		 			
		 			dlmListaPrijatelja.addElement(p);
		 		}
				panelPrijatelji.setVisible(true);
				panelMojProfil.setVisible(false);
				panelPrikazProfila.setVisible(false);
				panelGrupe.setVisible(false);
				sakrijDugmadIzPretrage();
			}
		});
		btnPrijatelji.setBounds(732, 147, 97, 25);
		panel.add(btnPrijatelji);
		
		btnGrupe = new JButton("Grupe");
		btnGrupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnGrupe.setBackground(new Color(240,240,240));
				panelGrupe.setVisible(true);
				panelMojProfil.setVisible(false);
				panelPrijatelji.setVisible(false);
				panelPrikazProfila.setVisible(false);
				spClanoviGrupe.setVisible(false);
				lblNazivGrupe.setVisible(false);
				btnDodajUGrupu.setVisible(false);
				spListaPrijateljaGrupa.setVisible(false);
				btnUdjiUGrupu.setVisible(false);
				btnNapustiGrupu.setVisible(false);
				btnZapocniGrupnoCaskanje.setVisible(false);
				
				
			}
		});
		btnGrupe.setBounds(906, 147, 97, 25);
		panel.add(btnGrupe);
		
		lblPretraga = new JLabel("Pretraga");
		lblPretraga.setBounds(44, 13, 56, 16);
		panel.add(lblPretraga);
		
		JButton btnPretrazi = new JButton("Pretrazi Profil");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dlmListaPretraga.clear();
				if(listaProfila!=null) {
					String pretraga = tfPretraga.getText();
					for(Profil p : listaProfila) {
						if(p.getIme().toLowerCase().contains(pretraga.toLowerCase()) && !p.getKorisnickoIme().equals(profil.getKorisnickoIme())) {
							dlmListaPretraga.addElement(p);
						}
					}	
				}
				
				
			
			}

			
		});
		btnPretrazi.setBounds(399, 48, 135, 25);
		panel.add(btnPretrazi);
		
		btnPretraziGrupe = new JButton("Pretrazi Grupe");
		btnPretraziGrupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pretragaGrupa = tfPretraga.getText();
				dlmListaPretraga.clear();
				
				if(clanoviGrupe != null) {
					for(Entry<String, List<Profil>> grupe : clanoviGrupe.entrySet()) {
						if(grupe.getKey().toLowerCase().contains(pretragaGrupa.toLowerCase())) {
							dlmListaPretraga.addElement(grupe.getKey());
							
						}
					}
				}
				
				
			}

			
		});
		btnPretraziGrupe.setBounds(546, 48, 142, 25);
		panel.add(btnPretraziGrupe);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 122, 321, 44);
		panel.add(scrollPane);
		
		listaPretraga = new JList();
		listaPretraga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(listaPretraga.getSelectedValue() instanceof Profil) {
					profilPretraga  = new Profil();
					profilPretraga = (Profil) listaPretraga.getSelectedValue();
					lblImeProfila.setText(profilPretraga.getIme());
					lblPrezimeProfila.setText(profilPretraga.getPrezime());
					lblMailProfila.setText(profilPretraga.getMail());
					lblPolProfila.setText(profilPretraga.getPol());
					lblZanimanjeProfila.setText(profilPretraga.getZanimanje());
					lblDatumRodjenjaProfila.setText(profilPretraga.getDatumRodjenja());
					lblUlogovaniKorisnikProfila.setText(profilPretraga.getKorisnickoIme());
					lblUlogovaniSteKaoProfil.setText("Korisnicko ime : ");
					
					panelGrupe.setVisible(false);
					panelMojProfil.setVisible(false);
					panelPrijatelji.setVisible(false);
					panelPrikazProfila.setVisible(true);
					prikazProfila();
					
				}else {
					String nazivGrupe=(String) listaPretraga.getSelectedValue();
					Grupa g = new Grupa();
					g.setNazivGrupe(nazivGrupe);
					grupa = g;
					panelGrupe.setVisible(true);
					panelMojProfil.setVisible(false);
					panelPrijatelji.setVisible(false);
					panelPrikazProfila.setVisible(false);
					lblNazivGrupe.setVisible(true);
					spClanoviGrupe.setVisible(true);
					spListaPrijateljaGrupa.setVisible(false);
					
					lblNazivGrupe.setText(nazivGrupe);
					List<Profil> clanovi=clanoviGrupe.get(nazivGrupe);
					dlmClanoviGrupe.removeAllElements();
					boolean provera=false;
					for(int i=0;i<clanovi.size();i++) {
						if(i==0) {
							if(clanovi.get(i).getIdProfil()==profil.getIdProfil()) {
								btnDodajUGrupu.setVisible(true);
								btnUdjiUGrupu.setVisible(false);
								btnNapustiGrupu.setVisible(false);
								dlmClanoviGrupe.addElement(clanovi.get(i));
								provera=true;
								continue;
							}
						}
						if(clanovi.get(i).getIdProfil()==profil.getIdProfil()) {
							btnUdjiUGrupu.setVisible(false);
							btnDodajUGrupu.setVisible(false);
							btnNapustiGrupu.setVisible(true);
							provera=true;
						}
						dlmClanoviGrupe.addElement(clanovi.get(i));
					}
					if(provera==false) {
						btnUdjiUGrupu.setVisible(true);
						btnDodajUGrupu.setVisible(false);
						btnNapustiGrupu.setVisible(false);
					}
										
				}
			}			
		});		
		scrollPane.setViewportView(listaPretraga);
		listaPretraga.setModel(dlmListaPretraga);
		
		
		
		panelGrupe = new JPanel();
		panelGrupe.setBackground(UIManager.getColor("Button.background"));
		panelGrupe.setBounds(0, 211, 1182, 544);
		contentPane.add(panelGrupe);
		panelGrupe.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(121, 67, 233, 291);
		panelGrupe.add(scrollPane_3);
		
		jListGrupe = new JList(dlmGrupe);
		jListGrupe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				grupa = (Grupa) jListGrupe.getSelectedValue();
				spClanoviGrupe.setVisible(true);
				lblNazivGrupe.setVisible(true);
				spListaPrijateljaGrupa.setVisible(false);
				lblNazivGrupe.setText(grupa.getNazivGrupe());
				btnUdjiUGrupu.setVisible(false);
				btnZapocniGrupnoCaskanje.setVisible(true);
				List<Profil>clanovi = clanoviGrupe.get(grupa.getNazivGrupe());
				dlmClanoviGrupe.removeAllElements();
				for(Profil p : clanovi) {
					dlmClanoviGrupe.addElement(p);
					
				}
				if(grupa.getIdProfil() == profil.getIdProfil()) {
					btnDodajUGrupu.setVisible(true);
					btnNapustiGrupu.setVisible(false);
				}else {
					btnDodajUGrupu.setVisible(false);
					btnNapustiGrupu.setVisible(true);
				}
				
			}
		});
		scrollPane_3.setViewportView(jListGrupe);
		
		JLabel lblListaGrupa = new JLabel("Lista Grupa");
		lblListaGrupa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaGrupa.setBounds(121, 27, 97, 16);
		panelGrupe.add(lblListaGrupa);
		
		btnKreirajGrupu = new JButton("Kreiraj Grupu");
		btnKreirajGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String imeNoveGrupe = tfImeNoveGrupe.getText();
				if(imeNoveGrupe != null) {
				Grupa gr = new Grupa();
				gr.setNazivGrupe(imeNoveGrupe);
				gr.setIdProfil(profil.getIdProfil());
				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.NAPRAVI_NOVU_GRUPU);
				tk.setKlijentObjekat(gr);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				
				
			}
			}
		});
		btnKreirajGrupu.setBounds(169, 453, 124, 25);
		panelGrupe.add(btnKreirajGrupu);
		
		lblUnesiImeGrupe = new JLabel("Unesi ime grupe");
		lblUnesiImeGrupe.setBounds(127, 398, 97, 16);
		panelGrupe.add(lblUnesiImeGrupe);
		
		tfImeNoveGrupe = new JTextField();
		tfImeNoveGrupe.setBounds(251, 395, 116, 22);
		panelGrupe.add(tfImeNoveGrupe);
		tfImeNoveGrupe.setColumns(10);
		
		lblNazivGrupe = new JLabel("");
		lblNazivGrupe.setBounds(454, 102, 181, 28);
		panelGrupe.add(lblNazivGrupe);
		
		spClanoviGrupe = new JScrollPane();
		spClanoviGrupe.setBounds(454, 143, 181, 215);
		panelGrupe.add(spClanoviGrupe);
		
		jListaClanovaGrupe = new JList(dlmClanoviGrupe);
		spClanoviGrupe.setViewportView(jListaClanovaGrupe);
		
		btnDodajUGrupu = new JButton("Dodaj Clana");
		btnDodajUGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				spListaPrijateljaGrupa.setVisible(true);
				dlmListaPrijatelja.clear();
				for(Profil p:listaPrijatelja) {
		 			
		 			dlmListaPrijatelja.addElement(p);
		 		}
				
				
			}
		});
		btnDodajUGrupu.setBounds(758, 65, 124, 25);
		panelGrupe.add(btnDodajUGrupu);
		
		spListaPrijateljaGrupa = new JScrollPane();
		spListaPrijateljaGrupa.setBounds(758, 143, 181, 215);
		panelGrupe.add(spListaPrijateljaGrupa);
		
		jListaPrijateljaGrupa = new JList(dlmListaPrijatelja);
		jListaPrijateljaGrupa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				izabraniPrijatelj = (Profil) jListaPrijateljaGrupa.getSelectedValue();
				List<Profil> clanovi = clanoviGrupe.get(grupa.getNazivGrupe());
				boolean provera = false;
				for(Profil p : clanovi) {
					if(p.getIdProfil() == izabraniPrijatelj.getIdProfil()) {
						provera = true;
						break;
					}
					
				}
				if(provera == false) {
					
					clanovi.add(izabraniPrijatelj);
					dlmClanoviGrupe.addElement(izabraniPrijatelj);
					TransferKlasa tk = new TransferKlasa();
					tk.setOperacija(Konstante.UPISI_NOVOG_CLANA_U_GRUPU);
					ProfilGrupa pg = new ProfilGrupa(grupa, izabraniPrijatelj, Konstante.CLAN_GRUPE,0);
					tk.setKlijentObjekat(pg);
					Komunikacija.vratiKomunikaciju().posalji(tk);
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Vec postoji clan");
					
				}
				
				
				
			}
		});
		spListaPrijateljaGrupa.setViewportView(jListaPrijateljaGrupa);
		
		btnUdjiUGrupu = new JButton("Udji u grupu");
		btnUdjiUGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.UDJI_U_GRUPU);
				ProfilGrupa pg = new ProfilGrupa(grupa, pr, Konstante.CLAN_GRUPE,0);
				tk.setKlijentObjekat(pg);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				btnUdjiUGrupu.setVisible(false);
				btnNapustiGrupu.setVisible(true);
				
			}
		});
		btnUdjiUGrupu.setBounds(464, 374, 160, 25);
		panelGrupe.add(btnUdjiUGrupu);
		
		btnNapustiGrupu = new JButton("Napusti grupu");
		btnNapustiGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.NAPUSTI_GRUPU);
				ProfilGrupa pg = new ProfilGrupa(grupa, pr, Konstante.CLAN_GRUPE,0);
				tk.setKlijentObjekat(pg);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				
				btnNapustiGrupu.setVisible(false);
				btnUdjiUGrupu.setVisible(true);
				
				
			}
		});
		btnNapustiGrupu.setBounds(464, 412, 160, 25);
		panelGrupe.add(btnNapustiGrupu);
		
		btnZapocniGrupnoCaskanje = new JButton("Zapocni Grupno Caskanje");
		btnZapocniGrupnoCaskanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.ZAPOCNI_GRUPNO_CASKANJE);
				grupa = (Grupa) jListGrupe.getSelectedValue();
				if(grupa != null) {
					GrupnaPoruka gp = new GrupnaPoruka(profil.getIdProfil(), grupa.getIdGrupa());
					gp.setKorisnickoImePosiljaoca(profil.getKorisnickoIme());
					gp.setImeGrupe(grupa.getNazivGrupe());
					tk.setKlijentObjekat(gp);
					Komunikacija.vratiKomunikaciju().posalji(tk);
					
				}
						
			}
		});
		btnZapocniGrupnoCaskanje.setBounds(454, 65, 181, 25);
		panelGrupe.add(btnZapocniGrupnoCaskanje);
		
		panelPrijatelji = new JPanel();
		panelPrijatelji.setBackground(UIManager.getColor("Button.background"));
		panelPrijatelji.setBounds(0, 211, 1182, 544);
		contentPane.add(panelPrijatelji);
		 panelPrijatelji.setLayout(null);
		 
		 JScrollPane scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(92, 56, 219, 217);
		 panelPrijatelji.add(scrollPane_1);
		 
		  jListaPrijatelja = new JList(dlmListaPrijatelja);
		 scrollPane_1.setViewportView(jListaPrijatelja);
		 
		 JLabel lblListaPrijatelja = new JLabel("Lista Prijatelja");
		 lblListaPrijatelja.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblListaPrijatelja.setBounds(92, 13, 138, 30);
		 panelPrijatelji.add(lblListaPrijatelja);
		 
		 cbZahtevi = new JComboBox();
		 
		 btnPrihvatiZahtev = new JButton("Prihvati zahtev");
		 cbZahtevi.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		izabranaLista = (String) cbZahtevi.getSelectedItem();
		 		
		 		btnPrihvatiZahtev.setEnabled(true);
		 		
		 		if(izabranaLista.equals("Lista poslatih zahteva")) {
		 			dlmListaZahteva.clear();
		 			btnPrihvatiZahtev.setEnabled(false);
		 			for(StatusPrijateljstva sp: listaPoslatihZahteva) {
		 				
		 				dlmListaZahteva.addElement(sp);
		 			}
		 			
		 		}
		 		else if(izabranaLista.equals("Lista primljenih zahteva")) {
		 			
		 			dlmListaZahteva.clear();
		 			for(StatusPrijateljstva sp1: listaPrimljenihZahteva) {
		 				dlmListaZahteva.addElement(sp1);
		 			}
		 		}
		 	}
		 });
		 cbZahtevi.setFont(new Font("Tahoma", Font.BOLD, 15));
		 
		 cbZahtevi.setBounds(516, 13, 219, 30);
		 panelPrijatelji.add(cbZahtevi);
		 cbZahtevi.addItem("Lista poslatih zahteva");
		 cbZahtevi.addItem("Lista primljenih zahteva");
		 
		 JScrollPane scrollPane_2 = new JScrollPane();
		 scrollPane_2.setBounds(516, 56, 219, 217);
		 panelPrijatelji.add(scrollPane_2);
		 
		jListaZahteva = new JList(dlmListaZahteva);
		jListaZahteva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				zahtev = (StatusPrijateljstva) jListaZahteva.getSelectedValue();
				
			}
		});
		 scrollPane_2.setViewportView(jListaZahteva);
		 
		
		 btnPrihvatiZahtev.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
			 		if(zahtev!=null) {
			 			dlmListaPrijatelja.addElement(zahtev.getProfil2());
			 			listaPrijatelja.add(zahtev.getProfil2());
			 		
			 			TransferKlasa tk = new TransferKlasa();
			 			tk.setOperacija(Konstante.PRIHVACEN_ZAHTEV);
			 			tk.setKlijentObjekat(zahtev);
			 			Komunikacija.vratiKomunikaciju().posalji(tk);
			 		
			 			dlmListaZahteva.removeElement(zahtev);
			 			listaPrimljenihZahteva.remove(zahtev);
						prikazProfila();		 		
			 		}		 		
			 		zahtev = null;		 		
		 		}
		 	}
		 );
		 btnPrihvatiZahtev.setBounds(785, 56, 123, 25);
		 panelPrijatelji.add(btnPrihvatiZahtev);
		 
		 JButton btnIzbrisiZahtev = new JButton("Izbrisi zahtev");
		 btnIzbrisiZahtev.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		TransferKlasa tk = new TransferKlasa();
		 		if(izabranaLista.equals("Lista primljenih zahteva")) {
		 			tk.setOperacija(Konstante.IZBRISI_PRIMLJEN_ZAHTEV);
		 			listaPrimljenihZahteva.remove(zahtev);
		 			
		 		}
		 		else if(izabranaLista.equals("Lista poslatih zahteva")) {
		 			tk.setOperacija(Konstante.IZBRISI_POSLAT_ZAHTEV);
		 			listaPoslatihZahteva.remove(zahtev);
		 		}
		 		dlmListaZahteva.removeElement(zahtev);
		 		tk.setKlijentObjekat(zahtev);
		 		Komunikacija.vratiKomunikaciju().posalji(tk);
		 		prikazProfila();
		 	}
		 });
		 btnIzbrisiZahtev.setBounds(785, 115, 123, 25);
		 panelPrijatelji.add(btnIzbrisiZahtev);
		 
		 JButton btnZapocniCaskanje = new JButton("Zapocni caskanje");
		 btnZapocniCaskanje.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		TransferKlasa tk = new TransferKlasa();
		 		tk.setOperacija(Konstante.ZAPOCNI_CASKANJE);
		 		izabraniPrijatelj = (Profil) jListaPrijatelja.getSelectedValue();
		 		if (izabraniPrijatelj != null) {
		 			PrivatnaPoruka pp = new PrivatnaPoruka(profil.getIdProfil(), izabraniPrijatelj.getIdProfil(), profil.getKorisnickoIme());
		 			tk.setKlijentObjekat(pp);
		 			Komunikacija.vratiKomunikaciju().posalji(tk);
		 		}
		 	}
		 });
		 btnZapocniCaskanje.setBounds(323, 56, 150, 25);
		 panelPrijatelji.add(btnZapocniCaskanje);
		 
		 JButton btnIzbrisiPrijatelja = new JButton("Izbrisi prijatelja");
		 btnIzbrisiPrijatelja.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		TransferKlasa tk = new TransferKlasa();
		 		tk.setOperacija(Konstante.IZBRISI_PRIJATELJA);
		 		Profil izabraniPrijatelj = (Profil) jListaPrijatelja.getSelectedValue();
		 		StatusPrijateljstva sp = new StatusPrijateljstva(profil, izabraniPrijatelj, Konstante.IZBRISI_PRIJATELJA);
		 		tk.setKlijentObjekat(sp);
		 		Komunikacija.vratiKomunikaciju().posalji(tk);
		 	}
		 });
		 btnIzbrisiPrijatelja.setBounds(323, 104, 150, 25);
		 panelPrijatelji.add(btnIzbrisiPrijatelja);
		
	
		panelPrikazProfila = new JPanel();
		panelPrikazProfila.setBounds(0, 211, 1182, 544);
		panelPrikazProfila.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panelPrikazProfila);
		panelPrikazProfila.setLayout(null);
		
		lblImeProfila = new JLabel("");
		lblImeProfila.setBounds(396, 67, 93, 16);
		panelPrikazProfila.add(lblImeProfila);

		
		lblPrezimeProfila = new JLabel("");
		lblPrezimeProfila.setBounds(396, 117, 104, 16);
		panelPrikazProfila.add(lblPrezimeProfila);
		
		lblMailProfila = new JLabel("");
		lblMailProfila.setBounds(396, 171, 104, 16);
		panelPrikazProfila.add(lblMailProfila);
		
		lblPolProfila = new JLabel("");
		lblPolProfila.setBounds(396, 221, 104, 16);
		panelPrikazProfila.add(lblPolProfila);
		
		lblZanimanjeProfila = new JLabel("");
		lblZanimanjeProfila.setBounds(396, 273, 120, 16);
		panelPrikazProfila.add(lblZanimanjeProfila);
		
		lblDatumRodjenjaProfila = new JLabel("");
		lblDatumRodjenjaProfila.setBounds(396, 330, 93, 16);
		panelPrikazProfila.add(lblDatumRodjenjaProfila);
		
		lblUlogovaniKorisnikProfila = new JLabel("");
		lblUlogovaniKorisnikProfila.setBounds(578, 24, 114, 16);
		panelPrikazProfila.add(lblUlogovaniKorisnikProfila);
		
		lblUnosImenaProfila = new JLabel("Ime : ");
		lblUnosImenaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosImenaProfila.setBounds(241, 67, 83, 16);
		panelPrikazProfila.add(lblUnosImenaProfila);
		
		lblUnosPrezimenaProfila = new JLabel("Prezime : ");
		lblUnosPrezimenaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosPrezimenaProfila.setBounds(241, 117, 95, 16);
		panelPrikazProfila.add(lblUnosPrezimenaProfila);
		
		lblUnosEMailaProfila = new JLabel("E-mail : ");
		lblUnosEMailaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosEMailaProfila.setBounds(241, 171, 83, 16);
		panelPrikazProfila.add(lblUnosEMailaProfila);
		
		lblUnosPolaProfila = new JLabel("Pol : ");
		lblUnosPolaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosPolaProfila.setBounds(241, 221, 83, 16);
		panelPrikazProfila.add(lblUnosPolaProfila);
		
		lblUnosZanimanjaProfila = new JLabel("Zanimanje : ");
		lblUnosZanimanjaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosZanimanjaProfila.setBounds(241, 273, 107, 16);
		panelPrikazProfila.add(lblUnosZanimanjaProfila);
		
		lblUnosDatumaProfila = new JLabel("Datum rodjenja : ");
		lblUnosDatumaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosDatumaProfila.setBounds(241, 330, 120, 16);
		panelPrikazProfila.add(lblUnosDatumaProfila);
		
		lblUlogovaniSteKaoProfil = new JLabel("");
		lblUlogovaniSteKaoProfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUlogovaniSteKaoProfil.setBounds(326, 24, 193, 16);
		panelPrikazProfila.add(lblUlogovaniSteKaoProfil);
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StatusPrijateljstva sp = new StatusPrijateljstva(profil, profilPretraga, Konstante.POSALJI_ZAHTEV);
				for(StatusPrijateljstva provera: listaPoslatihZahteva) { 
					if (provera.getProfil2() == profilPretraga) {
						return;
					}
				}
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.POSALJI_ZAHTEV);
				
				tk.setKlijentObjekat(sp);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				listaPoslatihZahteva.add(sp);
				prikazProfila();
			}
		});
		btnDodaj.setBounds(632, 321, 97, 25);
		btnDodaj.setVisible(false);
		
		btnIzbrisiPrijateljaPretraga = new JButton("Izbrisi Prijatelja");
		btnIzbrisiPrijateljaPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TransferKlasa tk = new TransferKlasa();
		 		tk.setOperacija(Konstante.IZBRISI_PRIJATELJA);
		 		StatusPrijateljstva sp = new StatusPrijateljstva(profil, profilPretraga, Konstante.IZBRISI_PRIJATELJA);
		 		tk.setKlijentObjekat(sp);
		 		Komunikacija.vratiKomunikaciju().posalji(tk);
			}
		});
		btnIzbrisiPrijateljaPretraga.setBounds(632, 321, 190, 25);
		btnIzbrisiPrijateljaPretraga.setVisible(false);
		
		btnIzbrisiPrimljenZahtevPretraga = new JButton("Izbrisi Primljen Zahtev");
		btnIzbrisiPrimljenZahtevPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.IZBRISI_PRIMLJEN_ZAHTEV);
				StatusPrijateljstva nadjeniZahtev = null;
				for (StatusPrijateljstva primljeniZahtev : listaPrimljenihZahteva) {
					if (primljeniZahtev.getProfil2().getIdProfil() == profilPretraga.getIdProfil()) {
						nadjeniZahtev = primljeniZahtev;
						break;
					}
				}
				if (nadjeniZahtev != null)
				{
					tk.setKlijentObjekat(nadjeniZahtev);
					listaPrimljenihZahteva.remove(nadjeniZahtev);
					dlmListaZahteva.removeElement(nadjeniZahtev);
					Komunikacija.vratiKomunikaciju().posalji(tk);
					prikazProfila();
				}
			}
		});
		btnIzbrisiPrimljenZahtevPretraga.setBounds(832, 321, 190, 25);
		btnIzbrisiPrimljenZahtevPretraga.setVisible(false);
		
		btnIzbrisiPoslatiZahtevPretraga = new JButton("Izbrisi Poslati Zahtev");
		btnIzbrisiPoslatiZahtevPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.IZBRISI_POSLAT_ZAHTEV);
				StatusPrijateljstva nadjeniZahtev = null;
				for (StatusPrijateljstva poslatiiZahtev : listaPoslatihZahteva) {
					if (poslatiiZahtev.getProfil2().getIdProfil() == profilPretraga.getIdProfil()) {
						nadjeniZahtev = poslatiiZahtev;
						break;
					}
				}
				if (nadjeniZahtev != null)
				{
					tk.setKlijentObjekat(nadjeniZahtev);
					listaPoslatihZahteva.remove(nadjeniZahtev);
					dlmListaZahteva.removeElement(nadjeniZahtev);
					Komunikacija.vratiKomunikaciju().posalji(tk);
					prikazProfila();
				}
			}
		});
		btnIzbrisiPoslatiZahtevPretraga.setBounds(632, 321, 190, 25);
		btnIzbrisiPoslatiZahtevPretraga.setVisible(false);
		
		btnPrihvatiZahtevPretraga = new JButton("Prihvati Zahtev");
		btnPrihvatiZahtevPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatusPrijateljstva nadjeniZahtev = null;
				for (StatusPrijateljstva primljeniZahtev : listaPrimljenihZahteva) {
					if (primljeniZahtev.getProfil2().getIdProfil() == profilPretraga.getIdProfil()) {
						nadjeniZahtev = primljeniZahtev;
						break;
					}
				}
				if (nadjeniZahtev != null) {					
					dlmListaPrijatelja.addElement(nadjeniZahtev.getProfil2());
					listaPrijatelja.add(nadjeniZahtev.getProfil2());
					
					TransferKlasa tk = new TransferKlasa();
					tk.setOperacija(Konstante.PRIHVACEN_ZAHTEV);
					tk.setKlijentObjekat(nadjeniZahtev);
					Komunikacija.vratiKomunikaciju().posalji(tk);
					
					dlmListaZahteva.removeElement(nadjeniZahtev);
					listaPrimljenihZahteva.remove(nadjeniZahtev);
					prikazProfila();
				}
			}
		});
		btnPrihvatiZahtevPretraga.setBounds(632, 321, 190, 25);
		btnPrihvatiZahtevPretraga.setVisible(false);
		 
		panelPrikazProfila.add(btnDodaj);
		panelPrikazProfila.add(btnIzbrisiPrijateljaPretraga);
		panelPrikazProfila.add(btnIzbrisiPrimljenZahtevPretraga);
		panelPrikazProfila.add(btnIzbrisiPoslatiZahtevPretraga);
		panelPrikazProfila.add(btnPrihvatiZahtevPretraga);
		
		
		panelMojProfil = new JPanel();
		panelMojProfil.setBackground(UIManager.getColor("Button.background"));
		panelMojProfil.setBounds(0, 211, 1182, 544);
		contentPane.add(panelMojProfil);
		panelMojProfil.setLayout(null);
		
		lblIme = new JLabel("");
		lblIme.setBounds(396, 67, 93, 16);
		panelMojProfil.add(lblIme);

		
		lblPrezime = new JLabel("");
		lblPrezime.setBounds(396, 117, 104, 16);
		panelMojProfil.add(lblPrezime);
		
		lblMail = new JLabel("");
		lblMail.setBounds(396, 171, 104, 16);
		panelMojProfil.add(lblMail);
		
		lblPol = new JLabel("");
		lblPol.setBounds(396, 221, 104, 16);
		panelMojProfil.add(lblPol);
		
		lblZanimanje = new JLabel("");
		lblZanimanje.setBounds(396, 273, 120, 16);
		panelMojProfil.add(lblZanimanje);
		
		lblDatumRodjenja = new JLabel("");
		lblDatumRodjenja.setBounds(396, 330, 93, 16);
		panelMojProfil.add(lblDatumRodjenja);
		
		lblUlogovaniKorisnik = new JLabel("");
		lblUlogovaniKorisnik.setBounds(578, 24, 114, 16);
		panelMojProfil.add(lblUlogovaniKorisnik);
		
		lblUnosImena = new JLabel("");
		lblUnosImena.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosImena.setBounds(241, 67, 83, 16);
		panelMojProfil.add(lblUnosImena);
		
		lblUnosPrezimena = new JLabel("");
		lblUnosPrezimena.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosPrezimena.setBounds(241, 117, 95, 16);
		panelMojProfil.add(lblUnosPrezimena);
		
		lblUnosEMaila = new JLabel("");
		lblUnosEMaila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosEMaila.setBounds(241, 171, 83, 16);
		panelMojProfil.add(lblUnosEMaila);
		
		lblUnosPola = new JLabel("");
		lblUnosPola.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosPola.setBounds(241, 221, 83, 16);
		panelMojProfil.add(lblUnosPola);
		
		lblUnosZanimanja = new JLabel("");
		lblUnosZanimanja.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosZanimanja.setBounds(241, 273, 107, 16);
		panelMojProfil.add(lblUnosZanimanja);
		
		lblUnosDatuma = new JLabel("");
		lblUnosDatuma.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnosDatuma.setBounds(241, 330, 120, 16);
		panelMojProfil.add(lblUnosDatuma);
		
		lblUlogovaniSteKao = new JLabel("");
		lblUlogovaniSteKao.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUlogovaniSteKao.setBounds(326, 24, 193, 16);
		panelMojProfil.add(lblUlogovaniSteKao);
		
		KontrolerKlijent.getInstanca().postaviFormu(this);
		prikaziMojProfil();
		NitObradaZahtevaServera nzs = new NitObradaZahtevaServera();
		nzs.start();
		
		vratiSveStatusePrijateljstva(profil);
		proveraNeprocitanihPoruka(profil);
		
	}
	
	private void vratiSveStatusePrijateljstva(Profil profil2) {
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_SVE_STATUSE_PRIJATELJSTVA);
		tk.setKlijentObjekat(profil2);
		Komunikacija.vratiKomunikaciju().posalji(tk);
	}
	
	private void proveraNeprocitanihPoruka(Profil profil2) {
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.PROVERA_NEPROCITANIH_PORUKA);
		tk.setKlijentObjekat(profil2);
		Komunikacija.vratiKomunikaciju().posalji(tk);
		
	}

	private void vratiSveProfile() {
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_SVE_PROFILE);
		Komunikacija.vratiKomunikaciju().posalji(tk);
				
		
	}

	private void prikaziMojProfil() {
		panelMojProfil.setVisible(true);
		panelPrijatelji.setVisible(false);
		panelPrikazProfila.setVisible(false);
		panelGrupe.setVisible(false);
		sakrijDugmadIzPretrage();		
	}

	public void prikaziZahtev(StatusPrijateljstva sp2) {
		
		listaPrimljenihZahteva.add(sp2);
		btnPrijatelji.setBackground(Color.GREEN);
		prikazProfila();
	}

	public void postaviSveProfile(List<Profil> sviProfili) {
		listaProfila=sviProfili;
	}

	public void prikaziPrijatelja(StatusPrijateljstva sp2) {
		listaPrijatelja.add(sp2.getProfil1());
		dlmListaPrijatelja.addElement(sp2.getProfil1());
		if(listaPoslatihZahteva != null) {
			StatusPrijateljstva izbrisiPoslatiZahtev = null;
			for(StatusPrijateljstva sp1: listaPoslatihZahteva) {
				if(sp1.getProfil2().getIdProfil()==sp2.getProfil1().getIdProfil()) {
					izbrisiPoslatiZahtev = sp1;
				}
			}			
			listaPoslatihZahteva.remove(izbrisiPoslatiZahtev);
			dlmListaZahteva.removeElement(izbrisiPoslatiZahtev);
			prikazProfila();
		}
	}
	
	public void izbrisiPrimljenZahtev(StatusPrijateljstva sp2) {
		StatusPrijateljstva izbrisiPrimljeniZahtev = null;
		if(listaPrimljenihZahteva != null) {
			for(StatusPrijateljstva sp1:listaPrimljenihZahteva) {
				if(sp1.getProfil2().getIdProfil() == sp2.getProfil2().getIdProfil()) {
					izbrisiPrimljeniZahtev = sp1;
					break;
				}
			}
			listaPrimljenihZahteva.remove(izbrisiPrimljeniZahtev);
			dlmListaZahteva.removeElement(izbrisiPrimljeniZahtev);
			prikazProfila();
		}		
	}

	public void izbrisiPoslatZahtev(StatusPrijateljstva sp2) {
		StatusPrijateljstva izbrisiPoslatiZahtev = null;
		if(listaPoslatihZahteva != null) {
			for(StatusPrijateljstva sp1: listaPoslatihZahteva) {
				if(sp1.getProfil2().getIdProfil()==sp2.getProfil2().getIdProfil()) {
					izbrisiPoslatiZahtev = sp1;
					break;
				}
			}			
			listaPoslatihZahteva.remove(izbrisiPoslatiZahtev);
			dlmListaZahteva.removeElement(izbrisiPoslatiZahtev);
			prikazProfila();
		}
	}

	public void vratiSveStatusePrijateljstva(Map<String, Object> mojiPodaci) {
		
		this.listaPrijatelja = (List<Profil>) mojiPodaci.get("listaPrijatelja");
		this.listaPoslatihZahteva = (List<StatusPrijateljstva>) mojiPodaci.get("listaPoslatihZahteva");
		this.listaPrimljenihZahteva = (List<StatusPrijateljstva>) mojiPodaci.get("listaPrimljenihZahteva");
		
	}

	public void zapocniCaskanje(Map<String, Object> istorijaPoruka) {
		PrivatnaPoruka pp = (PrivatnaPoruka) istorijaPoruka.get("parametri");
		Profil izabraniPrijatelj = null;
		for (Profil pr : listaPrijatelja)
		{
			if (pr.getIdProfil() == pp.getIdPrijatelj())
			{
				izabraniPrijatelj = pr;
				break;
			}
		}
		istorijaPoruka.put("prijatelj", izabraniPrijatelj);
		istorijaPoruka.put("mojProfil", profil);
		
	}

	public void izbrisiPrijatelja(StatusPrijateljstva sp2) {
		Profil profilZaBrisanje = null;
		for(Profil p: listaPrijatelja) {
			if(p.getKorisnickoIme().equals(sp2.getProfil1().getKorisnickoIme()) || p.getKorisnickoIme().equals(sp2.getProfil2().getKorisnickoIme())) {
				
				profilZaBrisanje = p;
			}
		}
		if(profilZaBrisanje != null) {
			listaPrijatelja.remove(profilZaBrisanje);
			dlmListaPrijatelja.removeElement(profilZaBrisanje);
			prikazProfila();
		}
	}

	public void primiPoruku(PrivatnaPoruka pp) {
		TransferKlasa tk = new TransferKlasa();
 		tk.setOperacija(Konstante.ZAPOCNI_CASKANJE);
 		int izabraniPrijatelj = pp.getIdProfil();
 		if (izabraniPrijatelj != 0) {
 			PrivatnaPoruka pp1 = new PrivatnaPoruka(profil.getIdProfil(), izabraniPrijatelj, profil.getKorisnickoIme());
 			tk.setKlijentObjekat(pp1);
 			Komunikacija.vratiKomunikaciju().posalji(tk);
 		}
	}

	private void sakrijDugmadIzPretrage() {
		btnDodaj.setVisible(false);
		btnIzbrisiPrijateljaPretraga.setVisible(false);
		btnIzbrisiPrimljenZahtevPretraga.setVisible(false);
		btnPrihvatiZahtevPretraga.setVisible(false);
		btnIzbrisiPoslatiZahtevPretraga.setVisible(false);
	}
	
	private void prikazProfila() {
		if (!panelPrikazProfila.isVisible()) {
			sakrijDugmadIzPretrage();
			return;
		}			
		btnDodaj.setVisible(true);
		btnIzbrisiPrijateljaPretraga.setVisible(false);
		btnIzbrisiPrimljenZahtevPretraga.setVisible(false);
		btnIzbrisiPoslatiZahtevPretraga.setVisible(false);
		btnPrihvatiZahtevPretraga.setVisible(false);
		for(Profil p: listaPrijatelja) {
			if(p.getIdProfil() == profilPretraga.getIdProfil()) {
				btnIzbrisiPrijateljaPretraga.setVisible(true);
				btnDodaj.setVisible(false);
				btnIzbrisiPrimljenZahtevPretraga.setVisible(false);
				btnIzbrisiPoslatiZahtevPretraga.setVisible(false);
				btnPrihvatiZahtevPretraga.setVisible(false);
				break;
			}
		}
		for(StatusPrijateljstva poslatiZahtev :listaPoslatihZahteva ) {
			if(poslatiZahtev.getProfil2().getIdProfil() == profilPretraga.getIdProfil()) {
				btnIzbrisiPrijateljaPretraga.setVisible(false);
				btnDodaj.setVisible(false);
				btnIzbrisiPrimljenZahtevPretraga.setVisible(false);
				btnIzbrisiPoslatiZahtevPretraga.setVisible(true);
				btnPrihvatiZahtevPretraga.setVisible(false);
				break;
			}
		}
		for(StatusPrijateljstva primljeniZahtev : listaPrimljenihZahteva) {
			if(primljeniZahtev.getProfil2().getIdProfil() == profilPretraga.getIdProfil()) {
				btnIzbrisiPrijateljaPretraga.setVisible(false);
				btnDodaj.setVisible(false);
				btnIzbrisiPrimljenZahtevPretraga.setVisible(true);
				btnIzbrisiPoslatiZahtevPretraga.setVisible(false);
				btnPrihvatiZahtevPretraga.setVisible(true);
				break;
			}
		}				
	}

	public Profil getProfil() {
		return profil;
	}

	public void napraviNovuGrupu(Grupa gr) {
		
		listaGrupa.add(gr);
		mojeGrupe.add(gr);
		dlmGrupe.addElement(gr);
		
		clanoviGrupe.put(gr.getNazivGrupe(), new ArrayList<>());
		List<Profil> clanovi = clanoviGrupe.get(gr.getNazivGrupe());
		clanovi.add(profil);
				
				
	}

	public void imeGrupeVecPostoji(String serverPorukaOdgovor) {
		JOptionPane.showMessageDialog(null,serverPorukaOdgovor);
		
	}

	public void ubaciNovogClanaUGrupu(Object serverObjekat) {
		ProfilGrupa pg = (ProfilGrupa) serverObjekat;
		listaGrupa.add(pg.getGrupa());
		mojeGrupe.add(pg.getGrupa());
		List<Profil> clanovi = clanoviGrupe.get(pg.getGrupa().getNazivGrupe());
		if(clanovi==null) {
			clanoviGrupe.put(pg.getGrupa().getNazivGrupe(),new ArrayList<>());
			clanovi = clanoviGrupe.get(pg.getGrupa().getNazivGrupe());
		}
		clanovi.add(pg.getProfil());
		dlmClanoviGrupe.addElement(pg.getProfil());
		dlmGrupe.addElement(pg.getGrupa());
		btnGrupe.setBackground(Color.green);
		
	}
	private void vratiSveGrupe() {
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_SVE_GRUPE);
		tk.setKlijentObjekat(profil);
		Komunikacija.vratiKomunikaciju().posalji(tk);
		
	}

	

	public void prikaziSveGrupe(Map<String, Object> grupe) {
		
		Map<String, List<Profil>> clanoviGrupe=(Map<String, List<Profil>>) grupe.get("clanoviGrupe");
		List<Grupa> mojeGrupe=(List<Grupa>) grupe.get("mojeGrupe");
		
		this.clanoviGrupe = clanoviGrupe;
		this.mojeGrupe=mojeGrupe;
		ubaciMojeGrupeUJList();
	}

	private void ubaciMojeGrupeUJList() {
		dlmGrupe.removeAllElements();
		for(Grupa g:mojeGrupe) {
			dlmGrupe.addElement(g);
		}
		
	}

	public void osveziListuGrupa(Grupa gr) {
		
		listaGrupa.add(gr);
		Profil clan=null;
		for(Profil p:listaProfila) {
			if(p.getIdProfil()==gr.getIdProfil()) {
				clan=p;
				break;
			}
		}
		clanoviGrupe.put(gr.getNazivGrupe(), new ArrayList<>());
		clanoviGrupe.get(gr.getNazivGrupe()).add(clan);
		
	}

	public void udjiUGrupu(ProfilGrupa pg) {
		
		List<Profil>listaClanova = clanoviGrupe.get(pg.getGrupa().getNazivGrupe());
		listaClanova.add(pg.getProfil());
		mojeGrupe.add(pg.getGrupa());
		dlmGrupe.addElement(pg.getGrupa());
		dlmClanoviGrupe.addElement(pg.getProfil());
		
		TransferKlasa tk=new TransferKlasa();
		tk.setOperacija(Konstante.OSVEZI_CLANOVE_GRUPE);
		tk.setKlijentObjekat(clanoviGrupe);
		Komunikacija.vratiKomunikaciju().posalji(tk);
		
	}

	public void napustiGrupu(ProfilGrupa pg) {
		List<Profil> listaClanova = clanoviGrupe.get(pg.getGrupa().getNazivGrupe());
		Profil obrisi=null;
		for(Profil p:listaClanova) {
			if(p.getIdProfil()==pg.getProfil().getIdProfil()) {
				obrisi=p;
				break;
			}
		}
		listaClanova.remove(obrisi);
		dlmClanoviGrupe.removeElement(obrisi);
		
		TransferKlasa tk=new TransferKlasa();
		tk.setOperacija(Konstante.OSVEZI_CLANOVE_GRUPE);
		tk.setKlijentObjekat(clanoviGrupe);
		Komunikacija.vratiKomunikaciju().posalji(tk);
		
	}

	public void osveziClanove(Map<String, List<Profil>> clanovi) {
		this.clanoviGrupe=clanovi;
		
	}

	public void zapocniGrupnoCaskanje(Map<String, Object> istorijaGrupnihPoruka) {
		GrupnaPoruka gp = (GrupnaPoruka) istorijaGrupnihPoruka.get("parametri");
		Grupa izabranaGrupa = null;
		for (Grupa gr : mojeGrupe)
		{
			if (gr.getIdGrupa() == gp.getIdGrupa())
			{
				izabranaGrupa = gr;
				break;
			}
		}
		istorijaGrupnihPoruka.put("grupa", izabranaGrupa);
		istorijaGrupnihPoruka.put("mojProfil", profil);
		
	}

	public void primiGrupnuPoruku(GrupnaPoruka gp) {
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.OTVORI_GRUPNU_FORMU);
		int izabranaGrupa=gp.getIdGrupa();
		if(izabranaGrupa != 0) {
			gp = new GrupnaPoruka(profil.getIdProfil(), gp.getIdGrupa());
			gp.setKorisnickoImePosiljaoca(profil.getKorisnickoIme());
			gp.setImeGrupe(gp.getImeGrupe());
			tk.setKlijentObjekat(gp);
			Komunikacija.vratiKomunikaciju().posalji(tk);
		
		}
	}
}
