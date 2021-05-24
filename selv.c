#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <semaphore.h>

int Nporcoes = 0;
int N = 10;
pthread_mutex_t mPor = PTHREAD_MUTEX_INITIALIZER;

sem_t porcoes_cald, cozinhar, tensCozinhar;

void *cozinheiro(void *param){
	while(1){
		sem_wait(&cozinhar);
		//if(Nporcoes == 0){
		pthread_mutex_lock(&mPor);
		printf("Enchi o caldeirao\n");
		sleep(2);	
		Nporcoes = N;
		pthread_mutex_unlock(&mPor);
		for (int i = 0; i < 10; ++i)
		{
			sem_post(&porcoes_cald);
		}
		sem_post(&tensCozinhar);
		//}
	}
}

void *selvagem(void *param){
	while(1){
		int n = 0;
		pthread_mutex_lock(&mPor);
		if(Nporcoes>0){
			//Nporcoes--;
			n=1;
		}
		pthread_mutex_unlock(&mPor);
		if(n == 1){
			sem_wait(&porcoes_cald);//bloqueado
			printf("Estou comendo\n");
			sleep(1);
			pthread_mutex_lock(&mPor);
			Nporcoes--;
			pthread_mutex_unlock(&mPor);
		}else{
			sem_wait(&tensCozinhar);
			printf("perciso de comida\n");
			sem_post(&cozinhar);
		}
	}
}

int main(int argc, char const *argv[])
{
	pthread_t thread_id[11];

	sem_init(&porcoes_cald, 0, 0);
	sem_init(&tensCozinhar, 0, 1);
	sem_init(&cozinhar, 0, 0);
	pthread_mutex_unlock(&mPor);

	pthread_create(&thread_id[0], NULL, &cozinheiro, NULL);// barbeiro

	for (int i = 1; i < 10; ++i)
	{
		pthread_create(&thread_id[i], NULL, &selvagem, NULL);// barbeiro
	}

	while(1){

	}

	return 0;
}