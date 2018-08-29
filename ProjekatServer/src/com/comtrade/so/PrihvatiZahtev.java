package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.konstante.Konstante;

public class PrihvatiZahtev extends OpstaSo{

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		StatusPrijateljstva sp = (StatusPrijateljstva) obj;
		sp.setStatus(Konstante.PRIHVACEN_ZAHTEV);
		Broker.getInstanca().prihvatiZahtev(sp);

	}

}
