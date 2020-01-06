public class SensorActivity extends Activity 
       implements SensorEventListener {
    
    private SensorManager sensorManager;
    private Sensor mSensor;

    // When the activity is created
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mSensor = null;


        // Get Android sensor system service
        sensorManager = (SensorManager) getSystemService(
            Context.SENSOR_SERVICE
        );
        
        // If no gravity sensor is predented,
        // get the sensor list
        if (sensorManager.getDefaultSensor(
            Sensor.TYPE_GRAVITY) != null
        ){
            List<Sensor> sensors = 
                sensorManager.getSensorList(
                    Sensor.TYPE_GRAVITY
                );
            // Go through the sensors
            for(int i = 0; i < sensors.size(); i++) {
                if (sensors.get(i)
                            .getVendor()
                            .contains("LS")) {
                    // Set the sensor
                    mSensor = sensors.get(i);
                }
            }
        }
        
        // If no sensor is found
        
        if (mSensor === null) {
            // Use the accelerometer.
            if (sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER) != null) {
                // Set the sensor
                mSensor = sensorManager.getDefaultSensor(
                    Sensor.TYPE_ACCELEROMETER
                );
            } else {
                // No accelerometer presented.
            }
        }
    }

    // When sensor data are changed, 
    // call pushData (WebSockets server)
    @Override
    public final void onSensorChanged(SensorEvent event) {
        pushData(event.values);
    }
    
    // When the activity pauses, 
    // unsubscribe the sensor listener
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    // When the activity resumes, 
    // re-register the sensor listener
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
            this, 
            mSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        );
    }
}