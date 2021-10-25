
typedef struct
{
  GLboolean doubleBuffer;
  GLint delay;
} Estado;

typedef struct
{
  GLint hor, min, seg;
} Horas;

typedef struct
{
  GLint numLados;
  GLfloat raio;
  GLfloat tamLado;
  Horas hora;
} Modelo;

typedef struct
{
  float x;
  float y;
}Point;

Estado estado;
Modelo modelo;
Point p;
BOOLEAN p_bool;


void init(void)
{

  struct tm *current_time;
  time_t timer = time(0);

  /* Delay para o timer */
  estado.delay = 1000;

  modelo.tamLado = 1;
  modelo.numLados = 60;
  modelo.raio = 0.75;

  /* Ler hora do Sistema */
  current_time = localtime(&timer);
  modelo.hora.hor = current_time->tm_hour;
  modelo.hora.min = current_time->tm_min;
  modelo.hora.seg = current_time->tm_sec;

  p_bool=FALSE;


  glClearColor(0.0, 0.0, 0.0, 0.0);
  

  glEnable(GL_POINT_SMOOTH);
  glEnable(GL_LINE_SMOOTH);
  glEnable(GL_POLYGON_SMOOTH);
}


void segundos(){
  //SEGUNDOS
  float angulo = ((360/60)*M_PI)/180;
  float angulo_aux=angulo*15;
  float x=0,y=0;
  float r_aux = (65*modelo.raio)/100;

  angulo_aux=angulo_aux-(modelo.hora.seg*angulo);
  x=(r_aux*cosf(angulo_aux))+p.x;
  y=(r_aux*sinf(angulo_aux))+p.y;

  glVertex2f(0.0f,0.0f);
  glVertex2f(x,y);
}

void minutos(){
  //MINUTOS
  float angulo = ((360/60)*M_PI)/180;
  float angulo_aux=angulo*15;
  float x=0,y=0;
  float r_aux = (45*modelo.raio)/100;

  angulo_aux=angulo_aux-(modelo.hora.min*angulo);
  x=(r_aux*cosf(angulo_aux))+p.x;
  y=(r_aux*sinf(angulo_aux))+p.y;

  glVertex2f(0.0f,0.0f);
  glVertex2f(x,y);

}

void horas(){
  //HORAS
  //hora =24
  if(modelo.hora.hor>12)modelo.hora.hor-=12;
  float angulo = ((360/12)*M_PI)/180;
  float angulo_aux=angulo*3;
  float x=0,y=0;
  float r_aux = (25*modelo.raio)/100;
 
  angulo_aux=angulo_aux-(modelo.hora.hor*angulo);
  x=(r_aux*cosf(angulo_aux))+p.x;
  y=(r_aux*sinf(angulo_aux))+p.y;


  glVertex2f(0.0f,0.0f);
  glVertex2f(x,y);
  }

void centro_relogio(){
  float angulo = ((360/modelo.numLados)*M_PI)/180;
  float angulo_aux=angulo;

  for(int i=0;i<modelo.numLados;i++){
    float x=((0.1*modelo.raio)*cosf(angulo_aux))+p.x;
    float y=((0.1*modelo.raio)*sinf(angulo_aux))+p.y;
    
    glVertex2f(x,y);
    angulo_aux+=angulo;

  }
}

void ponteiros(){

  glLineWidth(10);
  glBegin(GL_LINES);
  glColor3f(0.79f,0.52f,0.21f);
  horas();
  glEnd();

  glLineWidth(6);
  glBegin(GL_LINES);
  glColor3f(0.81f,0.5f,0.2f);
  minutos();
  glEnd();

  glLineWidth(3);
  glBegin(GL_LINES);
  glColor3f(0.8f,0.498039f,0.196078f);
  segundos();
  glEnd();

  glBegin(GL_POLYGON);
  glColor3f(0.7,0.7,0.7);
  centro_relogio();
  glEnd();
}

void mostrador(){
  glLineWidth(2);
  glBegin(GL_LINES);
  float angulo = ((360/modelo.numLados)*M_PI)/180;
  float angulo_aux=angulo;
  //glColor4f(1.0f,0.5f,0.0f,0.0f);
  glColor3f(0.5f,0.5f,0.5f);
  int raio=modelo.raio;
  float x1,y1;
  for(int i=1;i<=60;i++){
      if((i)%5==0){
        x1=(((modelo.raio*80)/100)*cosf(angulo_aux))+p.x;
        y1=(((modelo.raio*80)/100)*sinf(angulo_aux))+p.y;
      }else{
        x1=(((modelo.raio*90)/100)*cosf(angulo_aux))+p.x;
        y1=(((modelo.raio*90)/100)*sinf(angulo_aux))+p.y; 
      }
      float x=(modelo.raio*cosf(angulo_aux))+p.x;
      float y=(modelo.raio*sinf(angulo_aux))+p.y;
      glVertex2f(x,y);
      glVertex2f(x1,y1);
      angulo_aux+=angulo;
    }
    glEnd();
}

