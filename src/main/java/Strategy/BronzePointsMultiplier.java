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
public class BronzePointsMultiplier implements PointsMultiplier{
  public int calculateUserPoints(int points) {
      int totalPoints;
      totalPoints = points *2;
      return totalPoints;
  }
}
