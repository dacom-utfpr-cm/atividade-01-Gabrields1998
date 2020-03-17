package exercicio5;

import java.util.Scanner;

/**
 * NumberThread
 * Autor: Gabriel David Sacca
 * Descricao: Recebe um numero inteiro por argumento, espera a thread pedir
 * essa quantidade de numeros para o usuario e depois que a thread finalizar
 * soma todos os numeros digitados.
 */

class WaitNumberThread extends Thread {
    int QtdeNumber;
    int[] arrayNumber;

    WaitNumberThread(int number) {
        this.QtdeNumber = number;
        this.arrayNumber = new int[number];
    }

    public int[] getArrayNumber() {
        return arrayNumber;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < QtdeNumber; i++){
            System.out.println("digite um numero para ser somado");
            int flag = scanner.nextInt();
            this.arrayNumber[i] = flag;
        }
        scanner.close();
    }
     
}

public class NumberThread {

    public static void main(final String[] args) {
        if(args.length < 1) {
            System.out.println("java className <qtdNumeros>");
        } else {
            int number = Integer.parseInt(args[0]);
            WaitNumberThread t1 = new WaitNumberThread(number);
            t1.start();
            try {
                t1.join();
            } catch (Exception e) {
                System.out.println("Interrupted!!!");
            }

            int[] array = t1.getArrayNumber();
            int result = 0;
            for(int i = 0; i < number; i++){
                result += array[i];
            }
            System.out.println("this is the result of sum: " + result);
        } 
    }
}