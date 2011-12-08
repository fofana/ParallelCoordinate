/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ParallelCoordinate.ui;

/**
 *
 * @author fovea
 */
import ParallelCoordinate.model.*;

/**
 *
 * @author  flo
 */
public interface BrushListener {

    /**
     * Called when the brush is replaced by a new one.
     */
    void brushChanged(Brush b);

    /**
     * Called when the brush is modified.
     */
    void brushModified(Brush b);

}
