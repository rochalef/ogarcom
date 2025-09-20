package Model;

public class Produto {
    private int id;
    private String nome;
    private double preco;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String toString(){
        return "ID: " + id + " | Nome: " + nome + " | Preço: " + preco + "\n";
    }
}
