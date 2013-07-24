
/*
 * This example demonstrates the use of the filterUnitArray() 
 * function to deal with any number of signals. 
 * 
 * Dark grey circles   -> Source signals (simulated using Perlin noise) 
 * Jittery circles     -> Noisy signals
 * Small white circles -> Filtered signals 
 * 
 */

import signal.library.*;

// -----------------------------------------------------
// Create the filters
   SignalFilter myFilters;
// -----------------------------------------------------

// Main OneEuroFilter parameters
float minCutoff = 0.5;  // decrease this to get rid of slow speed jitter
float beta      = 13.0; // increase this to get rid of high speed lag

// How many parallel signals do we want?
int numChannels = 15;

// Edit this to simulate different types 
// of signal/noise combinations
float signalSpeed = 3.0;
float noiseStrenght = 0.08;
float noiseSpeed = 0.8;

// Signal channels
float[] sourceSignals;
float[] noisySignals;
float[] filteredSignals;

// Used to randomize the noise channels
float[] noiseOffsets;

void setup() {

  size(512, 512);
  
  noFill();
  
  // Initialize the signal arrays
  sourceSignals   = new float[numChannels]; // Simulated signals based on Perlin noise
  noisySignals    = new float[numChannels]; // Simulated signals with added high frequency noise
  filteredSignals = new float[numChannels]; // The filtered signals to be generated 

  // -----------------------------------------------------
  // Initialize the filters
     myFilters = new SignalFilter(this, numChannels);
  // -----------------------------------------------------
  
  
  // Randomize the position of each simulated signal on the noise spectrum
  noiseOffsets = new float[numChannels];
  for(int i = 0; i < numChannels; i++) { 
    noiseOffsets[i] = random(1000000);
  }

  
}


void draw()
{

  background(155);
  
  // Pass the parameters to the filter
  myFilters.setMinCutoff(minCutoff);
  myFilters.setBeta(beta);
  
  // Create the individual dummy signals (don't worry too much about those lines)
  for(int currentChannel = 0; currentChannel < numChannels; currentChannel++) { 
    sourceSignals[currentChannel] = noise( ( frameCount + noiseOffsets[currentChannel] )  / 500.0 * signalSpeed );
    noisySignals[currentChannel] = sourceSignals[currentChannel] + ( noise(frameCount * noiseSpeed + (float)currentChannel) - 0.5 )  * noiseStrenght;
  }

  
  // -----------------------------------------------------------
  // Filter the signals array [THIS IS THE IMPORTANT LINE HERE!]
     filteredSignals = myFilters.filterUnitArray( noisySignals );
  // -----------------------------------------------------------


  // How large should each element be?
  float w = width/(float)numChannels;

  // Draw rectangles for each signal
  for(int currentChannel = 0; currentChannel < numChannels; currentChannel++) {
    
    float x = w * (float)currentChannel;
    float ySource   = height - ( sourceSignals[currentChannel] * height );
    float yNoisy    = height - ( noisySignals[currentChannel] * height );
    float yFiltered = height - ( filteredSignals[currentChannel] * height );
    
    noStroke();
    
    // Noisy signal
    fill(130);
    ellipse(x + w/2, yNoisy, w, w);
    
    // Original signal
    fill(100);
    ellipse(x + w/2, ySource, w, w);
    
    // Filtered signal
    fill(200);
    ellipse(x + w/2, yFiltered, w * 0.6, w * 0.6);
    
  }


}
