package BO;

import BO.CardapioBO;
import BO.PedidosBO;

import DAO.ComandasDAO;

import Model.Comandas;
import Model.Pedidos;
import Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ComandasBO{
    Comandas comanda = new Comandas();
    List<Comandas> listaComandas = new ArrayList<>();
    List<Comandas> listaAbertas = new ArrayList<>();

    // Adicionar comanda ao banco de dados
    public void addComanda(Comandas comanda) {
        ComandasDAO cdao = new ComandasDAO();
        listaComandas.add(comanda);
        listaAbertas.add(comanda);
        cdao.addComanda(comanda);
    }

    // ==================== PEDIDOS ====================

    public void addPedido(int idComanda, int idProduto, int qtd) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda == null) {
            System.out.println("Comanda não encontrada!");
            return;
        }

        CardapioBO carbo = new CardapioBO();
        Produto p = carbo.buscarPorId(idProduto);
        List<Pedidos> listaPedidos = comanda.getListaPedidos();
        Pedidos pedido = new Pedidos(idComanda, qtd, idProduto, (float) p.getPreco());

        listaPedidos.add(pedido);
        comanda.setListaPedidos(listaPedidos);
        PedidosBO pedbo = new PedidosBO();
        pedbo.criarPedido(idComanda, idProduto, qtd);

        System.out.println("Pedido adicionado à comanda " + idComanda);
    }

    public void editarPedido(int idComanda, int id, int qtd) {
        Comandas comanda = buscarComandaPorId(idComanda);
        PedidosBO pbo = new PedidosBO();
        comanda.setListaPedidos(pbo.listarPedidos(idComanda));
        if (comanda == null) {
            System.out.println("Comanda não encontrada!");
            return;
        }
        if(qtd >= 0) {
            for (Pedidos p : comanda.getListaPedidos()) {
                if (p.getId() == id) {
                    p.setQtd(qtd);
                    pbo.editarPedido(id, qtd);
                    System.out.println("Pedido atualizado!");
                    return;
                }
            }
            System.out.println("Pedido não encontrado.");
        }else if (qtd == 0){
            for (Pedidos p : comanda.getListaPedidos()) {
                if (p.getId() == id) {
                    pbo.removerPedido(id);
                    comanda.getListaPedidos().remove(p);
                    System.out.println("Pedido excuído!");
                    return;
                }
            }
            System.out.println("Pedido não encontrado.");
        }else{
            System.out.println("Quantidade inválida!");
        }
    }

    public void deletarPedido(int idComanda, int idProduto) {
        Comandas comanda = buscarComandaPorId(idComanda);
        PedidosBO pbo = new PedidosBO();
        comanda.setListaPedidos(pbo.listarPedidos(idComanda));
        if (comanda == null) {
            System.out.println("Comanda não encontrada!");
            return;
        }

        for (Pedidos p : comanda.getListaPedidos()) {
            if (p.getIdProduto() == idProduto) {
                pbo.removerPedido(p.getId());
                comanda.getListaPedidos().remove(p);
                System.out.println("Pedido removido!");
                return;
            }
        }
        System.out.println("Pedido não encontrado.");
    }

    public void listarPedidos(int idComanda) {
        Comandas comanda = buscarComandaPorId(idComanda);
        PedidosBO pdo = new PedidosBO();
        comanda.setListaPedidos(pdo.listarPedidos(idComanda));
        if (comanda == null) {
            System.out.println("Comanda não encontrada!");
            return;
        }

        if (comanda.getListaPedidos().isEmpty()) {
            System.out.println("Nenhum pedido nessa comanda.");
        } else {
            for (Pedidos p : comanda.getListaPedidos()) {
                System.out.println("ID Pedido: " + p.getId() + " | Produto: " + p.getNomeProduto() + " | Quantidade: " + p.getQtd() +
                        " | Preço unitário: " + p.getPrecoProduto());
            }
        }
    }

    // ==================== COMANDAS ====================

    public boolean verificarStatus() {
        ComandasDAO cdao = new ComandasDAO();
        listaAbertas = cdao.listarAbertas();
        return listaAbertas.isEmpty();
    }

    public List<Comandas> listarTodas() {
        ComandasDAO cdao = new ComandasDAO();
        listaAbertas = cdao.listarTudo();
        if (listaComandas.isEmpty()){
            return null;
        } else {
            return listaComandas;
        }
    }

    public List<Comandas> listarAbertas() {
        ComandasDAO cdao = new ComandasDAO();
        listaAbertas = cdao.listarAbertas();
        if (listaAbertas.isEmpty()) {
            return null;
        } else {
            return listaAbertas;
        }
    }

    public void excluirComanda(int idComanda) {
        ComandasDAO cdao = new ComandasDAO();
        listaComandas = cdao.listarTudo();
        for (Comandas comanda : listaComandas) {
            if (comanda.getId() == idComanda) {
                listaComandas.remove(comanda);
                cdao.excluirComanda(comanda);
                System.out.println("Comanda excluída com sucesso!");
                return;
            }
        }
        System.out.println("Comanda não encontrada!");
    }

    // ==================== AUXILIAR ====================

    private Comandas buscarComandaPorId(int idComanda) {
        for (Comandas c : listaAbertas){
            if (c.getId() == idComanda){
                return c;
            }
        }
        for (Comandas c : listaComandas){
            if (c.getId() == idComanda){
                return c;
            }
        }
        return null;
    }
}
