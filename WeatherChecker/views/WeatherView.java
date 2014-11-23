package views;

import interfaces.IController;
import interfaces.IModelListener;
import interfaces.IView;
import model.WeatherModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


public class WeatherView extends JFrame implements IModelListener, IView {

    private static final long serialVersionUID = -5758555454500685115L;

    // View Components
    private JTextField mGrade = new JTextField(16);
    private JTextField mVitezaVantului = new JTextField(16);

    private JButton mGenerateButon = new JButton("Check weather");

    private WeatherModel mModel;

    /**
     * Constructor
     */
    public WeatherView() {
        // Initialize components

        mGrade.setEditable(false);
        mVitezaVantului.setEditable(false);

        mGrade.getDocument().addDocumentListener(new DocumentListener() {
            /*
                  some changes

             */
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {

            }
        });

        // Layout the components.

        JPanel content = new JPanel(new GridLayout(12, 2));

        content.setLayout(new GridLayout(8, 4, 8, 8));
        content.add(new JLabel(" Temperature: "));
        content.add(mGrade);
        content.add(new JLabel("                                   Celsius Degrees "));
        content.add(new JLabel("  "));
        content.add(new JLabel(" Windspeed: "));
        content.add(mVitezaVantului);
        content.add(new JLabel("                                                        km/h "));
        content.add(mGenerateButon);

        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather Checker");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Sets the view's reference to the model - Only get operations allowed
     *
     * @param model The calc model
     */
    public void addModel(WeatherModel model) {
        mModel = model;
        mVitezaVantului.setText(model.getValueWind());
        mGrade.setText(model.getValueTemperature());
    }

    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {


        mGenerateButon.setActionCommand(IController.ACTION_CHECK_WEATHER);
        mGenerateButon.addActionListener(controller);
    }

    @Override
    public void onMessage(boolean isError, String message) {
        if (isError) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Weather Forecast MVC", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void onUpdate() {
        mVitezaVantului.setText(mModel.getValueWind());
        mGrade.setText(mModel.getValueTemperature());
    }
}