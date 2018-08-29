package com.comtrade.nit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.comtrade.domen.Grupa;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.kontrolerKlijent.KontrolerKlijent;
import com.comtrade.transfer.TransferKlasa;

public class NitObradaZahtevaServera extends Thread {
	
	private Socket soket;
	public NitObradaZahtevaServera() {
		this.soket = Komunikacija.vratiKomunikaciju().getSoket();
		
	}
	
	public void run() {
		while(true) {
		try {
			ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
			try {
				TransferKlasa tk = (TransferKlasa) ois.readObject();
				obradiZahtevServera(tk);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			break;
		}
	}

}

	@SuppressWarnings({ "unchecked", "unused" })
	private void obradiZahtevServera(TransferKlasa tk) {
		
		switch (tk.getOperacija()) {
		case Konstante.POSALJI_ZAHTEV:
			
			KontrolerKlijent.getInstanca().prikaziZahtev(tk.getServerObjekat());
			
			break;
		case Konstante.VRATI_SVE_PROFILE:
			List<Profil> sviProfili=(List<Profil>) tk.getServerObjekat();
			KontrolerKlijent.getInstanca().postaviSveProfile(sviProfili);
			break;
			
		case Konstante.PRIHVACEN_ZAHTEV:
			
			KontrolerKlijent.getInstanca().prikaziPrijatelja((StatusPrijateljstva) tk.getServerObjekat());
			break;
		case Konstante.IZBRISI_PRIMLJEN_ZAHTEV:
			KontrolerKlijent.getInstanca().izbrisiPrimljenZahtev((StatusPrijateljstva) tk.getServerObjekat());
			break;
		case Konstante.IZBRISI_POSLAT_ZAHTEV:
			KontrolerKlijent.getInstanca().izbrisiPoslatZahtev((StatusPrijateljstva) tk.getServerObjekat());
		
			break;
			
		case Konstante.VRATI_SVE_STATUSE_PRIJATELJSTVA:
			
			KontrolerKlijent.getInstanca().vratiSveStatusePrijateljstva(( tk.getServerObjekat()));
			break;
			
		case Konstante.ZAPOCNI_CASKANJE:
			
			Map<String, Object> istorijaPoruka = (Map<String, Object>) tk.getServerObjekat();
			KontrolerKlijent.getInstanca().zapocniCaskanje(istorijaPoruka);
			break;
			
		case Konstante.IZBRISI_PRIJATELJA:
			StatusPrijateljstva sp = (StatusPrijateljstva) tk.getServerObjekat();
			KontrolerKlijent.getInstanca().izbrisiPrijatelja(sp);
			break;
			
		case Konstante.POSALJI_PORUKU:
			PrivatnaPoruka pp = (PrivatnaPoruka) tk.getServerObjekat();
			KontrolerKlijent.getInstanca().posaljiPoruku(pp);
			break;
			
		case Konstante.PROVERA_NEPROCITANIH_PORUKA:
			
			Map<String, Object> hm=(Map<String, Object>) tk.getServerObjekat();
			List<Integer> grupneNeprocitanePoruke = (List<Integer>) hm.get("grupneNeprocitanePoruke");
			List<Integer> posiljaociPrivatnihPoruka = (List<Integer>) hm.get("posiljaoci");
			KontrolerKlijent.getInstanca().proveraNeprocitanihPrivatnihPoruka(posiljaociPrivatnihPoruka);
			KontrolerKlijent.getInstanca().proveraNeprocitanihGrupnihPoruka(grupneNeprocitanePoruke);
			
			break;
			
		case Konstante.NAPRAVI_NOVU_GRUPU:
			Grupa gr = (Grupa) tk.getServerObjekat();
			KontrolerKlijent.getInstanca().napraviNovuGrupu(gr);
			
			break;
		case Konstante.IME_GRUPE_VEC_POSTOJI:
			KontrolerKlijent.getInstanca().imeGrupeVecPostoji((String) tk.getServerPorukaOdgovor());
			break;
			
		case Konstante.UPISI_NOVOG_CLANA_U_GRUPU:
			KontrolerKlijent.getInstanca().ubaciNovogClanaUGrupu(tk.getServerObjekat());
			break;
			
		case Konstante.VRATI_SVE_GRUPE:
			
			KontrolerKlijent.getInstanca().vratiSveGrupe(tk.getServerObjekat());
			break;
			
		case Konstante.OSVEZI_LISTU_GRUPA:
			
			KontrolerKlijent.getInstanca().osveziListuGrupa(tk.getServerObjekat());
			break;
			
		case Konstante.UDJI_U_GRUPU:
			
			KontrolerKlijent.getInstanca().udjiUGrupu(tk.getServerObjekat());
			break;
			
		case Konstante.NAPUSTI_GRUPU:
			
			KontrolerKlijent.getInstanca().napustiGrupu(tk.getServerObjekat());
			break;
		case Konstante.OSVEZI_CLANOVE_GRUPE:
			
			KontrolerKlijent.getInstanca().osveziClanove(tk.getServerObjekat());
			break;
		case Konstante.ZAPOCNI_GRUPNO_CASKANJE:
			
			KontrolerKlijent.getInstanca().zapocniGrupnoCaskanje(tk.getServerObjekat());
			break;
		case Konstante.POSALJI_GRUPNU_PORUKU:
			
			KontrolerKlijent.getInstanca().prikaziGrupnuPoruku(tk.getServerObjekat());
			break;
			
		case Konstante.VRATI_PODATKE_ZA_ADMINA:
			
			KontrolerKlijent.getInstanca().vratiPodatkeZaAdmina(tk.getServerObjekat());
			break;
		
		default:
			break;
		}
		
	}
}

