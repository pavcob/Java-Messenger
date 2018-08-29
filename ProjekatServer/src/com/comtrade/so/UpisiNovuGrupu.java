package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Grupa;

public class UpisiNovuGrupu extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		Grupa gr = (Grupa) obj;
		Broker.getInstanca().upisiNovuGrupu(gr);

	}

}
