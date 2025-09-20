package DAO;

import Conexao.Conexao;
import Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardapioDAO{
    public List<Produto> consulta(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Produto> listaProdutos = new ArrayList<>();
        try{
            stmt = con.prepareStatement("select ID, NOME, VALOR from cardapio");
            rs = stmt.executeQuery();
            while (rs.next()){
                Produto produto =  new Produto();
                produto.setId(rs.getInt(1));
                produto.setNome(rs.getString(2));
                produto.setPreco(rs.getDouble(3));
                listaProdutos.add(produto);
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
        return listaProdutos;
    }
}