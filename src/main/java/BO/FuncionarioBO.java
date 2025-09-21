package BO;

import DAO.FuncionarioDAO;
import Model.Funcionario;

public class FuncionarioBO{
    FuncionarioDAO fdao = new FuncionarioDAO();

    public Funcionario verTodosDados(int idGarcom){
        Funcionario f = new Funcionario();
        f = fdao.verDados(idGarcom);
        return f;
    }

    public String verNome(int idGarcom){
        Funcionario f = new Funcionario();
        f = fdao.verDados(idGarcom);
        return f.getNome();
    }

    public float verSalario(int idGarcom){
        Funcionario f = new Funcionario();
        f = fdao.verDados(idGarcom);
        return f.getSalario();
    }
}
