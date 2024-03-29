/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ParallelCoordinate.ui;

/**
 *
 * @author fovea
 */

import ParallelCoordinate.file.XMLFile;
import ParallelCoordinate.model.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

/**
 * The swing GUI Component for displaying a parallel coordinate visualisation.
 * Note that the actual rendering is done by the UI delegate, ParallelDisplayUI
 * (with its single subclass BasiParallelDisplayUI). This class is used to store
 * the state of the component and interact with the environment.
 *
 * @author Flo Ledermann flo@subnet.at
 * @version 0.1
 */
public class ParallelDisplay extends JComponent implements ChangeListener, ActionListener {

    /** Scale values for the axes.*/
    //private float axisScale[] = null;
    /** Offset values for the axes.*/
    //private float axisOffset[] = null;

    /** axis -> dimension linking. */
    //protected int axisOrder[] = null;
    private int[] swappedAxis = new int[2];
    protected Axis axes[];
    //private ParallelBrush _pcBrush;
    private JButton colorButton;
   
    public Color getColorButton(){
        return colorButton.getBackground();
    }
    public JButton getColorButtonComponent(){
        return colorButton;
    }
    /** Helper class for string the properties of an axis. */
    class Axis {
        int dimension;

        float scale;
        float offset;

        String label;

        Axis(int dimension, float scale, float offset, String label){
            this.dimension = dimension;
            this.scale = scale;
            this.offset = offset;
            this.label = label;
        }
    }

    /** brushed values of records */
    protected float brushValues[] = null;

    /** Our model. */
    private XMLFile model;   
    /** The mode of interaction we are in.*/
    protected int editMode = 0;

    public static final int REORDER = 0;
    public static final int SCALE = 1;
    public static final int TRANSLATE = 2;
    /** used to invert axis orientation */
    public static final int INVERT = 3;
    public static final int BRUSH = 4;

    public static final int HISTO_TOTALREC = 0;
    public static final int HISTO_BINREC = 1;
    public static final int HISTO_BRUSHREC = 2;

    /** Whether we have to redraw the whole background. This is usually only
     * needed if the model changes. */
    public boolean deepRepaint = true;
    boolean brushChanged = false;
    public JCheckBox lineInfo = new JCheckBox("Show Line Infos");
    JPanel brushPanel = new JPanel();
    ParallelPopup popupMenu;

    static {
        UIManager.put("ParallelCoordinate.ui.ParallelDisplayUI", "ParallelCoordinate.ui.BasicParallelDisplayUI");
    }

    /**
     * Creates a new ParallelDisplay.
     */
    public ParallelDisplay() {
        init(null);
    }

