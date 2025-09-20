package DAO;

import Model.Comandas;
import Conexao.Conexao;
import Model.TesteProduto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CardapioDAO{
    public List<TesteProduto> consulta(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<TesteProduto> listaProdutos = new ArrayList<>();
        try{
            stmt = con.prepareStatement("select ID, NOME, VALOR from cardapio");
            rs = stmt.executeQuery();
            while (rs.next()){
                TesteProduto produto =  new TesteProduto();
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