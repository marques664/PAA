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

        // Tabela DP de tamanho (M+1) x (N+1).
        // dp[i][j] guardará a quantidade máxima de ativos em comum
        // considerando apenas os i primeiros ativos da Corretora A e os j primeiros da Corretora B.
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Comparação da igualdade dos tickers
                if (a[i - 1].equals(b[j - 1])) {
                    // Se o ativo i-1 da Corretora A é igual ao ativo j-1 da Corretora B:
                    // Incrementamos 1 ao melhor resultado dos prefixos anteriores (dp[i-1][j-1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Se forem diferentes:
                    // O valor é o máximo entre descartar o ativo atual da Corretora A (dp[i-1][j])
                    // ou descartar o ativo atual da Corretora B (dp[i][j-1]).
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Passo 2: Reconstrução (Backtracking) para recuperar os ativos selecionados
        List<String> lcs = new ArrayList<>();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            // Se os ativos forem iguais no ponto atual, este ativo faz parte do consenso
            if (a[i - 1].equals(b[j - 1])) {
                lcs.add(a[i - 1]);
                i--;
                j--;
            }
            // Se vier do caminho de cima, significa que o ativo da Corretora A não contribuiu para a LCS atual
            else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            }
            // Caso contrário, o ativo da Corretora B não contribuiu
            else {
                j--;
            }
        }

        // Como o backtracking percorre do fim para o início, invertemos a lista para obter a ordem correta
        Collections.reverse(lcs);
        return lcs;
    }
}
