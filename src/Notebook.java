import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Notebook {
    private String notebookFileName;

    public Notebook(String notebookFileName) {
        StringBuilder stringBuilder = new StringBuilder("./");
        stringBuilder.append(notebookFileName);
        stringBuilder.append(".bin");
        this.notebookFileName = stringBuilder.toString();
    }
    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();
        try {
            FileInputStream fin = new FileInputStream(notebookFileName);
            ObjectInputStream in = new ObjectInputStream(fin);
            while (true) {
                try {
                    Note newNote = (Note) in.readObject();
                    notes.add(newNote);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            fin.close();
            in.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notes;
    }
    public boolean doesNoteExist(String title) {
        if (getNotes() == null) {
            return false;
        }
        for (Note note :
                getNotes()) {
            if (note.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
    public void add(String title, String text) {
        Date date = new Date();
        Note note = new Note(title, text, date);
        ArrayList<Note> notes = new ArrayList<Note>();
        File file = new File(notebookFileName);
        try {
            if (file.exists()) {
                FileInputStream fin = new FileInputStream(notebookFileName);
                ObjectInputStream in = new ObjectInputStream(fin);
                while (true) {
                    try {
                        Note newNote = (Note) in.readObject();
                        notes.add(newNote);
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                fin.close();
                in.close();
            }
            notes.add(note);
            FileOutputStream fout = new FileOutputStream(notebookFileName);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            for (int i = 0; i < notes.size(); i++) {
                out.writeObject(notes.get(i));
            }
            out.flush();
            fout.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
