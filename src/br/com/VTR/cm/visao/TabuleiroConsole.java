package br.com.VTR.cm.visao;

import br.com.VTR.cm.excecao.ExplosaoException;
import br.com.VTR.cm.excecao.SairException;
import br.com.VTR.cm.modelo.tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    private tabuleiro Tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(tabuleiro Tabuleiro){
        this.Tabuleiro = Tabuleiro;

        execucarJogo();
    }

    private void execucarJogo() {
        try {
            boolean continuar = true;

            while (continuar){
                cicloDoJogo();
                System.out.println("Outra partida? (S/n) ");
                String resposta = entrada.nextLine();

                if("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                } else {
                    Tabuleiro.reiniciar();
                }
            }
        } catch (SairException e){
            System.out.println("Thau!");
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try{
            while (!Tabuleiro.objetivoAlcancado()){
                System.out.println(Tabuleiro);

                String digitado = capturarValorDigitado("Digite (x, y):");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();

                digitado = capturarValorDigitado("1-Abrir ou 2-(Des)Marcar");

                if ("1".equals(digitado)){
                    Tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(digitado)) {
                    Tabuleiro.marcar(xy.next(), xy.next());
                }
            }
            System.out.println("Você ganhou!");
        } catch (ExplosaoException e){
            System.out.println(Tabuleiro);
            System.out.println("Você perdeu!");
        }
    }

    private String capturarValorDigitado(String texto){
        System.out.println(texto);
        String digitado = entrada.nextLine();

        if("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }

        return digitado;
    }
}
