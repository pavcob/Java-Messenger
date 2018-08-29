package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import com.comtrade.konstante.Konstante;

public class StatusPrijateljstva implements Serializable, OpstiDomen{
	
	private Profil profil1, profil2;
	private int status;

	public StatusPrijateljstva(Profil profil1, Profil profil2, int status) {
		super();
		this.profil1 = profil1;
		this.profil2 = profil2;
		this.status = status;
	}
	public StatusPrijateljstva() {
		
		
	}

	

	public StatusPrijateljstva(int idProfil) {		//za brisanje prijatelja
		
	}
	public Profil getProfil1() {
		return profil1;
	}



	public void setProfil1(Profil profil1) {
		this.profil1 = profil1;
	}



	public Profil getProfil2() {
		return profil2;
	}



	public void setProfil2(Profil profil2) {
		this.profil2 = profil2;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String vratiNazivTabele() {
		
		return "statusPrijateljstva";
	}

	@Override
	public String vratiZaInsert() {
		
		return "VALUES ("+getProfil1().getIdProfil()+","+getStatus()+","+getProfil2().getIdProfil()+")";
	}

	@Override
	public String vratiZaUpate(OpstiDomen od) {
		
		return "status = "+getStatus()+ " ";
	}

	@Override
	public String vratiZaDelete(OpstiDomen od) {
		
		
		return "DELETE FROM "+od.vratiNazivTabele();
	}

	@Override
	public List<OpstiDomen> srediSelect(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String toString() {
		if(status == Konstante.POSALJI_ZAHTEV) {
			return profil1.getKorisnickoIme() + " poslao zahtev " + profil2.getKorisnickoIme();
	}
		else if(status == Konstante.PRIMLJEN_ZAHTEV) {
			return profil1.getKorisnickoIme()+ " primio zahtev od " + profil2.getKorisnickoIme();
		}
		else return "Nepoznat status zahteva";
	}
	
	

}
