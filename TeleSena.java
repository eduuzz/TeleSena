import java.util.ArrayList;
import java.util.Collections;

public class TeleSena {
    private double valorVenda = 10;
    private int[] tele1 = new int[25];
    private int[] tele2 = new int[25];

    // Uso do ArrayList para crescimento dinâmico dos números premiados
    private static ArrayList<Integer> numerosPremiadosTele1 = new ArrayList<>();
    private static ArrayList<Integer> numerosPremiadosTele2 = new ArrayList<>();

    // Um "baralho" com os números que ainda não foram sorteados para o conjunto 1
    private static ArrayList<Integer> numerosDisponiveis1;
    // Um "baralho" para o conjunto 2
    private static ArrayList<Integer> numerosDisponiveis2;

    // Construtor padrão que define o valor de venda da Tele Sena
    public TeleSena() {
        this.valorVenda = 10;
    }

    // GETTERS
    public double getValorVenda() {
        return valorVenda;
    }

    public int[] getTele1() {
        return tele1;
    }

    public int[] getTele2() {
        return tele2;
    }

    public static ArrayList<Integer> getNumerosPremiadosTele1() {
        return numerosPremiadosTele1;
    }

    public static ArrayList<Integer> getNumerosPremiadosTele2() {
        return numerosPremiadosTele2;
    }

    // Prepara e realiza o sorteio inicial de 25 números
    public static void gerarTeleSenaPremiadaInicial() {
        // Prepara o baralho de 1 a 60
        numerosDisponiveis1 = new ArrayList<>();
        numerosDisponiveis2 = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            numerosDisponiveis1.add(i);
            numerosDisponiveis2.add(i);
        }

        // Embaralha para garantir aleatoriedade
        Collections.shuffle(numerosDisponiveis1);
        Collections.shuffle(numerosDisponiveis2);

        // Sorteia os 25 números iniciais de cada conjunto
        for (int i = 0; i < 25; i++) {
            numerosPremiadosTele1.add(numerosDisponiveis1.remove(0)); // Remove do baralho e adiciona aos premiados
            numerosPremiadosTele2.add(numerosDisponiveis2.remove(0));
        }

        System.out.println("Números Premiados Iniciais Sorteados:");
        System.out.println("Conjunto 1: " + numerosPremiadosTele1);
        System.out.println("Conjunto 2: " + numerosPremiadosTele2);
        System.out.println("\n");
    }

    // Sorteia o próximo número premiado se ninguém ganhou ainda
    public static void sortearProximoNumeroPremiado() {
        if (!numerosDisponiveis1.isEmpty()) {
            int novoNumero1 = numerosDisponiveis1.remove(0);
            numerosPremiadosTele1.add(novoNumero1);
            System.out.println(">> Novo número sorteado para Conjunto 1: " + novoNumero1);
        }
        if (!numerosDisponiveis2.isEmpty()) {
            int novoNumero2 = numerosDisponiveis2.remove(0);
            numerosPremiadosTele2.add(novoNumero2);
            System.out.println(">> Novo número sorteado para Conjunto 2: " + novoNumero2);
        }
    }

    // Método para sortear os números da Tele Sena
    public void sortearNumeros() {
        for (int i = 0; i < tele1.length; i++) {
            int numeroSorteado;
            do {
                numeroSorteado = (int) (Math.random() * 60) + 1;
            } while (numeroJaExiste(tele1, numeroSorteado, i));
            tele1[i] = numeroSorteado;
        }

        for (int i = 0; i < tele2.length; i++) {
            int numeroSorteado;
            do {
                numeroSorteado = (int) (Math.random() * 60) + 1;
            } while (numeroJaExiste(tele2, numeroSorteado, i));
            tele2[i] = numeroSorteado;
        }
    }

    // Método auxiliar para verificar se o número já existe no array
    private boolean numeroJaExiste(int[] array, int numero, int posicaoAtual) {
        for (int i = 0; i < posicaoAtual; i++) {
            if (array[i] == numero) {
                return true;
            }
        }
        return false;
    }   
}