package lms.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexSingletonView {

    private String path;
    private String indexHtml;


    private String loginForm;

    private static IndexSingletonView ourInstance = new IndexSingletonView();

    public static IndexSingletonView getInstance() {
        return ourInstance;
    }

    private IndexSingletonView() {
    }

    public void setPath(String path) {
        this.path = path;
        this.indexHtml = getPartialHtml("index");
        this.loginForm = getPartialHtml("login");
    }

    public String getLoginForm() {
        return loginForm;
    }


    public String getindexHtml() {
        return indexHtml;
    }

    private String getPartialHtml(String filename){
        StringBuilder strb = new StringBuilder();
        Path file = Paths.get(this.path + filename + ".html");
        Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                strb.append(line).append("\n");
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return strb.toString();
    }


}
