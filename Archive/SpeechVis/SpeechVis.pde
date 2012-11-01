/**
 * Get Line In
 * by Damien Di Fede.
 *  
 * This sketch demonstrates how to use the <code>getLineIn</code> method of 
 * <code>Minim</code>. This method returns an <code>AudioInput</code> object. 
 * An <code>AudioInput</code> represents a connection to the computer's current 
 * record source (usually the line-in) and is used to monitor audio coming 
 * from an external source. There are five versions of <code>getLineIn</code>:
 * <pre>
 * getLineIn()
 * getLineIn(int type) 
 * getLineIn(int type, int bufferSize) 
 * getLineIn(int type, int bufferSize, float sampleRate) 
 * getLineIn(int type, int bufferSize, float sampleRate, int bitDepth)  
 * </pre>
 * The value you can use for <code>type</code> is either <code>Minim.MONO</code> 
 * or <code>Minim.STEREO</code>. <code>bufferSize</code> specifies how large 
 * you want the sample buffer to be, <code>sampleRate</code> specifies the 
 * sample rate you want to monitor at, and <code>bitDepth</code> specifies what 
 * bit depth you want to monitor at. <code>type</code> defaults to <code>Minim.STEREO</code>,
 * <code>bufferSize</code> defaults to 1024, <code>sampleRate</code> defaults to 
 * 44100, and <code>bitDepth</code> defaults to 16. If an <code>AudioInput</code> 
 * cannot be created with the properties you request, <code>Minim</code> will report 
 * an error and return <code>null</code>.
 * 
 * When you run your sketch as an applet you will need to sign it in order to get an input. 
 * 
 * Before you exit your sketch make sure you call the <code>close</code> method 
 * of any <code>AudioInput</code>'s you have received from <code>getLineIn</code>.
 */

import ddf.minim.*;

Minim minim;
AudioInput in;

int bufferSize = 512;

int swidth = 1024;
int sheight = 500;

int rootFrame = 0;

void setup()
{
  size(swidth, sheight);
  colorMode(HSB,100);

  minim = new Minim(this);
  minim.debugOn();
  
  // get a line in from Minim, default bit depth is 16
  in = minim.getLineIn(Minim.STEREO, bufferSize);
  
  
}

void draw()
{
  rootFrame++;
  float rateOfRandomColorChange = 0.05f;
  background( (rootFrame*rateOfRandomColorChange)%100, 80,50);
  stroke(0,0,100);
  
  pushMatrix();
  // draw the waveforms
  for(int i = 0; i < bufferSize-1; i++)
  {
    int y1 = sheight*1/4;
    int y2 = sheight*3/4;
    int xgap = (int)(swidth*1.0/bufferSize);
    int peakHeight = sheight*1/4;
    line(i*xgap, y1 + in.left.get(i)*peakHeight, (i+1)*xgap, y1 + in.left.get(i+1)*peakHeight);
    line(i*xgap, y2 + in.right.get(i)*peakHeight, (i+1)*xgap, y2 + in.right.get(i+1)*peakHeight);
    
  }
  popMatrix();
}


void stop()
{
  // always close Minim audio classes when you are done with them
  in.close();
  minim.stop();
  
  super.stop();
}
