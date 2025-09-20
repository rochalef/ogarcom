package App;

import Model.Comandas;
import Model.Produto;
import BO.FuncionarioBO;
import BO.ComandasBO;
import BO.CardapioBO;

import java.util.*;

public class App{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ComandasBO cbo = new ComandasBO();
        FuncionarioBO fbo = new FuncionarioBO();
        List<Comandas> listaComandasAbertas = new ArrayList<>();
        listaComandasAbertas = cbo.listarAbertas();

        int idComanda;
        int mesa;

        String resp = null;

        do{
            resp = null;
            System.out.print("-------------------- SISTEMA DE PEDIIDOS - RESTAURANTE O GARÇOM --------------------\n" +
                    "\na) Menu comandas" +
                    "\nb) Relatório mensal" +
                    "\nc) Vizualizar cardápio" +
                    "\ns) Sair" +
                    "\n\nOpção escolhida: ");
                    resp = sc.nextLine();
            switch(resp){
                case "a":
                    if(cbo.verificarStatus()){
                        System.out.print("Não há nenhuma comanda criada até agora. Digite 'C' para criar uma nova comanda: ");
                        resp = sc.nextLine();
                        if(resp.equals("C")){
                            Comandas comanda = new Comandas();
                            System.out.print("\nDigite o número da mesa associdada: ");
                            mesa = sc.nextInt(); sc.nextLine();
                            System.out.print("Digite o nome da pessoa que fez o pedido: ");
                            String nome_pessoa = sc.nextLine();
                            System.out.print("Digite o nome do garçom responsável pela mesa: ");
                            int idGarcom = sc.nextInt(); sc.nextLine();
                            comanda.setNomePessoa(nome_pessoa);
                            comanda.setIdGarcom(idGarcom);
                            comanda.setMesa(mesa);
                            cbo.addComanda(comanda);
                            listaComandasAbertas.add(comanda);
                        }else{
                            System.out.println("Parece que você digitou errado! Tente novamente.");
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
                                "\nE) Voltar para o menu anterior" +
                                "\n\nOpção escolhida: ");
                        resp = sc.nextLine();
                        switch(resp){
                            case "A":
                                System.out.println("Digite o ID da comanda deseja vizualizar para editar seus pedidos: ");
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
                                idComanda = sc.nextInt();
                            case "E":
                                break;
                        }
                    }
                case "b":
                    cbo.verificarStatus();
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
        }while(!resp.equals("s"));
    }
}
