package org.eclipse.rcptt.tesla.nattable.model;

import org.eclipse.nebula.widgets.nattable.NatTable;

import com.google.common.base.Objects;

public class NatTableCellPosition {

	private static final String COL_PREFIX = "column:";
	private static final String ROW_PREFIX = "row:";

	private int row;
	private int col;

	private String text;

	/**
	 * Parses a string representation of a cell position in a {@link NatTable}, and returns the corresponding
	 * {@link NatTableCellPosition}. The path format is either "column:..." or "row:..." to specify the header cell with
	 * the given text, or "x:y", where x and y are the cell coordinates.
	 */
	public static NatTableCellPosition fromPath(String path) {
		if (path.startsWith(COL_PREFIX)) {
			return forColumnText(path.substring(COL_PREFIX.length()));
		} else if (path.startsWith(ROW_PREFIX)) {
			return forRowText(path.substring(ROW_PREFIX.length()));
		}
		String[] elements = path.split(":");
		String colParam = elements[0];
		String rowParam = elements[1];
		return new NatTableCellPosition(Integer.parseInt(colParam), Integer.parseInt(rowParam));
	}

	/**
	 * Returns the path string corresponding to this position, in the format understood by {@link #fromPath(String)}.
	 */
	public String getPath() {
		if (col == -1) {
			return COL_PREFIX + text;
		} else if (row == -1) {
			return ROW_PREFIX + text;
		}
		return String.format("%d:%d", col, row);
	}

	/**
	 * Returns a new cell position specifying the column header cell with the given text.
	 */
	public static NatTableCellPosition forColumnText(String text) {
		return new NatTableCellPosition(-1, 0, text);
	}

	/**
	 * Returns a new cell position specifying the row header cell with the given text.
	 */
	public static NatTableCellPosition forRowText(String text) {
		return new NatTableCellPosition(0, -1, text);
	}

	private NatTableCellPosition(int col, int row, String text) {
		this.col = col;
		this.row = row;
		this.text = text;
	}

	public NatTableCellPosition(int col, int row) {
		this(col, row, null);
	}

	public static boolean isValidPath(String path) {
		return path.startsWith(COL_PREFIX) || path.startsWith(ROW_PREFIX) || path.matches("[p]*[\\d]+:[p]*[\\d]+");
	}

	/**
	 * Returns the row coordinate, or -1 for header cells given by their text.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column coordinate, or -1 for header cells given by their text.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns the column or row header text, or null if the cell is specified by its coordinates.
	 */
	public String getText() {
		return text;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(col, row, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		NatTableCellPosition position = (NatTableCellPosition) obj;

		return row == position.row
				&& col == position.col
				&& Objects.equal(text, position.text);
	}

}
