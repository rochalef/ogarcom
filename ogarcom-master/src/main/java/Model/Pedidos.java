package Model;

public class Pedidos{
    private int id, idComanda, idProduto, qtd;
    private String nomeProduto;
    private float precoProduto;

    public Pedidos(int id, int idComanda, int idProduto, int qtd, float precoProduto, String nomeProduto){
        this.id = id;
        this.idComanda = idComanda;
        this.idProduto = idProduto;
        this.qtd = qtd;
        this.precoProduto = precoProduto;
        this.nomeProduto = nomeProduto;
    }

    public Pedidos(int idComanda, int idProduto, int qtd, float precoProduto){
        this.idComanda = idComanda;
        this.idProduto = idProduto;
        this.qtd = qtd;
        this.precoProduto = precoProduto;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getIdProduto() {return idProduto;}
    public void setIdProduto(int idProduto) {this.idProduto = idProduto;}

    public int getQtd() {return qtd;}
    public void setQtd(int qtd) {this.qtd = qtd;}

    public float getPrecoProduto() {return precoProduto;}
    public void setPrecoProduto(float precoProduto) {this.precoProduto = precoProduto;}

    public int getIdComanda() {return idComanda;}
    public void setIdComanda(int idComanda) {this.idComanda = idComanda;}

    public String getNomeProduto() {return nomeProduto;}
    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}
}
