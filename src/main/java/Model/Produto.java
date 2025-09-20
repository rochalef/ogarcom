package Model;

public class Produto {
    private int id;
    private String nome;
    private float preco;

    public Produto(){}

    public Produto(int id, String nome, float preco){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

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

    public double getPreco() {return preco;}
    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String toString(){
        return "ID: " + id + " | Nome: " + nome + " | Preço: " + preco + "\n";
    }
}
