#include<semaphore.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <string.h>
#include <math.h>
#include <sys/time.h>
#include <math.h>

#define N 10                    
pthread_mutex_t trinco_p=PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t trinco_c=PTHREAD_MUTEX_INITIALIZER; 

sem_t pode_p;
sem_t pode_c;

typedef struct thread_data
{
    int i;
}TH_DATA;
  
int buffer[N];
int Prod_ptr=0,Cons_ptr=0; 
   
int produz(){
    srand((int)pthread_self());
    int ret = random() % 100;
    printf("produzi:%d\n",ret);
    sleep(1);
    return ret;
}


void consome(int i){
    sleep(1);
    printf("Consumi:%d\n",i);
}
void *produzir(void * params)
{
    while (1)
    {
        int item =produz();
        sem_wait(&pode_p);//esta preso a espera que possa produzir
        pthread_mutex_lock(&trinco_p);
        buffer[Prod_ptr]=item;
        Prod_ptr=(Prod_ptr+1) % N;
        pthread_mutex_unlock(&trinco_p);
        sem_post(&pode_c);//avisa o consumidor que pode consumir
    }
    pthread_exit(NULL);
}
  
 
void *consumir(void * params)
{
    while (1)
    {
        int item ;
        sem_wait(&pode_c);//esta preso a espera que haja algo para consumir
        pthread_mutex_lock(&trinco_c);
        item=buffer[Cons_ptr];
        buffer[Cons_ptr] = 0;
        Cons_ptr= (Cons_ptr+1) % N;
        pthread_mutex_unlock(&trinco_c);
        sem_post(&pode_p);//avisa o produto que pode consumir

        consome(item);
    }
    pthread_exit(NULL);
}
void *print_state(void * unused)
{
   while (1)
   {
       printf("BUFFER:");
       pthread_mutex_lock(&trinco_c);
       pthread_mutex_lock(&trinco_p);
       for (int i = 0; i < N; i++)
       {
           printf("%d|",buffer[i]);
       }
        printf("\n");
       pthread_mutex_unlock(&trinco_c);
       pthread_mutex_unlock(&trinco_p);
       sleep(1);
   }
   
}



int main(int argc,char * argv[]){
    pthread_t tid[atoi(argv[1])+atoi(argv[2])+1];

    int n_prod=atoi(argv[1]);
    int n_cons=atoi(argv[2]);


    sem_init(&pode_p,0,N);
    sem_init(&pode_c,0,0);


    TH_DATA produtores[n_prod];
    TH_DATA consumidores[n_cons];

    for (int i = 0; i < n_prod; i++)
    {
        produtores[i].i=i;
        pthread_create(&tid[i],NULL,&produzir,&produtores[i]);//deviamos corrigir o estado se fizermos pthreadjoin
    }

     for (int i = 0; i < n_cons; i++)
    {
        consumidores[i].i=i;
        pthread_create(&tid[atoi(argv[1])+i],NULL,&consumir,&consumidores[i]);//deviamos corrigir o estado se fizermos pthreadjoin
    }   

    pthread_create(&tid[atoi(argv[1])+atoi(argv[2])+1],NULL,&print_state,NULL);
    pthread_exit(NULL);
}