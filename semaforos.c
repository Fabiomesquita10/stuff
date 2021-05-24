#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <semaphore.h>

//MAC
//tatic const char * semnameping = "pingsem";
//static const char * semnamepong = "pongsem";

//semaphore_t pingsem;
//semaphore_t pongsem;

//LINUX
sem_t pingsem,pongsem;


void* ping(void* unused){

    while(1){
        sem_wait(&pingsem);
        printf("ping!\n");
        sleep(1);
        sem_post(&pongsem);
    }
     return NULL;
}

void* pong(void* unused){
    while (1){   
        sem_wait(&pongsem);
        printf("pong!\n");
        sleep(1);
        sem_post(&pingsem);
       
    }
    return NULL;
}

int main(){

    pthread_t thread_id[2];
    int i;
    
    sem_init(&pingsem,0,1);
    sem_init(&pongsem,0,0);
 
    pthread_create(&thread_id[0],NULL,&ping,NULL);
    pthread_create(&thread_id[1],NULL,&pong,NULL);

    pthread_exit(NULL);
    
    for(i = 0; i<2; i++){
       pthread_join(thread_id[i],NULL);
    }
    return 0;
}