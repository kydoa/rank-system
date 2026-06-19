package com.project;

public class App {

    public static void main(String[] args) {
        BinarySearchTreeOfInteger bs = new BinarySearchTreeOfInteger();
        // Use the required initial scores from the project specification [7]
        Integer[] pontuacoesIniciais = {1500, 1200, 1800, 900, 2100, 750, 1300, 1700, 950, 2000};
        
        for (int i = 0; i < pontuacoesIniciais.length; i++) {
            bs.add(pontuacoesIniciais[i]);
        }

        // --- Demonstrating all required functionalities to satisfy requirements [7] ---
        
        System.out.println("--- GERENCIAMENTO ---");
        contagem(bs);
        vitoria(bs, 1200, 1300); // Example: score increases
        derrota(bs, 1800, 1700); // Example: score decreases

        System.out.println("\n--- VISUALIZAÇÕES ---");
        rankCrescente(bs);
        rankPre(bs);
        rankPos(bs);
        rankWidth(bs);

        System.out.println("\n--- CONSULTAS ---");
        procuraRank(bs, 1500);
        menorRank(bs);

        System.out.println("\n--- ANÁLISES ESTRATÉGICAS ---");
        faixaSkill(bs);         // Skill range [1]
        rankRef(bs, 1500, 200); // Reference score within limit [1]
        rankMed(bs);            // Average calculation [1]
    }

    // --- GERENCIAMENTO BÁSICO ---

    public static void derrota(BinarySearchTreeOfInteger bs, Integer antigo, Integer novo) {
        if (bs == null || (antigo < novo)) {
            throw new IllegalArgumentException("Valores irregulares");
        }
        bs.remove(antigo);
        bs.add(novo);
        System.out.println("DERROTA! Rank: " + antigo + " ---> " + novo);
    }

    public static void vitoria(BinarySearchTreeOfInteger bs, Integer antigo, Integer novo) {
        if (bs == null || (antigo > novo)) {
            throw new IllegalArgumentException("Valores irregulares");
        }
        bs.remove(antigo);
        bs.add(novo);
        System.out.println("VITÓRIA! Rank: " + antigo + " ---> " + novo);
    }

    public static void contagem(BinarySearchTreeOfInteger bs) {
        System.out.println("Número de jogadores: " + bs.size());
    }

    // --- VISUALIZAÇÕES DO RANKING ---

    public static void rankCrescente(BinarySearchTreeOfInteger bs) {
        LinkedListOfInteger aux = bs.positionsCentral();
        System.out.println("Rank em ordem crescente");
        int pos = aux.size();
        for (int i = 0; i < aux.size(); i++) {
            System.out.println(pos + "º: " + aux.get(i));
            pos--;
        }
    }

    public static void rankPre(BinarySearchTreeOfInteger bs) {
        LinkedListOfInteger aux = bs.positionsPre();
        System.out.println("Rank em pré-ordem");
        int pos = aux.size();
        for (int i = 0; i < aux.size(); i++) {
            System.out.println(pos + "º: " + aux.get(i));
            pos--;
        }
    }

    public static void rankPos(BinarySearchTreeOfInteger bs) {
        LinkedListOfInteger aux = bs.positionsPos();
        System.out.println("Rank em pós-ordem");
        int pos = aux.size();
        for (int i = 0; i < aux.size(); i++) {
            System.out.println(pos + "º: " + aux.get(i));
            pos--;
        }
    }

    public static void rankWidth(BinarySearchTreeOfInteger bs) {
        LinkedListOfInteger aux = bs.positionsWidth();
        System.out.println("Rank por nível");
        int pos = aux.size();
        for (int i = 0; i < aux.size(); i++) {
            System.out.println(pos + "º: " + aux.get(i));
            pos--;
        }
    }

    // --- CONSULTAS RÁPIDAS ---

    public static void procuraRank(BinarySearchTreeOfInteger bs, Integer rank) {
        System.out.println("Busca pelo rank: " + rank);
        if (!bs.contains(rank)) {
            System.out.println("Pontuação não encontrada");
        } else {
            System.out.println("Pontuação presente nos ranks");
        }
    }

    public static void menorRank(BinarySearchTreeOfInteger bs) {
        System.out.println("Menor rank: " + bs.getSmallest());
    }

    // --- ANÁLISES ESTRATÉGICAS ---

    public static void faixaSkill(BinarySearchTreeOfInteger bs) {
        LinkedListOfInteger aux = bs.positionsCentral();
        System.out.println("Faixa de Skill");
        for (int i = 0; i < aux.size(); i++) {
            Integer score = aux.get(i);
            if (score < 1000) System.out.println(score + ": Rank Iniciante");
            else if (score < 1500) System.out.println(score + ": Rank Intermediário");
            else if (score < 2000) System.out.println(score + ": Rank Avançado");
            else System.out.println(score + ": Rank Expert");
        }
    }

    public static void rankRef(BinarySearchTreeOfInteger bs, Integer rank, int limite) {
        LinkedListOfInteger aux = bs.positionsCentral();
        System.out.println("Pontuação referência: " + rank);
        System.out.println("Limite: " + limite);
        for (int i = 0; i < aux.size(); i++) {
            Integer current = aux.get(i);
            if (Math.abs(rank - current) <= limite) {
                int dif = Math.abs(rank - current);
                System.out.println(current + " (diferença: " + dif + ")");
            }
        }
    }

    public static void rankMed(BinarySearchTreeOfInteger bs) {
        LinkedListOfInteger aux = bs.positionsCentral();
        if (aux.isEmpty()) return;
        double soma = 0;
        for (int i = 0; i < aux.size(); i++) {
            soma += aux.get(i);
        }
        System.out.println("Média dos ranks: " + (soma / aux.size()));
    }
}
