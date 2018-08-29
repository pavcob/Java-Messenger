package com.comtrade.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.comtrade.transfer.TransferKlasa;

public class Komunikacija {
	
	private static Komunikacija instanca;
	private Socket soket;
	
	
	
	public Socket getSoket() {
		return soket;
	}
	public void setSoket(Socket soket) {
		this.soket = soket;
	}
	private Komunikacija() {
		
		try {
			soket = new Socket("127.0.0.1", 9000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Komunikacija vratiKomunikaciju() {
		
		if(instanca == null) {
			
			instanca = new Komunikacija();
		}
		return instanca;
	}
	public void posalji(TransferKlasa tk) {
		
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(soket.getOutputStream());
			outStream.writeObject(tk);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public TransferKlasa procitaj() throws ClassNotFoundException, IOException {
		ObjectInputStream inSoket = null;
		try {
			 inSoket = new ObjectInputStream(soket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (TransferKlasa) inSoket.readObject() ;
	}

}
