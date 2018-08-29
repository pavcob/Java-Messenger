package com.comtrade.transfer;

import java.io.Serializable;

public class TransferKlasa implements Serializable {
	
	private int operacija;
	private  Object klijentObjekat;
	private Object serverObjekat;
	private Object serverPorukaOdgovor;
	public int getOperacija() {
		return operacija;
	}
	public void setOperacija(int operacija) {
		this.operacija = operacija;
	}
	public Object getKlijentObjekat() {
		return klijentObjekat;
	}
	public void setKlijentObjekat(Object klijentObjekat) {
		this.klijentObjekat = klijentObjekat;
	}
	public Object getServerObjekat() {
		return serverObjekat;
	}
	public void setServerObjekat(Object serverObjekat) {
		this.serverObjekat = serverObjekat;
	}
	public Object getServerPorukaOdgovor() {
		return serverPorukaOdgovor;
	}
	public void setServerPorukaOdgovor(Object serverPorukaOdgovor) {
		this.serverPorukaOdgovor = serverPorukaOdgovor;
	}
	
	
	

}
