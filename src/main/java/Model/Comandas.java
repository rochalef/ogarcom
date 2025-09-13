package Model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Comandas {
    private int id, mesa, idGarcom;
    private String nome;
    private boolean status;
    private float precoFinal;

    List<Pedidos> listaPedidos = new ArrayList<>();

    // Funções CRUD

    public void criarPedido(int idProduto, int qtd){

        /* Fazer verificação de existencia de pedido; caso o produto já tenha sido pedido anteriormente,
        * só adiciona mais a quantidade pré-existente; caso contrário, cria mais um objeto para registrar
        * o pedido feito.*/

        Pedidos pedido = new Pedidos(idProduto, qtd);
        listaPedidos.add(pedido);
    }

    public void editarPedido(int idProduto){

    }

    // Lista de getters e setters para cada atributo

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getMesa() {return mesa;}
    public void setMesa(int mesa) {this.mesa = mesa;}

    public int getIdGarcom() {return idGarcom;}
    public void setIdGarcom(int idGarcom) {this.idGarcom = idGarcom;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public boolean isStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}

    public float getPrecoFinal() {return precoFinal;}
    public void setPrecoFinal(float precoFinal) {this.precoFinal = precoFinal;}
}
