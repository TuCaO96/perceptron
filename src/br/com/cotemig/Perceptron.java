package br.com.cotemig;

import java.util.Random;

/**
 * Criado por Arthur Ribeiro <71500286@aluno.faculdadecotemig.br></71500286> em 31/05/2017.
 */
public abstract class Perceptron {
    protected double[] pesos = new double[3];

    protected int[][] matriz = new int[10][3];

    protected int[][] matrizAvaliacao = new int[4][3];

    Perceptron(){

        // Peso da primeira entrada
        pesos[0] = -0.03;
        // Peso da segunda entrada
        pesos[1] = 0.05;
        // Peso do BIAS
        pesos[2]= 0.06;

        //Preenche matriz de treinamento
        preencheMatrizTreinamento();
        //Realiza treinamento
        treina();

        //Preenche matriz de avaliação com valores corretos
        preencheMatrizAvaliacao();
        //Avalia após treinamento
        avalia();
    }

    /**
     * @param index Índice da entrada
     * @param saida Saida esperada - saida obtida
     *
     * Peso ajustado = peso + (taxa de aprendizado * entrada correspondente * erro))
     * Erro -> (saida esperada - saida obtida)
     */

    protected void ajustarPeso(int index, int saida){
        //entrada 1
        pesos[0] = pesos[0] + (0.01 * matriz[index][0] * (matriz[index][2] - saida));
        //entrada 2
        pesos[1] = pesos[1] + (0.01 * matriz[index][1] * (matriz[index][2] - saida));
        //valor esperado
        pesos[2] = pesos[2] + (0.01 * (-1) * (matriz[index][2] - saida));
    }

    /**
     * Realiza o somatório e a função de ativação
     * @param x1 Entrada 1
     * @param x2 Entrada 2
     */
    protected int executa(int x1, int x2) {
        //realiza somatório
        double somatorio = (x1 * pesos[0]) + (x2 * pesos[1]) + (pesos[2]);

        //função de ativação
        if(somatorio > 0){
            return 1;
        }

        return 0;
    }

    protected void preencheMatrizAvaliacao(){
        // 1 e 1 = 1
        matrizAvaliacao[0][0] = 1;
        matrizAvaliacao[0][1] = 1;
        matrizAvaliacao[0][2] = 1;
        // 1 e 0 = 1
        matrizAvaliacao[1][0] = 1;
        matrizAvaliacao[1][1] = 0;
        matrizAvaliacao[1][2] = 1;
        // 0 e 1 = 1
        matrizAvaliacao[2][0] = 0;
        matrizAvaliacao[2][1] = 1;
        matrizAvaliacao[2][2] = 1;
        // 0 e 0 = 0
        matrizAvaliacao[3][0] = 0;
        matrizAvaliacao[3][1] = 0;
        matrizAvaliacao[3][2] = 0;
    }

    protected void preencheMatrizTreinamento(){

        Random r = new Random();

        int valorAleatorio;

        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[i].length; i++){
                valorAleatorio = r.nextInt();
                if(valorAleatorio > 0){
                    matriz[i][j] = 1;
                } else{
                    matriz[i][j] = 0;
                }
            }
        }
    }

    /**
     * Avalia após ser treinado
     */
    protected boolean avalia(){
        boolean sucesso = true;

        // Recebe o y (saída)
        int saida;

        // Faz todas as entradas
        for (int i = 0; i < matrizAvaliacao.length; i++) {
            // A saída recebe o resultado da rede que no caso é 1 ou 0
            saida = executa(matrizAvaliacao[i][0], matrizAvaliacao[i][1]);

            if (saida != matrizAvaliacao[i][2]) {
                // Caso a saída seja diferente do valor esperado
                sucesso = false;
            }
        }

        return sucesso;
    }

    /**
     * Realiza o treinamento
     */
    protected abstract void treina();
}
