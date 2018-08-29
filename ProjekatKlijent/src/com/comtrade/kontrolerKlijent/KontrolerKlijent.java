package com.comtrade.kontrolerKlijent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.ProfilGrupa;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.forma.admin.AdminForma;
import com.comtrade.forma.caskanje.FormaCaskanje;
import com.comtrade.forma.caskanje.FormaGrupnoCaskanje;
import com.comtrade.forma.profil.ProfilForma;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.konstante.Konstante;
import com.comtrade.transfer.TransferKlasa;

public class KontrolerKlijent {
	
	private static KontrolerKlijent instanca;
	private ProfilForma pf;
	private AdminForma af;
	private List<FormaCaskanje> listaOtvorenihFormi = new ArrayList<>();
	private List<FormaGrupnoCaskanje>listaGrupnihFormi = new ArrayList<>();
	
	public List<FormaGrupnoCaskanje> getListaGrupnihFormi() {
		return listaGrupnihFormi;
	}
	
	public List<FormaCaskanje> getListaOtvorenihFormi() {
		return listaOtvorenihFormi;
	}
	private KontrolerKlijent() {
		
		
	}
	public void postaviFormu(ProfilForma profilForma) {
		
		this.pf = profilForma;
		
	}
	
	public static KontrolerKlijent getInstanca() {
		
		if (instanca == null) {
			
			instanca = new KontrolerKlijent();
			
		}
		return instanca;
	}

	public void prikaziZahtev(Object object) {
		StatusPrijateljstva sp = (StatusPrijateljstva) object;
		pf.prikaziZahtev(sp);
		
	}

	

	public void postaviSveProfile(List<Profil> sviProfili) {
		pf.postaviSveProfile(sviProfili);
		
	}
	public void prikaziPrijatelja(StatusPrijateljstva sp) {
		pf.prikaziPrijatelja(sp);
		
	}
	public void izbrisiPrimljenZahtev(StatusPrijateljstva sp) {
		pf.izbrisiPrimljenZahtev(sp);
		
	}
	public void izbrisiPoslatZahtev(StatusPrijateljstva sp) {
		pf.izbrisiPoslatZahtev(sp);
		
	}

	public void vratiSveStatusePrijateljstva(Object object) {
		
		Map<String, Object> mojiPodaci = (Map<String, Object>) object;
		pf.vratiSveStatusePrijateljstva(mojiPodaci);
	}
	public synchronized void zapocniCaskanje(Map<String, Object> istorijaPoruka) {
		pf.zapocniCaskanje(istorijaPoruka);	
		List<PrivatnaPoruka> listaPoruka = (List<PrivatnaPoruka>) istorijaPoruka.get("listaPoruka");
		Profil izabraniPrijatelj = (Profil) istorijaPoruka.get("prijatelj");
		Profil profil = (Profil) istorijaPoruka.get("mojProfil");
		for(FormaCaskanje forma : listaOtvorenihFormi) {
			
			if(forma.getProfilPrijatelj().getIdProfil() == izabraniPrijatelj.getIdProfil()) {
				
				for(PrivatnaPoruka poruka : listaPoruka) {
					
					forma.primiPoruku(poruka);
				
				}
				return;
			}	 
		}
		 FormaCaskanje fc = new FormaCaskanje(listaPoruka, profil, izabraniPrijatelj);
		 fc.setVisible(true);
		 listaOtvorenihFormi.add(fc);
		
	}
	
		
	public void izbrisiPrijatelja(StatusPrijateljstva sp) {
		pf.izbrisiPrijatelja(sp);
		
	}
	public void posaljiPoruku(PrivatnaPoruka pp) {
		
		for(FormaCaskanje fc : listaOtvorenihFormi) {
			
			if(fc.getProfilPrijatelj().getIdProfil() == pp.getIdProfil()) {
				
				fc.primiPoruku(pp);
				return;
			}
			
		}
		pf.primiPoruku(pp);
		
	}
	public void proveraNeprocitanihPrivatnihPoruka(List<Integer> posiljaoci) {
		for (Integer idPrijatelja : posiljaoci) {
			TransferKlasa tk = new TransferKlasa();
	 		tk.setOperacija(Konstante.ZAPOCNI_CASKANJE);
 			PrivatnaPoruka pp = new PrivatnaPoruka(pf.getProfil().getIdProfil(), idPrijatelja, pf.getProfil().getKorisnickoIme());
 			tk.setKlijentObjekat(pp);
 			Komunikacija.vratiKomunikaciju().posalji(tk);
		}
	}
	
