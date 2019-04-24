/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;


/**
 *
 * @author Stephen
 */
public class Points {
  private PointsMultiplier multi;
  private int newPoints;
  //this can be set at runtime by the application preferences
  public void setPointsMultiplierStrategy(PointsMultiplier multi) {
        this.multi = multi;
  }
  
  //use the strategy
  public int FindCalculatePoints(int points) {
         newPoints = multi.calculateUserPoints(points);
         System.out.println(newPoints);
         return newPoints;
  }
}
