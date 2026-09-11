import java.util.ArrayList;
import java.util.List;

public class LCS {

    /**
     * Determina a Maior Subsequência Comum (LCS) de ações entre duas corretoras
     * utilizando Busca em Profundidade (DFS) / Backtracking sobre a árvore binária
     * implícita de geração de subsequências.
     *
     * Estrutura da árvore implícita para cada elemento A[i]:
     *                   []
     *                /      \
     *           pega A      não pega A
     *             [A]           []
     *            /   \         /   \
     *       pega B  não B   pega B  não B
     *        [AB]    [A]     [B]      []
     *
     * @param a Sequência de ativos da Corretora A (tamanho M)
     * @param b Sequência de ativos da Corretora B (tamanho N)
     * @return Lista contendo os ativos da subsequência comum na ordem exata de preferência.
     */
    public static List<String> obterLCS(String[] a, String[] b) {
        List<String> melhorSolucao = new ArrayList<>();
        List<String> caminhoAtual = new ArrayList<>();

        // Inicia a exploração em profundidade na raiz da árvore (índice 0)
        dfs(0, a, b, caminhoAtual, melhorSolucao);

        return melhorSolucao;
    }

    /**
     * Percorre a árvore binária implícita de decisões em profundidade.
     *
     * @param indice Posição atual sendo avaliada no vetor 'a'
     * @param a Vetor com ativos da Corretora A
     * @param b Vetor com ativos da Corretora B
     * @param caminhoAtual Subsequência sendo construída ao longo do ramo atual
     * @param melhorSolucao Melhor subsequência comum encontrada até o momento
     */
    private static void dfs(int indice, String[] a, String[] b, List<String> caminhoAtual, List<String> melhorSolucao) {
        // Caso base: folha da árvore binária atingida (todos os elementos de A foram decididos)
        if (indice == a.length) {
            // Verifica se o caminho atual é maior que a melhor solução encontrada e se é válido em B
            if (caminhoAtual.size() > melhorSolucao.size() && ehSubsequencia(caminhoAtual, b)) {
                melhorSolucao.clear();
                melhorSolucao.addAll(caminhoAtual);
            }
            return;
        }

        // Poda por viabilidade (Branch and Bound):
        // Se o tamanho atual + todos os elementos restantes de A não superam a melhor solução, não compensa continuar
        if (caminhoAtual.size() + (a.length - indice) <= melhorSolucao.size()) {
            return;
        }

        // RAMO ESQUERDO: "Pega A[indice]"
        caminhoAtual.add(a[indice]);
        dfs(indice + 1, a, b, caminhoAtual, melhorSolucao);
        
        // Backtracking: remove o elemento para poder explorar o ramo alternativo
        caminhoAtual.remove(caminhoAtual.size() - 1);

        // RAMO DIREITO: "Não pega A[indice]"
        dfs(indice + 1, a, b, caminhoAtual, melhorSolucao);
    }

    /**
     * Valida se a sequência candidata gerada a partir da Corretora A é uma subsequência
     * válida na lista de recomendações da Corretora B, respeitando a ordem relativa.
     *
     * @param sub Lista de tickers gerada na árvore
     * @param b Vetor de tickers da Corretora B
     * @return true se 'sub' é uma subsequência válida de 'b', false caso contrário.
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
