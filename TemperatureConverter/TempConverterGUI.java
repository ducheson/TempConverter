package TemperatureConverter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempConverterGUI implements Observer {
    private JLabel celsiusLabelRemote;
    private JTextField celsiusTextRemote;
    private JLabel fahLabelRemote;
    private JTextField fahTextRemote;
    private String title;
    private TempConverter modelRemote;
    private TempConverterController controlRemote;
    private double c;
    private double f;

    public TempConverterGUI() {
        controlRemote = new TempConverterController();
        controlRemote.setViewRemote(this);
        modelRemote = new TempConverter();
        modelRemote.addObserver(this);
        title = "Temperature Converter";
        celsiusLabelRemote = new JLabel("Celsius");
        celsiusTextRemote = new JTextField("0.0", 10);
        celsiusTextRemote.addActionListener(controlRemote);
        fahLabelRemote = new JLabel("Fahrenheit");
        fahTextRemote = new JTextField("32.0", 10);
        fahTextRemote.addActionListener(controlRemote);
    }

    public void display() {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.add(celsiusLabelRemote);
        panel.add(celsiusTextRemote);
        panel.add(fahLabelRemote);
        panel.add(fahTextRemote);
        
        frame.add(panel); // add Panel
        frame.pack();
        frame.setVisible(true);
    }

    public JTextField getCelsiusTextRemote() {
        return celsiusTextRemote;
    }

    public JTextField getFahTextRemote() {
        return fahTextRemote;
    }

    public TempConverter getModelRemote() {
        return modelRemote;
    }

    class TempConverterController implements ActionListener {
        private TempConverterGUI viewRemote;
        private TempConverter modelRemote;

        public TempConverterController() {}

        public void setViewRemote(TempConverterGUI viewRemote) {
            this.viewRemote = viewRemote;
        }

        public TempConverterController(TempConverterGUI viewRemote) {
            this.viewRemote = viewRemote;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField celTextFieldRemote = viewRemote.getCelsiusTextRemote();
            JTextField fahTextFieldRemote = viewRemote.getFahTextRemote();
            double cel = Double.parseDouble(celTextFieldRemote.getText());
            double fah = Double.parseDouble(fahTextFieldRemote.getText());

            modelRemote = viewRemote.getModelRemote();
            modelRemote.setCel(cel);
            modelRemote.setFah(fah);
            if (e.getSource() == celTextFieldRemote) {
                c = cel;
                f = modelRemote.c2f();
                fahTextFieldRemote.setText("" + f);
            } else {
                f = fah;
                c = modelRemote.f2c();
                celTextFieldRemote.setText("" + c);
            }
        }
    }

    @Override
    public void update() {
        c = modelRemote.getCel();
        f = modelRemote.getFah();
        System.out.println("Celsius = " + c + ", Fahrenheit = " + f);
    }   
}
