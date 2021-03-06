import java.util.ArrayList;

class rankacceptabilitymatrix {

    Double[] dataForRanking;
    int[][] rankAcceptability;

    rankacceptabilitymatrix(ArrayList<Double> result, int[][] rankAcceptability) {
        dataForRanking = result.toArray(new Double[result.size()]);
        this.rankAcceptability = rankAcceptability;
    }

    rankacceptabilitymatrix(ArrayList<Double> result) {
        dataForRanking = result.toArray(new Double[result.size()]);
        rankAcceptability = new int[dataForRanking.length][dataForRanking.length];
        for (int i = 0; i < rankAcceptability.length; i++) {
            for (int j = 0; j < rankAcceptability.length; j++) {
                rankAcceptability[i][j] = 0;
            }
        }
    }

    rankacceptabilitymatrix() {

    }

    //Die Daten, aus der NWA aufarbeiten
    int[][] ranking() {

        for (int i = 0; i < dataForRanking.length; i++) {
            ArrayList<Integer> index = highest();
            //Liste mit den akzeptierten Alternativen
            while (index.size() > 0 && index.get(0) != -1){
                rankAcceptability[index.get(0)][i]++;
                dataForRanking[index.get(0)] = -10.0;
                index.remove(0);
            }

        }
/*
        System.out.println("Ranka");
        System.out.println(Arrays.deepToString(rankAcceptability));
*/
        return rankAcceptability;
    }

    //den highest Wert finden
    ArrayList<Integer> highest() {
        double max = 0.0;       //der maximale Wert
        int inMax = -1;         //index des maximalen wertes
        //Liste mit den indexen der Alternativen, die am ehesten Akzeptiert werden
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < dataForRanking.length; i++) {
            if (dataForRanking[i] > max) {
                max = dataForRanking[i];
                inMax = i;
            }
        }
        index.add(inMax);
        //testen, ob der max Wert noch mal vorkommt und in Liste aufnehmen
        for (int i = 0; i < dataForRanking.length; i++) {
            if (dataForRanking[i] == max) {
                if(!index.contains(i)){
                    index.add(i);
                }
            }
        }
        //System.out.println(index.toString());
        return index;
    }

    int[][] getRankAcceptability() {
        return rankAcceptability;
    }
}
