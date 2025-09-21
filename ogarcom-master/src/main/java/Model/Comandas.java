package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comandas {
    private int id;
    private int mesa;
    private String nomePessoa;
    private int idGarcom;
    private String nomeGarcom;
    private String formaPagamento;
    private float valorFinal;
    private boolean status; // padronizei para "status"
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private List<Pedidos> listaPedidos = new ArrayList<>();

    public Comandas() {
        this.status = true; // aberta por padrão
        this.dataAbertura = LocalDateTime.now(); 
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMesa() { return mesa; }
    public void setMesa(int mesa) { this.mesa = mesa; }

    public String getNomePessoa() { return nomePessoa; }
    public void setNomePessoa(String nomePessoa) { this.nomePessoa = nomePessoa; }

    public int getIdGarcom() { return idGarcom; }
    public void setIdGarcom(int idGarcom) { this.idGarcom = idGarcom; }

    public String getNomeGarcom() { return nomeGarcom; }
    public void setNomeGarcom(String nomeGarcom) { this.nomeGarcom = nomeGarcom; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public float getValorFinal() { return valorFinal; }
    public void setValorFinal(float valorFinal) { this.valorFinal = valorFinal; }

    public boolean isStatus() { return status; } // BO usa isStatus()
    public void setStatus(boolean status) { this.status = status; }

    public LocalDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDateTime getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDateTime dataFechamento) { this.dataFechamento = dataFechamento; }

    public List<Pedidos> getListaPedidos() { return listaPedidos; }
    public void setListaPedidos(List<Pedidos> listaPedidos) { this.listaPedidos = listaPedidos; }

    @Override
    public String toString() {
        return "Comanda [ID=" + id +
               ", Mesa=" + mesa +
               ", Cliente=" + nomePessoa +
               ", Garçom=" + nomeGarcom +
               ", Valor Final=" + valorFinal +
               ", Pagamento=" + formaPagamento +
               ", Aberta em=" + (dataAbertura != null ? dataAbertura : "-") +
               ", Fechada em=" + (dataFechamento != null ? dataFechamento : "-") +
               ", Estado=" + (status ? "Aberta" : "Fechada") +
               "]";
    }
}
