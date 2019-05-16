public class Individuos {

    Parametros Problema = new Parametros();

    public int[] CorrigeIndividuo(int[] individuo) {
        int[] orcamentos = new int[this.Problema.numeroDeRelease];

        for(int i=0; i< this.Problema.tamanhoCromossomo ; i++) {
            if(individuo[i] != 0) {
                if( (orcamentos[individuo[i]-1] + this.Problema.custos[i]) <= this.Problema.orcamentoRelease ) {
                    orcamentos[individuo[i]-1] += this.Problema.custos[i];
                }else {
                    individuo[i] = 0;
                }
            }
        }

        return individuo;
    }

    //checando se o individuo é valido
    public boolean VerificaIndividuo(int[] individuo) {
        int[] orcamentos = new int[this.Problema.numeroDeRelease];
        for(int i=0; i< this.Problema.tamanhoCromossomo ; i++) {
            if(individuo[i] != 0) {
                if( (orcamentos[individuo[i]-1] + this.Problema.custos[i]) <= this.Problema.orcamentoRelease ) {
                    orcamentos[individuo[i]-1] += this.Problema.custos[i];
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    //calculando o score
    public double CalculaAptidao(int[] S) {

        int[] importancia = new int[10];
        double score = 0;
        for(int i=0;i<this.Problema.tamanhoCromossomo;i++) {
            importancia[i] = 0;
            for(int o=0;o<this.Problema.numeroDeRelease;o++) {
                importancia[i] += this.Problema.W[o] * this.Problema.V[i][o];
            }

            if(S[i] != 0) {
                score += importancia[i] * (this.Problema.numeroDeRelease - S[i]+1)
                        - (this.Problema.risco[i] * S[i]);
            }
        }
        return score;
    }



}
