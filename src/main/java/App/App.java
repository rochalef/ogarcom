package App;

import Model.Comandas;
import Model.Produto;
import BO.FuncionarioBO;
import BO.ComandasBO;
import BO.CardapioBO;
import BO.RelatorioBO;
import Model.Relatorio;


import java.util.*;

public class App{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ComandasBO cbo = new ComandasBO();
        FuncionarioBO fbo = new FuncionarioBO();
        RelatorioBO rbo = new RelatorioBO();
        List<Comandas> listaComandasAbertas = new ArrayList<>();
        listaComandasAbertas = cbo.listarAbertas();

        int idComanda;
        int mesa;

        String opcaoPrincipal = null;
        do{
            System.out.print("\n-------------------- SISTEMA DE PEDIIDOS - RESTAURANTE O GARÇOM --------------------\n" +
                    "\na) Menu comandas" +
                    "\nb) Relatório mensal" +
                    "\nc) Vizualizar cardápio" +
                    "\ns) Sair" +
                    "\n\nOpção escolhida: ");
                    opcaoPrincipal = sc.nextLine();
            switch(opcaoPrincipal){
                case "a":
                    if(cbo.verificarStatus()){
                        System.out.print("Não há nenhuma comanda criada até agora. Digite 'C' para criar uma nova comanda: ");
                        String resposta = sc.nextLine();
                        if(resposta.equals("C")){
                            Comandas comanda = new Comandas();
                            System.out.print("\nDigite o número da mesa associdada: ");
                            mesa = sc.nextInt(); sc.nextLine();
                            System.out.print("Digite o nome da pessoa que fez o pedido: ");
                            String nome_pessoa = sc.nextLine();
                            System.out.print("Digite o ID do garçom responsável pela mesa: ");
                            int idGarcom = sc.nextInt(); sc.nextLine();
                            comanda.setNomePessoa(nome_pessoa);
                            comanda.setIdGarcom(idGarcom);
                            comanda.setMesa(mesa);
                            cbo.addComanda(comanda);
                            listaComandasAbertas.add(comanda);
                            break;
                        }else{
                            System.out.println("Parece que você digitou errado! Tente novamente.");
                            break;
                        }
                    }else{
                        listaComandasAbertas = cbo.listarAbertas();
                        System.out.println("\nHá " + listaComandasAbertas.size() + " comandas registradas no sistema. São elas:\n");
                        for (Comandas comanda : listaComandasAbertas){
                            System.out.println(comanda + "\n");
                        }
                        System.out.print("A) Vizualizar e editar comanda existente" +
                                "\nB) Excluir comanda existente" +
                                "\nC) Criar uma nova comanda" +
                                "\nD) Somente vizualizar detalhes de uma comanda existente" +
                                "\nE) Fechar comanda existente" +
                                "\nF) Voltar para o menu anterior" +
                                "\n\nOpção escolhida: ");
                        String opcaoComanda = sc.nextLine();
                        switch(opcaoComanda){
                            case "A":
                                System.out.println("Digite o ID da comanda deseja vizualizar para editar seus pedidos: ");
                                idComanda = sc.nextInt(); sc.nextLine();
                                System.out.println("\n-------------------- PEDIDOS DA COMANDA --------------------\n");
                                cbo.listarPedidos(idComanda);
                                System.out.print("\nEscolha uma ação:\n" +
                                        "\n1 - Adicionar pedido" +
                                        "\n2 - Editar pedido" +
                                        "\n3 - Remover pedido" +
                                        "\n4 - Voltar" +
                                        "\n\nOpção escolhida: ");
                                int opcaoPedido = sc.nextInt(); sc.nextLine();
                                switch(opcaoPedido){
                                    case 1:
                                        System.out.print("\nDigite o ID do produto: ");
                                        int idProdutoAdd = sc.nextInt(); sc.nextLine();
                                        System.out.print("Digite a quantidade: ");
                                        int qtdAdd = sc.nextInt(); sc.nextLine();
                                        cbo.addPedido(idComanda, idProdutoAdd, qtdAdd);
                                        break;
                                    case 2:
                                        System.out.print("Digite o ID do pedido que deseja editar: ");
                                        int idPedidoEdit = sc.nextInt(); sc.nextLine();
                                        System.out.print("Digite a nova quantidade: ");
                                        int qtdEdit = sc.nextInt(); sc.nextLine();
                                        cbo.editarPedido(idComanda, idPedidoEdit, qtdEdit);
                                        break;
                                    case 3:
                                        System.out.print("Digite o ID do pedido que deseja remover: ");
                                        int idPedidoDel = sc.nextInt(); sc.nextLine();
                                        cbo.deletarPedido(idComanda, idPedidoDel);
                                        break;
                                    case 4:
                                        break;
                                    default:
                                        System.out.println("Opção inválida!");
                                }
                                break;
                            case "B":
                                System.out.println("Digite o ID da comanda que deseja deletar: ");
                                idComanda = sc.nextInt();
                                cbo.excluirComanda(idComanda);
                                break;
                            case "C":
                                Comandas comanda = new Comandas();
                                System.out.println("Digite o número da mesa associdada: ");
                                mesa = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Digite o nome da pessoa que fez o pedido: ");
                                String nome_pessoa = sc.nextLine();
                                System.out.println("Digite o ID do garçom responsável pela mesa: ");
                                int idGarcom = sc.nextInt(); sc.nextLine();
                                comanda.setNomePessoa(nome_pessoa);
                                comanda.setIdGarcom(idGarcom);
                                comanda.setMesa(mesa);
                                cbo.addComanda(comanda);
                                listaComandasAbertas.add(comanda);
                                break;
                            case "D":
                                System.out.println("Digite o ID da comanda que deseja vizualizar detalhes: ");
                                idComanda = sc.nextInt(); sc.nextLine();
                                System.out.println("\n-------------------- PEDIDOS DA COMANDA --------------------\n");
                                cbo.listarPedidos(idComanda);
                                break;
                            case "E":
                                System.out.println("Digite o ID da comanda que deseja fechar: ");
                                int idComandaFechar = sc.nextInt(); sc.nextLine();
                                cbo.fecharComanda(idComandaFechar);
                                break;
                            case "F":
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                case "b":
                    List<Comandas> todasComandas = cbo.listarTodas();

                    // Filtra apenas as fechadas (status == false)
                    List<Comandas> comandasFechadas = new ArrayList<>();
                    if(todasComandas != null) {
                        for(Comandas c : todasComandas){
                            if(!c.isStatus()){
                                comandasFechadas.add(c);
                            }
                        }
                    }

                    if(comandasFechadas.isEmpty()){
                        System.out.println("Não há comandas fechadas para gerar relatório.");
                    } else {
                        Relatorio relatorio = rbo.gerarRelatorio(comandasFechadas);
                        System.out.println("\n" + relatorio);
                    }
                    break;

                case "c":
                    CardapioBO carBO = new CardapioBO();
                    List<Produto> cardapio = carBO.vizualizarCardapio();
                    for(Produto p : cardapio){
                        System.out.print("\n" + p);
                    }
                    break;
                default:
                    break;
            }
        }while(!opcaoPrincipal.equals("s"));
    }
}
