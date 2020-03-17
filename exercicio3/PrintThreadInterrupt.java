package exercicio3;

import java.io.*;

/**
 * PrintThreadInterrupt
 * Autor: Gabriel David Sacca
 * Descricao: Classe que cria uma Thread,
 * le um arquivo e imprime frases deste arquivo a cada 10 segundos
 * porem antes de terminar sua execucao, a Thread e interrompida.
 */

class ReaderThread extends Thread {
    BufferedReader buffRead;

    ReaderThread(String path) {
        try {
            this.buffRead = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String linha = "";
        while (true) {
            try {
                if (linha != null) {
                    System.out.println(linha);
                    Thread.sleep(10000);
                } else
                    break;
                try {
                    linha = this.buffRead.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                try {
                    this.buffRead.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("This Thread was interrupt!!!");
                break;
            }
        }
        try {
            this.buffRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

public class PrintThreadInterrupt {

    public static void main(final String[] args) {
        if (args.length < 1) {
            System.out.println("passe o nome do arquivo!");
        } else {
            final ReaderThread t1 = new ReaderThread(args[0]);
            t1.start();
            try {
                Thread.sleep(30000);
                t1.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}