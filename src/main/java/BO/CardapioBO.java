package BO;

import DAO.CardapioDAO;
import Model.TesteProduto;
import java.util.List;

public class CardapioBO{
    public List<TesteProduto> vizualizarCardapio(){
        CardapioDAO cdao = new CardapioDAO();
        List<TesteProduto> listaProdutos = cdao.consulta();
        return listaProdutos;
    }
}