package formatQuestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import DAO.DatabaseHelper;
import java.sql.*;

public class readFile {

    private File file;
    private Scanner scanner;

    public readFile(File file) {
        this.file = file;
    }

    public void readFile() {
        if (file.exists()) {
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Không tìm thấy tệp văn bản.");
        }
    }

    public void display() {
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] questionData = line.split(",");

                String question = questionData[0];
                String answerA = questionData[1];
                String answerB = questionData[2];
                String answerC = questionData[3];
                String answerD = questionData[4];
                
                upLoadFileToXAMPP(question, answerA, answerB, answerC, answerD);

                // Hiển thị câu hỏi và các đáp án lên màn hình
                System.out.println("Câu hỏi: " + question);
                System.out.println("A. " + answerA);
                System.out.println("B. " + answerB);
                System.out.println("C. " + answerC);
                System.out.println("D. " + answerD);
                System.out.println();
            }
        } else {
            System.out.println("Không thể đọc tệp văn bản.");
        }
    }

    public void upLoadFileToXAMPP(String t_text, String t_opt1, String t_opt2, String t_opt3, String t_opt4) {
        try {
            Connection connection = DatabaseHelper.getConnection();
            PreparedStatement pstm = null;

            String sql = "INSERT INTO ta_que_question (t_text,t_opt1,t_opt2,t_opt3,t_opt4) VALUES (?,?,?,?,?)";
            pstm = connection.prepareStatement(sql);

            pstm.setString(1, t_text);
            pstm.setString(2, t_opt1);
            pstm.setString(3, t_opt2);
            pstm.setString(4, t_opt3);
            pstm.setString(5, t_opt4);

            pstm.executeUpdate();
        } catch (Exception e) {
        }
        System.out.println("thanh cong roiiiiiiiiiiiiiiii ----->");
    }

    public static void main(String[] args) {
        File file = new File("d:/game.txt");
        readFile read = new readFile(file);
        read.readFile();
        read.display();
    }
}
