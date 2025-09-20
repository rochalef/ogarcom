package DAO;

import Model.Comandas;
import Conexao.Conexao;
import Model.Funcionario;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public Funcionario verDados(int idGarcom) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario f = new Funcionario();
        try {
            stmt = con.prepareStatement("SELECT NOME, SALARIO FROM funcionarios WHERE ID = ?");
            stmt.setInt(1, idGarcom);
            rs = stmt.executeQuery();
            if(rs.next()){
                f.setNome(rs.getString("NOME"));
                f.setSalario(rs.getFloat("SALARIO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao buscar dados do funcionário");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return f;
    }
}

