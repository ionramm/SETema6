package model;

import exceptions.InputException;
import interfaces.IModelListener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class WeatherModel {
    // Constants
    public static final String INITIAL_VALUE = "0";
    public static final String INITIAL_VALUE1 = "0";

    // Member variable defining state of calculator, the total current value
    private BigInteger WindSpeed;
    private BigInteger Temperature;

    private List<IModelListener> mListeners;

    /**
     * Constructor
     */
    public WeatherModel() {
        WindSpeed = new BigInteger(INITIAL_VALUE);
        Temperature = new BigInteger(INITIAL_VALUE);
    }

    /**
     * Set the total value.
     *
     * @param valuew New value that should be used for the calculator total.
     */
    public void setValue(String valuew, String valuet
    ) throws InputException {
        try {

            // new initializations for my domains -> speed wind and grades

            WindSpeed = new BigInteger(valuew);
            Temperature = new BigInteger(valuet);
            notifyListeners();

        } catch (NumberFormatException e) {

            throw new InputException(valuew, e.getMessage());
        }
    }

    /**
     * Return speed wind values and grades values
     */
    public String getValueWind() {
        return WindSpeed.toString();
    }

    public String getValueTemperature() {
        return Temperature.toString();
    }

    /**
     * Adds the view listener to the list
     *
     * @param listener The model event listener
     */
    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }
}
