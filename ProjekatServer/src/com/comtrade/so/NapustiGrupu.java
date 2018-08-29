package com.comtrade.so;

import com.comtrade.broker.Broker;
import com.comtrade.domen.ProfilGrupa;

public class NapustiGrupu extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		ProfilGrupa pg = (ProfilGrupa) obj;
		Broker.getInstanca().napustiGrupu(pg);

	}

}
