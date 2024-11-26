#include <stdio.h>

int division(int a, int b);

int main() {
    int divisor, dividendo;

    printf("\nIngrese el numero divisor: ");
    scanf("%d", &divisor);

    printf("\nIngrese el numero dividendo: ");
    scanf("%d", &dividendo);
    printf("\nEl resultado es: %d\n", division(dividendo, divisor));

    return 0;
}

int division(int a, int b) {
    if (b > a) { 
        return 0;
    } else { 
        return division(a - b, b) + 1;
    }
}
