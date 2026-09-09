import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // validação de entrada 
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int m = sc.nextInt();
        int n = sc.nextInt();

        // input corretora A
        String[] a = new String[m];
        for (int i = 0; i < m; i++) {
            a[i] = sc.next();
        }

        // input corretora B
        String[] b = new String[n];
        for (int j = 0; j < n; j++) {
            b[j] = sc.next();
        }

        List<String> consenso = LCS.obterLCS(a, b);

        // Saída - Linha 1: Quantidade K de ativos alinhados no consenso
        System.out.println(consenso.size());

        // Saída - Linha 2: Ativos separados por espaço (ou linha em branco se K == 0)
        if (!consenso.isEmpty()) {
            System.out.println(String.join(" ", consenso));
        } else {
            System.out.println();
        }

        sc.close();
    }
}
