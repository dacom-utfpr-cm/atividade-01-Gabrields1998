package exercicio2;

import java.io.*;

/**
 * PrintThread
 * Autor: Gabriel David Sacca
 * Descricao: Classe que cria uma Thread,
 * le um arquivo e imprime frases deste arquivo a cada 10 segundos
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
            if (linha != null) {
                System.out.println(linha);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else
                break;
            try {
                linha = this.buffRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.buffRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
}

public class PrintThread {

    public static void main(final String[] args) {
        if(args.length < 1) {
            System.out.println("passe o nome do arquivo!");
        } else {
            final ReaderThread t1 = new ReaderThread(args[0]);
            t1.start();
        }

    }
}