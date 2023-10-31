import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurante {
    private static List<Produto> listaProdutos;
    private List<Mesa> mesasAbertas;
    private List<Mesa> mesasFechadas;

    public Restaurante() {
        listaProdutos = new ArrayList<>();
        mesasAbertas = new ArrayList<>();
        mesasFechadas = new ArrayList<>();
        // Inicialize algumas mesas aqui
        Mesa mesa1 = new Mesa(1, null);
        Mesa mesa2 = new Mesa(2, null);
        Mesa mesa3 = new Mesa(3, null);
        Mesa mesa4 = new Mesa(4, null);
        // Inicialize alguns produtos aqui
        Produto prato1 = new Produto(1, "Bobó", 25.90, "salgado");
        Produto prato2 = new Produto(2, "Sorvete", 10.0, "doce");
        Produto prato3 = new Produto(3, "Suco", 12, "não alcólico");

        listaProdutos.add(prato1);
        listaProdutos.add(prato2);
        listaProdutos.add(1, prato3);

    }

    public void adicionarProduto(Produto produto) {
        Scanner s = new Scanner(System.in);
        System.out.println("Cadastro de Produtos");
        System.out.print("Código do produto: ");
        int codigo = s.nextInt();
        s.nextLine(); // Limpar a quebra de linha após a entrada de número
        System.out.print("Descrição do produto: ");
        String descricao = s.nextLine();
        System.out.print("Preço do produto: ");
        double preco = s.nextDouble();
        s.nextLine(); // Limpar a quebra de linha após a entrada de número
        System.out.print("Tipo do produto (comida, bebida, não comestível): ");
        String tipo = s.next();
        Produto novoProduto = new Produto(0, "null", 0, "null");
        listaProdutos.add(novoProduto);
        System.out.println("Produto cadastrado com sucesso.\n");
        s.close();
    }

    public void listarProdutos() {
        // Liste os produtos (código, tipo, descrição e preço)
        System.out.println("Lista de Produtos:");
        for (Produto produto : listaProdutos) {
            System.out.println("Código: " + produto.getCodigo());
            System.out.println("Tipo: " + produto.getTipo());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println();
        }
    }

    public Mesa abrirMesa(int numero) {
        // Verifique se a mesa já está aberta e a adicione à lista de mesas abertas
        for (Mesa mesa : mesasAbertas) {
            if (mesa.getNumero() == numero) {
                System.out.println("A mesa já está aberta.");
                return mesa;
            }
        }
        Mesa novaMesa = new Mesa(numero, null);
        mesasAbertas.add(novaMesa);
        return novaMesa;
    }

    public void listarMesasAbertas() {
        System.out.println("Mesas Abertas:");
        for (Mesa mesa : mesasAbertas) {
            System.out.println("Número da Mesa: " + mesa.getNumero());
            System.out.println("Valor Parcial: " + mesa.calcularValorTotalPedido());
            System.out.println();
        }
    }

    public void fecharMesa(int numero) {
        // Localize a mesa na lista de mesas abertas, calcule o valor final, feche a
        // mesa e adicione-a à lista de mesas fechadas
        for (Mesa mesa : mesasAbertas) {
            if (mesa.getNumero() == numero) {
                double valorFinal = mesa.calcularValorTotalPedido();
                mesasAbertas.remove(mesa);
                mesasFechadas.add(mesa);
            }
        }
        System.out.println("Mesa não encontrada ou já fechada.");
    }

    public void anotarPedido(int numeroMesa) {
        // Solicite o código do produto e a quantidade, adicione os itens ao pedido da
        // mesa
        int codigoProduto, quantidade;
        Scanner sc = new Scanner(System.in);

        System.out.println("código do produto: ");
        codigoProduto = sc.nextInt();

        System.out.println("quantidade: ");
        quantidade = sc.nextInt();
        sc.close();

        for (Mesa mesa : mesasAbertas) {
            if (mesa.getNumero() == numeroMesa) {
                // Encontre o produto com base no código
                Produto produto = null;
                for (Produto p : listaProdutos) {
                    if (p.getCodigo() == codigoProduto) {
                        produto = p;
                        break;
                    }
                }

                if (produto != null) {
                    mesa.adicionarItemAoPedido(produto, quantidade);
                } else {
                    System.out.println("Produto não encontrado.");
                }
                return;
            }
        }
        System.out.println("Mesa não encontrada ou já fechada.");
    }

    public void listarPedidosMesa(int numeroMesa) {
        // Liste os itens pedidos na mesa (código do produto, nome, quantidade e preço
        // total)
        for (Mesa mesa : mesasAbertas) {
            if (mesa.getNumero() == numeroMesa) {
                mesa.listarItensPedido();
                System.out.println("Valor Parcial da Mesa: " + mesa.calcularValorTotalPedido());
                return;
            }
        }
        System.out.println("Mesa não encontrada ou já fechada.");
    }

    public void relatorioFinal() {
        // Mostre todos os valores das contas e o valor total
        if (!mesasAbertas.isEmpty()) {
            System.out.println("Aviso: Existem mesas abertas. Feche todas as mesas antes de gerar o relatório final.");
            return;
        }
        System.out.println("Relatório Final:");

        // Percorre as mesas fechadas e liste o valor total de casa mesa
        double valorTotalRestaurante = 0.0;
        for (Mesa mesa : mesasFechadas) {
            double valorMesa = mesa.calcularValorTotalPedido();
            valorTotalRestaurante += valorMesa;
            System.out.println("Mesa " + mesa.getNumero() + ": Valor Total R$" + valorMesa);
        }

        // Calcule e mostre o valor total do restaurante
        System.out.println("Valor Total do Restaurante: R$" + valorTotalRestaurante);
    }

    public static void main(String[] args) {

        Restaurante restaurante = new Restaurante();
        Scanner s = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("1-Abrir mesa: ");
            System.out.println("2-Listar as mesas abertas: ");
            System.out.println("3-Fechar mesa: ");
            System.out.println("4-Cadastrar produtos: ");
            System.out.println("5-Listar produtos: ");
            System.out.println("6-Anotar pedidos: ");
            System.out.println("7-Listar pedidos e valor parcial da mesa: ");
            System.out.println("8-Relatório final: ");
            System.out.println("9-Sair");
            opcao = s.nextInt();

            if (opcao == 1) {
                System.out.println("Digite o número da mesa: ");
                int numeroMesa = s.nextInt();
                restaurante.abrirMesa(numeroMesa);
                System.out.println("Mesa " + numeroMesa + " aberta.");
            } else if (opcao == 2) {
                restaurante.listarMesasAbertas();
            } else if (opcao == 3) {
                System.out.println("Digite o número da  mesa a ser fechada: ");
                int mesaParaFechar = s.nextInt();
                restaurante.fecharMesa(mesaParaFechar);
                System.out.println("Mesa " + mesaParaFechar + " fechada.");
            } else if (opcao == 4) {
                System.out.println("Informe o código do Produto: ");
                int codigo = s.nextInt();                
                for(Produto produto : listaProdutos){
                    if(produto.getCodigo() == codigo){
                        System.out.println("O produto já esxiste!");
                } else{
                        restaurante.adicionarProduto(produto);
                }
                } 
            } else if (opcao == 5) {
                restaurante.listarProdutos();
            } else if (opcao == 6) {
                System.out.print("Digite o número da mesa onde deseja anotar o pedido: ");
                int mesaParaPedido = s.nextInt();
                restaurante.anotarPedido(mesaParaPedido);
            } else if (opcao == 7) {
                System.out.println("Digite o número da mesa para listar pedidos: ");
                int mesaParaListarPedido = s.nextInt();
                restaurante.listarPedidosMesa(mesaParaListarPedido);
            } else if (opcao == 8) {
                restaurante.relatorioFinal();
            }
        } while (opcao != 9);
        System.out.println("Encerrar Sistema");
        s.close();
    }
}