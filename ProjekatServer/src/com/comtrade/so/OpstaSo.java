package com.comtrade.so;

import com.comtrade.broker.Broker;

public abstract class OpstaSo {
	
	public void izvrsiSo(Object obj) {
		
		synchronized ( Broker.getInstanca() ) {
			try {
				pokreniTransakciju();
				izvrsiKonkretnuOperaciju(obj);
				potvrdiTransakciju();
			} catch (Exception e) {
				ponistiTransakciju();
				e.printStackTrace();				
			}
			finally {
				zatvoriKonekciju();
			}			
		}
}

	public abstract  void izvrsiKonkretnuOperaciju(Object obj);
		

	private void pokreniTransakciju() {
		
		Broker.getInstanca().pokreniTransakciju();
	}
	
	private void potvrdiTransakciju() {
		
		Broker.getInstanca().potvrdiTransakciju();
	}
	private void zatvoriKonekciju() {
		
		Broker.getInstanca().zatvoriKonekciju();
	}
	private void ponistiTransakciju() {
		Broker.getInstanca().ponistiTransakciju();
	}
}
