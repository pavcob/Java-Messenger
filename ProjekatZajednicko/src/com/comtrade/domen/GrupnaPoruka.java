package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupnaPoruka implements OpstiDomen, Serializable {

	private int idGrupnaPoruka;
	private int idProfil;
	private int idGrupa;
	private String tekstPoruke;
	private String korisnickoImePosiljaoca;
	private String imeGrupe;
	
	public GrupnaPoruka(int idProfil, int idGrupa) {
	
		this.idProfil = idProfil;
		this.idGrupa = idGrupa;
		
	}

	public GrupnaPoruka() {
		// TODO Auto-generated constructor stub
	}
	
	public String getKorisnickoImePosiljaoca() {
		return korisnickoImePosiljaoca;
	}

	public void setKorisnickoImePosiljaoca(String korisnickoImePosiljaoca) {
		this.korisnickoImePosiljaoca = korisnickoImePosiljaoca;
	}

	public String getImeGrupe() {
		return imeGrupe;
	}

	public void setImeGrupe(String imeGrupe) {
		this.imeGrupe = imeGrupe;
	}

	public int getIdGrupnaPoruka() {
		return idGrupnaPoruka;
	}

	public void setIdGrupnaPoruka(int idGrupnaPoruka) {
		this.idGrupnaPoruka = idGrupnaPoruka;
	}

	public int getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}

	public int getIdGrupa() {
		return idGrupa;
	}

	public void setIdGrupa(int idGrupa) {
		this.idGrupa = idGrupa;
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
		return "grupnaporuka";
	}

	@Override
	public String vratiZaInsert() {
		return "(idProfil,idGrupa,tekstPoruke) VALUES ("+getIdProfil()+","+getIdGrupa()+",'"+getTekstPoruke()+"')";
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
				GrupnaPoruka gp = new GrupnaPoruka();
				gp.setIdGrupnaPoruka(rs.getInt("idGrupnaPoruka"));
				gp.setIdProfil(rs.getInt("idProfil"));
				gp.setIdGrupa(rs.getInt("idGrupa"));
				gp.setTekstPoruke(rs.getString("tekstPoruke"));
				rezultat.add(gp);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rezultat;
	}
	
	public GrupnaPoruka vratiGrupnuPoruku(ResultSet rs) {
		GrupnaPoruka gp = new GrupnaPoruka();
		try {
			gp.setIdGrupnaPoruka(rs.getInt("idGrupnaPoruka"));
			gp.setIdProfil(rs.getInt("idProfil"));
			gp.setIdGrupa(rs.getInt("idGrupa"));
			gp.setTekstPoruke(rs.getString("tekstPoruke"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return gp;
		
		
	}

	@Override
	public String toString() {
		return korisnickoImePosiljaoca+": "+ tekstPoruke;
	}

	


}
