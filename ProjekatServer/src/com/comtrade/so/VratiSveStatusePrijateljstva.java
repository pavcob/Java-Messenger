package com.comtrade.so;

import java.util.HashMap;

import com.comtrade.broker.Broker;
import com.comtrade.domen.StatusPrijateljstva;

public class VratiSveStatusePrijateljstva extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		HashMap<String, Object> hm = (HashMap<String, Object>) obj;
		StatusPrijateljstva sp = new StatusPrijateljstva();
		Broker.getInstanca().vratiSveStatusePrijateljstva(sp,hm);
	}

}
