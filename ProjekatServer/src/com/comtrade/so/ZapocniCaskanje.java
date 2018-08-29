package com.comtrade.so;

import java.util.ArrayList;
import java.util.HashMap;

import com.comtrade.broker.Broker;
import com.comtrade.domen.PrivatnaPoruka;

public class ZapocniCaskanje extends OpstaSo {

	@Override
	public void izvrsiKonkretnuOperaciju(Object obj) {
		
		Broker.getInstanca().vratiIstorijuCaskanja(obj);

	}

}
