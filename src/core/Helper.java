package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    //Tema seçme metodu
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    //Ekrana mesaj gönderme (gösterme)
    public static void showMsg(String str) {
        String msg;
        String title;

        switch (str) {
            case "fill":
                msg = "Please fill all the blanks!"; //Lütfen tüm boşlukları doldurunuz!
                title = "Error!"; //Hata
                break;
            case "done":
                msg = "Successful!"; //Başarılı
                title = "Result"; //Sonuç

            case "notFound":
                msg = "User not found!"; //Kullanıcı bulunamadı
                title = "Not found!";

            case "error":
                msg = "You Made a Wrong Transaction!"; //Yanlış İşlem Yaptınız
                title = "Error!"; //Hata

            default:
                msg = str;
                title = "Message";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }


    //Ekrana mesaj gönderme (gösterme)
    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            msg = "Are you sure you want to do this?"; //Bunu yapmak istediğinden emin misin?
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure ?", JOptionPane.YES_NO_OPTION) == 0;
        //JOptionPane --> kullanıcıya mesajlar göstermek, kullanıcıdan girdi almak veya bir onay almak için kullanılan bir Swing bileşenidir
    }


    //seçilen alanın boş olup olmadığını kontrol eder
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }


    //seçilen listedki alanların boş olup olmadığını tek tek kontrol eder
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }


    //Pencerenin ekranın ortasına yerleştirilmesini sağlar.
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

}
