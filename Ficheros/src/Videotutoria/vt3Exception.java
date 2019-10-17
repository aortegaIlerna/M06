package Videotutoria;

import java.io.FileNotFoundException;

public class vt3Exception extends FileNotFoundException {

    public vt3Exception(String message) {
        super("El objeto "+message+" no se ha creado correctamente");
    }
}
