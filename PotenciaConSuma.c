#include <stdio.h>
int Potencia(int, int);

int main() {
    int numero, exponente;
    printf("Ingrese un numero: ");
    scanf("%d", &numero);

    printf("Ingrese el exponente a calcular: ");
    scanf("%d", &exponente);

   if (exponente < 1) {
        printf("El exponente debe ser un entero positivo.\n");
    } else {
        printf("El resultado es: %d\n", Potencia(numero, exponente));
    }

    return 0;
}

int Potencia(int numero, int exponente) {
    if (exponente == 1) {
        return numero;
    } else {
        return numero * Potencia(numero, exponente - 1);
    }
}
