package com.comtrade.kontroler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.ProfilGrupa;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.konstante.Konstante;
import com.comtrade.nit.NitObradaZahteva;
import com.comtrade.so.IzbrisiPrijatelja;
import com.comtrade.so.IzbrisiPrimljenZahtev;
import com.comtrade.so.LogovanjeProfila;
import com.comtrade.so.NapustiGrupu;
import com.comtrade.so.OpstaSo;
import com.comtrade.so.PosaljiPoruku;
import com.comtrade.so.PosaljiZahtev;
import com.comtrade.so.PrihvatiZahtev;
import com.comtrade.so.ProcitanePoruke;
import com.comtrade.so.PromeniStatusGrupnePoruke;
import com.comtrade.so.ProveraPoruka;
import com.comtrade.so.UdjiUGrupu;
import com.comtrade.so.UpisiClanaUGrupu;
import com.comtrade.so.UpisiGrupnuPoruku;
import com.comtrade.so.UpisiNovuGrupu;
import com.comtrade.so.VratiPodatkeZaAdmina;
import com.comtrade.so.VratiSveGrupe;
import com.comtrade.so.VratiSveProfile;
import com.comtrade.so.VratiSveStatusePrijateljstva;
import com.comtrade.so.ZapocniCaskanje;
import com.comtrade.so.ZapocniGrupnoCaskanje;
import com.comtrade.so.unosProfila;
import com.comtrade.transfer.TransferKlasa;

public class Kontroler {
	
	private static Kontroler instanca;
	private List<NitObradaZahteva>listaAktivnih = new ArrayList<>();
	private Map<String, List<Profil>> clanoviGrupe=new HashMap<>();
	
	private Kontroler() {
		
		
	}
	
	public static  Kontroler getInstanca() {
		
		if(instanca == null) {
			
			instanca = new Kontroler();
			
		}
		return instanca;
	}

	public void upisiProfil(Profil p) {
		
		OpstaSo os = new unosProfila();
		os.izvrsiSo(p);
		
	}

	public void logovanje(Profil profil) {
		OpstaSo os = new LogovanjeProfila();
		os.izvrsiSo(profil);
		
	}
	
	public List<Profil> vratiSveProfile() {
		
		List<Profil>listaProfila = new ArrayList<>();
		Map<String,Object> hm = new HashMap<>();
		hm.put("profil", new Profil());
		
		OpstaSo os = new VratiSveProfile();
		os.izvrsiSo(hm);
		listaProfila = (List<Profil>) hm.get("listaProfila");
		return listaProfila;
	}

	public void postaviUListuAktivnih(NitObradaZahteva nitObradaZahteva) {
		listaAktivnih.add(nitObradaZahteva);  
		
	}

	public void posaljiZahtev(StatusPrijateljstva sp) {
		
		
		OpstaSo os = new PosaljiZahtev();
		os.izvrsiSo(sp);
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.POSALJI_ZAHTEV);
		
		StatusPrijateljstva sp1 = new StatusPrijateljstva(sp.getProfil2(), sp.getProfil1(), Konstante.PRIMLJEN_ZAHTEV);
		tk.setServerObjekat(sp1);
		
