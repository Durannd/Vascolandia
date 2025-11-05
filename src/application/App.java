package application;


import dao.ClienteDao;
import dao.FornecedorDao;
import dao.impl.ClienteDaoImpl;
import dao.impl.FornecedorDaoImpl;
import model.entities.Cliente;

public class App {
    public static void main(String[] args) {
       FornecedorDao fornecedorDao = new FornecedorDaoImpl();
       fornecedorDao.buscarPorAlimento("vasco");
    }
}
