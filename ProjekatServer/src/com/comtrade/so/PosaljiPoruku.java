package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.PrivatnaPoruka;

public class PosaljiPoruku extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		PrivatnaPoruka pp = (PrivatnaPoruka) obj;
		Broker.getInstanca().posaljiPoruku(pp);

	}

}
