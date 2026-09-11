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
       
        String[] a = new String[m];
        for (int i = 0; i < m; i++) {
            a[i] = sc.next();
        }

        String[] b = new String[n];
        for (int j = 0; j < n; j++) {
            b[j] = sc.next();
        }

        List<String> consenso = LCS.obterLCS(a, b);

        // quantidade de ativos em comum
        System.out.println(consenso.size());

        // ativos descritos
        if (!consenso.isEmpty()) {
            System.out.println(String.join(" ", consenso));
        } else {
            System.out.println();
        }

        sc.close();
    }
}