		for(NitObradaZahteva nit: listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(sp.getProfil2().getKorisnickoIme())) {
				
				nit.posalji(tk);
				break;
			}
		}
		
		
		
		
	}

	public void prihvatiZahtev(StatusPrijateljstva sp1) {
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.PRIHVACEN_ZAHTEV);
		tk.setServerObjekat(sp1);
		OpstaSo os = new PrihvatiZahtev();
		os.izvrsiSo(sp1);
		
		for(NitObradaZahteva nit: listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(sp1.getProfil2().getKorisnickoIme())) {
				nit.posalji(tk);
				break;
			}
		}
		
	}

	public void izbrisiPrimljenZahtev(StatusPrijateljstva sp2) {
		
		OpstaSo os = new IzbrisiPrimljenZahtev();
		os.izvrsiSo(sp2);
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.IZBRISI_POSLAT_ZAHTEV);
		StatusPrijateljstva sp1 = new StatusPrijateljstva(sp2.getProfil2(), sp2.getProfil1(), Konstante.IZBRISI_POSLAT_ZAHTEV);
		tk.setServerObjekat(sp1);
		
		for(NitObradaZahteva nit:listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(sp2.getProfil2().getKorisnickoIme())) {
				nit.posalji(tk);
				break;
			}
		}
		
		
		
	}

	public void izbrisiPoslatZahtev(StatusPrijateljstva sp3) {
		
		OpstaSo os = new IzbrisiPrimljenZahtev();
		os.izvrsiSo(sp3);
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.IZBRISI_PRIMLJEN_ZAHTEV);
		StatusPrijateljstva sp1 = new StatusPrijateljstva(sp3.getProfil2(), sp3.getProfil1(), Konstante.IZBRISI_PRIMLJEN_ZAHTEV);
		tk.setServerObjekat(sp1);
		
		for(NitObradaZahteva nit: listaAktivnih ) {
			
			if(nit.getKorisnickoIme().equals(sp3.getProfil2().getKorisnickoIme())) {
				nit.posalji(tk);
				break;
				
			}
		}
		
	}

	public void vratiSveStatusePrijateljstva(Profil mojProfil) {
		
		Map<String,Object> mojiPodaci = new HashMap<>();
		mojiPodaci.put("mojProfil", mojProfil);
		OpstaSo os = new VratiSveStatusePrijateljstva();
		os.izvrsiSo(mojiPodaci);
	
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_SVE_STATUSE_PRIJATELJSTVA);
		tk.setServerObjekat(mojiPodaci);
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(mojProfil.getKorisnickoIme())) {
				
				nit.posalji(tk);
				break;
			}
		}
		
		
	}

	public void zapocniCaskanje(PrivatnaPoruka pp) {
		Map<String,Object> istorijaCaskanja = new HashMap<>();
		istorijaCaskanja.put("parametri", pp);
		istorijaCaskanja.put("listaPoruka", new ArrayList<PrivatnaPoruka>());
		
		OpstaSo os = new ZapocniCaskanje();
		os.izvrsiSo(istorijaCaskanja);
		
		OpstaSo os1 = new ProcitanePoruke();
		os1.izvrsiSo(pp);
		
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.ZAPOCNI_CASKANJE);
		tk.setServerObjekat(istorijaCaskanja);
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(pp.getKorisnickoImePosiljaoca())) {
				
				nit.posalji(tk);
				break;
			}
		}
	
		
	}

	public void izbrisiPrijatelja(StatusPrijateljstva sp4) {
		OpstaSo os = new IzbrisiPrijatelja();
		os.izvrsiSo(sp4);
		
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.IZBRISI_PRIJATELJA);
		tk.setServerObjekat(sp4);
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(sp4.getProfil2().getKorisnickoIme()) || nit.getKorisnickoIme().equals(sp4.getProfil1().getKorisnickoIme())) {
				
				nit.posalji(tk);
				
			}
		}
		
		
	}

	public void posaljiPoruku(PrivatnaPoruka pp1) {
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(pp1.getKorisnickoImePrimaoca())) {
				
				pp1.setStatus(Konstante.PROCITANA_PORUKA);
				break;
			}
		}
		OpstaSo os = new PosaljiPoruku();
		os.izvrsiSo(pp1);
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.POSALJI_PORUKU);
		tk.setServerObjekat(pp1);
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(pp1.getKorisnickoImePrimaoca())) {
				
				nit.posalji(tk);
				break;
			}
		}
		
	}

	public void proveraNeprocitanihPoruka(Profil pr) {
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("parametar", pr);
		
		OpstaSo os = new ProveraPoruka();
		os.izvrsiSo(hm);
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.PROVERA_NEPROCITANIH_PORUKA);
		
		tk.setServerObjekat(hm);
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(pr.getKorisnickoIme())) {
				
				nit.posalji(tk);
				break;
			}
		}
		
	}

	public void upisiNovuGrupu(Grupa gr) {
		
		OpstaSo os = new UpisiNovuGrupu();
		os.izvrsiSo(gr);
		
		
	}

	public void upisiClanaUGrupu(ProfilGrupa pg) {
		
		OpstaSo os = new UpisiClanaUGrupu();
		os.izvrsiSo(pg);
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.UPISI_NOVOG_CLANA_U_GRUPU);
		tk.setServerObjekat(pg);
		
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getIdProfil() == pg.getIdProfil()) {
				
				nit.posalji(tk);
			}
		}
		
	}

	public void vratiSveGrupe(Object object) {
		Profil p = (Profil) object;
		
		Map<String, Object> grupe = new HashMap<>();
		grupe.put("profil", p);
		OpstaSo os = new VratiSveGrupe();
		os.izvrsiSo(grupe);
		this.clanoviGrupe=(Map<String, List<Profil>>) grupe.get("clanoviGrupe");
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_SVE_GRUPE);
		tk.setServerObjekat(grupe);
		
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(p.getKorisnickoIme())) {
				
				nit.posalji(tk);
				break;
			}
		}
		
		
	}

	public void obavestiSvePrijavljenje(NitObradaZahteva mojaNit, TransferKlasa tk) {
		
		for(NitObradaZahteva nit:listaAktivnih) {
			if(!nit.getKorisnickoIme().equals(mojaNit.getKorisnickoIme())) {
				nit.posalji(tk);
				
			}
		}
		
	}

	public void udjiUGrupu(Object klijentObjekat) {
		ProfilGrupa pg = (ProfilGrupa) klijentObjekat;
		
		OpstaSo os = new UdjiUGrupu();
		os.izvrsiSo(pg);
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.UDJI_U_GRUPU);
		tk.setServerObjekat(pg);
		for(NitObradaZahteva nit : listaAktivnih) {
			if(nit.getKorisnickoIme().equals(pg.getProfil().getKorisnickoIme())){
				
				nit.posalji(tk);
				break;
				
				
			}
		}
		
	}

	public void napustiGrupu(Object klijentObjekat) {
		ProfilGrupa pg = (ProfilGrupa) klijentObjekat;
		
		OpstaSo os = new NapustiGrupu();
		os.izvrsiSo(pg);
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.NAPUSTI_GRUPU);
		tk.setServerObjekat(pg);
		for(NitObradaZahteva nit : listaAktivnih) {
			if(nit.getKorisnickoIme().equals(pg.getProfil().getKorisnickoIme())) {
				nit.posalji(tk);
				break;
			}
		}
		
	}

	public void zapocniGrupnoCaskanje(GrupnaPoruka gp) {
		Map<String,Object> istorijaGrupnogCaskanja = new HashMap<>();
		istorijaGrupnogCaskanja.put("parametri", gp);
		istorijaGrupnogCaskanja.put("listaGrupnihPoruka", new ArrayList<GrupnaPoruka>());
		
		OpstaSo os = new ZapocniGrupnoCaskanje();
		os.izvrsiSo(istorijaGrupnogCaskanja);
		
		
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.ZAPOCNI_GRUPNO_CASKANJE);
		tk.setServerObjekat(istorijaGrupnogCaskanja);
		for(NitObradaZahteva nit : listaAktivnih) {
			
			if(nit.getKorisnickoIme().equals(gp.getKorisnickoImePosiljaoca())) {
				
				nit.posalji(tk);
				ProfilGrupa pg=new ProfilGrupa();
				pg.setIdGrupa(gp.getIdGrupa());
				pg.setIdProfil(gp.getIdProfil());
				pg.setStatus(Konstante.PROCITANA_PORUKA);
				OpstaSo os1 = new PromeniStatusGrupnePoruke();
				os1.izvrsiSo(pg);
				break;
			}
		}
		
	}

	public void posaljiGrupnuPoruku(GrupnaPoruka poruka) {
		OpstaSo os = new UpisiGrupnuPoruku();
		os.izvrsiSo(poruka);
		TransferKlasa tk=new TransferKlasa();
		tk.setOperacija(Konstante.POSALJI_GRUPNU_PORUKU);
		tk.setServerObjekat(poruka);
		List<Profil> clanovi=clanoviGrupe.get(poruka.getImeGrupe());
		
		for(Profil p:clanovi) {
			if(p.getIdProfil()!=poruka.getIdProfil()) {
				int provera=0;
				for(NitObradaZahteva nit : listaAktivnih) {
					if(nit.getKorisnickoIme().equals(p.getKorisnickoIme())) {
						nit.posalji(tk);
						provera=1;
						break;
					}
				}
				if(provera==0) {
					ProfilGrupa pg=new ProfilGrupa();
					pg.setIdGrupa(poruka.getIdGrupa());
					pg.setIdProfil(p.getIdProfil());
					pg.setStatus(Konstante.NEPROCITANE_GRUPNE_PORUKE);
					OpstaSo os1 = new PromeniStatusGrupnePoruke();
					os1.izvrsiSo(pg);
				}else {
					ProfilGrupa pg=new ProfilGrupa();
					pg.setIdGrupa(poruka.getIdGrupa());
					pg.setIdProfil(p.getIdProfil());
					pg.setStatus(Konstante.PROCITANA_PORUKA);
					OpstaSo os1 = new PromeniStatusGrupnePoruke();
					os1.izvrsiSo(pg);
				}
			}
		}
		
	}

	public void osveziClanoveGrupe(Map<String, List<Profil>> clanovi) {
		this.clanoviGrupe=clanovi;
		
	}

	public void vratiSvePodatke(Map<String, Object> podaci) {
		
		
		OpstaSo os = new VratiPodatkeZaAdmina();
		os.izvrsiSo(podaci);
		
		
	}

}
