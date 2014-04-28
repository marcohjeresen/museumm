/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

/**
 *
 * @author davs
 */
public class Line {
    private String text;
    private int priceDk;
    private int priceEuro;

    public Line(String text, int priceDk, int priceEuro) {
        this.text = text;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriceDk() {
        return priceDk;
    }

    public void setPriceDk(int priceDk) {
        this.priceDk = priceDk;
    }

    public int getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(int priceEuro) {
        this.priceEuro = priceEuro;
    }

    @Override
    public String toString() {
        String text = "";
        if (priceDk == 0) {
           text = "text: " + text + " priceDk: " + priceDk + " priceEuro: " + priceEuro;
        }
        return text;
    }

    
}
