import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

//Provides a basic Swing GUI
public class SwingUI {

    public static javax.swing.JFrame JFrame;
    public static JTextArea jTextArea;

    public static JButton updateFile;
    public static JButton DBExport;
    public static JButton saveDirButton;
    public static JButton process;

    public static String updateFilePath;
    public static String currentFilePath;
    public static String savePath;

    public static void createUI() {

        JFrame = new JFrame("MIC_LOV");
        JFrame.setSize(440, 400);
        JFrame.setVisible(true);
        JFrame.setResizable(true);
        JFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        JFrame.getContentPane().setLayout(null);

        JOptionPane jOptionPane = new JOptionPane();
        jOptionPane.showInputDialog(JFrame, "<html>Bitte das Query im SQLDeveloper laufen lassen und<br/>als 'text' exportieren ohne Einrahmung -> Kundendaten</html>", "\n" +
                "select LVVVALUE,TO_CHAR(LVVVALIDFROM, 'dd.mm.yyyy HH24:MI:ss')LVVVALIDFROM,TO_CHAR(LVVVALIDTO, 'dd.mm.yyyy HH24:MI:ss')LVVVALIDTO,LOAATTRIBUTE,LVAVALUE,LOTTRANSLATION,LVVSID,LOVSID,LVASID,LOASID from mic_lov,mic_lov_value,mic_lov_attribute,mic_lov_value_attribute,mic_lov_translation\n" +
                "where lovsid=lvvlovsid\n" +
                "and loalovsid=lovsid\n" +
                "and lvalvvsid=lvvsid\n" +
                "and lvaloasid=loasid\n" +
                "and lotrefsid=lvvsid\n" +
                "and lotlanguage='DEU'\n" +
                "and LOVGROUP='UNTERLAGEN_AES';");

        jTextArea = new JTextArea();
        jTextArea.setLocation(2, 6);
        jTextArea.setSize(250, 250);
        jTextArea.setRows(5);
        jTextArea.setColumns(5);
        jTextArea.setLineWrap(true);
        jTextArea.setAutoscrolls(true);
        JFrame.add(jTextArea);

        updateFile = new JButton();
        updateFile.setLocation(267, 14);
        updateFile.setSize(130, 50);
        updateFile.setText("Zolldaten");
        JFrame.add(updateFile);


        updateFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFilePath = IOStuff(1);
                jTextArea.setText(jTextArea.getText() + "\nATLAS File path saved");
            }
        });

        DBExport = new JButton();
        DBExport.setLocation(267, 76);
        DBExport.setSize(130, 50);
        DBExport.setText("Kundendaten");
        JFrame.add(DBExport);

        DBExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentFilePath = IOStuff(2);
                jTextArea.setText(jTextArea.getText() + "\nDB Export path saved");
            }
        });

        saveDirButton = new JButton();
        saveDirButton.setLocation(267, 135);
        saveDirButton.setSize(130, 50);
        saveDirButton.setText("Ausgabeordner");
        JFrame.add(saveDirButton);

        saveDirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePath = fileSaveDialog();
                jTextArea.setText(jTextArea.getText() + "\nSave Directory path saved");
            }
        });

        process = new JButton();
        process.setLocation(266, 195);
        process.setSize(100, 50);
        process.setText("Process");
        JFrame.add(process);

        process.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((updateFilePath != null) && (currentFilePath != null) && (savePath != null)) {
                    jTextArea.setText(jTextArea.getText() + "\nProcessing...");
                    TSVReader tsvReader = new TSVReader(updateFilePath);  //must be used for ATLAS file
                    DSVReader dsvReader = new DSVReader(currentFilePath); //DSV must be used for DB export
                    Processor.process(tsvReader, dsvReader, savePath);
                    jTextArea.setText(jTextArea.getText() + "\nSuccessful!");
                } else {
                    jTextArea.setText(jTextArea.getText() + "\nPlease provide valid files");
                }
            }
        });
        JFrame.validate();
        JFrame.repaint();

        jTextArea.setText("Please enter the ATLAS file");
        jTextArea.setText(jTextArea.getText() + "\nPlease enter the DB export");
    }

    public static String fileChooserTest() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(JFrame);
        File selectedFile;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        return null;

    }

    public static String fileSaveDialog() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showSaveDialog(JFrame);
        File selectedFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            return fileToSave.getAbsolutePath();

        }
        return null;

    }

    public static String IOStuff(int filetype) {
        String ret;
        ret = SwingUI.fileChooserTest();
        try {
            if (!Validator.validate(ret, filetype)) {
                SwingUI.jTextArea.setText(SwingUI.jTextArea.getText() + "\nThe given file does not have the required format,please try again:");
                ret = IOStuff(filetype);
            }
        } catch (FileNotFoundException e) {
            SwingUI.jTextArea.setText(SwingUI.jTextArea.getText() + "\nThe given file was not found, please again:");
            ret = IOStuff(filetype);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            SwingUI.jTextArea.setText(SwingUI.jTextArea.getText() + "\nNo Path/File was selected");
        }

        return ret;
    }

}
