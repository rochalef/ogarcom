package BO;

import DAO.ComandasDAO;
import Model.Comandas;
import Model.Pedidos;
import java.util.List;
import java.util.ArrayList;

public class ComandasBO{
    List<Pedidos> listaPedidos = new ArrayList<>();
    List<Comandas> listaComandas = new ArrayList<>();
    List<Comandas> listaAbertas = new ArrayList<>();

    // Adicionar comanda ao banco de dados

    public void addComanda(Comandas comanda){
        ComandasDAO cdao = new ComandasDAO();
        cdao.addComanda(comanda);
    }

    // Funções CRUD

    public void criarPedido(int idProduto, int qtd){
        for(Pedidos p : listaPedidos){
            if(p.getIdProduto() == idProduto){
                // Se o produto existir vai ser somado a qtd
                p.setQtd(p.getQtd() + qtd);
                return;
            }
        }
        Pedidos pedido = new Pedidos(idProduto, qtd);
        listaPedidos.add(pedido);
    }

    public void editarPedido(int idProduto, int qtd){
        //fazer
    }

    public void deletarPedido(int idProduto) {
        for (Pedidos p : listaPedidos) {
            if (p.getIdProduto() == idProduto) {
                listaPedidos.remove(p);
                System.out.println("PEDIDO REMOVIDO!");
                return;
            }
        }
        System.out.println("Pedido não encontrado. Tente novamente!");
    }

    public void listarPedidos() {
        if (listaPedidos.isEmpty()) {
            System.out.println("Ainda não foi realizado nenhum pedido!");
        } else {
            for (Pedidos p : listaPedidos) {
                System.out.println("PRODUTO: " + p.getIdProduto() + " - QUANTIDADE: " + p.getQtd());
            }
        }
    }

    public double calcularPrecoFinal(List<Pedidos> listaPedidos){
        int soma=0;
        for(Pedidos p : listaPedidos){
            // p.getIdPedido p pegar o id, joga no db e pega o preco, soma com o anterior
            soma += 0;
        }
        return soma;
    }
    
    public boolean verificarStatus(){
        ComandasDAO cdao = new ComandasDAO();
        listaAbertas = cdao.listarAbertas();
        return listaAbertas.isEmpty();
    }

    public List<Comandas> listarAbertas(){
        ComandasDAO cdao = new ComandasDAO();
        listaAbertas = cdao.listarAbertas();
        if(listaAbertas.isEmpty()){
            return null;
        }else {
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
        System.out.println("Comanda não encontrada. Tente novamente!");
    }

}
