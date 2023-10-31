import java.util.ArrayList;
import java.util.List;

class Mesa{
    private int numero;
    private List<Pedido> pedidos;

    
public Mesa(int numero, List<Pedido> pedidos) {
        this.numero = numero;
        this.pedidos = new ArrayList<>();
    }
    

    public int getNumero() {
    return numero;
}


public void setNumero(int numero) {
    this.numero = numero;
}


public List<Pedido> getPedidos() {
    return pedidos;
}


public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
}


    public void adicionarItemAoPedido(Produto produto, int quantidade) {
    Pedido novoItem = new Pedido(produto, quantidade);
    pedidos.add(novoItem);
}

    public void removerItemDoPedido(int indice) {
    if (indice >= 0 && indice < pedidos.size()) {
        pedidos.remove(indice);
    } else {
        System.out.println("Índice inválido. O item não foi removido.");
    }
}
public double calcularValorTotalPedido() {
    double valorTotal = 0.0;
    for (Pedido item : pedidos) {
        valorTotal += item.getQuantidade() * item.getProduto().getPreco();
    }
    return valorTotal;
}

public void listarItensPedido() {
    for (int i = 0; i < pedidos.size(); i++) {
        Pedido item = pedidos.get(i);
        Produto produto = item.getProduto();
        int quantidade = item.getQuantidade();
        double precoTotal = quantidade * produto.getPreco();
        System.out.println("Item " + (i + 1) + ": " + produto.getDescricao() + " (Quantidade: " + quantidade + ", Preço Total: " + precoTotal + ")");
    }
}

}