    /**
     * Creates a new ParallelDisplay with the given model.
     *
     * @param model The model to display.
     */
    public ParallelDisplay(XMLFile model){
        init(model);
    }
    /**
     * setSwappedAxis
     */
     public void setSwappedAxis(int new1, int new2){
         swappedAxis[0] = new1;
         swappedAxis[1] = new2;
     }
     public int[] getSwappedAxis(){
         return swappedAxis;
     }
    /**
     * Initializes the component with the given model.
     *
     * @param model The model to use.
     */
    protected void init(XMLFile model){

        System.out.println("Initializing ParallelDisplay Component");

        popupMenu = new ParallelPopup(this);

        setModel(model);

//        setMinimumSize(new Dimension(100, 100));
//        setPreferredSize(new Dimension(700,400));

        setBackground(Color.GRAY);//new Color(73,84,84));
        setDoubleBuffered(false);
        setOpaque(true);
        // Adding the Line info checkbox
        setLayout(new FlowLayout());
        //lineInfo.setVisible(false);
        lineInfo.setBackground(Color.GRAY);
        lineInfo.setFont(new Font(null, Font.ITALIC, 9));
        lineInfo.addActionListener(this);
        //this.add(lineInfo);
//        colorButton.setVisible(false);
        colorButton = new JButton("");
        colorButton.setBackground(Color.red);        
        colorButton.setEnabled(true);
	colorButton.setPreferredSize(new Dimension(20,20));
        colorButton.setBorder(null);
        colorButton.addActionListener(this);
        //colorButton.setEnabled(false);
        brushPanel.setBackground(Color.GRAY);
        //brushPanel.setSize(40, 40);
        brushPanel.add(lineInfo);
        brushPanel.add(colorButton);
        this.add(brushPanel);
        //colorButton.
        //this.add(colorButton);
//        this.setBoolPreference("hoverText", true);
//        this.setBoolPreference("hoverLine", true);
//        this.addMouseListener(_pcBrush);
//        this.addMouseMotionListener(_pcBrush);
        
        setDefaultPreferences();

        updateUI();
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        System.out.println("Action");
        if (source == lineInfo)
            if (lineInfo.isSelected()){
                this.setBoolPreference("hoverText", true);
                this.setBoolPreference("hoverLine", true);
                this.colorButton.setEnabled(false);
            }
            else{
                this.setBoolPreference("hoverText", false);
                this.setBoolPreference("hoverLine", false);
                this.colorButton.setEnabled(true);
            }
        if (source == colorButton){
            Color color = 
				JColorChooser.showDialog(
						this, 
						"Choose Color", 
						colorButton.getBackground());
            colorButton.setBackground(color);	
            this.setEditMode(BRUSH);
        }
    }
//    public void setPreferedSize(){
//        setPreferredSize(new Dimension(700,400));
//    }
    /** Returns the number of axes to display. Note that this is not necessarily
     * equal to the number of dimensions in the model.
     *
     * @return The number of axes to display.
     */
    public int getNumAxes(){
        if (axes != null)
            return axes.length;
        else
            return 0;
    }

    /**
     * Swaps two axes. This means the dimensions assigned to the two axes are swapped.
     *
     * @param axis1 The first axis.
     * @param axis2 The second axis.
     */
    public void swapAxes(int axis1, int axis2){
        Axis temp = axes[axis1];

        axes[axis1] = axes[axis2];
        axes[axis2] = temp;
        setupPopup();
    }

    /**
     * Adds an axis to the end of the display.
     *
     * @param num The dimension id the new axis should display.
     */
    public void addAxis(int num){
        addAxis(num, axes.length);
    }

    /**
     * Inserts a new axis between the two axes defined by targetRegion.
     *
     * @param num The dimension id the new axis should display.
     * @param targetRegion The location of the new axis. 0 puts the new axis in
     * front of all others.
     */
    public void addAxis(int num, int targetRegion){
        Axis newAxes[] = new Axis[axes.length+1];

        int i=0, j=0;

        for (;i<newAxes.length;i++){
            if (i == targetRegion){
                Axis newAxis = new Axis(num, model.getMinValue(num) - model.getMaxValue(num), model.getMaxValue(num), model.getAxisLabel(num));
                newAxes[i] = newAxis;

                System.out.println("adding axis " + newAxis.label);
            }
            else {
                newAxes[i] = axes[j];
                j++;
            }
        }

        axes = newAxes;

        setupPopup();

        deepRepaint = true;
        repaint();
    }

