package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Grupa implements OpstiDomen, Serializable{
	
	private int idGrupa;
	private String nazivGrupe;
	private int idProfil;
	
	public Grupa (int idGrupa, String nazivGrupe,int idProfil ) {
		
		this.idGrupa = idGrupa;
		this.nazivGrupe = nazivGrupe;
		this.idProfil = idProfil;
	
	}
	
	public Grupa() {
		// TODO Auto-generated constructor stub
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
	
	public String getNazivGrupe() {
		return nazivGrupe;
	}

	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}
	
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "grupa";
	}

	@Override
	public String vratiZaInsert() {
		// TODO Auto-generated method stub
		return "(nazivGrupe,idProfil) VALUES ( '"+getNazivGrupe()+"',"+getIdProfil()+")";
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
	
	public String toString () {
		
		return getNazivGrupe();
		
		
	}

	public Grupa vratiGrupu(ResultSet rs) {
		Grupa gr = null;
		
		try {
			int idGrupe = rs.getInt("idGrupa");
			String nazivGrupe = rs.getString("nazivGrupe");
			int idProfil = rs.getInt("grupa.idProfil");
			gr = new Grupa(idGrupe, nazivGrupe, idProfil);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return gr;
		
		
	}


	

}
