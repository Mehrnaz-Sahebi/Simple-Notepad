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
    public void viewTitles() {
        for (int i = 0; i < getNotes().size(); i++) {
            System.out.println(i + 1 + ". " + getNotes().get(i).getTitle());
        }
    }
    public void viewANote(int index) {
        index--;
        System.out.println(getNotes().get(index));
    }
    public void removeANote(int index) {
        index--;
        ArrayList<Note> notes = getNotes();
        notes.remove(index);
        FileOutputStream fout = null;
        ObjectOutputStream out = null;
        try {
            fout = new FileOutputStream(notebookFileName);
            out = new ObjectOutputStream(fout);
            for (int i = 0; i < notes.size(); i++) {
                out.writeObject(notes.get(i));
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fout != null && out != null) {
                try {
                    fout.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void exportANote(int index){
        index--;
        Note noteToExport = getNotes().get(index);
        try {
            FileWriter fileWriter = new FileWriter("./export/"+noteToExport.getTitle()+".txt");
            fileWriter.write(noteToExport.toString());
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
