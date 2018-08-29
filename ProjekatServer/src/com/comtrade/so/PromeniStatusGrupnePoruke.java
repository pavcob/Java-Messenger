package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.ProfilGrupa;

public class PromeniStatusGrupnePoruke extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		ProfilGrupa pg=(ProfilGrupa) obj;
		Broker.getInstanca().promeniStatusGrupnePoruke(pg);

	}

}
