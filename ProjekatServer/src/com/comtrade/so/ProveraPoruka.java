package com.comtrade.so;

import java.util.HashMap;
import java.util.List;

import com.comtrade.broker.Broker;

public class ProveraPoruka extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		HashMap<String, Object> hm  = (HashMap<String, Object>) obj; 
		List<Integer> posiljaoci = Broker.getInstanca().proveraNeprocitanihPoruka(hm.get("parametar"));
		hm.put("posiljaoci", posiljaoci);
		
		List<Integer> grupneNeprocitanePoruke=Broker.getInstanca().proveraNeprocitanihGrupnihPoruka(hm.get("parametar"));
		hm.put("grupneNeprocitanePoruke", grupneNeprocitanePoruke);

	}

}