    /**
     * Removes an axis from the display.
     *
     * @param num The number of the axis to remove.
     */
    public void removeAxis(int num){
        Axis newAxes[] = new Axis[axes.length-1];

        int i=0,j=0;

        for (;i<newAxes.length;i++){
            if (j == num){
                j++;
            }
            newAxes[i] = axes[j];
            j++;
        }

        axes = newAxes;

        setupPopup();

        deepRepaint = true;
        repaint();
    }

    
    /**
     * Sets the model to display.
     *
     * @param model The model to display.
     */
    //public void setModel(ParallelSpaceModel model){
    public void setModel(ParallelCoordinate.file.XMLFile model){
        if (this.model != null) {
            this.model.removeChangeListener(this);

            axes = null;

            brushValues = null;
        }

        this.model = model;

        if (model != null){
            model.addChangeListener(this);

            axes = new Axis[model.getNumDimensions()];
            String axisNames[] = new String[model.getNumDimensions()];
            brushValues = new float[model.getNumRecords()];

            for (int i=0; i<model.getNumDimensions(); i++){
                // initialize scaling of axis to show maximum detail
                Axis newAxis = new Axis(i, model.getMinValue(i) - model.getMaxValue(i), model.getMaxValue(i), model.getAxisLabel(i));
                axes[i] = newAxis;
                axisNames[i] = newAxis.label;
            }

            popupMenu.setAvailableAxes(axisNames);
            popupMenu.setVisibleAxes(axisNames);

        }

        if (cFrame != null){
            cFrame.updateAxes();
        }

        currentBrush = null;

        deepRepaint = true;
        repaint();

    }

    /**
     * Resets the display. The brush is removed, and the model data is rendered in
     * its initial state.
     */
    public void resetAll(){
        setCurrentBrush(null);
        setModel(model);
        setEditMode(REORDER);
        repaint();
    }

    /**
     * Sets the mode for user interaction with the display.
     *
     * @param mode The interaction mode to use.
     */
    public void setEditMode(int mode){
        editMode = mode;

        resetCursor();
    }

