package cellsociety.model.cells;

import cellsociety.model.Neighborhood;

/**
 * Abstract class for cells
 *
 * @author Franklin Wu
 */
public abstract class Cell {

  // TODO: 2020-10-17 add interface for cell
  private String state;

  private Neighborhood neighbors;

  /**
   * Creates a cell based on the state and its neighbors
   * @param state
   * @param neighbors
   */
  public Cell(String state, Neighborhood neighbors) {
    this.state = state;
    this.neighbors = neighbors;
  }

  /**
   * Sets its neighbors to the given neighborhood
   * @param neighbors
   */
  public void setNeighbors(Neighborhood neighbors) {
    this.neighbors = neighbors;
  }

  /**
   * Returns the state of the cell
   * @return
   */
  public String getState() {
    return this.state;
  }

  /**
   * Sets the state of the cell to the given state
   * @param state
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Returns the neighbors
   * @return
   */
  public Neighborhood getNeighborhood() {
    return this.neighbors;
  }

  public String toString() {
    return this.getState();
  }

}
