package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Profil;
import com.comtrade.domen.StatusPrijateljstva;

public class IzbrisiPrijatelja extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		StatusPrijateljstva sp = (StatusPrijateljstva) obj;
		Broker.getInstanca().izbrisiPrijatelja(sp);

	}

}
