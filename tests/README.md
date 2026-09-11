# Casos de Teste - LCS (Busca em Profundidade / Backtracking)

Este diretório contém instâncias de teste formatadas estritamente de acordo com as especificações do Trabalho Prático (Linha 1: M N, Linha 2: tickers de A, Linha 3: tickers de B).

Os testes cobrem desde execuções instantâneas até o limiar de explosão combinatória O(2^M * N), permitindo avaliar o comportamento prático do algoritmo implementado em `LCS.java`.

---

## Tabela de Casos de Teste

| Arquivo | M | N | Tipo de Cenário | Tempo Esperado | Saída Esperada |
| :--- | :---: | :---: | :--- | :---: | :--- |
| `01_rapido_m15.txt` | 15 | 100 | Pior caso pequeno (disjuntos) | ~0,02 s | `0` (linha em branco) |
| `02_medio_m20.txt` | 20 | 100 | Pior caso moderado (disjuntos) | ~0,10 s | `0` (linha em branco) |
| `03_melhor_caso_m50.txt`| 50 | 50 | Melhor caso (listas idênticas) | < 0,01 s | `50` (todos os 50 ativos) |
| `04_limiar_m25.txt` | 25 | 100 | Limiar crítico (disjuntos) | ~4 a 6 s | `0` (linha em branco) |
| `05_lento_m26.txt` | 26 | 100 | Pior caso lento (disjuntos) | ~7 a 10 s | `0` (linha em branco) |
| `06_muito_lento_m27.txt`| 27 | 100 | Pior caso muito lento (disjuntos)| ~15 a 20 s | `0` (linha em branco) |
| `07_extremo_m28.txt` | 28 | 100 | Limite superior prático (disjuntos)| ~30 a 45 s | `0` (linha em branco) |

---

## Como Executar

A partir da raiz do projeto (`C:\faculdade\paa\LCS`):

```bash
# Executando um caso rápido
java Main < testes/01_rapido_m15.txt

# Executando o caso de poda eficiente (M=50 instantâneo)
java Main < testes/03_melhor_caso_m50.txt

# Executando o limiar de 4 a 6 segundos
java Main < testes/04_limiar_m25.txt

# Medindo o tempo exato de execução no PowerShell
Measure-Command { Get-Content testes/04_limiar_m25.txt | java Main }

Get-Content .\teste_limite.txt | java -cp src .\src\Main.java
```
