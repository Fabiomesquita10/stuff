#include<semaphore.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<pthread.h>
#define TRUE 1
#define N 5 					/* number of philosophers */
#define LEFT (i+N-1)%N 				/* number of i's left neighbor */
#define RIGHT (i+1)%N 				/* number of i's right neighbor */
#define THINKING 0 				/* philosopher is thinking */
#define HUNGRY 1 				/* philosopher is trying to get forks */
#define EATING 2 				/* philosopher is eating */


int state[N]; 					/* array to keep track of everyone's state */
pthread_mutex_t mutex;				/* mutual exclusion for critical regions */
sem_t s[N];

void print_state()
{
	int i;
	for(i=0;i<N;i++){
		if(state[i]==0)
			printf("%d is thinking\t",i);
		else{
			if(state[i]==1)
				printf("%d is hungry\t",i);
			else
				printf("%d is eating\t",i);
		}
	}

	printf("\n");
}

void test(int i) 					/* i: philosopher number, from 0 to N-1 */
{
	if (state[i] == HUNGRY && state[LEFT] != EATING && state[RIGHT] != EATING) {
		state[i] = EATING;
		sem_post(&s[i]);
	}
}

void take3forks(int i) 				/* i: philosopher number, from 0 to N-1 */
{
	pthread_mutex_lock(&mutex); 		/* enter critical region */
	state[i] = HUNGRY; 		        /* record fact that philosopher i is hungry */
	test(i); 				/* try to acquire 2 forks */
	pthread_mutex_unlock(&mutex); 		/* exit critical region */
	sem_wait(&s[i]);
}

void put3forks(int i) 				/* i: philosopher number, from 0 to N-1 */
{
	pthread_mutex_lock(&mutex); 		/* enter critical region */
	state[i] = THINKING; 			/* philosopher has finished eating */
	test(LEFT); 				/* see if left neighbor can now eat */
	test(RIGHT); 				/* see if right neighbor can now eat */
	pthread_mutex_unlock(&mutex); 		/* exit critical region */
}

void eat()					/* philosopher will be eating between 1 and 5 seconds */
{
	int j;	
	j=1+(int) (5.0*rand()/(RAND_MAX+1.0));
	sleep(j);	
}

void think()				/* philosopher will be thinking between 1 and 5 seconds */
{
	int j;	
	j=1+(int) (5.0*rand()/(RAND_MAX+1.0));
	sleep(j);	
}

void *philosopher_thread(void *number) 		/* i: philosopher number, from 0 to N-1 */
{
	long i;
	i=(long)number;

	while (TRUE) { 				/* repeat forever */
		take3forks(i); 			/* acquire two forks or block */
		eat(); 				/* yum-yum, spaghetti */
		put3forks(i); 			/* put both forks back on table */
		think(); 			/* philosopher is thinking */
	}
}

int main()
{
	long i;
	pthread_t t[N];

	pthread_mutex_init(&mutex,NULL);

	printf("Starting the philosophers...\n");
	
	for(i=0;i<N;i++)
		sem_init(&s[i],0,0);



	for(i=0;i<N;i++)
		pthread_create(&t[i],NULL,philosopher_thread,(void *)i);
	
       
	while(TRUE){
	       pthread_mutex_lock(&mutex);
	       print_state();
	       pthread_mutex_unlock(&mutex);
	       fflush(stdout);
	       sleep(3);
	}
	
}















