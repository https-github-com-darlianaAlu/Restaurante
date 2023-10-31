class Produto{
    private int codigo;
    private String descricao;
    private double preco;
    private String tipo;

    public Produto(int codigo, String descricao, double preco, String tipo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", tipo=" + tipo + "]";
    }
        
}