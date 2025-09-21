package BO;

import DAO.ComandasDAO;
import Model.Comandas;
import Model.Pedidos;
import Model.Produto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComandasBO {

    private List<Comandas> listaComandas = new ArrayList<>();
    private List<Comandas> listaAbertas = new ArrayList<>();
    private ComandasDAO cdao = new ComandasDAO();

    // ==================== COMANDAS ====================

    // Abrir uma nova comanda
    public void abrirComanda(Comandas comanda) {
        comanda.setStatus(true); // aberta
        comanda.setDataAbertura(LocalDateTime.now());
        listaComandas.add(comanda);
        listaAbertas.add(comanda);
        cdao.addComanda(comanda);
        System.out.println("Comanda aberta para " + comanda.getNomePessoa() + " na mesa " + comanda.getMesa());
    }

    // Fechar comanda (adiciona valor final e forma de pagamento)
    public void fecharComanda(int idComanda, float valorFinal, String formaPagamento) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda != null && comanda.isStatus()) {
            comanda.setValorFinal(valorFinal);
            comanda.setFormaPagamento(formaPagamento);
            comanda.setStatus(false); // comanda fechada
            comanda.setDataFechamento(LocalDateTime.now());
            listaAbertas.remove(comanda);
            cdao.fecharComanda(comanda);
            System.out.println("Comanda ID " + idComanda + " fechada com sucesso!");
        } else {
            System.out.println("Comanda não encontrada ou já fechada!");
        }
    }

    // Excluir comanda
    public void excluirComanda(int idComanda) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda != null) {
            listaComandas.remove(comanda);
            listaAbertas.remove(comanda);
            cdao.excluirComanda(comanda);
            System.out.println("Comanda excluída com sucesso!");
        } else {
            System.out.println("Comanda não encontrada!");
        }
    }

    // Listar todas as comandas
    public List<Comandas> listarTodas() {
        listaComandas = cdao.listarTudo();
        return listaComandas.isEmpty() ? null : listaComandas;
    }

    // Listar apenas comandas abertas
    public List<Comandas> listarAbertas() {
        listaAbertas = cdao.listarAbertas();
        return listaAbertas.isEmpty() ? null : listaAbertas;
    }

    // ==================== PEDIDOS ====================

    public void addPedido(int idComanda, int idProduto, int qtd) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda == null || !comanda.isStatus()) {
            System.out.println("Comanda não encontrada ou já fechada!");
            return;
        }

        CardapioBO carbo = new CardapioBO();
        Produto p = carbo.buscarPorId(idProduto);
        if (p == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        Pedidos pedido = new Pedidos(idComanda, idProduto, qtd, (float)p.getPreco());
        comanda.getListaPedidos().add(pedido);

        PedidosBO pedbo = new PedidosBO();
        pedbo.criarPedido(idComanda, idProduto, qtd);

        System.out.println("Pedido adicionado à comanda " + idComanda);
    }

    public void editarPedido(int idComanda, int idPedido, int qtd) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda == null || !comanda.isStatus()) {
            System.out.println("Comanda não encontrada ou já fechada!");
            return;
        }

        PedidosBO pbo = new PedidosBO();
        comanda.setListaPedidos(pbo.listarPedidos(idComanda));

        if (qtd > 0) {
            for (Pedidos p : comanda.getListaPedidos()) {
                if (p.getId() == idPedido) {
                    p.setQtd(qtd);
                    pbo.editarPedido(idPedido, qtd);
                    System.out.println("Pedido atualizado!");
                    return;
                }
            }
            System.out.println("Pedido não encontrado.");
        } else if (qtd == 0) {
            for (Pedidos p : comanda.getListaPedidos()) {
                if (p.getId() == idPedido) {
                    pbo.removerPedido(idPedido);
                    comanda.getListaPedidos().remove(p);
                    System.out.println("Pedido excluído!");
                    return;
                }
            }
            System.out.println("Pedido não encontrado.");
        } else {
            System.out.println("Quantidade inválida!");
        }
    }

    public void deletarPedido(int idComanda, int idPedido) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda == null || !comanda.isStatus()) {
            System.out.println("Comanda não encontrada ou já fechada!");
            return;
        }

        PedidosBO pbo = new PedidosBO();
        for (Pedidos p : comanda.getListaPedidos()) {
            if (p.getId() == idPedido) {
                pbo.removerPedido(idPedido);
                comanda.getListaPedidos().remove(p);
                System.out.println("Pedido removido!");
                return;
            }
        }
        System.out.println("Pedido não encontrado.");
    }

    public List<Pedidos> listarPedidos(int idComanda) {
        Comandas comanda = buscarComandaPorId(idComanda);
        if (comanda == null) return null;

        PedidosBO pbo = new PedidosBO();
        comanda.setListaPedidos(pbo.listarPedidos(idComanda));
        return comanda.getListaPedidos();
    }

    // ==================== AUXILIARES ====================

    private Comandas buscarComandaPorId(int idComanda) {
        for (Comandas c : listaAbertas) {
            if (c.getId() == idComanda) return c;
        }
        for (Comandas c : listaComandas) {
            if (c.getId() == idComanda) return c;
        }
        return null;
    }

    public boolean verificarStatus() {
        listaAbertas = cdao.listarAbertas();
        return !listaAbertas.isEmpty();
    }
}
