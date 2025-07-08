import java.util.ArrayList;

public class ControleTeleSena {
    public Pessoa[] pessoas = new Pessoa[20];
    public int quantTeleVendidaTotal = 0;
    private static final int ESTOQUE_MAXIMO = 300;

    // Método de venda com controle de estoque
    public void realizarVendas() {
        System.out.println("--- REALIZANDO A VENDA DAS TELE SENAS ---");
        for (int i = 0; i < pessoas.length; i++) {
            pessoas[i] = new Pessoa("Pessoa " + (i + 1));
            int quantidadeDesejada = pessoas[i].definirQuantidadeDeCompra();

            if (quantTeleVendidaTotal + quantidadeDesejada <= ESTOQUE_MAXIMO) {
                // Se há estoque suficiente, a pessoa compra o que quer
                pessoas[i].setQuantTele(quantidadeDesejada);
            } else {
                // Se não, a pessoa compra apenas o que sobrou no estoque
                int quantidadeDisponivel = ESTOQUE_MAXIMO - quantTeleVendidaTotal;
                pessoas[i].setQuantTele(quantidadeDisponivel);
                System.out.println("AVISO: " + pessoas[i].getNome() + " queria " + quantidadeDesejada
                        + ", mas só pôde comprar " + quantidadeDisponivel + " (limite de estoque atingido).");
            }

            pessoas[i].criarBilhetes(); // Cria os bilhetes para a pessoa
            quantTeleVendidaTotal += pessoas[i].getQuantTele(); // Atualiza o total de vendas

            if (quantTeleVendidaTotal >= ESTOQUE_MAXIMO) {
                System.out.println("ESTOQUE ESGOTADO! Vendas encerradas.");
                break; // Encerra o loop de vendas
            }
        }
        System.out.println("----------------------------------------\n");
    }

    // Método que realiza o sorteio até encontrar um ganhador
    public void realizarSorteioAteTerGanhador() {
        ArrayList<Pessoa> ganhadores = new ArrayList<>();
        final int PONTUACAO_ALVO = 25;

        System.out.println("\n--- VERIFICANDO GANHADORES DE 'MAIS PONTOS' (ALVO: " + PONTUACAO_ALVO + " ACERTOS) ---\n");

        while (ganhadores.isEmpty()) {
            for (Pessoa pessoa : this.pessoas) {
                if (pessoa == null || pessoa.getTelesenaPessoa() == null)
                    continue; // Pula posições não preenchidas se o estoque acabar antes

                for (TeleSena bilhete : pessoa.getTelesenaPessoa()) {
                    int acertos1 = contarAcertos(bilhete.getTele1(), TeleSena.getNumerosPremiadosTele1());
                    int acertos2 = contarAcertos(bilhete.getTele2(), TeleSena.getNumerosPremiadosTele2());

                    if ((acertos1 == PONTUACAO_ALVO || acertos2 == PONTUACAO_ALVO) && !ganhadores.contains(pessoa)) {
                        ganhadores.add(pessoa);
                    }
                }
            }

            if (!ganhadores.isEmpty()) {
                // --- LÓGICA FINANCEIRA ---
                double valorTotalVendas = this.quantTeleVendidaTotal * 10.0;
                double premioTotal = valorTotalVendas * 0.80; // 80% do total
                double premioIndividual = premioTotal / ganhadores.size(); // Divide igualmente

                for (Pessoa ganhador : ganhadores) {
                    ganhador.setPremioRecebido(premioIndividual);
                }

                // --- RELATÓRIO FINAL ---
                System.out.println("===========================================");
                System.out.println("          RESULTADO FINAL DO SORTEIO       ");
                System.out.println("===========================================");
                System.out.println("Números Sorteados (Conjunto 1): " + TeleSena.getNumerosPremiadosTele1());
                System.out.println("Números Sorteados (Conjunto 2): " + TeleSena.getNumerosPremiadosTele2());
                System.out.println("-------------------------------------------");
                System.out.println("Valor Total das Vendas: R$" + String.format("%.2f", valorTotalVendas));
                System.out.println("Quantidade de Tele Senas Vendidas: " + this.quantTeleVendidaTotal);
                System.out.println("-------------------------------------------");
                System.out.println("Quantidade de Ganhadores: " + ganhadores.size());
                System.out.println("\n--- NOME DOS GANHADORES ---");
                for (Pessoa ganhador : ganhadores) {
                    System.out.println("- " + ganhador.getNome());
                }
                System.out
                        .println("\nValor do Prêmio para Cada Ganhador: R$" + String.format("%.2f", premioIndividual));
                System.out.println("===========================================");
                break;
            } else {
                System.out.println("Ninguém fez " + PONTUACAO_ALVO + " pontos. Sorteando mais números...");
                TeleSena.sortearProximoNumeroPremiado();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    // Método auxiliar para contar acertos de um bilhete em relação aos números premiados
    private int contarAcertos(int[] numerosBilhete, ArrayList<Integer> numerosPremiados) {
        int acertos = 0;
        for (int numBilhete : numerosBilhete) {
            if (numerosPremiados.contains(numBilhete)) {
                acertos++;
            }
        }
        return acertos;
    }
}