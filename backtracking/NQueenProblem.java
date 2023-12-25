
import java.util.Scanner;

public class NQueenProblem {
	public static int N;


	public static void printSolutionJSON(int board[][]){
		System.out.println("{\n\"data\":[");

		for (int i = 0; i < N; i++) {
			System.out.print("[");
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1)
					System.out.print("1");
				else
					System.out.print("0");

				if(j!=N-1){
					System.out.print(",");
				}
			}
			System.out.print("]");
			if(i!=N-1){
				System.out.println(",");
			}else{
				System.out.println();
			}
		}

			System.out.println("]\n}");
	}

	public static  boolean isSafe(int board[][], int row, int col){

		// verifica se já existe uma rainha na linha
		for (int i = 0; i < col; i++)
			if (board[row][i] == 1)
				return false;

		// verifica se já existe uma rainha na coluna
		for (int i = 0; i < row; i++)
			if (board[i][col] == 1)
				return false;

       // verifica se já existe uma rainha na diagonal acima
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;

       // verifica se já existe uma rainha na diagonal abaixo
		for (int i = row, j = col; j >= 0 && i < N; i++, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}

	//solve using backtracking
	public static boolean solve(int board[][], int col){
		// caso para parada
		if (col >= N)
			return true;

		for (int i = 0; i < N; i++) {

			if (isSafe(board, i, col)) {
				
				board[i][col] = 1;

				if (solve(board, col + 1) == true)
					return true;

				board[i][col] = 0; // BACKTRACK
			}
		}

		return false;
	}

	private boolean solveNQ(){
		int board[][] = new int[N][N];

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				board[i][j] = 0;
			}
		}

		if (solve(board, 0) == false) {
			System.out.print("Solution does not exist");
			return false;
		}

		printSolutionJSON(board);
		return true;
	}

	// Driver program to test above function
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		NQueenProblem Queen = new NQueenProblem();
		Queen.solveNQ();
	}
}
// This code is contributed by Abhishek Shankhadhar

