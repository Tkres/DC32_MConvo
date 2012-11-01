import processing.serial.Serial;

Serial serialPort;

boolean turntableControlOn = true;

void setup() {

  println(Serial.list()); // <- Print list to find usb of arduino
  if (turntableControlOn) {
    serialPort = new Serial(this, Serial.list()[4], 9600); //<- [number] is arduino usb number 
  }
}

void draw() {
}



void keyPressed() {
  if (turntableControlOn) {

    for (int i=1; i<=4; i++) {
      char num = (char)('0'+i);
      if (key == num) {
        serialPort.write(num);
      }
    }

    if (key=='s') {
      // a (not used) immediate stop-key for emergency purposes.
      serialPort.write('s');
    }
  }
}

