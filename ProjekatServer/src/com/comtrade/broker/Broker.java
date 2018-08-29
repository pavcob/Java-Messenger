package com.comtrade.broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.xml.stream.events.StartDocument;

import com.comtrade.domen.Grupa;
import com.comtrade.domen.GrupnaPoruka;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.PrivatnaPoruka;
import com.comtrade.domen.Profil;
import com.comtrade.domen.ProfilGrupa;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.konstante.Konstante;
import com.comtrade.transfer.TransferKlasa;
import com.mysql.fabric.xmlrpc.base.Array;

public class Broker {

	private Connection konekcija;

	private Broker() {

		ucitajDrajver();

	}

	private void ucitajDrajver() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Broker instanca;

	public static Broker getInstanca() {

		if (instanca == null) {

			instanca = new Broker();
		}

		return instanca;

	}

	public void pokreniTransakciju() {

		try {
			konekcija = DriverManager.getConnection("jdbc:mysql://localhost/projekat", "root", "");
			konekcija.setAutoCommit(false);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void potvrdiTransakciju() {

		try {
			konekcija.commit();
		} catch (SQLException e) {

		}
	}

	public void ponistiTransakciju() {

		try {
			konekcija.rollback();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void zatvoriKonekciju() {

		try {
			konekcija.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public boolean korisnikPostoji(OpstiDomen od) {
		Profil p = (Profil) od;
		boolean korisnikPostoji = false;
		String korisnickoIme = p.getKorisnickoIme();
		String eAdresa = p.getMail();
		try {
			PreparedStatement ps = konekcija.prepareStatement("SELECT * FROM " + od.vratiNazivTabele() + " WHERE korisnickoIme=? or eMail = ?");
			ps.setString(1, korisnickoIme);
			ps.setString(2, eAdresa);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				korisnikPostoji = true;
			} else {
				korisnikPostoji = false;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return korisnikPostoji;

	}

	public void upisiProfil(OpstiDomen od) {

		Profil p = (Profil) od;
		if (korisnikPostoji(p) == false) {

			String upit = "INSERT INTO " + od.vratiNazivTabele() + " " + od.vratiZaInsert();
			Statement st;
			String vratiProfil = "SELECT * FROM " + od.vratiNazivTabele() + " WHERE korisnickoIme ='"
					+ p.getKorisnickoIme() + "'";
			//System.out.println(vratiProfil);
			try {
				st = konekcija.createStatement();
				st.executeUpdate(upit);

				Statement st1;
				st1 = konekcija.createStatement();
				ResultSet rs = st1.executeQuery(vratiProfil);
				if (rs.next()) {

					int idProfil = rs.getInt("idProfil");
					p.setIdProfil(idProfil);
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void logovanjeProfila(OpstiDomen od) {

		Profil p = (Profil) od;
		
		String upit = "SELECT * FROM " + od.vratiNazivTabele() + " WHERE korisnickoIme ='" + p.getKorisnickoIme()
				+ "' AND lozinka='" + p.getLozinka() + "'";
		Statement st;
		try {
			st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit);
			if (rs.next()) {

				p.setIdProfil(rs.getInt("idProfil"));
				p.setKorisnickoIme(rs.getString("korisnickoIme"));
				p.setIme(rs.getString("ime"));
				p.setPrezime(rs.getString("prezime"));
				p.setMail(rs.getString("eMail"));
				p.setStatus(rs.getInt("status"));
				p.setPol(rs.getString("pol"));
				p.setZanimanje(rs.getString("zanimanje"));
				p.setDatumRodjenja(rs.getString("datumRodjenja")); 

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<OpstiDomen> vratiSveProfile(OpstiDomen p) {

		List<OpstiDomen> listaProfila = new ArrayList<>();
		String upit = "SELECT * FROM " + p.vratiNazivTabele() + "";
		try {
			Statement st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit);
			listaProfila = p.srediSelect(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProfila;

	}

	public void posaljiZahtev(OpstiDomen od) {
		StatusPrijateljstva sp = (StatusPrijateljstva) od;
		String upit = "INSERT INTO " + od.vratiNazivTabele() + " " + od.vratiZaInsert();
		try {
			PreparedStatement ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void prihvatiZahtev(OpstiDomen od) {
		StatusPrijateljstva sp = (StatusPrijateljstva) od;

		String upit = "UPDATE " + od.vratiNazivTabele() + " SET " + od.vratiZaUpate(od)
				+ " WHERE (statusPrijateljstva.idProfil1=" + sp.getProfil1().getIdProfil()
				+ " AND statusPrijateljstva.idProfil2=" + sp.getProfil2().getIdProfil()
				+ ") OR (statusPrijateljstva.idProfil1 =  " + sp.getProfil2().getIdProfil() + " AND "
				+ "statusPrijateljstva.idProfil2 = " + sp.getProfil1().getIdProfil() + ")";
		PreparedStatement ps;
		try {
			ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void izbrisiPrimljenZahtev(OpstiDomen od) {

		StatusPrijateljstva sp = (StatusPrijateljstva) od;

		String upit = od.vratiZaDelete(od) + " WHERE(statusPrijateljstva.idProfil1=" + sp.getProfil1().getIdProfil()
				+ " AND statusPrijateljstva.idProfil2=" + sp.getProfil2().getIdProfil()
				+ ") OR (statusPrijateljstva.idProfil1 =  " + sp.getProfil2().getIdProfil() + " AND "
				+ "statusPrijateljstva.idProfil2 = " + sp.getProfil1().getIdProfil() + ")";

		try {
			PreparedStatement ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void vratiSveStatusePrijateljstva(OpstiDomen od, HashMap<String, Object> hm) {
		Profil pr = (Profil) hm.get("mojProfil");
		StatusPrijateljstva sp = (StatusPrijateljstva) od;
		List<StatusPrijateljstva> listaPoslatihZahteva = new ArrayList<>();
		List<StatusPrijateljstva> listaPrimljenihZahteva = new ArrayList<>();
		List<Profil> listaPrijatelja = new ArrayList<>();

		String upit = "SELECT * FROM statusprijateljstva INNER JOIN profil ON "
				+ "(statusprijateljstva.idProfil2 = profil.idProfil) WHERE statusprijateljstva.idProfil1 = "
				+ pr.getIdProfil() + "";

		Statement st;
		try {
			st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit);

			while (rs.next()) {

				Profil prijatelj = pr.vratiProfil(rs);
				int status = rs.getInt("status");
				if (status == Konstante.POSALJI_ZAHTEV) {
					StatusPrijateljstva sp1 = new StatusPrijateljstva(pr, prijatelj, status);
					listaPoslatihZahteva.add(sp1);
				} else if (status == Konstante.PRIMLJEN_ZAHTEV) {
					StatusPrijateljstva sp1 = new StatusPrijateljstva(pr, prijatelj, status);
					listaPrimljenihZahteva.add(sp1);
				} else if (status == Konstante.PRIHVACEN_ZAHTEV) {
					listaPrijatelja.add(prijatelj);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hm.put("listaPrijatelja", listaPrijatelja);
		hm.put("listaPoslatihZahteva", listaPoslatihZahteva);
		hm.put("listaPrimljenihZahteva", listaPrimljenihZahteva);

	}

	public void vratiIstorijuCaskanja(Object obj) {
		HashMap<String, Object> istorijaCaskanja = (HashMap<String, Object>) obj;	
		 PrivatnaPoruka pp = (PrivatnaPoruka) istorijaCaskanja.get("parametri");
		 ArrayList<PrivatnaPoruka> listaPoruka = (ArrayList<PrivatnaPoruka>) istorijaCaskanja.get("listaPoruka");
		 
		 String upit = "SELECT pp.*, pr.korisnickoIme FROM privatnaPoruka pp inner join profil pr on pr.idProfil = pp.idProfil "
		 		+ "WHERE (pp.idProfil = " + pp.getIdProfil() + " and pp.idPrijatelj =" + pp.getIdPrijatelj() + 
				 ") OR (pp.idPrijatelj = " + pp.getIdProfil() + " and pp.idProfil = " + pp.getIdPrijatelj()+ ")"
				 		+ " ORDER BY pp.idPrivatnaPoruka ASC";
		 Statement st;
		 
		 try {
			st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit);
			List<OpstiDomen> rezultat = pp.srediSelect(rs);
			for(OpstiDomen poruka : rezultat) {
				
				listaPoruka.add((PrivatnaPoruka) poruka);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void izbrisiPrijatelja(StatusPrijateljstva sp) {
		
		String upit = "DELETE FROM "+sp.vratiNazivTabele()+" WHERE (idProfil1 = "+sp.getProfil1().getIdProfil()+" and idProfil2 = "+sp.getProfil2().getIdProfil()+") OR (idProfil2 = "+sp.getProfil1().getIdProfil()+" and idProfil1 = "+sp.getProfil2().getIdProfil()+")";
		try {
			PreparedStatement ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void posaljiPoruku(PrivatnaPoruka pp) {
		
		try {
			PreparedStatement ps = konekcija.prepareStatement(pp.vratiZaInsert());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void procitanePoruke(PrivatnaPoruka pp) {
		
		String upit = "UPDATE " +pp.vratiNazivTabele()+ " SET status=1 WHERE idPrijatelj = " + pp.getIdProfil() + " and idProfil = " + pp.getIdPrijatelj()+ " AND status = 0";
		
		PreparedStatement ps;
		try {
			ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public List<Integer> proveraNeprocitanihPoruka(Object object) {
		Profil p = (Profil) object;
		String upit = "SELECT idProfil FROM privatnaPoruka WHERE status = 0 AND idPrijatelj = " +p.getIdProfil(); 
		Statement st;
		HashSet<Integer> posiljaoci = new HashSet<>();
		List<Integer> rezultat = new ArrayList<>();
		 
		 try {
			st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit);
			while(rs.next()) {
				int idProfil = rs.getInt("idProfil");
				posiljaoci.add(idProfil);
				
			}
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 rezultat.addAll(posiljaoci);
		 return rezultat;

		}
	
	public List<Integer> proveraNeprocitanihGrupnihPoruka(Object object) {
		Profil p = (Profil) object;
		String upit="SELECT * FROM profilgrupa WHERE idProfil="+p.getIdProfil()+" AND statusPoruka="+Konstante.NEPROCITANE_GRUPNE_PORUKE+"";
		List<Integer> rezultat=new ArrayList<>();
		try {
			Statement st=konekcija.createStatement();
			ResultSet rs=st.executeQuery(upit);
			while(rs.next()) {
				int idGrupa = rs.getInt("idGrupa");
				rezultat.add(idGrupa);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return rezultat;
	}

	public void upisiNovuGrupu(Grupa gr) {
		ProfilGrupa pg = new ProfilGrupa();
		pg.setIdProfil(gr.getIdProfil());
		
		pg.setStatus(Konstante.ADMIN_GRUPE);
		String upit = "INSERT INTO " +gr.vratiNazivTabele()+ " "+gr.vratiZaInsert();
		try {
			PreparedStatement ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			gr.setIdProfil(0);
			return;
		}
		
		String upit1 = "SELECT * FROM "+gr.vratiNazivTabele()+" WHERE nazivGrupe ='"+gr.getNazivGrupe()+"' ";
		Statement st;
		try {
			st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit1);
			if(rs.next()) {
				
				int idGrupe = rs.getInt("idGrupa");
				gr.setIdGrupa(idGrupe);
				pg.setIdGrupa(gr.getIdGrupa());
				String upit2 = "INSERT INTO " +pg.vratiNazivTabele()+ " "+ pg.vratiZaInsert();
				try {
					PreparedStatement ps2 = konekcija.prepareStatement(upit2);
					ps2.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	public void upisiClanaUGrupu(ProfilGrupa pg) {
		pg.setIdProfil(pg.getProfil().getIdProfil());
		pg.setIdGrupa(pg.getGrupa().getIdGrupa());
		String upit = "INSERT INTO " +pg.vratiNazivTabele()+ " "+pg.vratiZaInsert();
		try {
			PreparedStatement ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void vratiSveGrupe(Map<String, Object> grupe) {
		Map<String, List<Profil>> clanoviGrupe=new HashMap<>();
		List<Grupa> mojeGrupe=new ArrayList<>();
		Grupa gr = new Grupa();
		Profil p = (Profil) grupe.get("profil");// profil za koji uzimamo grupe
		String upit = "SELECT * FROM `profilgrupa` INNER JOIN grupa ON profilgrupa.idGrupa=grupa.idGrupa INNER JOIN profil ON profilgrupa.idProfil=profil.idProfil";
		try {
			Statement st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit);
			while(rs.next()) {
				Grupa grupa = gr.vratiGrupu(rs);
				Profil profil = p.vratiProfil(rs);
				if(profil.getIdProfil()==p.getIdProfil()) {
					
					mojeGrupe.add(grupa);
				}
				
				try {
					List<Profil> listaClanova = clanoviGrupe.get(grupa.getNazivGrupe());
					if(grupa.getIdProfil()==profil.getIdProfil()) {
						
						listaClanova.add(0,profil);
					}else {
						listaClanova.add(profil);
					}
					
				} catch (Exception e) {
					clanoviGrupe.put(grupa.getNazivGrupe(), new ArrayList<>());
					List<Profil> listaClanova = clanoviGrupe.get(grupa.getNazivGrupe());
					listaClanova.add(profil);
				}
				
			}
			grupe.put("mojeGrupe", mojeGrupe);
			grupe.put("clanoviGrupe", clanoviGrupe);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void udjiUGrupu(ProfilGrupa pg) {
		String upit1 = "SELECT * FROM "+pg.getGrupa().vratiNazivTabele()+ " WHERE nazivGrupe = '"+pg.getGrupa().getNazivGrupe()+"'";
		try {
			Statement st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit1);
			
			if(rs.next()) {
				Grupa grupa = pg.getGrupa().vratiGrupu(rs);
				pg.getGrupa().setIdGrupa(grupa.getIdGrupa());
				pg.getGrupa().setIdProfil(grupa.getIdProfil());
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String upit = "INSERT INTO "+pg.vratiNazivTabele()+ " VALUES ("+pg.getProfil().getIdProfil()+", "+pg.getGrupa().getIdGrupa()+", "+pg.getStatus()+")";
		try {
			PreparedStatement ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void napustiGrupu(ProfilGrupa pg) {
		
		String upit = "DELETE FROM "+pg.vratiNazivTabele()+ " WHERE idProfil = "+pg.getProfil().getIdProfil()+" AND idGrupa = "+pg.getGrupa().getIdGrupa()+"";
		PreparedStatement ps;
		try {
			ps = konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void vratiIstorijuGrupnogCaskanja(Object obj) {
		Map<String, Object> istorijaGrupnogCaskanja = (Map<String, Object>) obj;	
		 GrupnaPoruka gp = (GrupnaPoruka) istorijaGrupnogCaskanja.get("parametri");
		 ArrayList<GrupnaPoruka> listaGrupnihPoruka = (ArrayList<GrupnaPoruka>) istorijaGrupnogCaskanja.get("listaGrupnihPoruka");
		 
		 String upit = "SELECT * FROM "+gp.vratiNazivTabele()+ " INNER JOIN  profil ON profil.idProfil=grupnaporuka.idProfil WHERE idGrupa = "+gp.getIdGrupa()+ "";
		 
		try {
			 Statement st = konekcija.createStatement();
			 ResultSet rs = st.executeQuery(upit);
			 while(rs.next()) {
				 String korisnickoIme=rs.getString("korisnickoIme");
				 GrupnaPoruka poruka = gp.vratiGrupnuPoruku(rs);
				 poruka.setKorisnickoImePosiljaoca(korisnickoIme);
				 listaGrupnihPoruka.add(poruka);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void upisiGrupnuPoruku(OpstiDomen od) {
		GrupnaPoruka poruka=(GrupnaPoruka) od;
		
		String upit="INSERT INTO "+od.vratiNazivTabele()+" "+od.vratiZaInsert();
		
		try {
			PreparedStatement ps=konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void promeniStatusGrupnePoruke(ProfilGrupa pg) {
		
		String upit="UPDATE profilgrupa SET statusPoruka="+pg.getStatus()+" WHERE idProfil="+pg.getIdProfil()+" AND idGrupa="+pg.getIdGrupa()+"";
		try {
			PreparedStatement ps=konekcija.prepareStatement(upit);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void vratiPodatkeZaAdmina(Map<String, Object> podaci) {
		OpstiDomen p=new Profil();
		String upitPr= "SELECT * FROM "+p.vratiNazivTabele();
		Statement stPr;
		try {
			stPr = konekcija.createStatement();
			ResultSet rsPr=stPr.executeQuery(upitPr);
			List<OpstiDomen> listaProfila=p.srediSelect(rsPr);
			podaci.put("sviProfili", listaProfila);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, List<Profil>> clanoviGrupe=new HashMap<>();
		Grupa gr = new Grupa();
		Profil pr=new Profil();
		String upit = "SELECT * FROM `profilgrupa` INNER JOIN grupa ON profilgrupa.idGrupa=grupa.idGrupa INNER JOIN profil ON profilgrupa.idProfil=profil.idProfil";
		try {
			Statement st = konekcija.createStatement();
			ResultSet rs = st.executeQuery(upit);
			while(rs.next()) {
				Grupa grupa = gr.vratiGrupu(rs);
				Profil profil = pr.vratiProfil(rs);

				try {
					List<Profil> listaClanova = clanoviGrupe.get(grupa.getNazivGrupe());
				
					listaClanova.add(profil);

				} catch (Exception e) {
					clanoviGrupe.put(grupa.getNazivGrupe(), new ArrayList<>());
					List<Profil> listaClanova = clanoviGrupe.get(grupa.getNazivGrupe());
					listaClanova.add(profil);
				}
				
			}
		podaci.put("sveGrupe", clanoviGrupe);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
	
}