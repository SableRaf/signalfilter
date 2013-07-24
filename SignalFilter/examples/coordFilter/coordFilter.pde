
/*
 * This example demonstrates the use of the filterCoord2D() 
 * function to filter coordinates in a given space
 * 
 *
 */

// Add the library to the sketch
import signal.library.*;

// -----------------------------------------------------
// Create the filter
   SignalFilter myFilter;
// -----------------------------------------------------

// Parameters of the OneEuroFilter
float freq      = 120.0;
float minCutoff = 0.05; // decrease this to get rid of slow speed jitter
float beta      = 6.0;  // increase this to get rid of high speed lag
float dcutoff   = 1.0;

void setup() {
  
    size(512, 512);
    
    cursor(CROSS);
    
    // ------------------------------------------------------------------
    // Create a filter with 3 channels (you need 3 for PVector filtering)
       myFilter = new SignalFilter(this, 3);
    // ------------------------------------------------------------------
    
}

 
void draw()
{
  
  background(155);
  
  // Pass the parameters to the filter
  myFilter.setFrequency(freq);
  myFilter.setMinCutoff(minCutoff);
  myFilter.setBeta(beta);
  myFilter.setDerivateCutoff(dcutoff);  
  
  
  noFill();
  strokeWeight(5);
  
  float noiseX = mouseX + random(-5,+5);
  float noiseY = mouseY + random(-5,+5);
  
  PVector noiseCoord = new PVector(noiseX, noiseY);
  
  println("");
  println("noiseCoord    = " + noiseCoord);
  
  // Draw a small ellipse at noisy position
  noStroke();
  fill(100);
  ellipse(noiseX, noiseY, 15, 15);
    
  // -------------------------------------------------------------------------
  // Filter the coordinate values 
     PVector filteredCoord = myFilter.filterCoord2D( noiseCoord, width, height );
  // -------------------------------------------------------------------------
  
  println( "filteredCoord = " + filteredCoord );
  
  // Draw purple ellipse at filtered position
  strokeWeight(5);
  stroke(200); // Purple
  noFill();
  ellipse(filteredCoord.x, filteredCoord.y, 45, 45); 
  
}