	public void proveraNeprocitanihGrupnihPoruka(List<Integer> posiljaoci) {
		for (Integer idGrupe : posiljaoci) {
			TransferKlasa tk = new TransferKlasa();
	 		tk.setOperacija(Konstante.ZAPOCNI_GRUPNO_CASKANJE);
 			GrupnaPoruka gp = new GrupnaPoruka(pf.getProfil().getIdProfil(), idGrupe);
 			gp.setKorisnickoImePosiljaoca(pf.getProfil().getKorisnickoIme());
 			tk.setKlijentObjekat(gp);
 			Komunikacija.vratiKomunikaciju().posalji(tk);
		}
		
	}

	public void napraviNovuGrupu(Grupa gr) {
		
		pf.napraviNovuGrupu(gr);
		
	}
	public void imeGrupeVecPostoji(String serverPorukaOdgovor) {
		
		pf.imeGrupeVecPostoji(serverPorukaOdgovor);
		
	}
	public void ubaciNovogClanaUGrupu(Object serverObjekat) {
		pf.ubaciNovogClanaUGrupu(serverObjekat);
		
	}
	public void vratiSveGrupe(Object serverObjekat) {
		Map<String, Object> grupe = (Map<String, Object>) serverObjekat;
		pf.prikaziSveGrupe(grupe);
		
	}
	public void osveziListuGrupa(Object serverObjekat) {
		Grupa gr=(Grupa) serverObjekat;
		pf.osveziListuGrupa(gr);
		
	}
	public void udjiUGrupu(Object serverObjekat) {
		ProfilGrupa pg = (ProfilGrupa) serverObjekat;
		pf.udjiUGrupu(pg);
		
	}
	public void napustiGrupu(Object serverObjekat) {
		ProfilGrupa pg = (ProfilGrupa) serverObjekat;
		pf.napustiGrupu(pg);
		
	}
	public void osveziClanove(Object serverObjekat) {
		Map<String, List<Profil>> clanovi=(Map<String, List<Profil>>) serverObjekat;
		pf.osveziClanove(clanovi);
		
	}
	public synchronized void zapocniGrupnoCaskanje(Object serverObjekat) {
		Map<String, Object> istorijaGrupnihPoruka = (Map<String, Object>) serverObjekat;
		pf.zapocniGrupnoCaskanje(istorijaGrupnihPoruka);	
		List<GrupnaPoruka> listaGrupnihPoruka = (List<GrupnaPoruka>) istorijaGrupnihPoruka.get("listaGrupnihPoruka");
		Grupa izabranaGrupa = (Grupa) istorijaGrupnihPoruka.get("grupa");
		Profil profil = (Profil) istorijaGrupnihPoruka.get("mojProfil");
		for(FormaGrupnoCaskanje forma : listaGrupnihFormi) {
			
			if(forma.getIzabranaGrupa().getIdGrupa() == izabranaGrupa.getIdGrupa()) {
				
				for(GrupnaPoruka poruka : listaGrupnihPoruka) {
					
					forma.primiGrupnuPoruku(poruka);
				
				}
				return;
			}	 
		}
		 FormaGrupnoCaskanje fc = new FormaGrupnoCaskanje(listaGrupnihPoruka, profil, izabranaGrupa);
		 fc.setVisible(true);
		 listaGrupnihFormi.add(fc);
		
	}

	public void prikaziGrupnuPoruku(Object serverObjekat) {
		GrupnaPoruka gp=(GrupnaPoruka) serverObjekat;
		for(FormaGrupnoCaskanje fc : listaGrupnihFormi) {
			
			if(fc.getIzabranaGrupa().getIdGrupa() == gp.getIdGrupa()) {
				
				fc.primiGrupnuPoruku(gp);

				return;
			}
			
		}
		pf.primiGrupnuPoruku(gp);
	}

	public void vratiPodatkeZaAdmina(Object serverObjekat) {
		Map< String, Object> podaci=(Map<String, Object>) serverObjekat;
		af.postaviPodatke(podaci);
		
		
	}

	public void postaviAdminFormu(AdminForma adminForma) {
		this.af=adminForma;
		
	}

	
	
	
	

}
