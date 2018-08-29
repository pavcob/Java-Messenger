package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PrivatnaPoruka implements Serializable, OpstiDomen {
	
	private int idPrivatnaPoruka;
	private int idProfil;
	private String korisnickoImePosiljaoca;
	private String korisnickoImePrimaoca;
	private int idPrijatelj;
	private int status;
	private String tekstPoruke;
	
	
	

	public PrivatnaPoruka(int idProfil2, int idPrijatelj2, String korisnickoIme) {
		idProfil = idProfil2;
		idPrijatelj = idPrijatelj2;
		korisnickoImePosiljaoca = korisnickoIme;
	}

	public PrivatnaPoruka() {
		// TODO Auto-generated constructor stub
	}

	public int getIdPrivatnaPoruka() {
		return idPrivatnaPoruka;
	}

	public void setIdPrivatnaPoruka(int idPrivatnaPoruka) {
		this.idPrivatnaPoruka = idPrivatnaPoruka;
	}

	public int getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}

	public int getIdPrijatelj() {
		return idPrijatelj;
	}

	public void setIdPrijatelj(int idPrijatelj) {
		this.idPrijatelj = idPrijatelj;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTekstPoruke() {
		return tekstPoruke;
	}

	public void setTekstPoruke(String tekstPoruke) {
		this.tekstPoruke = tekstPoruke;
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "privatnaPoruka";
	}

	@Override
	public String vratiZaInsert() {
	
		
		return "INSERT INTO " +vratiNazivTabele()+ "(idProfil, idPrijatelj, status, tekstPoruke) VALUES ("+getIdProfil()+","+ getIdPrijatelj() +","+ getStatus() + ",'"+ getTekstPoruke()+"')" ;
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
		List<OpstiDomen> rezultat = new ArrayList<>();
		try {
			while(rs.next()) {
				PrivatnaPoruka pp = new PrivatnaPoruka();
				pp.setIdProfil(rs.getInt("idProfil"));
				pp.setIdPrijatelj(rs.getInt("idPrijatelj"));
				pp.setStatus(rs.getInt("status"));
				pp.setTekstPoruke(rs.getString("tekstPoruke"));
				pp.setKorisnickoImePosiljaoca(rs.getString("korisnickoIme"));
				rezultat.add(pp);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rezultat;
	}

	public String getKorisnickoImePosiljaoca() {
		return korisnickoImePosiljaoca;
	}

	public void setKorisnickoImePosiljaoca(String korisnickoImePosiljaoca) {
		this.korisnickoImePosiljaoca = korisnickoImePosiljaoca;
	}
	
	@Override
	public String toString() {
		
		return korisnickoImePosiljaoca+ ":  " +tekstPoruke;
	}

	public String getKorisnickoImePrimaoca() {
		return korisnickoImePrimaoca;
	}

	public void setKorisnickoImePrimaoca(String korisnickoImePrimaoca) {
		this.korisnickoImePrimaoca = korisnickoImePrimaoca;
	}
	

}
