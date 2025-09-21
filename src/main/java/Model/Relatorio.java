package Model;

public class Relatorio {
    private double faturamentoMensal;
    private int totalPedidos;
    private String pratoMaisVendido;

    public Relatorio(double faturamentoMensal, int totalPedidos, String pratoMaisVendido) {
        this.faturamentoMensal = faturamentoMensal;
        this.totalPedidos = totalPedidos;
        this.pratoMaisVendido = pratoMaisVendido;
    }

    // Getters e Setters
    public double getFaturamentoMensal() {
        return faturamentoMensal;
    }

    public void setFaturamentoMensal(double faturamentoMensal) {
        this.faturamentoMensal = faturamentoMensal;
    }

    public int getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(int totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public String getPratoMaisVendido() {
        return pratoMaisVendido;
    }

    public void setPratoMaisVendido(String pratoMaisVendido) {
        this.pratoMaisVendido = pratoMaisVendido;
    }

    @Override
    public String toString() {
        return "--------------Relatório Mensal--------------\n" +
                String.format("Faturamento: R$ %.2f\n", faturamentoMensal) +
                "Total de Pedidos: " + totalPedidos + "\n" +
                "Prato mais vendido: " + pratoMaisVendido + "\n" +
                "---------------------------------------------";
    }
}
