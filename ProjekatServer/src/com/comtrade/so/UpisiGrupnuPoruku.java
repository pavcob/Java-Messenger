package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.GrupnaPoruka;

public class UpisiGrupnuPoruku extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		GrupnaPoruka poruka=(GrupnaPoruka) obj;
		
		Broker.getInstanca().upisiGrupnuPoruku(poruka);

	}

}
