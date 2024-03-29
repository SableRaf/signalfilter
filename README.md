# Signal Filter

The Signal Filter [Processing](http://processing.org/) library is a simple way to turn noisy raw data–such as a tracker's position or your microphone volume for example–into a signal that changes smoothly over time.

Signal Filter is built upon the [OneEuroFilter](https://gery.casiez.net/1euro/) by [Géry Casiez](https://gery.casiez.net) which is a lightweight alternative to common filtering algorithms. Signal Filter wraps the Java implementation of the 1€ filter by [Stéphane Conversy](http://recherche.enac.fr/~conversy/).

*Please report bugs and submit feature requests on the [issues](https://github.com/SableRaf/SignalFilter/issues) page.*


## About

“The 1€ ﬁlter (“one Euro ﬁlter”) is a simple algorithm to ﬁlter noisy signals for high precision and responsiveness. It uses a ﬁrst order low-pass ﬁlter with an adaptive cutoff frequency: at low speeds, a low cutoff stabilizes the signal by reducing jitter, but as speed increases, the cutoff is increased to reduce lag.” [Casiez 2012]

This library provides convenience functions to deal with the most common scenarios: single signal, coordinates (as PVector or individual floats), multiple channels. Look at the examples and the documentation for more information.

To learn more about the 1€ Filter algorithm, read the [CHI 2012 paper (PDF)](https://hal.inria.fr/hal-00670496/document) by [Géry Casiez](https://gery.casiez.net).

You can also try the [online demo](https://gery.casiez.net/1euro/InteractiveDemo/) of the 1€ filter for a comparison with other filters.


## Installation (PDE)

To install the Signal Filter library in the Processing Development Environment, go to `tools > Manage Tools...` and find the `Libraries` tab. Search for "Signal Filter" and click `install`.

## Usage

Import the library, create your filter, and apply it to your signal. You can get more control over the parameters (look at comments in the examples for instructions).

```java


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

```


## Examples

After installing the Signal Filter library, you will find the following examples in `File > Examples... > Contributed Libraries > Signal Filter`

* [helloFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/helloFilter/helloFilter.pde)

* [basicFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/basicFilter/basicFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/basicFilter.jpg)

* [multiFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/multiFilter/multiFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/multiFilter.jpg)

* [coordFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/coordFilter/coordFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/coordFilter.jpg)



## Tested

Processing Versions:

* 4.0.1
* 3.5.3
* 3.4
* 2.0.1
* 2.0
* 2.0b9
* 2.0b8
* 2.0b7
* 1.5.1


## Dependencies

None.

## Questions?

Wanna chat? Ping me on [Twitter](https://twitter.com/sableRaph). For bug reports, please use the [issues](https://github.com/SableRaf/SignalFilter/issues) page.


## License

* The library is Open Source Software released under the [GNU General Public License](http://www.gnu.org/licenses/gpl.html). It is developed and maintained by [Raphaël de Courville](https://twitter.com/sableRaph).

*This README file was last updated on 2020-11-26*
