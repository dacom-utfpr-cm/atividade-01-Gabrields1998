package exercicio1;

/**
 * SleepThread 
 * Autor: Gabriel David Sacca Descricao: Classe que cria tres
 * threads e esperam sua execucao
 */
public class SleepThread {

    public static void main(final String[] args) {
        for(int i = 0; i < 3; i++){
            new Thread(() -> {
                System.out.println("Hello i am your Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Bye Bye after 3 seconds" + Thread.currentThread().getName());
            }).start();
        }
    }
}