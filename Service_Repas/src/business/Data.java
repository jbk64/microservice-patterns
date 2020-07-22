package business;

import java.util.ArrayList;

public class Data {

    private ArrayList<Repas> lstRepas = new ArrayList<Repas>();

    public Data() {

        // TODO Auto-generated constructor stub
        lstRepas.add(new Repas("Filet mignon aux champignons/riz", 45));
        lstRepas.add(new Repas("Poulet rotisalade", 60));
        lstRepas.add(new Repas("cabillaud en papillote avec poireaux et carotte/riz", 60));
        lstRepas.add(new Repas("saint-jacques/fondue de poireaux", 60));
        lstRepas.add(new Repas("filet dinde a la creme/pates", 60));
        lstRepas.add(new Repas("roti de veau/petits pois carottes", 60));
        lstRepas.add(new Repas("soles meunieres/ecrasee", 60));

    }

    public Repas getRepas(int id) {
        return lstRepas.get(id);
    }
}
