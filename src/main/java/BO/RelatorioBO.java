package BO;

import DAO.ComandasDAO;
import Model.Relatorio;
import Model.Comandas;
import Model.Pedidos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.format.DateTimeFormatter;


public class RelatorioBO {

    private ComandasDAO cdao = new ComandasDAO();
    private PedidosBO pbo = new PedidosBO();

    public Relatorio gerarRelatorioMensal(int mes, int ano) {
        List<Comandas> comandasFechadas = listarComandasMes(mes, ano);

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

    public List<Comandas> listarComandasMes(int mes, int ano) {
        List<Comandas> listaFechadas = cdao.listarFechadas();
        List<Comandas> fechadasDoMes = new ArrayList<>();

        for (Comandas c : listaFechadas) {
            LocalDateTime dataAbertura = c.getDataHoraAbertura();

            if (dataAbertura != null &&
                    dataAbertura.getMonthValue() == mes &&
                    dataAbertura.getYear() == ano) {

                fechadasDoMes.add(c);
            }
        }

        if (fechadasDoMes.isEmpty()) {
            System.out.println("Nenhuma comanda registrada " + String.format("%02d", mes) + "/" + ano);
        } else {
            System.out.println("===== RELATÓRIO DE COMANDAS =====");
            System.out.println("MÊS/ANO: " + String.format("%02d", mes) + "/" + ano);
            System.out.println("------------------------------------------");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            for (Comandas c : fechadasDoMes) {
                System.out.println("ID: " + c.getId());
                System.out.println("Preço final: R$ " + String.format("%.2f", c.getPrecoFinal()));
                System.out.println("Data e Hora da Abertura: " + c.getDataHoraAbertura().format(formatter));
                System.out.println("------------------------------------------");
            }
        }

        return fechadasDoMes;
    }

}
