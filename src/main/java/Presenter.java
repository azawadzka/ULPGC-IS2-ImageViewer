import model.ConcreteImageLoader;
import model.ImagesSet;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter {

    public Presenter(View v) {
        ConcreteImageLoader loader = new ConcreteImageLoader();
        ImagesSet set = new ImagesSet();
        if (set.isEmpty()) v.handleEmptyDirectory();

        String[] initPaths = set.getInitialPaths();
        v.receiveInitialImages(
                loader.load(initPaths[0]),
                loader.load(initPaths[1]),
                loader.load(initPaths[2]));

        ActionListener nextListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                v.receiveNext(loader.load(set.getNextPath()));
            }
        };
        v.setListenerNext(nextListener);

        ActionListener prevListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                v.receivePrev(loader.load(set.getPrevPath()));
            }
        };
        v.setListenerPrev(prevListener);
    }
}

