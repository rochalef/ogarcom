package BO;

import DAO.CardapioDAO;
import Model.Produto;
import java.util.List;

public class CardapioBO{
    public List<Produto> vizualizarCardapio(){
        CardapioDAO cdao = new CardapioDAO();
        List<Produto> listaProdutos = cdao.consulta();
        return listaProdutos;
    }
}