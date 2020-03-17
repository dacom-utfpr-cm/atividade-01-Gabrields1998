package exercicio4;

import java.util.Scanner;

/**
 * InterruptTest
 * Autor: Gabriel David Sacca
 * Descricao: um Thread cria 10 instancias de Thread e a execucao principal
 * da a possibilidade de matar uma das Threads, alem disso uma terceira Thread
 * monitora o estado das 10 Threads criadas. Assim que o usuario decidir matar 
 * uma Thread o observer avisa isto em forma de Sysout.
 */

class ThreadTest extends Thread {

    @Override
    public void run() {
        System.out.println("This is the Thread: " + Thread.currentThread().getName());
        while (true) {
            try {
                Thread.sleep(0000);
            } catch (final InterruptedException e) {
                try {
                    Thread.sleep(2000);
                } catch (final InterruptedException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }

}

class ThreadObserver extends Thread {
    ThreadTest[] Threads = new ThreadTest[10];

    ThreadObserver(final ThreadTest[] Thread) {
        this.Threads = Thread;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                if (!this.Threads[i].isAlive()) {
                    System.out.println("This Thread was interrupted: " + Threads[i].getName());
                }
            }
        }
    }
}

public class InterruptTest {

    public static void main(final String[] args) {
        final ThreadTest[] t1 = new ThreadTest[10];
        for (int i = 0; i < 10; i++) {
            t1[i] = new ThreadTest();
            t1[i].start();
        }

        new ThreadObserver(t1).start();

        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Deseja interromper uma Thread de 0 a 9?");
            final int i = scanner.nextInt();
            t1[i].interrupt();
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                e.printStackTrace();
                scanner.close();
                break;
            }
        }
    }
}