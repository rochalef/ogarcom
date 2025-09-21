package Model;

public class Relatorio {
    //quantidade de itens vendidos
    //valor arrecadado
    private int totalPedidos;
    private double valorArrecadado;
    private String pratoMaisVendido;

    public Relatorio(int totalPedidos, double valorArrecadado, String pratoMaisVendido) {
        this.totalPedidos = totalPedidos;
        this.valorArrecadado = valorArrecadado;
        this.pratoMaisVendido = pratoMaisVendido;
    }
    public int getTotalPedidos() {return totalPedidos;}
    public void setTotalPedidos(int totalPedidos) {this.totalPedidos = totalPedidos;}
    public double getValorArrecadado() {return valorArrecadado;}

    @Override
    public String toString(){
        return "****** RELATÓRIO MENSAL ********\n" +
                "TOTAL DE PEDIDOS REALIZADOS: " + totalPedidos + "\n" +
                "VALOR TOTAL ARRECADADO: R$ " + String.format("%.2f", valorArrecadado) + "\n" +
                "PRATO MAIS VENDIDO: " + pratoMaisVendido + "\n";
    }
}
