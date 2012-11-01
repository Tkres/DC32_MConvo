// Sweep
// by BARRAGAN <http://barraganstudio.com> 
// This example code is in the public domain.


#include <Servo.h> 
Servo servoEight; 
Servo servoNine;  
Servo servoTen; 
Servo servoEleven; 


int pos = 0;    // variable to store the servo position 
void setup() 
{ 
  servoEight.attach(8);
  servoNine.attach(9);  
  servoTen.attach(10);
  servoEleven.attach(11);

  Serial.begin(9600);

} 

/*
overall just need to send case commands, it should reset regardless.
 */

int currentFace = 0;
void loop() 
{ 

  if (Serial.available()>0) {

    char ch = Serial.read();
    switch(ch) {
    case '1':
      // reset current face
      returnFace();
      // move to current face
      faceEight();
      stopAll();
      currentFace = 8;
      break;
    case '2':
      returnFace();
      faceNine();
      stopAll();
      currentFace = 9;
      break;
    case '3':
      returnFace();
      faceTen();
      stopAll();
      currentFace = 10;
      break;
    case '4':
      returnFace();
      faceEleven();
      stopAll();
      currentFace = 11;
      break;
    }
    
    case 's':
      // immediate stop function
      stopAll();
      break;
  }
} 

void returnFace() {
  if (currentFace == 0) {
    return;
  }
  if (currentFace == 8) {
    returnEight();
  }
  if (currentFace == 9) {
    returnNine();
  }
  if (currentFace == 10) {
    returnTen();
  }
  if (currentFace == 11) {
    returnEleven();
  }
}

//<<<<<<<Face Eight>>>>>>>>>
void faceEight() {
  servoNine.write(69.5);
  servoTen.write(80);
  delay(2000); 
}
void returnEight(){
  servoNine.write(102);
  servoTen.write(105);
  delay(2000);
}
//<<<<<<<Face Nine>>>>>>>>>>>>>
void faceNine() {
  servoEight.write(68.5);
  servoEleven.write(100);
  delay(2000);
}
void returnNine(){
  servoEight.write(103.5);
  servoEleven.write(81);
  delay(2000);

}
//<<<<<<Face Ten>>>>>>>>>>>>>>>
void faceTen() {
  servoEight.write(80);
  servoEleven.write(110);
  delay(2000); 
}
void returnTen(){
  servoEight.write(102);
  servoEleven.write(75.3);
  delay(2000); 
}
//<<<<<<<Face Eleven>>>>>>>>>>>
void faceEleven() {
  servoNine.write(77);
  servoTen.write(69);
  delay(2000); 
}
void returnEleven(){
  servoNine.write(101);
  servoTen.write(115);
  delay(2000); 
}
void stopAll(){
  servoEight.write(90);
  servoNine.write(90);
  servoTen.write(90);
  servoEleven.write(90);
  //delay(2000);
}








