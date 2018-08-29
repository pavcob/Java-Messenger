package com.comtrade.domen;

import java.sql.ResultSet;
import java.util.List;

public interface OpstiDomen {
	
	public String vratiNazivTabele();
	public String vratiZaInsert();
	public String vratiZaUpate(OpstiDomen od);
	public String vratiZaDelete(OpstiDomen od);
	public List<OpstiDomen>srediSelect(ResultSet rs);
	
}
