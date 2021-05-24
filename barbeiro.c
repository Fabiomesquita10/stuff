/*
1 produtor = barbeiro
1 cadeira atendimento
N cadeira espera
*/
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <semaphore.h>
int N = 10;
sem_t cadeiraEsp, cadeiraBarb, corte;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
int nVagas = 10;

void *barbeiro(void *param){
	while(1){
		sem_wait(&cadeiraEsp);
		//sem_wait(&cadeiraBarb);
		printf("Comecei a cortar\n");
		sleep(2);
		sem_post(&cadeiraBarb);
		printf("acabei o corte\n");
		sem_post(&corte);
	}
}

void *cliente(void *param){
	while(1){
		int n = 0;
		pthread_mutex_lock(&mutex);
		if(nVagas > 0){
			nVagas--;
			n = 1;
		}
		pthread_mutex_unlock(&mutex);
		if(n==1){
			sem_post(&cadeiraEsp);    		  //+1 cadeira espera
			printf("Ha espera para cortar o cabelo\n");
			sem_wait(&cadeiraBarb);         //fica bloqueado que o barbeiro chame
			printf("cortando o cabelo\n");
			pthread_mutex_lock(&mutex);
			nVagas++;
			pthread_mutex_unlock(&mutex);
			sem_wait(&corte);
			printf("Acabei de cortar\n");
		}//else
			//printf("fui embora\n");
	}
}

int main(int argc, char const *argv[])
{
	pthread_t thread_id[20];

	sem_init(&cadeiraEsp, 0, 0);
	sem_init(&cadeiraBarb, 0, 1);
	sem_init(&corte, 0, 0);
	pthread_mutex_unlock(&mutex);

	pthread_create(&thread_id[0], NULL, &barbeiro, NULL);// barbeiro

	for (int i = 1; i < 20; ++i)
	{
		pthread_create(&thread_id[i], NULL, &cliente, NULL);// barbeiro
	}

	while(1){

	}

	return 0;
}