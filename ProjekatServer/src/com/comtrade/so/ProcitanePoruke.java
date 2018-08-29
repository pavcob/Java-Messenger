package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.PrivatnaPoruka;

public class ProcitanePoruke extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		PrivatnaPoruka pp = (PrivatnaPoruka) obj;
		Broker.getInstanca().procitanePoruke(pp);

	}

}
