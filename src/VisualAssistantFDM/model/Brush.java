/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualAssistantFDM.model;

/**
 *
 * @author fovea
 */
import java.awt.Color;

import java.io.*;

/**
 * Represents a set of "brushed" records.
 * @author  Flo Ledermann flo@subnet.at
 */
public class Brush {

    /** The color to use to draw the records on the screen. */
    private Color color;

    /** Holds value of property brushValue. */
    private float[] brushValues;

    private int numBrushed = 0;

    /** Holds value of property name. */
    private String name;

    /** Creates a new instance of Brush */
    public Brush(int numValues, Color color) {
        brushValues = new float[numValues];
        this.color = color;
    }

    /** Creates a new instance of Brush */
    public Brush(int numValues){
        this(numValues, Color.black);
    }

    /** Creates a new instance of Brush */
    public Brush(float[] brushValues){
        this.setBrushValues(brushValues);
    }


    /** Getter for property color.
     * @return Value of property color.
     */
    public Color getColor() {
        return this.color;
    }

    /** Setter for property color.
     * @param color New value of property color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /** Indexed getter for property brushValue.
     * @param index Index of the property.
     * @return Value of the property at <CODE>index</CODE>.
     */
    public float getBrushValue(int index) {
        return this.brushValues[index];
    }

    /** Indexed setter for property brushValue.
     * @param index Index of the property.
     * @param brushValue New value of the property at <CODE>index</CODE>.
     */
    public void setBrushValue(int index, float brushValue) {
        if ((brushValue > 0.0f) && (brushValues[index] == 0.0f)){
            numBrushed++;
        }
        else if ((brushValue == 0.0f) && (brushValues[index] > 0.0f)){
            numBrushed--;
        }

        this.brushValues[index] = brushValue;
    }

    /**
     * Sets the array with brush vallues directly.
     */
    public void setBrushValues(float[] newValues){
        this.brushValues = (float[])newValues.clone();

        numBrushed = 0;
        for (int i=0; i<brushValues.length; i++){
            if (brushValues[i] > 0.0f) numBrushed++;
        }
    }

    /**
     * Returns the nuber of records of the dataset.
     */
    public int getNumValues(){
        return brushValues.length;
    }

    /**
     * Returns the number of brushed records.
     */
    public int getNumBrushed(){
        return numBrushed;
    }

    /**
     * Returns a new Brush, which is the result of a subtraction of another brush
     * from the values of this brush.
     */
    public Brush subtract(Brush secondBrush){
        Brush newBrush = new Brush(brushValues.length);

        for (int i=0; i<brushValues.length; i++){
            float newVal = brushValues[i] - secondBrush.getBrushValue(i);
            if (newVal < 0.0f) newVal = 0.0f;
            newBrush.setBrushValue(i,newVal);
        }

        return newBrush;
    }

    /**
     * Returns a new brush which is the result of a merging of this brush
     * and another brush.
     */
    public Brush add(Brush secondBrush){
        Brush newBrush = new Brush(brushValues.length);

        for (int i=0; i<brushValues.length; i++){
            float newVal = brushValues[i] + secondBrush.getBrushValue(i);
            if (newVal > 1.0f) newVal = 1.0f;
            newBrush.setBrushValue(i,newVal);
        }

        return newBrush;
    }

    /**
     * Returns a new Brush which is the result of an intersection operation of
     * this brush with another brush.
     */
    public Brush intersect(Brush secondBrush){
        Brush newBrush = new Brush(brushValues.length);

        for (int i=0; i<brushValues.length; i++){
            newBrush.setBrushValue(i,Math.min(brushValues[i], secondBrush.getBrushValue(i)));
        }

        return newBrush;
    }

    /** Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }

    /** Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        if (name != null) {
            return getName();
        }
        else {
            return "<unnamed brush>";
        }
    }

    /**
     * Returns a new Brush identical to this one.
     */
    public Object clone(){
        Brush newBrush = new Brush((float[])brushValues.clone());
        newBrush.setName(getName());
        newBrush.setColor(getColor());

        return newBrush;
    }

    /**
     * 1.Zeile: "Name des Channels" = String
     * 2.Zeile: "Anzahl der Datenwerte" = integer
     * 3.-"Anzahl der Datenwerte+2"Zeilen: je ein "Datenwert" = float
     */
    public void writeToFile(File f, boolean overwrite) throws IOException{
        if (!f.exists() || overwrite){
            FileWriter out = new FileWriter(f);

            String bname;
            if (name != null) bname = name;
            else bname = "<unnamed brush>";

            out.write(bname + "\n");
            out.write(brushValues.length + "\n");
            for (int i=0; i<brushValues.length; i++){
                out.write(brushValues[i] + "\n");
            }

            out.close();
        }
        else {
            throw new IOException("File " + f.getAbsolutePath() + " exists!");
        }
    }

}