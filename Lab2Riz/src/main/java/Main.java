import entity.Clients;
import entity.Masters;
import repo.ClientsRepozitory;
import repo.IRepo;
import repo.MasterRep;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IRepo<Clients> clientsList = new ClientsRepozitory();
        IRepo<Masters> mastersList = new MasterRep();
        List<Masters> masters = mastersList.getAll();
        List<Clients> clients = clientsList.getAll();
        

        for(Clients cl: clients){
            FunctionsBD.ClientsOut(cl);
        }
        for(Masters ma: masters){
            FunctionsBD.MastersOut(ma);
        }
        clientsList.insert(new Clients(null,"turbo","adidas",masters.get(1)));
        clientsList.delete(15);

    }

}
