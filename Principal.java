public class Principal {
    public static void main(String[] args) {

        System.out.println("INICIANDO SISTEMA DA TELE SENA\n");

        // 1. Cria o controle e realiza as vendas com controle de estoque
        ControleTeleSena controle = new ControleTeleSena();
        controle.realizarVendas();

        // 2. Sorteia os 25 números premiados INICIAIS
        TeleSena.gerarTeleSenaPremiadaInicial();

        // 3. Inicia o processo de verificação contínua até encontrar um vencedor
        controle.realizarSorteioAteTerGanhador();

        // 4. Exibe o lucro do Silvio Santos
        double lucroSilvio = (controle.quantTeleVendidaTotal * 10.0) * 0.20; // Os 20% restantes
        System.out.println("Lucro Obtido pelo Silvio Santos: R$" + String.format("%.2f", lucroSilvio));
        System.out.println("===========================================");
        System.out.println("\nSORTEIO FINALIZADO.");
    }
}