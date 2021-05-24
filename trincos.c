#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

pthread_mutex_t mutex_ping = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_pong = PTHREAD_MUTEX_INITIALIZER;


void* ping(void* unused){

    while(1){
        pthread_mutex_lock(&mutex_ping); // abrir outra vez por seguranÃ§a
        printf("ping!\n");
        sleep(1);
        pthread_mutex_unlock(&mutex_pong);
    }
     return NULL;
}

void* pong(void* unused){
    while (1){   
        pthread_mutex_lock(&mutex_pong);
        printf("pong!\n");
        sleep(1);
        pthread_mutex_unlock(&mutex_ping);
    }
    return NULL;
}

int main(){

    pthread_t thread_id[2];
    int i;
    //trincos ficam abertos por default
    pthread_mutex_lock(&mutex_pong); // se tiverem os 2 fechados n funciona 
    pthread_create(&thread_id[0],NULL,&ping,NULL);
    pthread_create(&thread_id[1],NULL,&pong,NULL);

    pthread_exit(NULL);
    
    for(i = 0; i<2; i++){
       pthread_join(thread_id[i],NULL);
    }
    return 0;
}