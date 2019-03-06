#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

#define BUFSIZE 32
#define FLAGSIZE 64

void win()
{
 char buf[FLAGSIZE];
  FILE *f = fopen("flag.txt","r");
  if (f == NULL) {
    printf("Flag File is Missing\n");
    exit(0);
  }

  fgets(buf,FLAGSIZE,f);
  printf(buf);
}

void func()
{
  char buffer[64];
  gets(buffer);
  return 0;
}

int main(int argc, char **argv)
{
  volatile int (*fp)();
  

  fp = 0;
  func();
}