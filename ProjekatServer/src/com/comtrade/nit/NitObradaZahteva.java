package com.comtrade.nit;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.ProfilGrupa;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontroler.Kontroler;
import com.comtrade.transfer.TransferKlasa;

public class NitObradaZahteva extends Thread{
	
	private Socket soket;
	private String korisnickoIme;
	private int idProfil;
	
	
	

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public Socket getSoket() {
		return soket;
	}

	public void setSoket(Socket soket) {
		this.soket = soket;
	}
	
	
	public void run() {
		
		while (true) {
			
			try {
				ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
				try {
					TransferKlasa tk =(TransferKlasa) ois.readObject();
					obradiZahtevKlijenta(tk);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				break;
			}
		}
	}

	private void obradiZahtevKlijenta(TransferKlasa tk) {
			
		TransferKlasa tf1 = new TransferKlasa();
		switch (tk.getOperacija()) {
		
		case Konstante.SACUVAJ_PROFIL:
			
			Profil p = (Profil) tk.getKlijentObjekat();
			Kontroler.getInstanca().upisiProfil(p);
			if(p.getIdProfil() != 0) {
				tf1.setOperacija(Konstante.USPESNA_OPERACIJA);
				tf1.setServerPorukaOdgovor("Profil je uspesno napravljen");
				tf1.setServerObjekat(p);
				posalji(tf1);
				setKorisnickoIme(p.getKorisnickoIme());
				setIdProfil(p.getIdProfil());
				Kontroler.getInstanca().postaviUListuAktivnih(this);
				
				
			}
			else {
				tf1.setServerPorukaOdgovor("Promenite korisnicko ime ili e-mail");
				posalji(tf1);
				
			}
			
			break;
		case Konstante.LOGOVANJE:
			
			Profil profil = (Profil) tk.getKlijentObjekat();
			Kontroler.getInstanca().logovanje(profil);
			if(profil.getIdProfil() != 0) {
				
				tf1.setOperacija(Konstante.USPESNA_OPERACIJA);
				tf1.setServerPorukaOdgovor("Uspesno logovanje");
				tf1.setServerObjekat(profil);
				posalji(tf1);
				setKorisnickoIme(profil.getKorisnickoIme());
				setIdProfil(profil.getIdProfil());
				Kontroler.getInstanca().postaviUListuAktivnih(this);
				
			}
			else {
				tf1.setServerPorukaOdgovor("Neuspesno logovanje");
				posalji(tf1);
			}
			break;
		case Konstante.VRATI_SVE_PROFILE:
			List<Profil>listaProfila = Kontroler.getInstanca().vratiSveProfile();
			tf1.setOperacija(Konstante.VRATI_SVE_PROFILE);
			tf1.setServerObjekat(listaProfila);
			posalji(tf1);
			
			

		
			break;
		case Konstante.POSALJI_ZAHTEV:
			StatusPrijateljstva sp = (StatusPrijateljstva) tk.getKlijentObjekat();
			
			Kontroler.getInstanca().posaljiZahtev(sp);
			
			break;
		case Konstante.PRIHVACEN_ZAHTEV:
			StatusPrijateljstva sp1 = (StatusPrijateljstva) tk.getKlijentObjekat();
			Kontroler.getInstanca().prihvatiZahtev(sp1);
			
			break;
		case Konstante.IZBRISI_PRIMLJEN_ZAHTEV:
			StatusPrijateljstva sp2 = (StatusPrijateljstva) tk.getKlijentObjekat();
			Kontroler.getInstanca().izbrisiPrimljenZahtev(sp2);
			
			break;
			
		case Konstante.IZBRISI_POSLAT_ZAHTEV:
			StatusPrijateljstva sp3 = (StatusPrijateljstva) tk.getKlijentObjekat();
			Kontroler.getInstanca().izbrisiPoslatZahtev(sp3);
			
			break;
		
		case Konstante.VRATI_SVE_STATUSE_PRIJATELJSTVA:
			
			Profil mojProfil = (Profil) tk.getKlijentObjekat();
			Kontroler.getInstanca().vratiSveStatusePrijateljstva(mojProfil);
			
			break;
			
		case Konstante.ZAPOCNI_CASKANJE:
			
			PrivatnaPoruka pp = (PrivatnaPoruka) tk.getKlijentObjekat();
			Kontroler.getInstanca().zapocniCaskanje(pp);
			
			break;
			
		case Konstante.IZBRISI_PRIJATELJA:
			
			StatusPrijateljstva sp4 = (StatusPrijateljstva) tk.getKlijentObjekat();
			Kontroler.getInstanca().izbrisiPrijatelja(sp4);
			
			break;
			
		case Konstante.POSALJI_PORUKU:
			
			PrivatnaPoruka pp1 = (PrivatnaPoruka) tk.getKlijentObjekat();
			Kontroler.getInstanca().posaljiPoruku(pp1);
			
			break;
			
		case Konstante.PROVERA_NEPROCITANIH_PORUKA:
			
			Profil pr = (Profil) tk.getKlijentObjekat();
			Kontroler.getInstanca().proveraNeprocitanihPoruka(pr);
			
			break;
			
		case Konstante.NAPRAVI_NOVU_GRUPU:
			
			Grupa gr = (Grupa) tk.getKlijentObjekat();
			Kontroler.getInstanca().upisiNovuGrupu(gr);
			if(gr.getIdGrupa() != 0) {
				tk.setOperacija(Konstante.OSVEZI_LISTU_GRUPA);
				tk.setServerObjekat(gr);
				posalji(tk);
				Kontroler.getInstanca().obavestiSvePrijavljenje(this,tk);
				
			}
			else {
				TransferKlasa tk2 = new TransferKlasa();
				tk2.setOperacija(Konstante.IME_GRUPE_VEC_POSTOJI);
				tk2.setServerPorukaOdgovor("Takvo ime grupe vec postoji! ");
				posalji(tk2);
			}
			
			break;
			
		case Konstante.UPISI_NOVOG_CLANA_U_GRUPU:
			ProfilGrupa pg = (ProfilGrupa) tk.getKlijentObjekat();
			Kontroler.getInstanca().upisiClanaUGrupu(pg);
			
			break;
			
		case Konstante.VRATI_SVE_GRUPE:
			Kontroler.getInstanca().vratiSveGrupe(tk.getKlijentObjekat());
			break;
			
		case Konstante.UDJI_U_GRUPU:
			Kontroler.getInstanca().udjiUGrupu(tk.getKlijentObjekat());
			break;
			
		case Konstante.NAPUSTI_GRUPU:
			Kontroler.getInstanca().napustiGrupu(tk.getKlijentObjekat());
			break;
			
		case Konstante.OSVEZI_CLANOVE_GRUPE:
			Map<String, List<Profil>> clanovi=(Map<String, List<Profil>>) tk.getKlijentObjekat();
			tk.setServerObjekat(clanovi);
			Kontroler.getInstanca().osveziClanoveGrupe(clanovi);
			Kontroler.getInstanca().obavestiSvePrijavljenje(this, tk);
			
			break;
			
		case Konstante.ZAPOCNI_GRUPNO_CASKANJE:
			GrupnaPoruka gp = (GrupnaPoruka) tk.getKlijentObjekat();
			Kontroler.getInstanca().zapocniGrupnoCaskanje(gp);
			break;
			
		case Konstante.POSALJI_GRUPNU_PORUKU:
			GrupnaPoruka poruka = (GrupnaPoruka) tk.getKlijentObjekat();
			Kontroler.getInstanca().posaljiGrupnuPoruku(poruka);
			break;
			
		case Konstante.OTVORI_GRUPNU_FORMU:
			
			poruka = (GrupnaPoruka) tk.getKlijentObjekat();
			Kontroler.getInstanca().zapocniGrupnoCaskanje(poruka);
			break;
			
		case Konstante.VRATI_PODATKE_ZA_ADMINA:
			Map<String, Object> podaci = new HashMap<>();
			Kontroler.getInstanca().vratiSvePodatke(podaci);
			tk.setServerObjekat(podaci);
			posalji(tk);
		default:
		}
		
	}

	public void posalji(TransferKlasa tf1) {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
			oos.writeObject(tf1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public int getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}

}
