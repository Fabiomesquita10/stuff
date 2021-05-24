#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <semaphore.h>

int lugaresDisp = 5, pessoasSairam = 0;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
sem_t bancosDisp;

void *gordo(void *param){
	while(1){
		int n = 0;
		pthread_mutex_lock(&mutex);
		if(lugaresDisp>0){
			lugaresDisp--;
			n = 1;
		}
		pthread_mutex_unlock(&mutex);
		if(n == 1){
			sem_wait(&bancosDisp); //bloqueia se nao tiver banco disponivel na mesa
			printf("sentei me\n");
			sleep(2); //comer
			printf("vou embora\n");
			pthread_mutex_lock(&mutex);
			pessoasSairam++;
			pthread_mutex_unlock(&mutex);
			if(pessoasSairam == 5){
				printf("Mesa vazia\n");
				sleep(1);	
				for (int i = 0; i < 5; ++i)
				{
					pthread_mutex_lock(&mutex);
					lugaresDisp++;
					pessoasSairam = 0;
					pthread_mutex_unlock(&mutex);
					sem_post(&bancosDisp);
					
				}
			}
		}
	}
}


int main(int argc, char const *argv[])
{
	pthread_t thread_id[10];

	sem_init(&bancosDisp, 0, 5);
	pthread_mutex_unlock(&mutex);

	for (int i = 1; i < 10; ++i)
	{
		pthread_create(&thread_id[i], NULL, &gordo, NULL);// barbeiro
	}

	while(1){

	}

	return 0;
}