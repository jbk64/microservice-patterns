package business;

import java.util.ArrayList;

public class Data {

	private ArrayList<Boisson>lstBoissons=new ArrayList<Boisson>();
	public Data() {
		
		// TODO Auto-generated constructor stub
		lstBoissons.add(new Boisson("Coca Cola", 7));
		lstBoissons.add(new Boisson("Fanta", 7));
		lstBoissons.add(new Boisson("Eau gazeuse", 6));
		lstBoissons.add(new Boisson("Eau plate", 6));

	}
	public Boisson getBoisson(int id) {return lstBoissons.get(id);}
}
