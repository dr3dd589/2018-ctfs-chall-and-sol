#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

#define COLOR_RESET  "\033[0m"
#define BOLD         "\033[1m"
#define BLACK_TEXT   "\033[30;1m"
#define RED_TEXT     "\033[31;1m"
#define GREEN_TEXT   "\033[32;1m"
#define YELLOW_TEXT  "\033[33;1m"
#define BLUE_TEXT    "\033[34;1m"
#define MAGENTA_TEXT "\033[35;1m"
#define CYAN_TEXT    "\033[36;1m"
#define WHITE_TEXT   "\033[37;1m"

unsigned int hp_player = 100;
unsigned int hp_boss = 100;
int pomeg_count = 6;
int aguav_count = 6;

int readint()
{
  int choice;
  scanf("%d", &choice);
  return choice;
}

void menu()
{

  printf("%s", BLUE_TEXT);
  puts("================================");
  puts("1. Attack            2. Item");
  puts("3. Pwnball           4. Run");
  puts("================================");
  printf("%s> ", COLOR_RESET);
  return;
}

void status()
{

  printf("%s", CYAN_TEXT);
printf("                                                         ███▓\n");
printf("                                             ████       █    ▓\n");
printf("                                           ██░   ██    █░░    ▓\n");
printf("                                          █░       █   █░░░░  ▓\n");
printf("                                          █░░ ░     ███████░░░ ▓\n");
printf("                                         █░░░░██    █      ██░░▓\n");
printf("                                         █░░░█░░█   ▓        █░▓██\n");
printf("                                         █░░░░░░░█            ██  █\n");
printf("                                          █░░░░░░█           ░░   █\n");
printf("                                          █░░░░░░█░              █\n");
printf("                                           ██░░░█░░░          ░ ░█\n");
printf("                                             ███░░░░░▓██░░▒    ░░█\n");
printf("                                              █░░░░░    █░░▒      ▓\n");
printf("                                              █░░▓░░   ▒ █░▒      ▓\n");
printf("                                              █░▓▓░░░░ █▒█░▒      ▓\n");
printf("                                              █░▓░░░░░░▓███        ▓\n");
printf("                                              █░░░░▒█░░░░░░░░     ░█\n");
printf("                                               █░░▒▒▒█░░░░░░░░░░░░░█\n");
printf("                                               █░░▒▒▒▒▓░░░░░░░░░░░░█\n");
printf("                                               █░░▒▒▒▒▒██░░░░░░░▓░░█\n");
printf("                                                █░▒▒▓▒▒▒▒███▓▓░░░░█\n");
printf("                                                █░░▒▒▓▓▒▒▒▒▒▒▒▓▓░░█\n");
printf("                                                █░░▒▒▒▒▓▓██████ ██\n");
printf("                                                █░░▒▒▒▒▒▒█\n");
 

 printf("%s", RED_TEXT);
printf("                                   ==================================\n");
printf("                                   Laprus HP: %u\n", hp_boss);
printf("                                   ==================================\n");


  printf("================================\n");
  printf("Pikachu HP: %u\n", hp_player);
  printf("================================\n");

  

 
  printf("%s        ▀████▀▄▄              ▄█\n", YELLOW_TEXT);
printf("          █▀░░░░▀▀▄▄▄▄▄    ▄▄▀▀█\n");
printf("  ▄        █░░░░░░░░░░▀▀▀▀▄░░▄▀\n");
printf(" ▄▀░▀▄      ▀▄░░░░░░░░░░░░░░▀▄▀\n");
printf("▄▀░░░░█     █▀░░░▄█▀▄░░░░░░▄█\n");
printf("▀▄░░░░░▀▄  █░░░░░▀██▀░░░░░██▄█\n");
printf(" ▀▄░░░░▄▀ █░░░▄██▄░░░▄░░▄░░▀▀░█\n");
printf("  █░░▄▀  █░░░░▀██▀░░░░▀▀░▀▀░░▄▀\n");
printf(" █░░░█  █░░░░░░▄▄░░░░░░░░░░░▄▀\n");

  return;
}

