import java.util.concurrent.Callable;

public class Matrix {
	int rows;
	int columns;
	
	double values[][];
	
	public Matrix(int rows, int columns)	{
		this.rows = rows;
		this.columns = columns;
		values = new double[rows][columns];
	}
	
	public Matrix(double[][] values) {
		this.values = values;
	}

	public Matrix() {
		
	}
	
	public void setVal(int row, int column, double value) {
		this.values[row][column] = value;
	}
	public double getVal(int row, int column)	{
		return this.values[row][column];
	}
	
	public int getNumRows()	{
		return rows;
	}
	public int getNumColumns() {
		return columns;
	}
	
	public Matrix scale(double val) {
		Matrix returnM = new Matrix(this.values);
		
		for(int r=0; r<this.rows; r++) {
			for(int c=0; c<this.columns; c++) {
				returnM.setVal(r, c, this.getVal(r, c)*val);
			}
		}
		
		return returnM;
	}
	
	public Matrix multiply(Matrix m) throws MatrixDimensionException	{
		if(this.columns != m.rows) {
			throw new MatrixDimensionException(this, "matrix not correct dim to multiply");
		}
		Matrix returnM = new Matrix(this.rows, m.columns);
		
		for(int r=0; r<this.rows; r++) {			
		
			for(int c=0; c<m.columns; c++) {
				double value = 0;
					for(int n=0; n<this.columns; n++) {
						value += this.getVal(r, n)*m.getVal(n, c);
					}
				returnM.setVal(r, c, value);

			}
		}
		
		return returnM;
	}
	
	public Matrix copyExcludingCross(int row, int column) {
		Matrix returnM = new Matrix(this.rows-1, this.columns-1);
		
		boolean rHit=false;
		for(int r=0; r<returnM.rows; r++) {
			if(row == r)	{rHit=true;}
			boolean cHit=false;
			for(int c=0; c<returnM.columns; c++) {
				if(column == c) {cHit=true;}
				
				if(cHit && rHit) {
				returnM.setVal(r, c, this.getVal(r+1, c+1));
				}	else if(cHit) {
					returnM.setVal(r, c, this.getVal(r, c+1));
				}	else if(rHit)	{
					returnM.setVal(r, c, this.getVal(r+1, c));
				}	else	{
					returnM.setVal(r, c, this.getVal(r, c));
				}
				
			}
		}
		
		return returnM;
	}
	
	public double determinate() throws MatrixDimensionException {
		if(this.rows != this.columns) {
			throw new MatrixDimensionException(this, "Cannot take the determinate of a non square matrix");
		}
		//base case:
		if(this.rows ==2)	{
			return this.getVal(0, 0)*this.getVal(1, 1) - this.getVal(0, 1)*this.getVal(1, 0);
		}
		
		double value = 0;
		for(int c=0; c<this.columns; c++)	{			
			double factor = Math.pow(-1, c)*this.getVal(0, c) ;
			Matrix subMatrix = this.copyExcludingCross(0, c);			
			value += (factor*subMatrix.determinate());
		}
		return value;
	}
	
	public Matrix transpose() {
		Matrix returnM = new Matrix(this.columns, this.rows);
		for(int r=0; r<this.rows; r++) {
			for(int c=0; c<this.columns; c++) {
				returnM.setVal(c, r, this.getVal(r, c));
			}
		}
		return returnM;
	}
	
	public double magnitudeOfColumn(int c) {
		double sum = 0;
		for(int r=0; r<this.rows; r++) {
			sum+=Math.pow(this.getVal(r, c), 2);
			
		}
		return Math.sqrt(sum);
	}
	
	public double magnitudeOfRow(int r) {
		double sum = 0;
		for(int c=0; c<this.columns; c++) {
			sum+=Math.pow(this.getVal(r, c), 2);
			
		}
		return Math.sqrt(sum);
	}
	
	public void normalizeV() {
		for(int c = 0; c<this.columns; c++) {
			double mag = this.magnitudeOfColumn(c);
			for(int r=0; r<this.rows; r++)	{
				this.setVal(r, c, this.getVal(r, c)/mag);
			}
		}
	}
	
	public void normalizeH() {
		for(int r = 0; r<this.rows; r++) {
			double mag = this.magnitudeOfRow(r);
			for(int c=0; c<this.columns; c++)	{
				this.setVal(r, c, this.getVal(r, c)/mag);
			}
		}
	}
	
	public Matrix normalizedV()	{
		Matrix m = new Matrix(this.values);
		m.normalizeV();
		return m;
	}
	public Matrix normalizedH()	{
		Matrix m = new Matrix(this.values);
		m.normalizeH();
		return m;
	}
	
	
	//other functions:
	public void print() {
		for(int r=0; r<this.rows; r++) {
			System.out.print("|");
			for(int c=0; c<this.columns; c++) {
				System.out.print(" " + this.getVal(r, c) + " ");
			}
			System.out.print("|\n");
		}
	}
	

	
}	
