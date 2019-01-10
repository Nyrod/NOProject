#include <stdio.h>
#include <conio.h>  

 // Kod algorytmu Euklidesa obliczania NWD 
// Zak³adamy, ¿e m i n s¹ wiêksze od 0 
int euclid(int m, int n) {
	int r;
	if (n>m) {
		r=m;
		m=n;
		n=r;
	}
	r = m % n;
	while (r != 0){
		m=n;
		n=r;
		r=m % n;
	}
	return n;
}

void main(){
int zm1,zm2,w;
printf ("Pierwsza liczba?\n");
scanf("%d",&zm1);
printf ("\nDruga liczba?\n");
scanf("%d",&zm2);	
printf ("\nNWD=%d", euclid(zm1,zm2));
getche();
}
