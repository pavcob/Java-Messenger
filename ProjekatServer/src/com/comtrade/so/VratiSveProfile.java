package com.comtrade.so;

import java.util.HashMap;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Profil;

public class VratiSveProfile extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		HashMap<String,Object>hm = (HashMap<String, Object>) obj;
		Profil p = (Profil) hm.get("profil");
		List<OpstiDomen>lista = Broker.getInstanca().vratiSveProfile(p);
		hm.put("listaProfila", lista);
		
		
	}

}
