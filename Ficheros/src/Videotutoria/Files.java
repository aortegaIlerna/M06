package Videotutoria;

import java.io.*;

public class Files {

    public static void main(String[] args) {

        /*Constructores*/
        creacionFicheros();

        /*Leer y escribir ficheros secuenciales*/
        fileWriter();
        fileReader();

        /* Fichero de acceso aleatorio */
        randomAccesFile();

        /* Ficheros Binarios*/
        fileOutputStream();
        fileReaderStream();

        /* XML */
        xmlReaderDOM();
        xmlWriter();
        /*TODO crear método XMLReaderSAX */
        //xmlReaderSAX();

    }

    private static void xmlWriter() {
        FicheroXML xml = new FicheroXML();
        xml.writeXML(new File("ficheros/gato.xml"), new Gato());
    }

    private static void xmlReaderDOM() {
        FicheroXML xmlDom = new FicheroXML();
        xmlDom.parserDOM();
    }

    private static void fileReaderStream() {
        File ficheroGato = new File("ficheros/gato");
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

        File ficheroGato = new File("ficheros/gato");
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
        File fichero1 = new File("C:\\Users\\aortega\\IdeaProjects\\Ilerna1\\ficheros\\filewriter7.txt");
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
        File fichero1 = new File("ficheros/filewriter.txt");
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
        File fichero1 = new File("ficheros/filewriter.txt");
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
        File fichero1 = new File("ficheros\\fichero1.txt");
        System.out.println(fichero1.getName());
        System.out.println(fichero1.getParent());
        System.out.println(fichero1.getPath());
        System.out.println(fichero1.getAbsolutePath());
        printLinea();
        File fichero2 = new File(new File("src/Videotutorial2/rsc/"), "fichero2.txt");
        System.out.println(fichero2.getName());
        System.out.println(fichero2.getParent());
        System.out.println(fichero2.getPath());
        System.out.println(fichero2.getAbsolutePath());
        printLinea();
        File fichero3 = new File("src/Videotutorial2/rsc/ext/", "fichero3.txt");
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

}
