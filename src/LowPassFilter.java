package signal.library;

/**
*
* @author s. conversy from n. roussel c++ version
*/

class LowPassFilter {

   double y, a, s;
   boolean initialized;

   void setAlpha(double alpha) throws Exception {
       if (alpha <= 0.0 || alpha > 1.0) {
           throw new Exception("alpha should be in (0.0, 1.0] and is now "+ alpha);
       }
       a = alpha;
   }

   public LowPassFilter(double alpha) throws Exception {
       init(alpha, 0);
   }

   public LowPassFilter(double alpha, double initval) throws Exception {
       init(alpha, initval);
   }

   private void init(double alpha, double initval) throws Exception {
       y = s = initval;
       setAlpha(alpha);
       initialized = false;
   }

   public double filter(double value) {
       double result;
       if (initialized) {
           result = a * value + (1.0 - a) * s;
       } else {
           result = value;
           initialized = true;
       }
       y = value;
       s = result;
       return result;
   }

   public double filterWithAlpha(double value, double alpha) throws Exception {
       setAlpha(alpha);
       return filter(value);
   }

   public boolean hasLastRawValue() {
       return initialized;
   }

   public double lastRawValue() {
       return y;
   }

   public double lastFilteredValue() {
       return s;
   }
   
}
