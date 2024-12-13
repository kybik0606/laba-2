import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormulaCalculator extends JFrame { //Основной класс, который расширяет JFrame и
    private JTextField inputX; //создает окно приложения с текстовыми полями для ввода и кнопками для вычислений.
    private JTextField inputY;
    private JTextField inputZ;
    private JTextField resultField;
    private double sum = 0.0;  // Внутренняя переменная для накопления суммы

    public FormulaCalculator() {
        setTitle("Formula Calculator");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//указывает, что при закрытии окна приложение должно завершиться
        setLayout(new GridLayout(6, 2, 5, 5));  // Добавим отступы между компонентами

        // Поля ввода для x, y, z
        add(new JLabel("x:"));
        inputX = new JTextField();
        add(inputX);

        add(new JLabel("y:"));
        inputY = new JTextField();
        add(inputY);

        add(new JLabel("z:"));
        inputZ = new JTextField();
        add(inputZ);

        // Кнопки для вычисления формул с новым стилем
        JButton buttonFormula1 = new JButton("Compute Formula 1");
        JButton buttonFormula2 = new JButton("Compute Formula 2");

        // Настраиваем цвет, шрифт и фон кнопок
        buttonFormula1.setBackground(new Color(70, 130, 180));  // Синий фон
        buttonFormula1.setForeground(Color.WHITE);               // Белый текст
        buttonFormula1.setFont(new Font("Arial", Font.BOLD, 12));

        buttonFormula2.setBackground(new Color(70, 130, 180));
        buttonFormula2.setForeground(Color.WHITE);
        buttonFormula2.setFont(new Font("Arial", Font.BOLD, 12));

        add(buttonFormula1);
        add(buttonFormula2);

        // Поле для вывода результата
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setBackground(new Color(240, 248, 255)); // Светло-голубой фон
        add(new JLabel("Result:"));
        add(resultField);

        // Кнопки "M+" и "MC"
        JButton buttonMPlus = new JButton("M+");
        JButton buttonMC = new JButton("MC");

        buttonMPlus.setBackground(new Color(70, 130, 180));
        buttonMPlus.setForeground(Color.WHITE);
        buttonMPlus.setFont(new Font("Arial", Font.BOLD, 12));

        buttonMC.setBackground(new Color(70, 130, 180));
        buttonMC.setForeground(Color.WHITE);
        buttonMC.setFont(new Font("Arial", Font.BOLD, 12));

        add(buttonMPlus);
        add(buttonMC);

        // Обработчик для кнопки формулы №1
        buttonFormula1.addActionListener(new ActionListener() {//Это означает, что каждый раз, когда кнопка "M+" нажимается,
            @Override                                          //срабатывает событие, запускающее код внутри метода actionPerformed
            public void actionPerformed(ActionEvent e) {//кнопка нажата
                try {
                    double x = Double.parseDouble(inputX.getText());//Извлечение значений из текстовых полей
                    double y = Double.parseDouble(inputY.getText());//текстовые значения преобразуются в double с помощью метода Double.parseDouble
                    double z = Double.parseDouble(inputZ.getText());
                    double result = computeFormula1(x, y, z);
                    resultField.setText(String.valueOf(result));//Результат преобразуется в строку с помощью String.valueOf
                } catch (NumberFormatException ex) {//Если введенные данные в текстовые поля нельзя преобразовать в число (например, если пользователь ввел некорректные символы)
                    resultField.setText("Invalid input");//, происходит исключение NumberFormatException
                }
            }
        });

        // Слушатель для кнопки формулы №2
        buttonFormula2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(inputX.getText());
                    double y = Double.parseDouble(inputY.getText());
                    double z = Double.parseDouble(inputZ.getText());
                    double result = computeFormula2(x, y, z);
                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    resultField.setText("Invalid input");
                }
            }
        });

        // Слушатель для кнопки "M+"
        buttonMPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double currentResult = Double.parseDouble(resultField.getText());
                    sum += currentResult;
                    resultField.setText(String.valueOf(sum));
                } catch (NumberFormatException ex) {
                    resultField.setText("No result to add");
                }
            }
        });

        // Слушатель для кнопки "MC"
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sum = 0.0;
                resultField.setText("0.0");
            }
        });
    }

    // Формула №1
    private double computeFormula1(double x, double y, double z) {
        return (Math.sin(Math.PI * y * y) + Math.log(y * y)) /
                (Math.sin(Math.PI * z * z) + Math.sin(x) + Math.log(z * z) + x * x + Math.pow(Math.E, Math.cos(z * x)));
    }

    // Формула №2
    private double computeFormula2(double x, double y, double z) {
        return y * (x * x) / (Math.log10(Math.pow(z, y)) + Math.pow(Math.cos(Math.sqrt(x)), 2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {//гарантирует, что создание и управление GUI происходит в правильном потоке.
            FormulaCalculator calculator = new FormulaCalculator();
            calculator.setVisible(true);
        });
    }
}
