package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public class ProfilGrupa implements OpstiDomen, Serializable {
	
	private Grupa grupa;
	private Profil profil;
	private int status;
	private int idProfil;
	private int idGrupa;
	private int statusPoruka;
	
	public ProfilGrupa(Grupa grupa, Profil profil, int status,int statusPoruka) {
		
		this.grupa = grupa;
		this.profil = profil;
		this.status = status;
		this.statusPoruka=statusPoruka;
	}
	
	

	public ProfilGrupa() {
		// TODO Auto-generated constructor stub
	}

	
	public int getStatusPoruka() {
		return statusPoruka;
	}



	public void setStatusPoruka(int statusPoruka) {
		this.statusPoruka = statusPoruka;
	}

	
	public Grupa getGrupa() {
		return grupa;
	}

	public void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "profilgrupa";
	}

	@Override
	public String vratiZaInsert() {
		
		return "VALUES ("+getIdProfil()+ ","+getIdGrupa()+ "," +getStatus()+"," +getStatusPoruka()+")";
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
		// TODO Auto-generated method stub
		return null;
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



	
}
