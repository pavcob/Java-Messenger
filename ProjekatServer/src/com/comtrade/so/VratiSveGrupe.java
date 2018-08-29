package com.comtrade.so;


import java.util.Map;

import com.comtrade.broker.Broker;

@SuppressWarnings("unchecked")
public class VratiSveGrupe extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		Map<String, Object> grupa =  (Map<String, Object>) obj;
		Broker.getInstanca().vratiSveGrupe(grupa);

	}

}
