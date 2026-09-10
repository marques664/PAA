import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LCS {

    /**
     * Determina a Maior Subsequência Comum (LCS) de ações entre dois relatórios de corretoras.
     *
     * @param a Sequência de ativos da Corretora A (tamanho M)
     * @param b Sequência de ativos da Corretora B (tamanho N)
     * @return Lista contendo os ativos da subsequência comum na ordem exata de preferência.
     */

    public static List<String> obterLCS(String[] a, String[] b) {
        int m = a.length;
        int n = b.length;

        // i primeiros ativos da Corretora A e os j primeiros da Corretora B.
        // m+1 e n+1 para representar uma sequência vazia 
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                
                if (a[i - 1].equals(b[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // adiciona 1 na diagonal da matriz
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // decidi entre descartar o ativo da corretora A ou B
                }
            }
        }
        

        // backtracking
        List<String> lcs = new ArrayList<>();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            // se iguais, sobe a matriz na diagonal e lcs.add
            if (a[i - 1].equals(b[j - 1])) {
                lcs.add(a[i - 1]);
                i--;
                j--;
            }
            
            // decidi qual caminho tem a melhor solução, pra cima ou para a esquerda
            else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            }
            else {
                j--;
            }
        }

        Collections.reverse(lcs);
        return lcs;
    }
}
