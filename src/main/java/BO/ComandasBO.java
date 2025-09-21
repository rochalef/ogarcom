package BO;

import BO.CardapioBO;
import BO.PedidosBO;

import DAO.ComandasDAO;

import Model.Comandas;
import Model.Pedidos;
import Model.Produto;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class ComandasBO{
    Comandas comanda = new Comandas();
    List<Comandas> listaComandas = new ArrayList<>();
    List<Comandas> listaAbertas = new ArrayList<>();
    List<Comandas> listaFechadas = new ArrayList<>();

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

    public void editarPedido(int idComanda, int idPedido, int qtd) {
        Comandas comanda = buscarComandaPorId(idComanda);
        PedidosBO pbo = new PedidosBO();
        comanda.setListaPedidos(pbo.listarPedidos(idComanda));
        if (comanda == null) {
            System.out.println("Comanda não encontrada!");
            return;
        }
        if(qtd > 0) {
            for (Pedidos p : comanda.getListaPedidos()) {
                if (p.getId() == idPedido) {
                    p.setQtd(qtd);
                    pbo.editarPedido(idPedido, qtd);
                    System.out.println("Pedido atualizado!");
                    return;
                }
            }
            System.out.println("Pedido não encontrado.");
        }else if (qtd == 0){
            for (Pedidos p : comanda.getListaPedidos()) {
                if (p.getId() == idPedido) {
                    pbo.removerPedido(idPedido);
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

    public void deletarPedido(int idComanda, int idPedido) {
        Comandas comanda = buscarComandaPorId(idComanda);
        PedidosBO pbo = new PedidosBO();
        comanda.setListaPedidos(pbo.listarPedidos(idComanda));
        if (comanda == null) {
            System.out.println("Comanda não encontrada!");
            return;
        }

        for (Pedidos p : comanda.getListaPedidos()) {
            if (p.getId() == idPedido) {
                pbo.removerPedido(idPedido);
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


    // ==================== FECHAR COMANDA ====================
    public void fecharComanda(int idComanda) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda == null) {
            System.out.println("Comanda não encontrada!");
            return;
        }

        PedidosBO pbo = new PedidosBO();
        comanda.setListaPedidos(pbo.listarPedidos(idComanda));

        System.out.println("---------------------------------------");
        System.out.println("Pedidos da Comanda " + idComanda + ":");

        float totalPedidos = 0;
        if (comanda.getListaPedidos().isEmpty()) {
            System.out.println("Nenhum pedido nessa comanda.");
        } else {
            for (Pedidos p : comanda.getListaPedidos()) {
                System.out.println(" - " + p.getQtd() + "x " + p.getNomeProduto() + " | Preço unitário: R$ " + String.format("%.2f", p.getPrecoProduto()));
                totalPedidos += p.getPrecoProduto() * p.getQtd();
            }
        }

        System.out.println("---------------------------------------");
        System.out.println("Total dos pedidos: R$ " + String.format("%.2f", totalPedidos));

        // 10% do garçom
        Scanner scanner = new Scanner(System.in);
        System.out.print("Deseja adicionar os 10% de serviço? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();

        float precoFinal;
        if (resposta.equals("S")) {
            float taxaGarcom = totalPedidos * 0.10f;
            precoFinal = totalPedidos + taxaGarcom;
            System.out.println("Taxa de serviço (10%): R$ " + String.format("%.2f", taxaGarcom));
            System.out.println("Preço final: R$ " + String.format("%.2f", precoFinal));
        } else {
            precoFinal = totalPedidos;
            System.out.println("Taxa de serviço não adicionada.");
            System.out.println("Preço final: R$ " + String.format("%.2f", precoFinal));
        }

        System.out.println("---------------------------------------");

        // Fecha a comanda no banco de dados
        comanda.setPrecoFinal(precoFinal);
        ComandasDAO cdao = new ComandasDAO();
        cdao.fecharComanda(comanda);

        // Remove da lista de abertas
        listaAbertas.remove(comanda);

        System.out.println("Comanda " + comanda.getId() + " fechada com sucesso!");
        //scanner.close(); // Fechar o scanner para evitar vazamento de recursos
    }



    // Buscar comandas fechadas
    public List<Comandas> listarFechadas() {
        ComandasDAO cdao = new ComandasDAO();
        listaFechadas = cdao.listarFechadas();

        if (listaFechadas.isEmpty()) {
            System.out.println("Não há comandas fechadas.");
            return new ArrayList<>(); // retorna lista vazia em vez de null
        } else {
            for (Comandas c : listaFechadas) {
                System.out.println("ID: " + c.getId() + " | Preço final: R$ " + c.getPrecoFinal());
            }
            return listaFechadas;
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
