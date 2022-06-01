Tamaño = 9
Tablero = [[0 for col in range(1,10)] for row in range(1,10)]

def imprimirTablero(tablero):
    for fila in range(Tamaño):
        if(fila%3 ==0 and fila !=0):
            print("----------------------")
        for columna in range(Tamaño):
            if (columna % 3 == 0 and columna != 0):
                print("| ", end="")
            print(tablero[fila][columna], end=" ")
        print()


def esValido(tablero, fila, columna, num):
    for x in range(9):
        if tablero[fila][x] == num:
            return False

    for x in range(9):
        if tablero[x][columna] == num:
            return False

# Verifica el 3x3
    tempFila = fila - fila % 3
    tempColumna = columna - columna % 3
    for i in range(3):
        for j in range(3):
            if tablero[i + tempFila][j + tempColumna] == num:
                return False
    return True


def pedirNumero(tablero):
    num = 10
    columna = 10
    fila = 10
    while(num<0 or num>9):
        num = int(input("Digite un numero: "))
    while (columna < 0 or columna > 8):
        columna = int(input("Digite una posicion en x [0 - 8]: "))
    while (fila < 0 or fila > 8):
        fila = int(input("Digite una posicion en y [0 - 8]: "))

    if(esValido(tablero, fila, columna, num)):
        agregarNumero(tablero, fila, columna, num)
    else:
        print("Numero inválido")


def menu(tablero):
    
    print("\tSudoku")
    print("1. Ingresar Casillas Especificas")
    print("2. Resolver")
    print("3. Reglas")
    print("4. Salir")

    a = int(input("\nIngrese una opcion: "))

    if a == 1:
        llenarTablero(tablero)
    elif a == 2:
        if (Resolver(Tablero, 0, 0)):
            print("Resuelto!!")
            imprimirTablero(Tablero)

        else:
            print("No se puede resolver :(")

    elif a == 3:
        print("Reglas: No hay :v")
        menu(tablero)

    elif a == 4:

        print("Gracias por jugar!")
        exit()
    else:
        print("Error")


def agregarNumero(tablero, fila, columna, num):
    tablero[fila][columna] = num
    imprimirTablero(tablero)





def Resolver(tablero, fila, columna):
    if (fila == Tamaño - 1 and columna == Tamaño):
        return True
    if columna == Tamaño:
        fila += 1
        columna = 0
    if tablero[fila][columna] > 0:
        return Resolver(tablero, fila, columna + 1)
    for num in range(1, Tamaño + 1, 1):

        if esValido(tablero, fila, columna, num):

            tablero[fila][columna] = num
            if Resolver(tablero, fila, columna + 1):
                return True
        tablero[fila][columna] = 0
    return False

def llenarTablero(tablero):
    opc = "S"
    while opc == "S" or opc == "s":
        pedirNumero(tablero)
        opc = input("Desea agregar otro número? [S/N]   >  ")

    if opc != "S" or opc != "s":
        menu(tablero)

# Se llena el tablero de 0


# Se imprime el Tablero
imprimirTablero(Tablero)
# Inicia el programa
menu(Tablero)


