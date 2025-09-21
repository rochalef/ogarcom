package BO;

import DAO.ComandasDAO;
import Model.Relatorio;
import Model.Comandas;
import Model.Pedidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioBO {

    // Gera relatório mensal a partir das comandas fechadas do banco
    public List<Relatorio> gerarRelatorioMensal() {
        List<Comandas> comandasFechadas = new ComandasDAO().listarFechadas(); // Pega todas as comandas fechadas
        Relatorio relatorio = gerarRelatorio(comandasFechadas); // Reutiliza o método que gera relatório
        List<Relatorio> listaRelatorios = new ArrayList<>();
        listaRelatorios.add(relatorio);
        return listaRelatorios;
    }

    // Gera um relatório a partir de qualquer lista de comandas fechadas
    public Relatorio gerarRelatorio(List<Comandas> comandasFechadas) {
        int totalPedidos = 0;
        double valorArrecadado = 0;
        Map<String, Integer> contagemProdutos = new HashMap<>();

        for (Comandas comanda : comandasFechadas) {
            for (Pedidos pedido : comanda.getListaPedidos()) {
                totalPedidos += pedido.getQtd();
                valorArrecadado += pedido.getPrecoProduto() * pedido.getQtd();

                contagemProdutos.put(
                        pedido.getNomeProduto(),
                        contagemProdutos.getOrDefault(pedido.getNomeProduto(), 0) + pedido.getQtd()
                );
            }
        }

        String pratoMaisVendido = "";
        int maxVendas = 0;
        for (Map.Entry<String, Integer> entry : contagemProdutos.entrySet()) {
            if (entry.getValue() > maxVendas) {
                maxVendas = entry.getValue();
                pratoMaisVendido = entry.getKey();
            }
        }

        return new Relatorio(totalPedidos, valorArrecadado, pratoMaisVendido);
    }
}