void attack()
{
  sleep(1);
  printf("\n%s", YELLOW_TEXT);
	  puts("Pikachu used Tackle.");
	  sleep(1);
	  puts("It wasn't very effective");
	  sleep(1);
	  srand(time(0));
	  
	  int attack_hp = rand() % (10 + 1 - 5) + 5;
	  hp_boss = hp_boss - attack_hp;
	  status();

	  sleep(1);
	  printf("\n%s" CYAN_TEXT);
	  puts("Laprus used Blizzard");
	  sleep(1);
	  puts("It was super effective");
	  sleep(1);

	  if (hp_player > 50)
	    attack_hp = rand() % (60 + 1 - 50) + 50;
	  else
	    attack_hp = hp_player;
	  hp_player = hp_player - attack_hp;
	  status();
	  
	  if (hp_player == 0)
	    {
	      puts("Pikachu fainted");
	      exit(0);
	    }
	    

}


void aguav()
{
  if (aguav_count > 0)
    {
      printf("%s[+] ", GREEN_TEXT);
  puts("Pikachu ate aguav berry");
  sleep(1);
  puts("Pikachu's HP increased");
  printf("%s", COLOR_RESET);
  sleep(1);
  aguav_count-=1;
  hp_player = hp_player + 10;
    }
  else
    {
      puts("No more aguav berries left");
    }
  return;
}

void pomeg()
{
  if (pomeg_count > 0)
    {
      printf("%s[-] ", MAGENTA_TEXT);
  puts("Pikachu ate pomeg berry");
  sleep(1);
  puts("Pikachu's HP decreased");
  printf("%s", COLOR_RESET);
  sleep(1);
  pomeg_count-=1;
  hp_player = hp_player - 10;
    }
  else
    {
      puts("No more pomeg berries left");
    }
  return;
}

void items_menu()
{
  printf("%s", WHITE_TEXT);

  puts("\n=======================================\n");
  printf("1. Aguav Berry x %d : Each restores your pokemon's HP by 10\n", aguav_count);
  printf("2. Pomeg Berry x %d: Each lowers pokemon's HP by 10\n\n", pomeg_count);
  puts("=======================================\n");
  puts("Which item do you want to use?");
  printf("> ");

  int choice = readint();
  if (choice == 1)
    aguav();
  else if (choice == 2)
    pomeg();
  else
    {
      puts("Incorrect choice");
      exit(0);
    }
  
}


void pokeball()
{
  puts("You threw a pwnball");
  sleep(1);
  if (hp_boss < 10)
    {
      puts("Gotcha! Succesfully caught Laprus");
      printf("%s", RED_TEXT);
      sleep(1);
      puts("Here's your flag:");
      FILE* fptr = fopen("./flag", "r");
      if (fptr == NULL)
	{
	  puts("Cannot open file.");
	  exit(0);
	}
      else
	{
	  char c = fgetc(fptr);
	  while(c!=EOF)
	    {
	      printf("%c", c);
	      c = fgetc(fptr);
	    }
	  fclose(fptr);
	}
    }
  else
    {
      puts("Laprus needs to be weakened before capture.");
      puts("Laprus escaped.");
      sleep(1);
    }
  exit(0);
}

void banner()
{
  printf("%s                                  ,'\\\n", YELLOW_TEXT);
printf("    _.----.        ____         ,'  _\\   ___    ___     ____\n");
printf("_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n");
printf("\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n");
printf(" \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n");
printf("   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n");
printf("    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n");
printf("     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n");
printf("      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n");
printf("       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n");
printf("        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n");
printf("                                `'                            '-._|\n");
printf("\n");

 printf("======================== GOTTA PWN 'EM ALL ========================\n\n");

 sleep(1);
 printf("A wild Laprus appeared! Capture it to get the flag.\n");
 sleep(1);

}

int main()
{
  setvbuf(stdout, 0, 2, 0);
  setvbuf(stdin, 0, 2, 0);
  alarm(180);

  banner();
  int choice;
  while(1)
    {
      status();
      menu();
      choice = readint();
      if (choice == 1)
	  attack();
      else if(choice == 2)
	items_menu();
      else if(choice == 3)
	pokeball();
    }
  return 1;
}
