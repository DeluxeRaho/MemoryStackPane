package view;

import model.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MemoryIO {

    public void serializeToFile(MemoryLogic model, String path) throws IOException {
        ObjectOutputStream oos = null;
        try{
            FileOutputStream fout = new FileOutputStream(path);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(model);
        }finally{
            if(oos!=null){
                oos.close();
            }
        }
    }


    @SuppressWarnings("unchecked")
    public MemoryLogic deSerializeFromFile(String path) throws IOException,  ClassNotFoundException {
        ObjectInputStream ois = null;
        MemoryLogic model = null;

        try{
            FileInputStream fin = new FileInputStream(path);
            ois = new ObjectInputStream(fin);
            model = (MemoryLogic) ois.readObject();
            return model;
        }finally{
            if(ois!=null) {
                ois.close();
            }
            return model;
        }

    }


}

