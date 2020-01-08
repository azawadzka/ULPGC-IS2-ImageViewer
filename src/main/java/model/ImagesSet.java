package model;

import java.io.File;
import java.util.ArrayList;

public class ImagesSet {

    private ArrayList<String> paths;
    private int currentIndex = 0;

    public ImagesSet() {
        this.paths = readFiles();
    }

    public String getNextPath() {
        currentIndex++;
        return paths.get(getCyclicIndex(currentIndex + 1));
    }

    public String getPrevPath() {
        currentIndex--;
        return paths.get(getCyclicIndex(currentIndex - 1));
    }

    public String[] getInitialPaths() {
        return new String[]{
                paths.get(getCyclicIndex(currentIndex - 1)),
                paths.get(getCyclicIndex(currentIndex)),
                paths.get(getCyclicIndex(currentIndex + 1))
        };
    }

    public boolean isEmpty() {
        return paths.isEmpty();
    }

    private ArrayList readFiles() {
        File directory = new File("images");
        File[] files = directory.listFiles();
        ArrayList list = new ArrayList<String>();
        for (File f : files) {
            list.add(f.getPath());
        }
        return list;
    }

    private int getCyclicIndex(int index) {
        return (index % paths.size() + paths.size()) % paths.size();
    }
}