import java.util.ArrayList;
import java.util.List;

public class LCS {

    /**
     * Determina a Maior Subsequência Comum (LCS) de ações entre duas corretoras
     * utilizando Busca em Profundidade (DFS) / Backtracking sobre a árvore binária
     * implícita de geração de subsequências.
     *
     * Estrutura da árvore implícita para cada elemento A[i]:
     * [A,B]
     *                   []
     *                /      \
     *           pega A      não pega A
     *             [A]           []
     *            /   \         /   \
     *       pega B  não B   pega B  não B
     *        [AB]    [A]     [B]      []
     *
     */
    public static List<String> obterLCS(String[] a, String[] b) {
        List<String> melhorSolucao = new ArrayList<>();
        List<String> caminhoAtual = new ArrayList<>();

        // Inicia a exploração em profundidade na raiz da árvore (índice 0)
        dfs(0, a, b, caminhoAtual, melhorSolucao);

        return melhorSolucao;
    }

    private static void dfs(int indice, String[] a, String[] b, List<String> caminhoAtual, List<String> melhorSolucao) {
        // folha da arvore atingida
        if (indice == a.length) {
            // Verifica se o caminho atual é maior que a melhor solução encontrada e se é válido em B
            if (caminhoAtual.size() > melhorSolucao.size() && ehSubsequencia(caminhoAtual, b)) {
                melhorSolucao.clear();
                melhorSolucao.addAll(caminhoAtual);
            }
            return;
        }

        // Poda por maior tamanho possivel do caminho atual + elementos restantes em A
        if (caminhoAtual.size() + (a.length - indice) <= melhorSolucao.size()) {
            return;
        }

        // ramo esquerdo: Pega A[indice]
        caminhoAtual.add(a[indice]);
        dfs(indice + 1, a, b, caminhoAtual, melhorSolucao);
        
        // backtracking para acessar o ramo alternativo
        caminhoAtual.remove(caminhoAtual.size() - 1);

        // ramo direito: Não pega A[indice]
        dfs(indice + 1, a, b, caminhoAtual, melhorSolucao);
    }

    /**
     * Valida se a sequência candidata gerada a partir da Corretora A é uma subsequência
     * válida na lista de recomendações da Corretora B, respeitando a ordem relativa.
     */
    private static boolean ehSubsequencia(List<String> sub, String[] b) {
        int ponteiroB = 0;
        for (String ticker : sub) {
            boolean encontrou = false;
            while (ponteiroB < b.length) {
                if (b[ponteiroB].equals(ticker)) {
                    encontrou = true;
                    ponteiroB++;
                    break;
                }
                ponteiroB++;
            }
            if (!encontrou) {
                return false;
            }
        }
        return true;
    }
}
