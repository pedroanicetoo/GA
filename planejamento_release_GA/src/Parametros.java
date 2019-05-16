public class Parametros {

    //parametros estaticos
    public static int numeroDeRelease = 3;
    public static int tamanhoCromossomo = 10;
    public static int orcamentoRelease = 125;


    //Prioridades de cada cliente (cliente 1, 2 e 3)
    public	int[] W = {3,4,2};
    //risco de cada de funcionalidade
    public  int[] risco = {3, 6, 2, 6, 4, 8, 9, 7, 6, 6};
    //custos de cada funcionalidade
    public  int[] custos = {60,40,40,30,20,20,25,70,50,20};
    //matriz com a importancia de cada funcionalidade dada por cada cliente
    public int[][] V = {
            {10, 10, 5}, //req 1
            {8, 10, 6}, //req 2
            {6, 4, 8}, //req 3
            {5, 9, 1}, //req 4
            {7, 7, 5}, //req 5
            {8, 6, 2}, //req 6
            {6, 6, 4}, //req 7
            {9, 8, 3}, //req 8
            {6, 7, 5}, //req 9
            {10, 10, 7} //req 10
    };
}
