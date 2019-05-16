public class GA_saida {

    //variáveis

    //outras variaveis
    GA_geracoes geracao = new GA_geracoes();

    //PRINTANDO INDIVIDUOS DA GERACAO
    public void printPopulacao() {
        this.geracao.populacao.forEach(p -> {
            for(int i=0; i< this.geracao.parametrosDoProblema.tamanhoCromossomo;i++) {
                System.out.print("|" + p[i] + "|");
            }
            System.out.print("\n");
        });
    }

    //PRINTANDO INDIVIDUOS DA GERACAO TEMP
    public void printPopulacaoNova() {
        this.geracao.novaPopulacao.forEach(p -> {
            for(int i=0; i< this.geracao.parametrosDoProblema.tamanhoCromossomo;i++) {
                System.out.print("|" + p[i] + "|");
            }
            System.out.print("\n");
        });
    }

    //PRINTANDO O SCORE DE CADA INDIVIDUO DA POPULACAO
    public void PrintScore() {
        System.out.println("Print do score");
        this.geracao.populacao.forEach( p ->{
            System.out.println(this.geracao.Individuo.CalculaAptidao(p));
        });
    }

    //PRINTANDO RESPOSTA
    public void resposta() {
        this.geracao.OrdenarFitness();
        System.out.println("________________________________");
        System.out.println("Melhor Individuo:");
        for(int i=0; i< this.geracao.parametrosDoProblema.tamanhoCromossomo;i++) {
            System.out.print("|" + this.geracao.populacao.get(0)[i] + "|");
        }
        System.out.println("\nScore:" + this.geracao.Individuo.CalculaAptidao(this.geracao.populacao.get(0)));
        System.out.println("________________________________");
    }

    public void start() {
        this.geracao.PrimeiraGeracao();
        System.out.println("Geracao 0");
        this.printPopulacao();
        for(int i=1;i<this.geracao.numGeracoes;i++) {
            System.out.println("Geracao "+i);
            this.geracao.novaGeracao();
            this.geracao.Mutacao();
            this.geracao.SelecaoNatural();
            this.printPopulacao();
        }
        this.resposta();
    }

}
