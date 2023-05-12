public class Notebook {
    private String notebookFileName;

    public Notebook(String notebookFileName) {
        StringBuilder stringBuilder = new StringBuilder("./");
        stringBuilder.append(notebookFileName);
        stringBuilder.append(".bin");
        this.notebookFileName = stringBuilder.toString();
    }
}
