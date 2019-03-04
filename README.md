# Signal Filter

Signal Filter is a [Processing](http://processing.org/) library for noisy signal filtering. It provides a convenient way to get rid of the noise in raw signals (like blob tracking coordinates for example).

Signal Filter is built upon the [OneEuroFilter](http://www.lifl.fr/~casiez/1euro/) by [Géry Casiez](http://www.lifl.fr/~casiez). It uses the Java implementation by [Stéphane Conversy](http://lii-enac.fr/~conversy/).

*Please report bugs and submit feature requests on the [issues](https://github.com/SableRaf/SignalFilter/issues) page.*


## About

“The 1€ ﬁlter (“one Euro ﬁlter”) is a simple algorithm to ﬁlter noisy signals for high precision and responsiveness. It uses a ﬁrst order low-pass ﬁlter with an adaptive cutoff frequency: at low speeds, a low cutoff stabilizes the signal by reducing jitter, but as speed increases, the cutoff is increased to reduce lag.” [Casiez 2012]

This library provides convenience functions to deal with the most common scenarios: single signal, coordinates (as PVector or individual floats), multiple channels. Look at the examples and the documentation for more information.

To learn more about the 1€ Filter algorithm, read the [CHI 2012 paper (PDF)](http://www.lifl.fr/~casiez/publications/CHI2012-casiez.pdf) by [Géry Casiez](http://www.lifl.fr/~casiez).

You can also try the [online demo](http://cristal.univ-lille.fr/~casiez/1euro/InteractiveDemo/) of the 1€ filter for a comparison with other filters.

## Download

* [SignalFilter.zip](http://s176381904.onlinehome.fr/processing/SignalFilter/download/SignalFilter.zip)

## Installation

Unzip and put the extracted *SignalFilter* folder into the libraries folder of your Processing sketch folder. Reference and examples are included in the *SignalFilter* folder.

## Usage

Import the library, create your filter, and apply it to your signal. Easy! Optionnally, you can get more control over the parameters (recommended). Look at the examples for instructions.

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

* [helloFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/helloFilter/helloFilter.pde)

* [basicFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/basicFilter/basicFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/basicFilter.jpg)

* [multiFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/multiFilter/multiFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/multiFilter.jpg)

* [coordFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/coordFilter/coordFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/coordFilter.jpg)



## Tested

Processing Versions:

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

## To Do

* Enable usage [outside of the draw() loop](https://github.com/SableRaf/signalfilter/issues/2)


## Questions?

Wanna chat? Ping me on [Twitter](https://twitter.com/sableRaph). For bug reports, please use the [issues](https://github.com/SableRaf/SignalFilter/issues) page.


## License

* The library is Open Source Software released under the [GNU General Public License](http://www.gnu.org/licenses/gpl.html). It is developed and maintained by [Raphaël de Courville](https://twitter.com/sableRaph).

*This README file was last updated on 2019-03-04*
