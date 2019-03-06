#include <iostream>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <pwd.h>
#include <string>

using namespace std;

int main(int argc, char** argv){
    struct passwd *result = getpwnam("root");

    if (setuid(result->pw_uid) !=0 ){
        perror("Error");
    }

    string fileName(argv[1]);

    string command = "chown root 'includes/"+fileName+"'";
    int status = system(command.c_str());
    
    if(status != 0){
        perror("Error chown");
        return -1;
    }

    cout<<"OK"<<endl;
    return 0;
}