    void resetCursor(){
        switch (editMode){
            case BRUSH:
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;
            default:
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    /**
     * Returns the currently active interaction mode.
     *
     * @return The currently active interaction mode.
     */
    public int getEditMode(){
        return editMode;
    }

    /**
     * Returns the model.
     * Actually the model should be hidden, because of the confusion that might
     * occur by mistaking axes and dimensions. This requires
     * a rewrite of some parts of the code, so this is marked to do!
     *
     * @return The model that is currently displayed ba the component.
     */
    public XMLFile getModel(){
        return model;
    }

    /**
     * Returns the number of Records in the model.
     */
    public int getNumRecords(){
        if (model != null)
            return model.getNumRecords();
        else
            return 0;
    }

    public String getRecordLabel(int num){
        if (model != null)
            return model.getRecordLabel(num);
        else
            return null;
    }

    public float getValue(int recordNum, int axisNum){
        if (model != null) {
            return model.getValue(recordNum, axes[axisNum].dimension);
        }
        else {
            return 0;
        }
    }

    private int brushCount = 0;

    public float getBrushValue(int num){
        if ((currentBrush != null) && (currentBrush.getNumValues() > num))
            return currentBrush.getBrushValue(num);
        else
            return 0.0f;
    }

    public void setBrushValue(int num, float val){
        if ((currentBrush != null) && (currentBrush.getNumValues() > num))
            currentBrush.setBrushValue(num, val);
    }

    /**
     * Returns the number of records that are currently brushed.
     */
    public int getBrushedCount(){
        if (currentBrush != null)
            return currentBrush.getNumBrushed();
        else
            return 0;
    }

    /** Getter for property currentBrush.
     * @return Value of property currentBrush.
     */
    public Brush getCurrentBrush() {
        return currentBrush;
    }

    /** Setter for property currentBrush.
     * @param currentBrush New value of property currentBrush.
     */
    public void setCurrentBrush(Brush currentBrush) {
        this.currentBrush = currentBrush;

        fireBrushChanged(currentBrush);
        System.out.println("currentBrush set: " + currentBrush);

        brushChanged = true;

        repaint();
    }

    /**
     * Returns an array with the ids of the records in the given value range.
     *
     * @param axisnum The axis the given range is valid for.
     * @param min The lower boundary of the range (>=).
     * @param max The upper boundary of the range (<).
     */
    public int[] getRecordsByValueRange(int axisnum, float min, float max){

        int ids[] = new int[getNumRecords()];
        int count = 0;

        for (int i=0; i<getNumRecords(); i++){
            float val = getValue(i, axisnum);
            if ((val >= min) && (val < max))
                ids[count++] = i;
        }

        if (count > 0) {
            int newids[] = new int[count];
            System.arraycopy(ids,0,newids,0,count);
            return newids;
        }
        else return new int[0];
    }

    /**
     * Returns the number of records in the given value range.
     *
     * @param axisnum The axis the given range is valid for.
     * @param min The lower boundary of the range (>=).
     * @param max The upper boundary of the range (<).
     */
    public int getNumRecordsInRange(int axisnum, float min, float max){
        int count = 0;
        for (int i=0; i<getNumRecords(); i++){
            float val = getValue(i, axisnum);
            if ((val >= min) && (val < max))
                count++;
        }

        return count;
    }

    /**
     * Returns the number of brushed records in the given value range.
     *
     * @param axisnum The axis the given range is valid for.
     * @param min The lower boundary of the range (>=).
     * @param max The upper boundary of the range (<).
     */
    public int getNumBrushedInRange(int axisnum, float min, float max){
        float count = 0;
        for (int i=0; i<getNumRecords(); i++){
            float val = getValue(i, axisnum);
            if ((val >= min) && (val < max))
                count += getBrushValue(i);
        }

        return (int)count;
    }

    /**
     * Shortcut for preference access. For downwards compatibility.
     */
    public Color getRecordColor(){
        return getColorPreference("recordColor");
    }

    /**
     * Shortcut for preference access. For downwards compatibility.
     */
    public Color getBrushedColor(){
        return getColorPreference("brushColor");
    }

    /**
     * Sets the user interface delegate for the component.
     */
    public void setUI(ParallelDisplayUI ui){
        super.setUI(ui);
    }

    /**
     * Invalidates the component and causes a complete repaint.
     */
    public void invalidate(){
        super.invalidate();
        deepRepaint = true;
    }

    /**
     * Swing method.
     */
    public void updateUI(){
        try {
            setUI((ParallelDisplayUI)UIManager.getUI(this));
        }
        catch (ClassCastException ccex){
        }
        invalidate();
    }

    /**
     * Swing method.
     */
    public String getUIClassID(){
        //System.out.println("retrieving classID");
        return "ParallelCoordinate.ui.ParallelDisplayUI";
    }

    /**
     * Invoked when the model has changed its state.
     *
     * @param e A ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        repaint();
    }

    /**
     * Returns the current offset (translation in axis units) for the axis.
     *
     * @param num The axis number.
     *
     * @return The offset value.
     **/
    public float getAxisOffset(int num){
        if (axes != null){
            return axes[num].offset;
        }
        else return 0;
    }

    /**
     * Returns the current scale (visible region in axis units) for the axis.
     *
     * @param num The axis number.
     *
     * @return The scale value.
     **/
    public float getAxisScale(int num){
        if (axes != null){
            return axes[num].scale;
        }
        else return 0;
    }

    /**
     * Returns a String label for a specific axis.
     *
     * @param num The axis number.
     *
     * @return A Human-readable label for the axis.
     */

    public String getAxisLabel(int num){
        if (model != null){
            String label = axes[num].label;
            if (label != null) return label;
            else return ("X" + axes[num].dimension);
        }
        else {
            return null;
        }
    }

    /**
     * Sets the offset (translation in axis units) for the axis.
     *
     * @param axis The axis number.
     * @param offset The offset value.
     **/
    public void setAxisOffset(int axis, float offset){
        if (axes != null){
            axes[axis].offset = offset;
        }

        repaint();
    }

    /**
     * Sets the scale (visible region in axis units) for the axis.
     *
     * @param axis The axis number.
     * @param scale The scale value.
     **/
    public void setAxisScale(int axis, float scale){
        if (axes != null){
            axes[axis].scale = scale;
        }

        repaint();
    }

    /**
     * Configures (scales, translates) all axes to show all values between its
     * minimum and its maximum on a maximum scale.
     */
    public void minMaxScale(){
        for (int i=0; i<getNumAxes(); i++){
            // initialize scaling of axis to show maximum detail
            axes[i].offset = model.getMaxValue(axes[i].dimension);
            axes[i].scale = model.getMinValue(axes[i].dimension) - axes[i].offset;
        }

        deepRepaint = true;
        repaint();
    }

    /**
     * Configures (scales, translates) all axes to show all values between zero
     * and its maximum on a maximum scale.
     */
    public void zeroMaxScale(){
        for (int i=0; i<getNumAxes(); i++){
            // initialize scaling of axis to show maximum detail
            axes[i].offset = model.getMaxValue(axes[i].dimension);
            axes[i].scale = -1 * axes[i].offset;
        }

        deepRepaint = true;
        repaint();
    }

    /**
     * Configures (scales, translates) all axes to show values between zero
     * (or the nagative minimum of all axes) and the maximum value of all axes
     * on a maximum scale.
     */
    public void minMaxAbsScale(){
        int i;

        float absmax = Float.NEGATIVE_INFINITY;
        float absmin = 0.0f;

        for (i=0; i<getNumAxes(); i++){
            // initialize scaling of axis to show maximum detail
            float val = model.getMaxValue(axes[i].dimension);
            if (val > absmax) absmax = val;
            val =  model.getMinValue(axes[i].dimension);
            if (val < absmin) absmin = val;
        }

        for (i=0; i<getNumAxes(); i++){
            axes[i].offset = absmax;
            axes[i].scale = absmin - absmax;
        }

        deepRepaint = true;
        repaint();
    }

    private Vector progressListeners = new Vector();

    public void addProgressListener(ProgressListener l){
        progressListeners.add(l);
    }

    public void removeProgressListener(ProgressListener l){
        progressListeners.remove(l);
    }

    private Vector brushListeners = new Vector();

    public void addBrushListener(BrushListener l){
        brushListeners.add(l);
    }

    public void removeBrushListener(BrushListener l){
        brushListeners.remove(l);
    }


    public void fireProgressEvent(ProgressEvent e){
        Vector list = (Vector)progressListeners.clone();
        for (int i=0; i<list.size(); i++){
            ProgressListener l = (ProgressListener)list.elementAt(i);
            l.processProgressEvent(e);
        }
    }

    public void fireBrushChanged(Brush b){
        Vector list = (Vector)brushListeners.clone();
        for (int i=0; i<list.size(); i++){
            BrushListener l = (BrushListener)list.elementAt(i);
            l.brushChanged(b);
            l.brushModified(b);
        }
    }

    public void fireBrushModified(Brush b){
        Vector list = (Vector)brushListeners.clone();
        for (int i=0; i<list.size(); i++){
            BrushListener l = (BrushListener)list.elementAt(i);
            l.brushModified(b);
        }
    }


    Hashtable preferences = new Hashtable();

    /** Holds value of property currentBrush. */
    private Brush currentBrush;

    /**
     * Fills the preferences hashtable with initial default values.
     */
    public void setDefaultPreferences(){
        preferences.put("brushRadius", new Float(0.2f));
        preferences.put("softBrush", new Boolean(true));
        preferences.put("hoverText", new Boolean(false));
        preferences.put("hoverLine", new Boolean(false));
        preferences.put("histogram", new Boolean(false));
        preferences.put("histogramBins", new Integer(10));
        preferences.put("histogramWidth", new Integer(HISTO_TOTALREC));
        preferences.put("recordColor",  Color.black); // Line color
        preferences.put("brushColor", Color.black); 
    }

    public void setFloatPreference(String key, float val){
        Object obj = new Float(val);
        preferences.put(key, obj);
    }

    public void setIntPreference(String key, int val){
        Object obj = new Integer(val);
        preferences.put(key, obj);
    }

    public void setBoolPreference(String key, boolean val){
        Object obj = new Boolean(val);
        preferences.put(key, obj);
    }
    
    public void setColorPreference(String key, int r, int g, int b){
        Object obj = new Color(r,g,b);
        preferences.put(key, obj);
    }
    public void setPreference(String key, Object val){
        preferences.put(key, val);
    }

    public Object getPreference(String key){
        return preferences.get(key);
    }

    public Color getColorPreference(String key){
        Object obj = preferences.get(key);
        if ((obj != null) && (obj instanceof Color)){
            return (Color)obj;
        }
        // we should throw an exception here;
        else return null;
    }


    public boolean getBoolPreference(String key){
        Object obj = preferences.get(key);
        if ((obj != null) && (obj instanceof Boolean)){
            return ((Boolean)obj).booleanValue();
        }
        // we should throw an exception here;
        else return false;
    }

    public float getFloatPreference(String key){
        Object obj = preferences.get(key);
        if ((obj != null) && (obj instanceof Float)){
            return ((Float)obj).floatValue();
        }
        // we should throw an exception here;
        else return 0.0f;
    }

    public int getIntPreference(String key){
        Object obj = preferences.get(key);
        if ((obj != null) && (obj instanceof Integer)){
            return ((Integer)obj).intValue();
        }
        // we should throw an exception here;
        else return 0;
    }

    /**
     * Initializes the popup menu.
     */
    protected void setupPopup(){
        int i;

        String visible[] = new String[axes.length];
        for (i = 0; i<axes.length; i++){
            visible[i] = axes[i].label;
        }

        //String available[] = new String[model.getNumDimensions()];
        //for (i = 0; i<available.length; i++){
        //    available[i] = model.getDimensionLabel(i);
        //}

        popupMenu.setVisibleAxes(visible);
        //popupMenu.setAvailableAxes(available);

        if (cFrame != null) {
            cFrame.updateAxes();
        }
    }

    CorrelationFrame cFrame = null;

    public void setCorrelationFrame(CorrelationFrame f){
        cFrame = f;
    }

    /**
     * Helper class: the popup menu.
     */
    class ParallelPopup extends JPopupMenu implements ActionListener{

        JMenu addAxisMenu;
        JMenu removeAxisMenu;

        ParallelDisplay parent;

        int targetRegion = 0;

        ParallelPopup(ParallelDisplay parent){
            super();

            this.parent = parent;

            addAxisMenu = new JMenu("Add axis");
            removeAxisMenu = new JMenu("Remove axis");

            this.add(addAxisMenu);
            this.add(removeAxisMenu);
        }

        void setVisibleAxes(String[] axes){
            removeAxisMenu.removeAll();

            for (int i=0; i<axes.length; i++){
                JMenuItem item = new JMenuItem();
                item.setText(axes[i]);
                item.setName("R" + i);
                item.addActionListener(this);

                removeAxisMenu.add(item);
            }
        }

        void setAvailableAxes(String[] axes){
            addAxisMenu.removeAll();

            for (int i=0; i<axes.length; i++){
                JMenuItem item = new JMenuItem();
                item.setText(axes[i]);
                item.setName("A" + i);
                item.addActionListener(this);

                addAxisMenu.add(item);
            }
        }

        public void actionPerformed(ActionEvent e){
            System.out.println("Context menu action");
            JMenuItem item = (JMenuItem)e.getSource();
            if (item.getName().startsWith("R")){
                int num = Integer.parseInt(item.getName().substring(1));
                parent.removeAxis(num);
            }
            else if (item.getName().startsWith("A")){
                int num = Integer.parseInt(item.getName().substring(1));
                System.out.println("adding axis " + num);
                parent.addAxis(num, targetRegion);
            }
        }

        public void setTargetRegion(int region){
            targetRegion = region;
        }
    }


}