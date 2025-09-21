package DAO;

import Model.Comandas;
import BO.FuncionarioBO;
import Conexao.Conexao;
import Model.Pedidos;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandasDAO {
    public void addComanda(Comandas comanda) {
        FuncionarioBO fbo = new FuncionarioBO();
        LocalDateTime agora = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(agora);
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try {
            fbo.verNome(comanda.getIdGarcom());
            stmt = con.prepareStatement("INSERT INTO comandas (NOME_PESSOA, NOME_GARCOM, MESA, DATAHORA_ABERTURA, ID_GARCOM, ESTADO) VALUES (?, ?, ?, ?, ?, true);");
            stmt.setString(1, comanda.getNomePessoa());
            stmt.setString(2, fbo.verNome(comanda.getIdGarcom()));
            stmt.setInt(3, comanda.getMesa());
            stmt.setTimestamp(4, ts);
            stmt.setInt(5, comanda.getIdGarcom());
            stmt.executeUpdate();

            System.out.println("Comanda de " + comanda.getNomePessoa() + " inserida com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public void alterarComanda(Comandas comanda) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE comandas SET MESA = ? WHERE ID = ?");
            stmt.setInt(1, comanda.getMesa());
            stmt.setInt(2, comanda.getId());
            stmt.executeUpdate();

            System.out.println("Comanda de " + comanda.getNomePessoa() + " alterada com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao alterar informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public void excluirComanda(Comandas comanda) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM comandas WHERE ID = ?");
            stmt.setInt(1, comanda.getId());
            stmt.executeUpdate();
            System.out.println("Comanda de " + comanda.getNomePessoa() + " excluída com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao excluir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public List<Comandas> listarTudo() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Comandas> listaComandas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT ID, NOME_PESSOA, NOME_GARCOM, MESA, ESTADO, DATAHORA_ABERTURA FROM comandas");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Comandas comanda = new Comandas();
                comanda.setId(rs.getInt("ID"));
                comanda.setNomePessoa(rs.getString("NOME_PESSOA"));
                comanda.setNomeGarcom(rs.getString("NOME_GARCOM"));
                comanda.setMesa(rs.getInt("MESA"));
                comanda.setStatus(rs.getBoolean("ESTADO"));
                comanda.setDataHoraAbertura(rs.getTimestamp("DATAHORA_ABERTURA").toLocalDateTime());

                listaComandas.add(comanda);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return listaComandas;
    }

    public List<Comandas> listarAbertas(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs;

        List<Comandas> listaAbertas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT ID, NOME_PESSOA, NOME_GARCOM, MESA, ESTADO, DATAHORA_ABERTURA FROM comandas WHERE ESTADO = TRUE");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Comandas comanda = new Comandas();
                comanda.setId(rs.getInt("ID"));
                comanda.setNomePessoa(rs.getString("NOME_PESSOA"));
                comanda.setNomeGarcom(rs.getString("NOME_GARCOM"));
                comanda.setMesa(rs.getInt("MESA"));
                comanda.setStatus(rs.getBoolean("ESTADO"));
                comanda.setDataHoraAbertura(rs.getTimestamp("DATAHORA_ABERTURA").toLocalDateTime());

                listaAbertas.add(comanda);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao listar informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
        return listaAbertas;
    }

    public void fecharComanda(Comandas comanda) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "UPDATE comandas SET ESTADO = FALSE, VALOR_FINAL = ?, DATAHORA_FECHAMENTO = NOW() WHERE ID = ?"
            );
            stmt.setDouble(1, comanda.getPrecoFinal());
            stmt.setInt(2, comanda.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao fechar a comanda no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public List<Comandas> listarFechadas() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs;

        List<Comandas> listaFechadas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT ID, NOME_PESSOA, NOME_GARCOM, MESA, ESTADO, DATAHORA_ABERTURA, VALOR_FINAL FROM comandas WHERE ESTADO = FALSE");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Comandas comanda = new Comandas();
                comanda.setId(rs.getInt("ID"));
                comanda.setNomePessoa(rs.getString("NOME_PESSOA"));
                comanda.setNomeGarcom(rs.getString("NOME_GARCOM"));
                comanda.setMesa(rs.getInt("MESA"));
                comanda.setStatus(rs.getBoolean("ESTADO"));
                comanda.setDataHoraAbertura(rs.getTimestamp("DATAHORA_ABERTURA").toLocalDateTime());
                comanda.setPrecoFinal((float) rs.getDouble("VALOR_FINAL"));
                listaFechadas.add(comanda);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao listar informações no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
        return listaFechadas;
    }


}
