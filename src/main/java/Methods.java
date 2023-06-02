import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Класс направлен на проверку файлов по определенным критериям и его простой анализ с записью результатов
 */
public class Methods {
    /**Константа проверки пути*/
    public static final int PATH = 0;
    /**Константа проверки на возможность чтения*/
    public static final int READ = 1;
    /**Константа проверки на возможность записи*/
    public static final int WRITE = 2;
    /**Константа проверки на существование файла*/
    public static final int EXIST = 3;
    /**Константа проверки расширения .txt*/
    public static final int TXT = 4;
    /**
     * Проверяет по пути, является ли файл текстовым
     * @param f файл для проверки
     * @return  true - имеет расширение .txt
     *          false - не имеет
     */
    private boolean txt(File f){
        if (f.toString().endsWith(".txt")){ return true;}
        System.out.println("Расширение должно быть .txt\nВведите новый путь:");
        return false;
    }
    /**
     * Проверяет файл по нескольким условиям (корректный путь, можно прочитать, можно перезаписать, существует, имеет расширение .txt)
     * @param f файл для проверки
     * @param index определяет какой метод нужен (константы: PATH, READ, WRITE, EXIST, TXT)
     * @return true - условие выполнено, false - нет
     */
    public boolean open_file(File f, int index){
        try {
            switch (index) {
                case PATH:
                    if (f.isAbsolute()){return true;}
                    System.out.println("Введен некорректный путь");
                    break;
                case READ:
                    if (f.canRead()){return true;}
                    System.out.println("Невозможно прочитать файл");
                    break;
                case WRITE:
                    if (f.canWrite()){return true;}
                    System.out.println("Невозможно записать в файл\nСоздан новый файл");
                    return false;
                case EXIST:
                    return f.exists();
                case TXT:
                    return txt(f);
            }
            System.out.println("Введите новый путь:");
            return false;

        } catch (SecurityException ex) {
            System.out.println("Доступ к файлу закрыт\nВведите новый путь:");
            return false;
        }
    }
    /**
     * Анализирует первый файл (считает количество употреблений символов английского алфавита разных регистов)
     * и записывает результат во второй
     * @param f1 файл для анализа
     * @param f2 файл для записи
     * @return true - анализ и запись успешны, false - нет
     */
    public boolean rewrite(File f1, File f2) {
        int[] symbols = new int[200];
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
}
