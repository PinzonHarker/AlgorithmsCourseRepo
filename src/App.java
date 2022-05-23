import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class App {

    // public static void unionAll(WeightedQuickUnionUF q, int list[][]) {
    //     for (int i = 0; i < 1; i++) {
    //         q.union(list[i][0], list[i][1]);
    //         for (int j = 0; j < list.length; j++){
    //             System.out.print(q.find(j) + " ");
    //         }
    //     }
    // }

    public static void main(String[] args) throws Exception {
        final int listUnions[][] = {
                { 4, 3 },
                { 3, 8 },
                { 6, 5 },
                { 9, 4 },
                { 2, 1 },
                { 5, 0 },
                { 7, 2 },
                { 6, 1 },
                { 7, 3 }
        };

        final WeightedQuickUnionUF qq = new WeightedQuickUnionUF(10);
        for (int i = 0; i < listUnions.length; i++) {
            qq.union(listUnions[i][0], listUnions[i][1]);
            for (int j = 0; j < 10; j++){
                System.out.print(qq.find(j) + " ");
            }
            System.out.println("     c= " + i);
        }
        
        

    }
}
