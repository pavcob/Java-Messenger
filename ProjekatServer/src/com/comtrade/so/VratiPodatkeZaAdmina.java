package com.comtrade.so;

import java.util.Map;

import com.comtrade.broker.Broker;

public class VratiPodatkeZaAdmina extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		Map<String, Object> podaci = (Map<String, Object>) obj;
		Broker.getInstanca().vratiPodatkeZaAdmina(podaci);

	}

}
