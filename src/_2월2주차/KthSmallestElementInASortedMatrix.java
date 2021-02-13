package _2월2주차;

public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int row = (k - 1) / matrix[0].length;
        int col = (k - 1) % matrix[0].length;

        return matrix[row][col];
    }
}
