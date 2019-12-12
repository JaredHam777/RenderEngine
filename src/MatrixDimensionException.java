
public class MatrixDimensionException extends Exception {
	
	Matrix m;
	
	public MatrixDimensionException(Matrix m) {
		super();
		this.m = m;
			
	}
	
	public MatrixDimensionException(Matrix m, String message) {
		super(message);
		this.m = m;
	}
}