void poligono(){

  glBegin(GL_POLYGON);
  float angulo = ((360/modelo.numLados)*M_PI)/180;
  float angulo_aux=angulo;
  glColor3f(0.3f,0.3f,0.3f);
    for(int i=0;i<modelo.numLados;i++){
      float x=(modelo.raio*cosf(angulo_aux))+p.x;
      float y=(modelo.raio*sinf(angulo_aux))+p.y;
      
     
      glVertex3f(x,y,0);
      
      angulo_aux+=angulo;

  }
  glEnd();
}



/* Callback de desenho */
void draw(void)
{
  glClear(GL_COLOR_BUFFER_BIT);
  
  /* Espaço para chamada das rotinas auxiliares de desenho ... */

  //relogio - Circunferencia
  
  poligono();
  //traços do relogio
 
  mostrador();

  //Ponteiros 
  ponteiros();//horas,minutos,segundos

  glFlush();

  if (estado.doubleBuffer)
    glutSwapBuffers();
}

void timer(int value)
{
  glutTimerFunc(estado.delay, timer, 0);
   /* Acções do temporizador ...
     Não colocar aqui primitivas OpenGL de desenho glBegin, glEnd, etc.
     Simplesmente alterar os valores de modelo.hora.hor, modelo.hora.min e modelo.hora.seg */

  /* Redesenhar o ecrã (invoca o callback de desenho) */

  modelo.hora.seg++;
  if (modelo.hora.seg > 59) {
        modelo.hora.min++;
        modelo.hora.seg = 0;
  }
  if (modelo.hora.min > 59) {
    modelo.hora.hor++;
    modelo.hora.min = 0;
  }
  if (modelo.hora.hor > 23) {
    modelo.hora.hor = 0;
  }

  if(p_bool==TRUE)print_horas();    
 
  glutPostRedisplay();
}

int main(int argc, char **argv)
{
  estado.doubleBuffer = GL_FALSE; // Colocar GL_TRUE para ligar o Double Buffer
 
  modelo.raio=1.0f;
  modelo.numLados=60;

  p.x=0.0f;
  p.y=0.0f;

  glutInit(&argc, argv);
  glutInitWindowPosition(0, 0);
  glutInitWindowSize(500, 500);
  glutInitDisplayMode(((estado.doubleBuffer) ? GLUT_DOUBLE : GLUT_SINGLE) | GLUT_RGB);
  if (glutCreateWindow("Exemplo") == GL_FALSE)
    exit(1);

  init();

  imprime_ajuda();

  /* Registar callbacks do GLUT */

  /* callbacks de janelas/desenho */
  glutReshapeFunc(reshape);
  glutDisplayFunc(draw);

  /* Callbacks de teclado */
  glutKeyboardFunc(key);
  //glutKeyboardUpFunc(keyUp);
  //glutSpecialFunc(specialKey);
  //glutSpecialUpFunc(specialKeyUp);

  /* Callbacks timer/idle */
  glutTimerFunc(estado.delay, timer, 0);
  glutIdleFunc(idle);

  /* Começar loop */
  glutMainLoop();

  return 0;
}

///// cilindro
static void display(void)
{    
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    
    glColor3d(1,0,0); 

    glDisable(GL_CULL_FACE);

    glPushMatrix();
        glTranslated(0.0,1.2,-6);
        glRotated(45.0,45.0,45.0,45.0);

        GLUquadricObj* qobj = gluNewQuadric();
        gluQuadricDrawStyle(qobj,GLU_FILL);
        gluQuadricNormals(qobj,GLU_SMOOTH);
        gluCylinder(qobj,0.5,0.5,2.0,50,60);
        gluDeleteQuadric(qobj);
    glPopMatrix(); 

    glPushMatrix();
        glTranslated(0.0,-1.0,-6);
        glRotated(45.0,45.0,45.0,45.0);

        qobj = gluNewQuadric();
        gluQuadricDrawStyle(qobj,GLU_LINE);
        //gluQuadricNormals(qobj,GLU_SMOOTH);
        gluCylinder(qobj,0.5,0.5,2.0,16,32);
        gluDeleteQuadric(qobj);
    glPopMatrix(); 
 
    glutSwapBuffers();
} 
 

 ////cone 
 static void display(void)
{    
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    
    glColor3d(1,0,0); 
 
    glPushMatrix();
        glTranslated(0.0,1.2,-6);
        glRotated(45.0,45.0,45.0,45.0);
        glutSolidCone(1,2,50,50);
    glPopMatrix(); 
 
    glPushMatrix();
        glTranslated(0.0,-1.2,-6);
        glRotated(45.0,45.0,45.0,45.0);
        glutWireCone(1,2,16,16);
    glPopMatrix(); 
 
    glutSwapBuffers();
} 