package Videotutoria;

import java.io.*;

public class Files {

    public static void main(String[] args) {
        /*Constructores*/
        System.out.println("Constructor");
        creacionFicheros();
        printLinea(1);


        /*Leer y escribir ficheros secuenciales*/
        System.out.println("fileWriter");
        fileWriter();
        printLinea(2);
        System.out.println("fileReader");
        fileReader();
        printLinea(3);


        /* Fichero de acceso aleatorio*/
        System.out.println("randomAccesFile");
        randomAccesFile();
        printLinea(4);


         /*Ficheros Binarios*/
        System.out.println("fileOutputStream");
        fileOutputStream();
        printLinea(5);
        System.out.println("fileReaderStream");
        fileReaderStream();
        printLinea(6);

         /*XML*/
        System.out.println("xmlReaderDOM");
        xmlReaderDOM();
        printLinea(7);
        System.out.println("xmlWriter");
        xmlWriter();
        printLinea(8);
        System.out.println("xmlReaderSAX");
        xmlReaderSAX();
        printLinea(9);

    }

    private static void xmlReaderSAX() {
        FicheroXML xml = new FicheroXML();
        xml.parserSAX();
    }

    private static void xmlWriter() {
        FicheroXML xml = new FicheroXML();
        xml.writeXML(new File("Ficheros/ficheros/gato.xml"), new Gato());
    }

    private static void xmlReaderDOM() {
        FicheroXML xmlDom = new FicheroXML();
        xmlDom.parserDOM();
    }

    private static void fileReaderStream() {
        File ficheroGato = new File("Ficheros/ficheros/gato");
        try {
            FileInputStream fileIn = new FileInputStream(ficheroGato);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
            Gato gato = (Gato) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(gato.toString());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("No se ha podido leer el fichero");
        }

    }

    private static void fileOutputStream() {
        Gato gato = new Gato();
        gato.setEdad(4);
        gato.setPeso(14.23);
        gato.setName("VT2");

        File ficheroGato = new File("Ficheros/ficheros/gato");
        try {
            FileOutputStream file = new FileOutputStream(ficheroGato, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            objectOutputStream.writeObject(gato);
        } catch (IOException ex) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

    private static void randomAccesFile() {
        System.out.println(System.getProperty("file.separator"));
        File fichero1 = new File("C:\\Users\\aortega\\Desktop\\Intellij\\M06\\Ficheros\\ficheros\\filewriter7.txt");
        System.out.println(fichero1.getAbsolutePath());
        System.out.println(fichero1.getPath());
        try {
            RandomAccessFile randomAccess = new RandomAccessFile(fichero1, "rw");

            long pos = fichero1.length();
            randomAccess.seek(pos);
            randomAccess.writeBytes("Linea desde randomAccesFile");
            randomAccess.seek(pos);
            String next;
            while ((next = randomAccess.readLine()) != null) {
                System.out.println(next + ": Tamaño = " + next.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void fileWriter() {
        File fichero1 = new File("Ficheros/ficheros/filewriter.txt");
        try {
            FileWriter writer = new FileWriter(fichero1);
            writer.write("Fichero de prueba son las 18:47");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se ha podido escribir en el fichero de texto");
        }

    }

    private static void fileReader() {
        File fichero1 = new File("Ficheros/ficheros/filewriter.txt");
        try {
            FileReader reader = new FileReader(fichero1);
            int valor = reader.read();
            while (valor != -1) {
                System.out.print((char) valor);
                valor = reader.read();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println("No se ha podido leer el fichero de texto");
        }
    }

    private static void creacionFicheros() {
        File fichero1 = new File("Ficheros\\ficheros\\fichero1.txt");
        System.out.println(fichero1.getName());
        System.out.println(fichero1.getParent());
        System.out.println(fichero1.getPath());
        System.out.println(fichero1.getAbsolutePath());
        printLinea();
        File fichero2 = new File(new File("Ficheros\\ficheros"), "fichero2.txt");
        System.out.println(fichero2.getName());
        System.out.println(fichero2.getParent());
        System.out.println(fichero2.getPath());
        System.out.println(fichero2.getAbsolutePath());
        printLinea();
        File fichero3 = new File("Ficheros\\ficheros", "fichero3.txt");
        System.out.println(fichero3.getName());
        System.out.println(fichero3.getParent());
        System.out.println(fichero3.getPath());
        System.out.println(fichero3.getAbsolutePath());
        try {
            FileWriter writer = new FileWriter(fichero3);
            writer.write("Esto es una prueba");
        } catch (IOException e) {
            e.printStackTrace();
        }
        printLinea();
    }

    private static void printLinea() {
        System.out.println("******************************************");
    }


    private static void printLinea(int contador) {
        System.out.println(contador + "******************************************");
    }

}
