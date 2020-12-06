import java.util.Arrays;

// Problem Statement: Given a bag with maximum capacity and list of items with their weight and value.
// Need to put optimum no. of items in the bag so that value is maximum.
public class _2_01Knapsack {
    static int[][] t;

    public static void main(String[] args) {
        int[] wt = new int[]{1, 3, 4, 5};
        int[] value = new int[]{1, 4, 5, 7};
        int capacity = 7;
        int n = wt.length;

        // for Memoization
        t = new int[n + 1][capacity + 1];
        for (int[] row : t)
            Arrays.fill(row, -1);

        System.out.println(knapsack(wt, value, n, capacity));
        System.out.println(knapsackDynamic(wt, value, n, capacity));
    }

    // Recursion with memoization same time complexity as dynamic version
    public static int knapsack(int[] wt, int[] value, int n, int capacity) {
        if (n == 0 || capacity == 0)
            return 0;

        if (t[n][capacity] != -1)
            return t[n][capacity];

        if (wt[n-1] <= capacity) {
            return t[n][capacity] = Math.max(value[n - 1] + knapsack(wt, value, n - 1, capacity - wt[n - 1]), // we choose the item
                    knapsack(wt, value, n - 1, capacity));  // we reject the item
        } else {
            return t[n][capacity] = knapsack(wt, value, n - 1, capacity);  // we reject the item as its weight is more than capacity
        }
    }

    public static int knapsackDynamic(int[] wt, int[] value, int n, int capacity) {
        int[][] dc = new int[n + 1][capacity + 1]; // n+1 rows , capacity+1 columns

        for (int i = 0; i < n+1; i++)  // corresponds to base condition of recursion for n == 0
            dc[i][0] = 0;

        for (int j = 1; j<capacity + 1; j++)  // // corresponds to base condition of recursion for capacity == 0
            dc[0][j] = 0;

        // exact same as recursion... only replace n with i and capacity with j
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < capacity + 1; j ++) {
                if (wt[i - 1] <= j)
                    dc[i][j] = Math.max((value[i-1] + dc[i-1][j-wt[i-1]]), dc[i-1][j]);
                else
                    dc[i][j] = dc[i-1][j];
            }

        return dc[n][capacity];
    }
}
