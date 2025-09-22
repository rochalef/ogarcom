package BO;

import DAO.ComandasDAO;
import Model.Comandas;
import Model.Pedidos;
import Model.*;

import java.util.*;

public class RelatorioBO {

    private ComandasDAO cdao = new ComandasDAO();
    private PedidosBO pbo = new PedidosBO();

    public Relatorio gerarRelatorioMensal() {
        List<Comandas> comandasFechadas = cdao.listarFechadas();

        double faturamento = 0.0;
        int totalPedidos = 0;
        Map<String, Integer> contadorPratos = new HashMap<>();


        for (Comandas comanda : comandasFechadas) {
            faturamento += comanda.getPrecoFinal();

            List<Pedidos> pedidos = pbo.listarPedidos(comanda.getId());
            totalPedidos += pedidos.size();

            for (Pedidos p : pedidos) {
                contadorPratos.put(
                        p.getNomeProduto(),
                        contadorPratos.getOrDefault(p.getNomeProduto(), 0) + p.getQtd()
                );
            }
        }


        String pratoMaisVendido;
        if (contadorPratos.isEmpty()) {
            pratoMaisVendido = "Nenhum pedido registrado.";
        } else {
            String maisVendido = null;
            int qtdMax = 0;
            for (Map.Entry<String, Integer> entry : contadorPratos.entrySet()) {
                if (entry.getValue() > qtdMax) {
                    maisVendido = entry.getKey();
                    qtdMax = entry.getValue();
                }
            }
            pratoMaisVendido = maisVendido + " (Quantidade: " + qtdMax + ")";
        }

        return new Relatorio(faturamento, totalPedidos, pratoMaisVendido);
    }
}
