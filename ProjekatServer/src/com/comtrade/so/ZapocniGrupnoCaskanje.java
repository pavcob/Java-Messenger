package com.comtrade.so;

import com.comtrade.broker.Broker;

public class ZapocniGrupnoCaskanje extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		Broker.getInstanca().vratiIstorijuGrupnogCaskanja(obj);

	}

}
