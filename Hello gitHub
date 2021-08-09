package summer_day02;
import java.util.Scanner;

public class yanghuisanjiao {
	public static void main(String []args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int [][]a=new int [n][n];
		a[0][0]=1;
		a[1][0]=1;
		a[1][1]=1;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<=i;j++) {
				if(j==0||j==i) {
					a[i][j]=1;
				}
				else {
					a[i][j]=a[i-1][j]+a[i-1][j-1];
				}
				System.out.print(a[i][j]+"\t");	
			}
			System.out.println();
		}
		
	}
	
	
	

}
