package br.com.cotemig;

/**
 * Criado por Arthur Ribeiro <71500286@aluno.faculdadecotemig.br></71500286> em 31/05/2017.
 */
public class PerceptronAnd extends Perceptron {
    @Override
    protected void treina() {

        // Recebe o y (saída)
        int saida;

        // Treina todas as entradas
        for (int i = 0; i < matriz.length; i++) {
            // A saída recebe o resultado da rede que no caso é 1 ou 0
            saida = executa(matriz[i][0], matriz[i][1]);
            // Caso a saída seja 1 e uma das entradas seja 0, é falso
            if((matriz[i][0] == 0 || matriz[i][1] == 0) && saida == 1){
                ajustarPeso(i, saida);
            }
        }
    }
}