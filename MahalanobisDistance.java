import java.util.Arrays;

import weka.core.Instance;
import weka.core.Instances;

public class MahalanobisDistance {

	public static void main(String[] args) {
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
		MahalanobisDistance m = new MahalanobisDistance();
		System.out.println(" A = ");
		MatrixUtils.print(a);
		System.out.println();
		System.out.println(" B = ");
		MatrixUtils.print(b);
		System.out.println();
		System.out.printf("Mahalanobis distance between matrices A nd B is %.3f",m.distance(a,b));
	}

	public double distance(Instances a,Instances b) {
		if ( a.equalHeaders(b) ) {
			System.err.println("Both datasets must have the same headers");
		}
		distance(getFeaturesAsMatrix(a),getFeaturesAsMatrix(b));
		return 2.0d;
	}

	private double[][] getFeaturesAsMatrix(Instances a) {
		double[][] res = new double[a.numInstances()][a.numAttributes()];
		for ( int i = 0 ; i < res.length ; ++i ) {
			Instance ii = a.instance(i);
			for ( int k = 0 ; k < res[i].length ; ++k ) {
				res[i][k] = ii.value(k);
			}
		}
		return res;
	}

	/**
	 * calculate the mahalanobis distance between two matrices of feature values
	 */
	public double distance(double a[][],double b[][]) {
		int total_num_objects = a.length+b.length;
		if ( a[0].length != b[0].length ) {
			System.out.println("Objects must have the same number of feautures");
			return -1.0;
		}
		int num_feautures = a[0].length;
		double mean_diff_row_vector[] = new double[num_feautures];
		double mean_a_row_vector[] = new double[num_feautures];
		double mean_b_row_vector[] = new double[num_feautures];
		mean_a_row_vector = MatrixUtils.meanRowVector(a);
		mean_b_row_vector = MatrixUtils.meanRowVector(b);
		for ( int i = 0 ; i < mean_diff_row_vector.length ; ++i ) {
			mean_diff_row_vector[i] = mean_a_row_vector[i]-mean_b_row_vector[i];
		}
		double covariance_a[][] = MatrixUtils.calculateCovariance(a);
		double covariance_b[][] = MatrixUtils.calculateCovariance(b);
		MatrixUtils.multiply(covariance_a,a.length/((double)total_num_objects));
		MatrixUtils.multiply(covariance_b,b.length/((double)total_num_objects));
		double covariance_total[][] = new double[num_feautures][num_feautures];
		for ( int i = 0 ;  i < num_feautures ; ++i ) {
			for ( int k = 0 ; k < num_feautures ; ++k ) {
				covariance_total[i][k] = covariance_a[i][k]+covariance_b[i][k];
			}
		}
		double distance_squared = MatrixUtils.multiply_matrices(MatrixUtils.multiply_matrices(mean_diff_row_vector, MatrixUtils.inverse(covariance_total)),MatrixUtils.transpose(mean_diff_row_vector))[0][0];
		return Math.sqrt(distance_squared);

	}

	/**
	 * calculate the pooled covariance data
	 * covariance1*weight+covariance2*weight
	 */
	public double[][] pool ( double[][] a, double[][] b , double [][]a_covariance , double [][]b_covariance ) {
		double res[][] = new double[a_covariance.length][a_covariance[0].length];
		for ( int i = 0 ; i < a_covariance.length ; ++i ) {
			for ( int k = 0 ; k < a_covariance[0].length ; ++k ) {
				res[i][k] = ((double)a.length)/(a.length+b.length)*a_covariance[i][k];
			}
		}
		for ( int i = 0 ; i < a_covariance.length ; ++i ) {
			for ( int k = 0 ; k < a_covariance[0].length ; ++k ) {
				res[i][k] += ((double)b.length)/(a.length+b.length)*b_covariance[i][k];
			}
		}
		return res;
	}

}
