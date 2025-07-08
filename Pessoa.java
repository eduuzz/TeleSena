public class Pessoa {
    private String nome;
    private TeleSena[] telesenaPessoa;
    private int quantTele;
    private double premioRecebido;

    public Pessoa(String nome) {
        this.nome = nome;
        this.quantTele = 0;
        this.premioRecebido = 0.0; 
    }

    // Método para definir a quantidade de TeleSenas a serem compradas
    public int definirQuantidadeDeCompra() {
        this.quantTele = (int) (Math.random() * 15) + 1; // Sorteia uma quantidade entre 1 e 15
        return this.quantTele;
    }

    // Método para criar os bilhetes de TeleSena
    public void criarBilhetes() {
        this.telesenaPessoa = new TeleSena[this.quantTele];
        for (int i = 0; i < this.quantTele; i++) {
            this.telesenaPessoa[i] = new TeleSena();
            this.telesenaPessoa[i].sortearNumeros();
        }
    }

    // Getters e Setters
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public TeleSena[] getTelesenaPessoa() {return telesenaPessoa;}
    public int getQuantTele() {return quantTele;}
    public void setQuantTele(int quantTele) {this.quantTele = quantTele;}
    public double getPremioRecebido() {return premioRecebido;}
    public void setPremioRecebido(double premioRecebido) {this.premioRecebido = premioRecebido;}
}