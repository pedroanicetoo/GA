import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GA_geracoes {
    //variaveis locais
    private int tamanhoPopulacao;
    public int numGeracoes;
    private int elitismo;
    private  double taxadeCruzamento;
    private double taxaMutacao;
    public ArrayList<int[]> populacao = new ArrayList();
    public ArrayList<int[]> novaPopulacao = new ArrayList();

    //outras variaveis
    Parametros parametrosDoProblema = new Parametros();
    Individuos Individuo = new Individuos();

    //definindo numeros padroes
    public GA_geracoes() {
        this.numGeracoes = 2000;
        this.tamanhoPopulacao = 100;
        this.elitismo = 1;
        this.taxaMutacao = 0.01;
        this.taxadeCruzamento = 0.6;
    }

//Sets

    public void setTamanhoPopulacao(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public void setNumGeracoes(int numGeracoes) {
        this.numGeracoes = numGeracoes;
    }

    public void setElitismo(int elitismo) {
        this.elitismo = elitismo;
    }

    public void setTaxaMutacao(float taxaMutacao) {
        this.taxaMutacao = taxaMutacao;
    }


    //PRIMEIRA POPULACAO
    public void PrimeiraGeracao() {
        Random random = new Random();;
        int[] individuo;
        for(int i=0; i< this.tamanhoPopulacao; i++) {
            individuo = new int[this.parametrosDoProblema.tamanhoCromossomo];
            for(int j =0; j < this.parametrosDoProblema.tamanhoCromossomo;j++) {
                individuo[j] = random.nextInt((this.parametrosDoProblema.numeroDeRelease + 1));
            }

            individuo = this.Individuo.CorrigeIndividuo(individuo);
            this.populacao.add(individuo);
        }
    }

    //Selecionando os Pais
    public int Sorteio() {
        Random random = new Random();
        int c1 = random.nextInt(this.tamanhoPopulacao);
        int c2 = random.nextInt(this.tamanhoPopulacao);
        if(this.Individuo.CalculaAptidao(this.populacao.get(c1)) > this.Individuo.CalculaAptidao(this.populacao.get(c2)))
            return c1;
        else
            return c2;
    }

    //Criar nova Geracao
    public void novaGeracao() {
        Random random = new Random();
        int[] individuo;
        int[] individuo2;
        for(int i= this.elitismo; i<this.tamanhoPopulacao / 2 ;i++) {
            individuo = new int[this.parametrosDoProblema.tamanhoCromossomo];
            individuo2 = new int[this.parametrosDoProblema.tamanhoCromossomo];
            int pai = this.Sorteio();
            int mae = this.Sorteio();
            double probilite = random.nextDouble();

            if(probilite >= this.taxadeCruzamento) {
                this.novaPopulacao.add(this.populacao.get(pai));
                this.novaPopulacao.add(this.populacao.get(mae));
            } else {
                for(int o=0; o<this.parametrosDoProblema.tamanhoCromossomo;o++) {
                    if(i % 2 == 0) {
                        individuo[o] = this.populacao.get(pai)[o];
                        individuo2[o] = this.populacao.get(mae)[o];
                    } else {
                        individuo[o] = this.populacao.get(mae)[o];
                        individuo2[o] = this.populacao.get(pai)[o];
                    }
                }
            }
            individuo = this.Individuo.CorrigeIndividuo(individuo);
            individuo2 = this.Individuo.CorrigeIndividuo(individuo2);
            this.novaPopulacao.add(individuo);
            this.novaPopulacao.add(individuo2);
        }
    }


    //MUTACAO
    public void Mutacao() {
        Random random = new Random();
        this.novaPopulacao.forEach(p -> {
            for(int i=0; i< this.parametrosDoProblema.tamanhoCromossomo;i++) {
                double prob = random.nextDouble();
                if(prob <= this.taxaMutacao) {
                    int releaseSorteada= random.nextInt(3) + 1;
                    int releaseAntiga = p[i];
                    p[i] = releaseSorteada;
                    if(!this.Individuo.VerificaIndividuo(p)) {
                        p[i] = releaseAntiga;
                    }
                }
            }
        });
    }

    //ordenando populacao de acordo com o Fitness
    public void OrdenarFitness() {
        Collections.sort(this.populacao, new Comparator() {
            @Override
            public int compare(Object arg0, Object arg1) {
                Individuos p = new Individuos();
                int[] i1 = (int[]) arg0;
                int[] i2 = (int[]) arg1;
                return (int) (p.CalculaAptidao(i2) - p.CalculaAptidao(i1));
            }
        });
    }

    //escolhendo sobreviventes
    public void SelecaoNatural() {
        this.OrdenarFitness();
        for(int i=0; i<this.elitismo;i++) {
            this.novaPopulacao.add(this.populacao.get(i));
        }
        this.populacao = (ArrayList<int[]>) this.novaPopulacao.clone();
        this.novaPopulacao.clear();
    }
}
