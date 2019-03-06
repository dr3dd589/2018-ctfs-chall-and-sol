#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define MAX 100
/*
 * gcc -fno-stack-protector -z execstack -o buf buf.c -m32
 */

char* exec_string = "/bin/sh";

void shell(char* cmd)
{
	system(cmd);
}

void print_name(char* input)
{
	char buf[15];
	strcpy(buf, input);
	printf("Hello %s\n", buf);
}

int main(int argc, char** argv)
{
	char buf[MAX];
	printf(R"EOF(
         ___    ___   _        ___  _____   ___      _   ___  
 _ __   / _ \  / _ \ | |__    / __\/__   \ / __\    / | / _ \ 
| '_ \ | | | || | | || '_ \  / /     / /\// _\_____ | || (_) |
| | | || |_| || |_| || |_) |/ /___  / /  / / |_____|| | \__, |
|_| |_| \___/  \___/ |_.__/ \____/  \/   \/         |_|   /_/ 
                                                              
)EOF");
	printf("First give me your name : ");
	fgets(buf, MAX, stdin);
	print_name(buf);

	return EXIT_SUCCESS;
}
