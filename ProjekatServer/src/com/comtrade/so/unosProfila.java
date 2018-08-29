package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Profil;

public class unosProfila extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		Profil p = (Profil) obj;
		Broker.getInstanca().upisiProfil(p);
		
	}

}
