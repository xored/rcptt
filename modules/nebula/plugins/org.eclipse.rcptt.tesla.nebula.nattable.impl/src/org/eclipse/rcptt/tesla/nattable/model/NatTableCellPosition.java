package org.eclipse.rcptt.tesla.nattable.model;

import org.eclipse.nebula.widgets.nattable.NatTable;

import com.google.common.base.Objects;

public class NatTableCellPosition {

	private static final String COL_PREFIX = "column:";
	private static final String ROW_PREFIX = "row:";

	private int row;
	private int col;

	private boolean isIndexColumnCoordinate = false;
	private boolean isIndexRowCoordinate = false;

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
		NatTableCellPosition position = new NatTableCellPosition(0, 0);

		// parse column coordinate
		String colParam = elements[0];
		if (colParam.startsWith("p")) {
			position.col = Integer.parseInt(colParam.substring(1));
			position.isIndexColumnCoordinate = false;
		} else {
			position.col = Integer.parseInt(colParam);
			position.isIndexColumnCoordinate = true;
		}

		// parse row coordinate
		String rowParam = elements[1];
		if (rowParam.startsWith("p")) {
			position.row = Integer.parseInt(rowParam.substring(1));
			position.isIndexColumnCoordinate = false;
		} else {
			position.row = Integer.parseInt(rowParam);
			position.isIndexRowCoordinate = true;
		}
		return position;
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
		StringBuilder path = new StringBuilder();
		if (!isIndexColumnCoordinate) {
			path.append("p");
		}
		path.append(col);
		path.append(":");
		if (!isIndexRowCoordinate) {
			path.append("p");
		}
		path.append(row);
		return path.toString();
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

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String getText() {
		return text;
	}

	public boolean isIndexColumnCoordinate() {
		return isIndexColumnCoordinate;
	}

	public boolean isIndexRowCoordinate() {
		return isIndexRowCoordinate;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(col, row, isIndexColumnCoordinate, isIndexRowCoordinate);
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
				&& isIndexColumnCoordinate == position.isIndexRowCoordinate
				&& isIndexRowCoordinate == position.isIndexRowCoordinate;
	}

}
