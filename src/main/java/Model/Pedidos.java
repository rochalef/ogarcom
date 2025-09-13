package Model;

public class Pedidos{
    private int id, idProduto, qtd;
    private float precoFinal, precoProduto;

    public Pedidos(int idProduto, int qtd){
        this.idProduto = idProduto;
        this.qtd = qtd;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getIdProduto() {return idProduto;}
    public void setIdProduto(int idProduto) {this.idProduto = idProduto;}

    public int getQtd() {return qtd;}
    public void setQtd(int qtd) {this.qtd = qtd;}

    public float getPrecoFinal() {return precoFinal;}
    public void setPrecoFinal(float precoFinal) {this.precoFinal = precoFinal;}

    public float getPrecoProduto() {return precoProduto;}
    public void setPrecoProduto(float precoProduto) {this.precoProduto = precoProduto;}
}
