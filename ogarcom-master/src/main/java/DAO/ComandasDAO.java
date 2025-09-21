package DAO;

import Conexao.Conexao;
import Model.Comandas;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComandasDAO {

    // Adicionar nova comanda
    public void addComanda(Comandas comanda) {
        String sql = "INSERT INTO comandas (mesa, nome_pessoa, id_garcom, estado, data_abertura) VALUES (?, ?, ?, true, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, comanda.getMesa());
            stmt.setString(2, comanda.getNomePessoa());
            stmt.setInt(3, comanda.getIdGarcom());
            stmt.setTimestamp(4, Timestamp.valueOf(comanda.getDataAbertura()));
            stmt.executeUpdate();

            // Pegar ID gerado e setar na comanda
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                comanda.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar comanda: " + e.getMessage(), e);
        }
    }

    // Fechar comanda
    public void fecharComanda(Comandas comanda) {
        String sql = "UPDATE comandas SET valor_final = ?, forma_pagamento = ?, estado = false, data_fechamento = ? WHERE id = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setFloat(1, comanda.getValorFinal());
            stmt.setString(2, comanda.getFormaPagamento());
            stmt.setTimestamp(3, Timestamp.valueOf(comanda.getDataFechamento()));
            stmt.setInt(4, comanda.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar comanda: " + e.getMessage(), e);
        }
    }

    // Excluir comanda
    public void excluirComanda(Comandas comanda) {
        String sql = "DELETE FROM comandas WHERE id = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, comanda.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir comanda: " + e.getMessage(), e);
        }
    }

    // Listar todas
    public List<Comandas> listarTudo() {
        List<Comandas> lista = new ArrayList<>();
        String sql = "SELECT * FROM comandas";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Comandas c = new Comandas();
                c.setId(rs.getInt("id"));
                c.setMesa(rs.getInt("mesa"));
                c.setNomePessoa(rs.getString("nome_pessoa"));
                c.setIdGarcom(rs.getInt("id_garcom"));
                c.setFormaPagamento(rs.getString("forma_pagamento"));
                c.setValorFinal(rs.getFloat("valor_final"));
                c.setStatus(rs.getBoolean("estado"));
                Timestamp abertura = rs.getTimestamp("data_abertura");
                if (abertura != null) c.setDataAbertura(abertura.toLocalDateTime());
                Timestamp fechamento = rs.getTimestamp("data_fechamento");
                if (fechamento != null) c.setDataFechamento(fechamento.toLocalDateTime());
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar comandas: " + e.getMessage(), e);
        }
        return lista;
    }

    // Listar apenas abertas
    public List<Comandas> listarAbertas() {
        List<Comandas> lista = new ArrayList<>();
        String sql = "SELECT * FROM comandas WHERE estado = true";
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Comandas c = new Comandas();
                c.setId(rs.getInt("id"));
                c.setMesa(rs.getInt("mesa"));
                c.setNomePessoa(rs.getString("nome_pessoa"));
                c.setIdGarcom(rs.getInt("id_garcom"));
                c.setStatus(true);
                Timestamp abertura = rs.getTimestamp("data_abertura");
                if (abertura != null) c.setDataAbertura(abertura.toLocalDateTime());
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar comandas abertas: " + e.getMessage(), e);
        }
        return lista;
    }
}
