
// Add the library to the sketch
import signal.library.*;

// -----------------------------------------------------
// Create the filter
   SignalFilter myFilter;
// -----------------------------------------------------

// Variables for the dummy & filtered signal
float sourceSignal;
float noisySignal;
float filteredSignal;

void setup() {
  
  // -----------------------------------------------------
  // Initialize the filter
     myFilter = new SignalFilter(this);
  // -----------------------------------------------------

  
}


void draw()
{

  // Generate a dummy signal
  sourceSignal = sin(frameCount / 1000.0);
  
  // Add random noise to our dummy signal
  noisySignal = sourceSignal + random(-0.05, 0.05);
  
  // -----------------------------------------------------
  // Filter the signal
     filteredSignal = myFilter.filterUnitFloat( noisySignal );
  // -----------------------------------------------------
  
  // Display the results in the console
  println("");
  println("Source   = " + sourceSignal);
  println("Noisy    = " + noisySignal);
  println("Filtered = " + filteredSignal);
  
}
