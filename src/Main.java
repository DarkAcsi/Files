import java.io.*;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static boolean open_file(File f, int index){
        boolean metod[] = {f.isAbsolute(), f.canRead(), f.canWrite(), f.exists()};
        try {
            if (!metod[index]) {
                switch (index) {
                    case 0:
                        System.out.println("Введен некорректный путь");
                        break;
                    case 1:
                        System.out.println("Невозможно прочитать файл");
                        break;
                    case 2:
                        System.out.println("Невозможно записать в файл\nСоздан новый файл");
                        return false;
                    case 3:
                        return false;
                }
                System.out.println("Введите новый путь:");
                return false;
            }
        } catch (SecurityException ex) {
            System.out.println("Доступ к файлу закрыт\nВведите новый путь:");
            return false;
        }
        return true;
    }
    static boolean rewrite(File f1, File f2) {
        int symbols[] = new int[200];
        try (FileReader r = new FileReader(f1)) {
            int c;
            while((c=r.read())!=-1){symbols[c] += 1;}
        } catch (IOException ex) {
            System.out.println("Возникла непредвиденная ошибка во время чтения файла\nРабота программы прекращена");
            return false;
        }
        try (FileWriter w = new FileWriter(f2)) {
            for (int i = 'A'; i < 'Z'+1; i++){
                w.write((char)i + " = " + symbols[i] + '\n');
            }
            for (int i = 'a'; i < 'z'+1; i++){
                w.write((char)i + " = " + symbols[i] + '\n');
            }
        } catch (IOException ex) {
            System.out.println("Возникла непредвиденная ошибка во время записи в файл\nРабота программы прекращена");
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("Введите путь к файлу для чтения:");
        File file1 = new File(sc.nextLine());
        while (!(open_file(file1,0) && open_file(file1,1))){
            file1 = new File(sc.nextLine());
        }
        System.out.println("Введите путь к файлу для записи:");
        File file2;
        while (true){
            String str = sc.nextLine();
            file2 = new File(str);
            if (!(open_file(file2,0))){continue;}
            if (!str.endsWith(".txt")){
                System.out.println("Расширение должно быть .txt\nВведите новый путь:");
                continue;
            }
            if (!open_file(file2,3) || !open_file(file2,2)){continue;}
            break;
        }
        if (rewrite(file1, file2)){
            System.out.println("Считывание и запись прошли успешно");
        }
    }
}