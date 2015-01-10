/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package signal.library;


import processing.core.*;
import java.util.ArrayList;

/**
 * Processing implementation of the One Euro Filter [Casiez 2012]
 * http://www.lifl.fr/~casiez/publications/CHI2012-casiez.pdf
 * 
 * @example basicFilter 
 * 
 */

public class SignalFilter {
	
	// Default Parameters of the OneEuroFilter
	// They can be changed using the corresponding setters
    double freq       = 120.0;
    double mincutoff  = 3.0;
    double beta_      = 0.007;
    double dcutoff    = 1.0;
	
	// myParent is a reference to the parent sketch
	PApplet myParent;
	
	// Each SignalFilter can have a number of OneEuroFilter objects
	private ArrayList<OneEuroFilter> channels;
	
	// Number of channels 
	int size;

	
	
	// CONSTRUCTORS -------------------------------------------------------------------------
	
	/**
	 * Call this constructor in the setup() method of your 
	 * sketch to initialize and start the library.
	 * 
	 * @example basicFilter
	 * 
	 * @param theParent 
	 * 					Your sketch's PApplet object
	 */
	public SignalFilter(PApplet theParent) {
		myParent = theParent;
		try{
			init(1);
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Call this constructor in the setup() method of your 
	 * sketch to initialize and start the library.
	 * 
	 * 
	 * @example multiFilter
	 * 
	 * @param theParent
	 * 					Your sketch's PApplet object
	 * @param size
	 * 				number of filtering channels
	 */
	public SignalFilter(PApplet theParent, int size) {
		if (size <= 0) {
			System.out.println("Error in SignalFilter(): The number of channels cannot be " + size + ". The size should be at least 1");
		}
		myParent = theParent;
		try{
			init(size);
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	// PUBLIC METHODS -------------------------------------------------------------------------
	
	/**
	 * Return filtered values for the given PVector 
	 * Note: values have to be at the unit scale [0.0, 1.0] 
	 * 
	 * @param noisyVector
	 * @return filteredVector			 
	 */
	public PVector filterUnitVector(PVector noisyVector) {
		
		if (channels.size() < 3) {
			System.out.println("Error in filterUnit(): The number of channels cannot be " + size + ". You need 3 channels to filter a PVector (even if you only use the x and y values).");
		}
		
		// Convert the source vector to an array
		float[] noisyValues = noisyVector.array();
		
		// Create the target array to receive the filtered values
		float[] filteredValues = new float[3];
		
		// Filter the noise and return an array of filtered values
		try{
			filteredValues = filterValues( noisyValues );
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// Create the target vector to receive the filtered values
		PVector filteredVector = new PVector(0,0,0);
		
		// Convert the array to a PVector
		try{
			filteredVector = toVector(filteredValues);
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// Return the filtered values
		return filteredVector;
	}

	/**
	 * Return filtered value for the given float
	 * Note: value has to be at the unit scale [0.0, 1.0]
	 * 
	 * @param noisyFloat
	 * @return filteredFloat			 
	 */
	public float filterUnitFloat(float noisyFloat) {
		
		// Create the source array to receive the source value
		float[] noisyArray = new float[1];
		
		// Create the target array to receive the filtered values
		float[] filteredArray = new float[1];
		
		// Pass the value to the array
		noisyArray[0] = noisyFloat;
		
		// Filter the noise and return an array of filtered values
		try{
			filteredArray = filterValues( noisyArray );
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		
		float filteredFloat = filteredArray[0];
		
		// Return the filtered value
		return filteredFloat;
	}
	
	/**
	 * Return filtered values for the given float[] array
	 * Note: values have to be at the unit scale [0.0, 1.0]
	 * 
	 * @param noisyArray
	 * @return filteredArray			 
	 */
	public float[] filterUnitArray(float[] noisyArray) {
		
		// Get the size of the source array
		int valueCount = noisyArray.length;
		
		// Create the target array to receive the filtered values
		float[] filteredArray = new float[valueCount];
		
		// Filter the noise and return an array of filtered values
		try{
			filteredArray = filterValues( noisyArray );
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// Return the filtered values
		return filteredArray;
	}
	
	/**
	 * Convenience method to filter 2D coordinate values passed as a PVector
	 * 
	 * @param noisyVector
	 * 					coordinates as a PVector
	 * @param scaleX
	 * 					width of the coordinate space
	 * @param scaleY
	 * 					height of the coordinate space
	 * @return filteredVector			 
	 */
	public PVector filterCoord2D( PVector noisyVector, float scaleX, float scaleY ) {
		
		PVector unitVector = new PVector(0,0);
		
		// Convert the coordinate values to unit scale [0.0, 1.0]
		unitVector.x = noisyVector.x / scaleX;
		unitVector.y = noisyVector.y / scaleY;
		
		// Create the target vector and filter the noise
		PVector filteredVector = filterUnitVector( unitVector );
		
		// Scale the values back to the original coordinate system
		filteredVector.x = filteredVector.x * scaleX;
		filteredVector.y = filteredVector.y * scaleY;
		
		// Return the filtered values
		return filteredVector;
		
	}
	
	/**
	 * Convenience method to filter 2D coordinate values passed as floats
	 * 
	 * @param coordX
	 * 					coordinate on the X-axis as a float
	 * @param coordY
	 * 					coordinate on the Y-axis as a float		
	 * @param scaleX
	 * 					width of the coordinate space
	 * @param scaleY
	 * 					height of the coordinate space
	 * 
	 * @return filteredVector			 
	 */
	public PVector filterCoord2D( float coordX, float coordY, float scaleX, float scaleY ) {
		
		PVector unitVector = new PVector(0,0);
		
		// Convert the coordinate values to unit scale [0.0, 1.0]
		unitVector.x = coordX / scaleX;
		unitVector.y = coordY / scaleY;
		
		// Create the target vector and filter the noise
		PVector filteredVector = filterUnitVector( unitVector );
		
		// Scale the values back to the original coordinate system
		filteredVector.x = filteredVector.x * scaleX;
		filteredVector.y = filteredVector.y * scaleY;
		
		// Return the filtered values
		return filteredVector;
		
	}
	
	/**
	 * Convenience method to filter 3D coordinate values passed as a PVector
	 * 
	 * @param noisyVector
	 * @return filteredVector			 
	 */
	public PVector filterCoord3D(PVector noisyVector, float scaleX, float scaleY, float scaleZ) {
		
		PVector unitVector = new PVector(0,0,0);
		
		// Convert the coordinate values to unit scale [0.0, 1.0]
		unitVector.x = noisyVector.x / scaleX;
		unitVector.y = noisyVector.y / scaleY;
		unitVector.z = noisyVector.z / scaleZ;
		
		// Create the target vector and filter the noise
		PVector filteredVector = filterUnitVector( unitVector );
		
		// Scale the values back to the original coordinate system
		filteredVector.x = filteredVector.x * scaleX;
		filteredVector.y = filteredVector.y * scaleY;
		filteredVector.z = filteredVector.z * scaleZ;
		
		// Return the filtered values
		return filteredVector;
		
	}
	
	/**
	 * Convenience method to filter 3D coordinate values passed as float
	 * 
	 * @param coordX
	 * 					coordinate on the X-axis as a float
	 * @param coordY
	 * 					coordinate on the Y-axis as a float	
	 * @param coordZ
	 * 					coordinate on the Z-axis as a float		
	 * @param scaleX
	 * 					width of the coordinate space
	 * @param scaleY
	 * 					height of the coordinate space
	 * @param scaleZ
	 * 					depth of the coordinate space
	 * 
	 * @return filteredVector			 
	 */
	public PVector filterCoord3D( float coordX, float coordY, float coordZ, float scaleX, float scaleY, float scaleZ ) {
		
		PVector unitVector = new PVector(0,0);
		
		// Convert the coordinate values to unit scale [0.0, 1.0]
		unitVector.x = coordX / scaleX;
		unitVector.y = coordY / scaleY;
		unitVector.z = coordZ / scaleZ;
		
		// Create the target vector and filter the noise
		PVector filteredVector = filterUnitVector( unitVector );
		
		// Scale the values back to the original coordinate system
		filteredVector.x = filteredVector.x * scaleX;
		filteredVector.y = filteredVector.y * scaleY;
		filteredVector.z = filteredVector.z * scaleZ;
		
		// Return the filtered values
		return filteredVector;
		
	}
	

	/**
	 * Set the frequency for all filtering channels
	 * 
	 * @param f
	 *          Frequency parameter of the OneEuro Filter
	 */
    public void setFrequency(float f) {
    	
        // Store the value as double
        freq = (double) f;
        
        // Pass the value to all channels
        for(final OneEuroFilter filter : channels) {
        	try{
        		filter.setFrequency(freq);
        	}
    	    catch (Exception e) {
    	        e.printStackTrace();
    	    }
        }
    }

	/**
	 * Set the minimum cutoff for all the filtering channels
	 * 
	 * @param mc
	 *          Minimum cutoff (intercept) parameter of the OneEuro Filter
	 */
    public void setMinCutoff(float mc) {  
    	
        // Store the value as double
        mincutoff = (double) mc;
        
        // Pass the value to all channels
        for(final OneEuroFilter filter : channels) {
        	try{
        		filter.setMinCutoff(mincutoff);
        	}
    	    catch (Exception e) {
    	        e.printStackTrace();
    	    }
        }
    }

	/**
	 * Set the Beta (cutoff slope) parameter for all filtering channels
	 * 
	 * @param b
	 *          Beta (cutoff slope) parameter of the OneEuro Filter
	 */
    public void setBeta(float b) {
    	
    	// Store the value as double
        beta_ = (double) b;
        
        // Pass the value to all channels
        for(final OneEuroFilter filter : channels) {
        	try{
        		filter.setBeta(beta_);
        	}
    	    catch (Exception e) {
    	        e.printStackTrace();
    	    }
        }
    }

	/**
	 * Set the derivative cutoff for all filtering channels
	 * 
	 * @param b
	 *          Cutoff for derivative parameter of the OneEuro Filter
	 */
    public void setDerivateCutoff(float dc) {

    	// Store the value as double
        dcutoff = (double) dc;
        
        // Pass the value to all channels
        for(final OneEuroFilter filter : channels) {
        	try{
        		filter.setDerivateCutoff(dcutoff);
        	}
    	    catch (Exception e) {
    	        e.printStackTrace();
    	    }
        }
    }

	
	
	// PRIVATE METHODS -------------------------------------------------------------------------
	
	
	/**
	 * Initialize the SignalFilter object
	 * 
	 * @param channelCount
	 */
	private void init(int channelCount) throws Exception {
		if (channelCount <= 0) {
			throw new Exception("Exception in init(): The number of channels cannot be " + channelCount + ". The size should be at least 1");
		}
		try{
			createChannels(channelCount);
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	/**
	 * Create a OneEuroFilter object for each filtering channel
	 * 
	 * @param channelCount			 
	 */
	private void createChannels(int channelCount)  throws Exception {
		if (channelCount <= 0) {
			throw new Exception("Exception in createChannels(): The number of channels cannot be " + channelCount + ". The size should be at least 1");
		}
		channels = new ArrayList<OneEuroFilter>(channelCount);
		for(int i=0; i<channelCount; i++) {
			try{
				channels.add(new OneEuroFilter(freq, mincutoff, beta_, dcutoff)); // Create a default filter for this channel
			}
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	}
	
	
	/**
	 * Return filtered values for the given array.
	 * Note: values have to be at the unit scale [0.0, 1.0]
	 * 
	 * @param noisyValues
	 * 						float array of values to filter. The number of values should match the number of filtering channels created when calling the SignalFilter() constructor (default is 1).
	 * @return filteredValues			 
	 */
	private float[] filterValues(float[] noisyValues) throws Exception {
		
		// Save the amount of source values and channels array (they should match)
		int valueCount   = noisyValues.length;
		int channelCount = channels.size();
		
		if (valueCount != channelCount) {
			throw new Exception("Exception in filterValues(): The number of filtering channels ("+ channelCount +") must match the number of signals you want to filter (" + valueCount + ")");
		}
		
		// Create the array to return
		float[] filteredValues = new float[valueCount];
		
		// Create timestamp
		double timestamp = myParent.frameCount / freq;
		
		// Get the filtered values for each noisy value
		for(int i=0; i<valueCount; i++) {
			OneEuroFilter f = channels.get(i);
			double value = (double) noisyValues[i];
			filteredValues[i] = (float) f.filter(value, timestamp);
		}
		
		// Return the filtered values
		return filteredValues;
	}
	
	

	/**
	 * Convert an array of 2 or 3 float to a PVector
	 * 
	 * @param array
	 * @return v
	 * 			A PVector built from the values of the array	 
	 */
	private PVector toVector(float[] array) throws Exception {
		
		int valueCount = array.length;
		
		if (valueCount > 3) {
			throw new Exception("Exception in toVector(): An array of length " + valueCount + " cannot be converted to PVector. The maximum number of values is 3");
		}
		else if (valueCount < 2) {
			throw new Exception("Exception in toVector(): An array of length " + valueCount + " cannot be converted to PVector. The minimum number of values is 2");
		}
		
		float[] a = array;
		
		// Convert the array back to a PVector
		float x = a[0];
		float y = a[1];
		float z = a[2];
		PVector v = new PVector(x,y,z);
		
		// Return the resulting PVector
		return v;
	}
	
	// -------------------------------------------------------------------------

}

