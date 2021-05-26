package src.edu.ufp.inf.lp2.teorico._07_nio;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.Date;




public class FilePropertiesApp {
    public static void main(String[] args) {
        String filename=(args.length == 1 ? args[0] : "Teste.txt");
        try {
            System.out.println("============== using java.io package ==============");
            // Open file inside “data” folder
            File file = new File("data", filename);

            file.createNewFile();
            /*
            file.mkdir();
            file.renameTo(file);
            */
            // Print some file information
            System.out.println("File name – " + file.getName());
            System.out.println("File path – " + file.getPath());
            System.out.println("File Size - " + file.length());
            System.out.println("File Absolut Path – " + file.getAbsolutePath());
            Date d = new Date(file.lastModified());
            System.out.println("File Last Modified – " + d);
            System.out.println("File is dir? – " + file.isDirectory());
            System.out.println("File is file? – " + file.isFile());
            System.out.println("File is hidden? – " + file.isHidden());
            System.out.println("File is canRead? – " + file.canRead());
            // Open folder "src" inside “LP2” project folder
            File folder = new File("src");
            // Print some folder information
            System.out.println("Folder name – " + folder.getName());
            System.out.println("Folder path – " + folder.getPath());
            System.out.println("Folder Size - " + folder.length());
            System.out.println("Folder Absolut Path – " + folder.getAbsolutePath());
            Date d2=new Date(folder.lastModified());
            System.out.println("Folder Last Modified – " + d2);
            System.out.println("Folder is dir? – " + folder.isDirectory());
            System.out.println("Folder is file? – " + folder.isFile());
            System.out.println("Folder is hidden? – " + folder.isHidden());
        } catch (Exception e) {
            System.out.println("FilePropertiesApp – main(): " + e.toString());
        }
        try{
            //Use Java 8 java.nio.file package: Path, Paths, Files
            System.out.println("============== using java.nio.file package ==============");

            // Open file inside “data” folder
            File file = new File("data", filename);
            //A Path instance reflects the underlying OS representation
            Path pathToFile = file.toPath();
            //Path pathToFile = Paths.get("data/"+filename);

            System.out.println("File name – " + pathToFile.getFileName());
            System.out.println("File name – " + pathToFile.toString());
            System.out.println("File name – " + pathToFile.toAbsolutePath().toString());
            //Files class offers set of static methods for reading, writing, and manipulating files and directories
            System.out.println("Is directory: " + Files.isDirectory(pathToFile));
            System.out.println("Is hidden: " + Files.isHidden(pathToFile));
            System.out.println("Is exe: " + Files.isExecutable(pathToFile));
            System.out.println("Is read: " + Files.isReadable(pathToFile));
            System.out.println("Is write: " + Files.isWritable(pathToFile));
            System.out.println("Is symblink: " + Files.isSymbolicLink(pathToFile));
            System.out.println("Is file: " + Files.isRegularFile(pathToFile));

            FileTime ft=Files.getLastModifiedTime(pathToFile);
            System.out.println("Filetime: " + ft.toString());
            /*
            Files.delete(pathToFile);
            Files.copy(sourcePath, targetPath, REPLACE_EXISTING);
            Files.move(sourcePath, targetPath, REPLACE_EXISTING);
             */

            Path pathToDir=Paths.get("src");
            FileSystem fs=pathToFile.getFileSystem();
            System.out.println("Directory name - " + pathToDir.getFileName());
            System.out.println("Directory path - " + pathToDir.toAbsolutePath().toAbsolutePath());

            //Print 'src' content recursivelly
            printRecursiveFolderContent(pathToDir,"");
            // Print 'src' folder content
            PrintFilesVisitor pfv=new PrintFilesVisitor();
            Files.walkFileTree(pathToDir, pfv);

            //Print root directories of all HD drives in the OS
            Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
            for (Path name : dirs) {
                System.err.println(name);
            }

        } catch (Exception e) {
            System.out.println("FilePropertiesApp – main(): " + e.toString());
        }
    }
    // imprimir ficheiro pastas e sub-pastas do Folder
    private static void printRecursiveFolderContent(Path pathToDir, String tabs) throws IOException {
        System.out.println(tabs + pathToDir.toString());
        tabs+="\t";
        DirectoryStream<Path> stream = Files.newDirectoryStream(pathToDir);
        for (Path p : stream) {
            if (Files.isDirectory(p)) {
                printRecursiveFolderContent(p, tabs);
            } else {
                System.out.println(tabs + p.getFileName());
            }
        }
    }

}
