/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ParallelCoordinate.ui;

/**
 *
 * @author fovea
 */
public class ProgressEvent extends java.util.EventObject {

    // type constants
    public static final int PROGRESS_START = 0;
    public static final int PROGRESS_UPDATE = 1;
    public static final int PROGRESS_FINISH = 2;
    public static final int PROGRESS_CANCEL = 3;

    private int type;
    private float progress = 0.0f;
    private long timestamp;
    private String message;

    /** Creates new RenderEvent */
    public ProgressEvent(Object source, int type) {
        this(source, type, 0.0f);
    }

    public ProgressEvent(Object source, int type, float progress) {
        this(source, type, progress, null);
    }

    public ProgressEvent(Object source, int type, float progress, String message) {
        super(source);
        this.type = type;
        this.progress = progress;
        this.message = message;

        this.timestamp = System.currentTimeMillis();
    }

    public int getType(){
        return type;
    }

    public long getTimestamp(){
        return timestamp;
    }

    public float getProgress(){
        return progress;
    }

    public String getMessage(){
        return message;
    }

}
