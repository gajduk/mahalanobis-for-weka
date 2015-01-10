import java.util.Arrays;

import org.junit.Test;


public class MahalanobisTest {

	@Test
	public void testMeanRowVector() {
		double a[][] = { { 2,2 },
				{ 2,5},
				{ 6,5},
				{ 7,3},
				{ 4,7},
				{ 6,4},
				{ 5,3},
				{ 4,6},
				{ 2,5},
				{ 1,3} };
		double b[][] = { { 6,5 },
				{ 7,4},
				{ 8,7},
				{ 5,6},
				{ 5,4} };
		System.out.println(Arrays.toString(MatrixUtils.meanRowVector(a)));
		System.out.println(Arrays.toString(MatrixUtils.meanRowVector(b)));
	}

	@Test
	public void testmultiply() {
		double a[][] = { { 2,2 },
				{ 2,5},
				{ 6,5},
				{ 7,3},
				{ 4,7},
				{ 6,4},
				{ 5,3},
				{ 4,6},
				{ 2,5},
				{ 1,3} };
		MatrixUtils.multiply(a,2);
		MatrixUtils.print(a);
	}

	@Test
	public void testCenterMatrix() {
		System.out.println();
		double a[][] = { { 2,2 },
				{ 2,5},
				{ 6,5},
				{ 7,3},
				{ 4,7},
				{ 6,4},
				{ 5,3},
				{ 4,6},
				{ 2,5},
				{ 1,3} };
		double b[][] = { { 6,5 },
				{ 7,4},
				{ 8,7},
				{ 5,6},
				{ 5,4} };
		MatrixUtils.print(MatrixUtils.centerMatrix(MatrixUtils.meanRowVector(a), a));
		MatrixUtils.print(MatrixUtils.centerMatrix(MatrixUtils.meanRowVector(b), b));
	}

	@Test
	public void testCovariance() {
		System.out.println();
		double a[][] = { { 2,2 },
				{ 2,5},
				{ 6,5},
				{ 7,3},
				{ 4,7},
				{ 6,4},
				{ 5,3},
				{ 4,6},
				{ 2,5},
				{ 1,3} };
		double b[][] = { { 6,5 },
				{ 7,4},
				{ 8,7},
				{ 5,6},
				{ 5,4} };
		double [][]a_centered = MatrixUtils.centerMatrix(MatrixUtils.meanRowVector(a), a);
		double [][]b_centered = MatrixUtils.centerMatrix(MatrixUtils.meanRowVector(b), b);
		MatrixUtils.print(MatrixUtils.calculateCovariance(a_centered));
		System.out.println();
		MatrixUtils.print(MatrixUtils.calculateCovariance(b_centered));
	}

	@Test
	public void testPooledCovariance() {
		System.out.println();
		double a[][] = { { 2,2 },
				{ 2,5},
				{ 6,5},
				{ 7,3},
				{ 4,7},
				{ 6,4},
				{ 5,3},
				{ 4,6},
				{ 2,5},
				{ 1,3} };
		double b[][] = { { 6,5 },
				{ 7,4},
				{ 8,7},
				{ 5,6},
				{ 5,4} };
		double [][]a_centered = MatrixUtils.centerMatrix(MatrixUtils.meanRowVector(a), a);
		double [][]b_centered = MatrixUtils.centerMatrix(MatrixUtils.meanRowVector(b), b);
		double [][]a_covariance = MatrixUtils.calculateCovariance(a_centered);
		double [][]b_covariance = MatrixUtils.calculateCovariance(b_centered);
		double [][] pooled_covariance = new MahalanobisDistance().pool(a, b, a_covariance, b_covariance);
		MatrixUtils.print(pooled_covariance);
	}

	@Test
	public void testInverse() {
		double a[][] = { { 1 , 3 , 1 },
				{ 1 , 1 , 2 },
				{ 2 , 3 , 4 } };
		MatrixUtils.print(MatrixUtils.inverse(a));

	}

	@Test
	public void testDeterminanta() {
		double a[][] = { { 1 , 3 , 1 },
				{ 1 , 1 , 2 },
				{ 2 , 3 , 4 } };
		System.out.println(MatrixUtils.determinanta(a));
		double b[][] = { { 1 , 3},
				{ 2 , 3  } };
		System.out.println(MatrixUtils.determinanta(b));
	}

	@Test
	public void testDistance() {
		double a[][] = { { 2,2 },
				{ 2,5},
				{ 6,5},
				{ 7,3},
				{ 4,7},
				{ 6,4},
				{ 5,3},
				{ 4,6},
				{ 2,5},
				{ 1,3} };
		double b[][] = { { 6,5 },
				{ 7,4},
				{ 8,7},
				{ 5,6},
				{ 5,4} };
		System.out.println(new MahalanobisDistance().distance(a, b));
	}


}






















