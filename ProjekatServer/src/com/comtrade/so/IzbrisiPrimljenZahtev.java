package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.konstante.Konstante;

public class IzbrisiPrimljenZahtev extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		StatusPrijateljstva sp = (StatusPrijateljstva) obj;
		Broker.getInstanca().izbrisiPrimljenZahtev(sp);

	}

}
