package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.StatusPrijateljstva;
import com.comtrade.konstante.Konstante;

public class PosaljiZahtev extends OpstaSo{

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		StatusPrijateljstva sp = (StatusPrijateljstva) obj;
		Broker.getInstanca().posaljiZahtev(sp);
		StatusPrijateljstva sp2 = new StatusPrijateljstva(sp.getProfil2(), sp.getProfil1(),Konstante.PRIMLJEN_ZAHTEV);
		Broker.getInstanca().posaljiZahtev(sp2);
		
	}

}
