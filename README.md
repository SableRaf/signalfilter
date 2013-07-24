# Signal Filter (beta)

A [Processing](http://processing.org/) API for signal filtering by [Raphaël de Courville](https://vimeo.com/sableraf/) ([@sableRaph](https://twitter.com/sableRaph)).

We often have to deal with data from noisy sources like blob tracking or raw sensors. Signal Filter provides a convenient way to smooth the noise from those sources with easy to tweak parameters to accomodate various types of signals.

Signal Filter is built upon the [OneEuroFilter](http://www.lifl.fr/~casiez/1euro/) by [Géry Casiez](http://www.lifl.fr/~casiez). It uses the Java implementation by [Stéphane Conversy](http://lii-enac.fr/~conversy/) from [Université de Toulouse](http://www.univ-toulouse.fr/).

*Note: Signal Filter is considered beta and should not be used for production. Please report bugs and submit feature requests on the [issues](https://github.com/SableRaf/SignalFilter/issues) page.*


## About

The library provides different functions to deal with the most common scenarios: single signal, coordinates (as PVector of floats), multiple channels. Look at the examples and the documentation for more information.

To learn more about the filtering algorithm behind Signal Filter, read the [CHI 2012 paper (PDF)](http://www.lifl.fr/~casiez/publications/CHI2012-casiez.pdf) by [Géry Casiez](http://www.lifl.fr/~casiez). 

You can also try the [online version](http://oin.name/1eurofilter/) of the 1€ filter by [Jonathan Aceituno](http://p.oin.name/).

## Download

* [SignalFilter.zip](http://s176381904.onlinehome.fr/processing/SignalFilter/download/SignalFilter.zip)

## Installation

Unzip and put the extracted *SignalFilter* folder into the libraries folder of your Processing sketch folder. Reference and examples are included in the *SignalFilter* folder.


## Examples

* [basicFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/basicFilter/basicFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/basicFilter.jpg)

* [multiFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/multiFilter/multiFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/multiFilter.jpg)

* [coordFilter](https://github.com/SableRaf/signalfilter/tree/master/SignalFilter/examples/coordFilter/coordFilter.pde)

![Snapshots](https://raw.github.com/SableRaf/signalfilter/master/ressources/coordFilter.jpg)



## Tested

Systems: OSX 

*Signal Filter should theoretically work on every platform. 
If you try it on Windows or Linux, please let me know using the [issues](https://github.com/SableRaf/SignalFilter/issues) page.*

Processing Version: 2.0

## Dependencies

None.



## License

* The library is Open Source Software released under the [GNU General Public License](http://www.gnu.org/licenses/gpl.html). It's developed and maintained by [Raphaël de Courville](https://vimeo.com/sableraf/).

*This README file was last updated on 2013-07-24 by Raphaël de Courville.*