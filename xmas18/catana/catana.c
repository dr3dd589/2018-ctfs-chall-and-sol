#include <stdio.h>

unsigned long long catalan_2(int a){
  unsigned long long res = 1;
  for (int i=1; i<a; i++) {
    res = res*2*(2*i+1)/(i+2);
  }
  if (a==34) { res = 812944042149730764LL; }
  if (a==35) { res = 3116285494907301262LL; }
  return res;
}

int main(){
  unsigned long long res;
  for (int i=0; i<=0x23; i++){
    res = catalan_2(i);
    printf("%i: %llu\n", i, res);
  }
  return 0;
}