package org.eclipse.rcptt.ui.controls;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.internal.text.html.HTMLPrinter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.rcptt.internal.ui.Q7UIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@SuppressWarnings("restriction")
public class SuggestionsPopup {
	private Text text;
	private Shell shell;
	private org.eclipse.swt.widgets.List list;
	private Shell description;
	private Browser browser;

	private List<SuggestionItem> suggestions;

	private int listMaxHeight;
	private int descWidth;
	private int descHeight;

	private static final int LIST_MAX_HEIGHT = 200;
	private static final int DESC_WIDTH = 300;
	private static final int DESC_HEIGHT = 200;

	private Job closePopupJob;
	private boolean mouseDown;

	public SuggestionsPopup(Text textField) {
		text = textField;

		int popupStyle = SWT.TOOL | SWT.ON_TOP | SWT.NO_TRIM;
		int listStyle = SWT.SINGLE | SWT.V_SCROLL;

		shell = new Shell(text.getShell(), popupStyle);
		list = new org.eclipse.swt.widgets.List(shell, listStyle);
		description = new Shell(text.getShell(), popupStyle);
		description.setForeground(text.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		description.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		browser = new Browser(description, SWT.NONE);

		addListeners();

		listMaxHeight = LIST_MAX_HEIGHT;
		descWidth = DESC_WIDTH;
		descHeight = DESC_HEIGHT;
	}

	public void open() {
		if (suggestions == null || suggestions.isEmpty()) {
			return;
		}
		calculateBounds();
		shell.setVisible(true);
		mouseDown = false;
		closePopupJob = null;
	}

	public boolean isVisible() {
		return shell.isVisible();
	}

	public void close() {
		if (mouseDown) {
			return;
		}
		closePopupJob = new Job("ClosePopup") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						closeAll();
					}
				});
				return Status.OK_STATUS;
			}

		};
		closePopupJob.schedule(100);
	}

	private void closeAll() {
		shell.setVisible(false);
		description.setVisible(false);
	}

	private void addListeners() {
		text.getShell().addControlListener(new ControlListener() {

			@Override
			public void controlMoved(ControlEvent e) {
				shell.setFocus();
				closeAll();
			}

			@Override
			public void controlResized(ControlEvent e) {
				shell.setFocus();
				closeAll();
			}

		});

		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseDown(MouseEvent e) {
				mouseDown = true;
				if (closePopupJob != null) {
					closePopupJob.cancel();
					closePopupJob = null;
				}
				select(list.getSelectionIndex());
			}

			@Override
			public void mouseUp(MouseEvent e) {
				closeAll();
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}

		});

		text.addTraverseListener(new TraverseListener() {

			@Override
			public void keyTraversed(TraverseEvent e) {
				switch (e.detail) {
				case SWT.TRAVERSE_ARROW_NEXT:
					if (e.keyCode == SWT.ARROW_DOWN) {
						int oldIndex = list.getSelectionIndex();
						select(Math.min(oldIndex + 1, list.getItemCount() - 1));

						e.detail = SWT.TRAVERSE_NONE;
						e.doit = false;
					}
					break;
				case SWT.TRAVERSE_ARROW_PREVIOUS:
					if (e.keyCode == SWT.ARROW_UP) {
						int oldIndex = list.getSelectionIndex();
						select(Math.max(oldIndex - 1, 0));

						e.detail = SWT.TRAVERSE_NONE;
						e.doit = false;
					}
					break;
				}
			}

		});
	}

	private void calculateBounds() {
		Rectangle textBounds = text.getBounds();
		Rectangle screenBounds = shell.getDisplay().getBounds();
		Point textLocation = text.getParent().toDisplay(textBounds.x, textBounds.y);
		Point popupSize = list.computeSize(textBounds.width, SWT.DEFAULT, false);

		int shellX = textLocation.x;
		int shellY = textLocation.y;

		popupSize.x = textBounds.width;
		if (popupSize.y > listMaxHeight) {
			popupSize.y = listMaxHeight;
		}

		int spaceBelow = screenBounds.height - (textLocation.y + textBounds.height) - 30;
		int spaceAbove = textLocation.y - 30;

		if (spaceAbove > spaceBelow && popupSize.y > spaceBelow) {
			if (popupSize.y > spaceAbove) {
				popupSize.y = spaceAbove;
			}
			shellY -= popupSize.y;

		} else {
			if (popupSize.y > spaceBelow) {
				popupSize.y = spaceBelow;
			}
			shellY += textBounds.height;
		}

		int descX = shellX;
		int descY = shellY;

		int spaceBefore = textLocation.x - 30;
		int spaceAfter = screenBounds.width - (textLocation.x + textBounds.width) - 30;

		if (spaceBefore > spaceAfter && descWidth > spaceAfter) {
			if (descWidth > spaceBefore) {
				descWidth = spaceBefore;
			}
			descX -= descWidth;

		} else {
			if (descWidth > spaceAfter) {
				descWidth = spaceAfter;
			}
			descX += popupSize.x;
		}

		shell.setBounds(shellX, shellY, popupSize.x, popupSize.y);
		list.setSize(popupSize.x, popupSize.y);
		description.setBounds(descX, descY, descWidth, descHeight);
		browser.setSize(descWidth, descHeight);
	}

	private void select(int index) {
		if (0 <= index && index < list.getItemCount()) {
			String value = list.getItem(index);
			text.setText(value);
			text.selectAll();
			list.select(index);
			list.showSelection();

			String desc = getDescription(value);
			if (desc != null && !desc.equals("")) {
				description.setVisible(true);
				browser.setText(applyStyling(desc));
			} else {
				description.setVisible(false);
				browser.setText("");
			}

		}
	}

	public void setItems(List<SuggestionItem> suggestions) {
		Set<String> set = new TreeSet<String>();
		for (SuggestionItem suggestion : suggestions) {
			set.add(suggestion.getName());
		}
		String[] items = set.toArray(new String[0]);
		list.setItems(items);
		this.suggestions = suggestions;
	}

	public String getDescription(String name) {
		for (SuggestionItem suggestion : suggestions) {
			if (name.equals(suggestion.getName())) {
				return suggestion.getDescription();
			}
		}
		return null;
	}

	public int getListMaximumHeight() {
		return listMaxHeight;
	}

	public void setListMaximumHeight(int height) {
		listMaxHeight = height;
	}

	public int getDescriptionWidth() {
		return descWidth;
	}

	public void setDescriptionWidth(int width) {
		descWidth = width;
	}

	public int getDescriptionHeight() {
		return descHeight;
	}

	public void setDescriptionHeight(int height) {
		descHeight = height;
	}

	public static String applyStyling(String html) {
		String styles = loadStyles();
		FontData fontData = JFaceResources.getDialogFont().getFontData()[0];
		styles = HTMLPrinter.convertTopLevelFont(styles, fontData);

		StringBuffer b = new StringBuffer(html);
		b.insert(0, "</style></head><body>");
		b.insert(0, styles);
		b.insert(0, "<html><head><style type=\"text/css\">");
		b.append("</body></html>");

		return b.toString();
	}

	private static String styles = null;

	private static String loadStyles() {
		if (styles != null)
			return styles;

		try {
			URL url = Q7UIPlugin.getDefault().getBundle()
					.getResource("/docs.css");
			// Check for missing styles resource.
			if (url == null) {
				return styles = "";
			}
			return styles = Resources.toString(url, Charsets.UTF_8);
		} catch (IOException e) {
			Q7UIPlugin.log(e);
			return styles = "";
		}
	}

}

