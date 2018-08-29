package com.comtrade.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.comtrade.nit.NitObradaZahteva;

public class Server extends Thread {
	
	public void run() {
		try {
			pokreniServer();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void pokreniServer() {
		
		try {
			ServerSocket ss = new ServerSocket(9000);
			System.out.println("Server je pokrenut i spreman je da prihvati klijente");
			while(true) {
				
				Socket s = ss.accept();
				NitObradaZahteva oz = new NitObradaZahteva();
				oz.setSoket(s);
				oz.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
