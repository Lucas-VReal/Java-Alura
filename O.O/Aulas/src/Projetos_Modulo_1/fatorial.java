package Projetos_Modulo_1;

public class fatorial {

	public static void main(String[] args) {
		
		for (int n = 1; n <= 10; n++) {
			System.out.print(n + "!: ");
			int total = 1;
			for(int fatorial = 1; fatorial <= n; fatorial++) {
				if (fatorial <= n && fatorial == 1) {
					System.out.print(fatorial);
				}else if(fatorial <= n && fatorial > 1){
					System.out.print(" x " + fatorial);
				}
				total = (fatorial * total);
			}
			System.out.print(" = "+ total);
			System.out.println();
		}

	}

}
