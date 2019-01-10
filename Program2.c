#include <iostream.h>
#include <conio.h>

void main(){
 const int wymiar=8; // wymiar tablicy
 int tablica[wymiar][wymiar]; //kwadratowa tablica;
 int dlugosc_sciezki, wyk_krokow; //wyk_krokow - dla danego kierunku
 int kierunek; //0-w prawo; 1-w d¢l; 2-w lewo; 3-w gore;
 int licznik=1; //potrzebne do wypelnienia kolejnymi liczbami
 int x,y; //pozycja w tablicy (x-kolumna; y-wiersz)
 clrscr();
 dlugosc_sciezki=wymiar; //za pierwszym razem cala szerokosc
 x=0; y=0;
 wyk_krokow=1;
 kierunek=0;
 while (dlugosc_sciezki>=2) {
  tablica[x][y]=licznik;
  licznik++;
  wyk_krokow++;
  if (wyk_krokow>dlugosc_sciezki)  {
   kierunek++;
   wyk_krokow=2;
   if (kierunek>3) kierunek=0;
   if (kierunek==3 || (kierunek==1 && dlugosc_sciezki<wymiar)) dlugosc_sciezki--;
  }
  switch (kierunek)  {
   case 0: x++; break;
   case 1: y++; break;
   case 2: x--; break;
   case 3: y--; break;
  }
 }
 clrscr();
 for (x=0;x<=wymiar-1;x++)
  for (y=0;y<=wymiar-1;y++) {
   gotoxy(x*4+1,y*2+1);
   cout << tablica[x][y];
  }
 getch();
}
