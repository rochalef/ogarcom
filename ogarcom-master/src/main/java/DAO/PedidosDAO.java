package DAO;

import Conexao.Conexao;
import BO.CardapioBO;
import Model.Pedidos;
import java.sql.*;
import java.util.*;

public class PedidosDAO{
    public void criarPedido(int idComanda, int idProduto, int qtd) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT NOME, VALOR FROM cardapio WHERE ID = ?");
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nomeProduto = rs.getString("NOME");
                float valorProduto = rs.getFloat("VALOR");
                PreparedStatement stmtInsert = con.prepareStatement(
                        "INSERT INTO pedidos (ID_COMANDA, ID_ITEM, NOME_ITEM, VALOR, QUANTIDADE) VALUES (?, ?, ?, ?, ?)"
                );
                stmtInsert.setInt(1, idComanda);
                stmtInsert.setInt(2, idProduto);
                stmtInsert.setString(3, nomeProduto);
                stmtInsert.setDouble(4, valorProduto);
                stmtInsert.setInt(5, qtd);
                stmtInsert.executeUpdate();

                stmtInsert.close();
            } else {
                System.out.println("Produto não encontrado!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar pedido ao banco de dados!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
    }


    public List<Pedidos> listarPedidos(int idComanda) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedidos> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedidos WHERE ID_COMANDA = ?");
            stmt.setInt(1, idComanda);
            rs = stmt.executeQuery();

            while (rs.next()){
                Pedidos p = new Pedidos(
                        rs.getInt("ID"),
                        rs.getInt("ID_COMANDA"),
                        rs.getInt("ID_ITEM"),
                        rs.getInt("QUANTIDADE"),
                        rs.getFloat("VALOR"),
                        rs.getString("NOME_ITEM")
                );

                pedidos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar listar pedidos!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return pedidos;
    }

    public void editarPedido(int idPedido, int novaQtd) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pedidos SET quantidade = ? WHERE id = ?");
            stmt.setInt(1, novaQtd);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao editar o pedido selecionado!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public void removerPedido(int idPedido) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM pedidos WHERE id = ?");
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar remover o pedido selecionado!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
