
/*
 * This example demonstrates the use of the filterUnitFloat() function
 * to filter a single signal (simulated here with Perlin noise)
 * 
 */

// Add the library to the sketch
import signal.library.*;

// -----------------------------------------------------
// Create the filter
   SignalFilter myFilter;
// -----------------------------------------------------

// Main OneEuroFilter parameters
float minCutoff = 0.05; // decrease this to get rid of slow speed jitter
float beta      = 4.0;  // increase this to get rid of high speed lag

float xPos = 0;

float sourceSignal, sourceSignalPrev;
float noisySignal, noisySignalPrev;
float filteredSignal, filteredSignalPrev;

void setup() {

  size(512, 512);
  
  background(180);

  // -----------------------------------------------------
  // Initialize the filter
     myFilter = new SignalFilter(this);
  // -----------------------------------------------------
}


void draw()
{
  
  // Pass the parameters to the filter
  myFilter.setMinCutoff(minCutoff);
  myFilter.setBeta(beta);
  
  // Save previous values (needed to draw the lines)
  sourceSignalPrev = sourceSignal;
  filteredSignalPrev = filteredSignal;
  noisySignalPrev = noisySignal;

  // Generate simulated signal based on Perlin noise
  sourceSignal = noise(frameCount / 250.0);
  noisySignal = sourceSignal + ( noise(frameCount) - 0.5 )  / 10.0;

  
  // -----------------------------------------------------
  // Filter the signal [THIS IS THE IMPORTANT LINE HERE!]
     filteredSignal = myFilter.filterUnitFloat( noisySignal );
  // -----------------------------------------------------
  
  

  float textBgHeight = 30;
  color textBgColor = color(150);

  // Compute x positions to draw the plot line
  float x1 = xPos-0.1;
  float x2 = xPos;  
  
  float sectionSize = height / 3.0;

  // Draw original signal
  noStroke();
  fill(255);
  float ySource1 = map(sourceSignalPrev, 0.0, 1.0, textBgHeight, sectionSize);
  float ySource2 = map(sourceSignal, 0.0, 1.0, textBgHeight, sectionSize);
  stroke(255);
  line(x1, ySource1, x2, ySource2);
  noStroke();
  fill(textBgColor);
  rect(0, 0, width, textBgHeight );
  fill(255);
  text( "Original signal", 10, 20 );

  // Draw noisy signal
  pushMatrix();
  translate(0, sectionSize);
  noStroke();
  fill(255);
  float yNoisy1 = map(noisySignalPrev, 0.0, 1.0, textBgHeight, sectionSize);
  float yNoisy2 = map(noisySignal, 0.0, 1.0, textBgHeight, sectionSize);
  stroke(255);
  line(x1, yNoisy1, x2, yNoisy2);
  noStroke();
  fill(textBgColor);
  rect(0, 0, width, textBgHeight );
  fill(255);
  text( "Noisy signal", 10, 20 );
  popMatrix();

  // Draw filtered signal
  pushMatrix();
  translate(0, sectionSize * 2.0);
  noStroke();
  float yFiltered1 = map(filteredSignalPrev, 0.0, 1.0, textBgHeight, sectionSize);
  float yFiltered2 = map(filteredSignal, 0.0, 1.0, textBgHeight, sectionSize);
  stroke(255);
  line(x1, yFiltered1, x2, yFiltered2);
  noStroke();
  fill(textBgColor);
  rect(0, 0, width, textBgHeight );
  fill(255);
  text( "Filtered signal", 10, 20 );
  popMatrix();


  // Output the values to the console for debug
  println( " " );
  println( "source   = " + sourceSignal );
  println( "noisy    = " + noisySignal );
  println( "filtered = " + filteredSignal );
  
  // Move the head to the right
  xPos += 1;

  // Wrap condition
  if ( xPos > width ) {
    background(180); // clear screen
    xPos = 0.0;      // move back to the left
  }
}
