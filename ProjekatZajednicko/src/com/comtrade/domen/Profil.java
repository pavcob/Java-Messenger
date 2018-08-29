package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Profil implements Serializable,OpstiDomen {
	
	private String korisnickoIme, lozinka, ime, prezime,mail, zanimanje,datumRodjenja;
	private String pol;
	private boolean jeAdmin = false;
	private int status = 1;
	private int idProfil;
	
	public Profil(int idProfil,String korisnickoIme, String lozinka, String ime, String prezime, String mail,int status, String zanimanje,
			String datumRodjenja, String pol) {
		super();
		this.idProfil = idProfil;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.mail = mail;
		this.zanimanje = zanimanje;
		this.pol = pol;
		this.status = status;
		this.datumRodjenja = datumRodjenja;
	}

	public Profil() {
		// TODO Auto-generated constructor stub
	}
	

	public int getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getZanimanje() {
		return zanimanje;
	}

	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}
	
	

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	@Override
	public String vratiNazivTabele() {
		
		return "profil";
	}

	@Override
	public String vratiZaInsert() {
		
		return "(korisnickoIme,lozinka,ime,prezime,eMail,status,pol,zanimanje,datumRodjenja) VALUES"
				+ " ('"+getKorisnickoIme()+"','"+getLozinka()+"','"+getIme()+"','"+getPrezime()+"','"+getMail()+"',"
						+ ""+getStatus()+",'"+getPol()+"','"+getZanimanje()+"','"+getDatumRodjenja()+"')";
	}

	@Override
	public String vratiZaUpate(OpstiDomen od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiZaDelete(OpstiDomen od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OpstiDomen> srediSelect(ResultSet rs) {
		List<OpstiDomen>listaProfila = new ArrayList<>();
		try {
			while(rs.next()) {
				
				try {
					int idProfil = rs.getInt("idProfil");
					String korisnickoIme = rs.getString("korisnickoIme");
					String lozinka = rs.getString("lozinka");
					String ime = rs.getString("ime");
					String prezime = rs.getString("prezime");
					String eMail = rs.getString("eMail");
					int status = rs.getInt("status");
					String pol = rs.getString("pol");
					String zanimanje = rs.getString("zanimanje");
					String datumRodjenja = rs.getString("datumRodjenja");
					Profil p = new Profil(idProfil, korisnickoIme, lozinka, ime, prezime, eMail, status, zanimanje, datumRodjenja, pol);
					listaProfila.add(p);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProfila;
	}
	
	public Profil vratiProfil(ResultSet rs) {
		Profil pr=null;
				try {
						int idProfil = rs.getInt("idProfil");
						String korisnickoIme = rs.getString("korisnickoIme");
						String lozinka = rs.getString("lozinka");
						String ime = rs.getString("ime");
						String prezime = rs.getString("prezime");
						String eMail = rs.getString("eMail");
						int status = rs.getInt("status");
						String pol = rs.getString("pol");
						String zanimanje = rs.getString("zanimanje");
						String datumRodjenja = rs.getString("datumRodjenja");
						pr = new Profil(idProfil, korisnickoIme, lozinka, ime, prezime, eMail, status, zanimanje, datumRodjenja, pol);	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
								
		return pr;
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}
	
	

	
	
	
	
	
	
	

}
