package Model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Comandas {
    private int id, mesa, idGarcom;
    private String nome_pessoa, nome_garcom;
    private LocalDateTime dataHoraAbertura,dataHoraFechamento;
    private List<Pedidos> listaPedidos = new ArrayList<>();
    private boolean status;
    private float precoFinal;

    // Lista de getters e setters para cada atributo

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getMesa() {return mesa;}
    public void setMesa(int mesa) {this.mesa = mesa;}

    public int getIdGarcom() {return idGarcom;}
    public void setIdGarcom(int idGarcom) {this.idGarcom = idGarcom;}

    public String getNomePessoa() {return nome_pessoa;}
    public void setNomePessoa(String nome_pessoa) {this.nome_pessoa = nome_pessoa;}

    public boolean isStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}

    public float getPrecoFinal() {return precoFinal;}
    public void setPrecoFinal(float precoFinal) {this.precoFinal = precoFinal;}
    
    public String getStatus() {return (status ? "Aberta" : "Fechada");}

    public String getNomeGarcom() {return nome_garcom;}
    public void setNomeGarcom(String nome_garcom) {this.nome_garcom = nome_garcom;}

    public LocalDateTime getDataHoraAbertura() {return dataHoraAbertura;}
    public void setDataHoraAbertura(LocalDateTime dataHoraAbertura) {this.dataHoraAbertura = dataHoraAbertura;}
    
    public LocalDateTime getDataHoraFechamento() {return dataHoraFechamento;}
    public void setDataHoraFechamento(LocalDateTime dataHoraFechamento) {this.dataHoraFechamento = dataHoraFechamento;}

    public List<Pedidos> getListaPedidos() {return listaPedidos;}
    public void setListaPedidos(List<Pedidos> listaPedidos) {this.listaPedidos = listaPedidos;}

    public String toString(){return "ID: " + id +
            " | Mesa: " + mesa +
            " | Nome do cliente: " + nome_pessoa +
            " | Nome do garçom: " + nome_garcom +
            " | Status: " + (status ? "Aberta" : "Fechada") +
            " | Data e hora de abertura: " + dataHoraAbertura;
    }
}
