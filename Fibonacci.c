#include <stdio.h>

int fibonacci(int x) {
    if (x == 0){
        return 0;
    }
    if (x == 1) 
    {
        return 1;
    }else{
    return fibonacci(x - 1) + fibonacci(x - 2); 
    }
}


int main() {
    int numero;
    printf("Ingrese el número de elementos de la serie: ");
    scanf("%d", &numero);

    for (int i = 0; i < numero; i++) {
        printf("%d ", fibonacci(i));
    }

    return 0;
}
