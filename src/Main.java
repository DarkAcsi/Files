import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // получение файла для чтения
        System.out.println("Введите путь к файлу для чтения:");
        File file1 = new File(sc.nextLine());
        Methods f = new Methods();
        while (!(f.open_file(file1, Methods.PATH) && f.open_file(file1,Methods.READ))){
            file1 = new File(sc.nextLine());
        }
        // получение файла для записи
        System.out.println("Введите путь к файлу для записи:");
        File file2;
        while (true){
            String str = sc.nextLine();
            file2 = new File(str);
            if (!(f.open_file(file2,Methods.PATH))){continue;}
            if (!f.open_file(file2,Methods.TXT)){continue;}
            if (!f.open_file(file2,Methods.EXIST) || !f.open_file(file2,Methods.WRITE)){continue;}
            break;
        }
        // Считывание и запись
        if (f.rewrite(file1, file2)){
            System.out.println("Считывание и запись прошли успешно");
        }
    }
}