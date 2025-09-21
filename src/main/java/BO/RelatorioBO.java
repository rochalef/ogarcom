package BO;
import Model.Relatorio;
import Model.Comandas;
import Model.Pedidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioBO {

    private List<Relatorio> listaRelatorios = new ArrayList<>();


    public Relatorio gerarRelatorio(List<Comandas> listaComandas) {
        int totalPedidos = 0;
        double valorArrecadado = 0;
        Map<String, Integer> contagemProdutos = new HashMap<>();

        for (Comandas c : listaComandas) {
            for (Pedidos p : c.getListaPedidos()) {
                totalPedidos += p.getQtd();
                valorArrecadado += p.getPrecoProduto() * p.getQtd();

                contagemProdutos.put(
                        p.getNomeProduto(),
                        contagemProdutos.getOrDefault(p.getNomeProduto(), 0) + p.getQtd()
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

        Relatorio relatorio = new Relatorio(totalPedidos, valorArrecadado, pratoMaisVendido);
        listaRelatorios.add(relatorio);
        return relatorio;
    }

    public List<Relatorio> listarRelatorios() {
        if (listaRelatorios.isEmpty()) {
            System.out.println("Nenhum relatório gerado.");
            return null;
        } else {
            return listaRelatorios;
        }
    }

    // Limpa a lista de relatórios
    public void limparRelatorios() {
        listaRelatorios.clear();
        System.out.println("Lista de relatórios limpa!");
    }
}
