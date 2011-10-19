/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualAssistantFDM.gui;

/**
 *
 * @author fovea
 */
import VisualAssistantFDM.model.*;

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
