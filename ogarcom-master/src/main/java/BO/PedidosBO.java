package BO;

import DAO.PedidosDAO;
import Model.Pedidos;

import java.util.List;

public class PedidosBO {
    public void criarPedido(int idComanda, int idProduto, int qtd) {
        PedidosDAO pdao = new PedidosDAO();
        pdao.criarPedido(idComanda, idProduto, qtd);
    }

    public List<Pedidos> listarPedidos(int idComanda) {
        PedidosDAO pdao = new PedidosDAO();
        List<Pedidos> p = pdao.listarPedidos(idComanda);
        return p;
    }

    public void editarPedido(int idPedido, int novaQtd) {
        PedidosDAO pdao = new PedidosDAO();
        pdao.editarPedido(idPedido, novaQtd);
    }

    public void removerPedido(int idPedido) {
        PedidosDAO pdao = new PedidosDAO();
        pdao.removerPedido(idPedido);
    }
}
