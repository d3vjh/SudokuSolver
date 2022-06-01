package sudoku;

import java.util.Scanner;

/*
 * Falta agregar que se llene a mano, un validador de rango 1-9 (opc que verifique si esta en linea o columna)
 */
public class Main {

	private static final int tamaño = 9;

	public static void main(String[] args) {

		int[][] Tablero = new int[9][9];

		imprimirTablero(Tablero);
		menu(Tablero);


	}

	private static void imprimirTablero(int[][] tablero) {
		for (int fila = 0; fila < tamaño; fila++) {
			if (fila % 3 == 0 && fila != 0) {
				System.out.println("-----------");
			}
			for (int columna = 0; columna < tamaño; columna++) {
				if (columna % 3 == 0 && columna != 0) {
					System.out.print("|");
				}
				System.out.print(tablero[fila][columna]);
			}
			System.out.println();
		}
	}

	private static boolean enFila(int[][] tablero, int num, int fila) {
		for (int i = 0; i < tamaño; i++) {
			if (tablero[fila][i] == num) {
				return true;
			}
		}
		return false;
	}

	private static boolean enColumna(int[][] tablero, int num, int columna) {
		for (int i = 0; i < tamaño; i++) {
			if (tablero[i][columna] == num) {
				return true;
			}
		}
		return false;
	}

	private static boolean enCaja(int[][] tablero, int num, int fila, int columna) {
		int tempFila = fila - fila % 3;
		int tempColumn = columna - columna % 3;

		for (int i = tempFila; i < tempFila + 3; i++) {
			for (int j = tempColumn; j < tempColumn + 3; j++) {
				if (tablero[i][j] == num) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean esValido(int[][] tablero, int num, int fila, int columna) {
		return !enFila(tablero, num, fila) && !enColumna(tablero, num, columna) && !enCaja(tablero, num, fila, columna);
	}

	private static boolean resolver(int[][] tablero) {
		for (int fila = 0; fila < tamaño; fila++) {
			for (int columna = 0; columna < tamaño; columna++) {
				if (tablero[fila][columna] == 0) {
					for (int aux = 1; aux <= tamaño; aux++) {
						if (esValido(tablero, aux, fila, columna)) {
							tablero[fila][columna] = aux;

							if (resolver(tablero)) {
								return true;
							} else {
								tablero[fila][columna] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	
	private static void pedirNumero(int tablero[][]){
		Scanner s= new Scanner(System.in);
		String opc = "";
		int num;
		int columna;
		int fila;
		
		do {
			do {
			System.out.print("Digite un numero: ");
			num = s.nextInt();
			}while(num>9 || num<0);
			do {
			System.out.print("Digite un posicion x: ");
			columna = s.nextInt();
			}while(columna>8 || columna<0);
			
			do {
			System.out.print("Digite un posicion y: ");
			fila = s.nextInt();
			}while(fila>8 || fila<0);
			
			if(esValido(tablero, num, fila, columna)) {
				agregarNumero(tablero, fila, columna, num);
			}else {
				System.err.println("Numero inválido");
			}
			
			System.out.print("Desea agregar otro número? [S/N]   >  ");
			s.nextLine();
			opc = s.nextLine();

		} while(opc.equalsIgnoreCase("S"));

		if (!opc.equalsIgnoreCase("n")) {
			System.err.println("Error de entrada...");

		}
		menu(tablero);
	}
	
	
	private static void menu(int tablero[][]) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("\tSudoku\n");
		System.out.println("1. Ingresar en casillas especificas");
		System.out.println("2. Resolver");
		System.out.println("3. Reglas");
		System.out.println("4. Salir");
		
		System.out.print("\nIngrese una opción: ");
		int a = entrada.nextInt();
		
		
		
		switch (a) {
		case 1: 
			pedirNumero(tablero);
			break;
		case 2:
			resolver(tablero);
			if (resolver(tablero)) {
				System.out.println("\tResuleto!!");
			} else {
				System.out.println("\tNo se puede resolver:(");
				pedirNumero(tablero);
			}
			

			imprimirTablero(tablero);
			break;
		case 3:
			reglas(tablero);
		case 4:
			System.out.println("\n\nGracias por jugar!");
			System.exit(0);
		default:
			break;
		}
	}
	
	private static void agregarNumero(int tablero[][], int fila, int columna, int num) {

		tablero[fila][columna] = num;
		imprimirTablero(tablero);

	}
	
	private static void reglas(int [][]tablero) {
		System.out.println("\n");
		System.out.println("\tReglas");
		System.out.println("Un sudoku común y corriente");
		System.out.println("LLenar el tablero desde el 0 al 8 (Filas y columnas)");
		System.out.println("Presione \"2\" para resolver el sudoku");
		System.out.println("Obtenga la solución al instante\n\n");
		menu(tablero);
	}

}