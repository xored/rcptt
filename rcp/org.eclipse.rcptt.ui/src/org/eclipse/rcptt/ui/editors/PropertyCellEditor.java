package org.eclipse.rcptt.ui.editors;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.internal.text.html.HTMLPrinter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.rcptt.internal.ui.Q7UIPlugin;
import org.eclipse.rcptt.ui.controls.ISuggestionProvider;
import org.eclipse.rcptt.ui.controls.SuggestionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@SuppressWarnings("restriction")
public class PropertyCellEditor extends TextCellEditor {

	private Text text;
	private Shell shell;
	private List list;
	private Shell description;
	private Browser browser;

	private ISuggestionProvider provider;
	private java.util.List<SuggestionItem> suggestions;

	private java.util.List<Job> jobs;
	private Control lastFocusLostControl;

	private int listMaxHeight;
	private int descWidth;
	private int descHeight;

	private static final int LIST_MAX_HEIGHT = 200;
	private static final int DESC_WIDTH = 300;
	private static final int DESC_HEIGHT = 200;

	public PropertyCellEditor(Composite parent) {
		super(parent);
		text = (Text) super.getControl();
		createPopup(text);
		addListeners();
	}

	private void createPopup(Text textField) {
		text = textField;

		int popupStyle = SWT.TOOL | SWT.ON_TOP | SWT.NO_TRIM;
		int listStyle = SWT.SINGLE | SWT.V_SCROLL;

		shell = new Shell(text.getShell(), popupStyle);
		list = new org.eclipse.swt.widgets.List(shell, listStyle);
		description = new Shell(text.getShell(), popupStyle);
		description.setForeground(text.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		description.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		browser = new Browser(description, SWT.NONE);

		listMaxHeight = LIST_MAX_HEIGHT;
		descWidth = DESC_WIDTH;
		descHeight = DESC_HEIGHT;
	}

	private void addListeners() {
		text.getShell().addControlListener(new ControlListener() {

			@Override
			public void controlMoved(ControlEvent e) {
				shell.setFocus();
				close();
			}

			@Override
			public void controlResized(ControlEvent e) {
				shell.setFocus();
				close();
			}

		});

		shell.addControlListener(new ControlListener() {

			@Override
			public void controlMoved(ControlEvent e) {
			}

			@Override
			public void controlResized(ControlEvent e) {
				setChildElementsBounds();
			}

		});

		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseDown(MouseEvent e) {
				select(list.getSelectionIndex());
			}

			@Override
			public void mouseUp(MouseEvent e) {
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

		text.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				Q7UIPlugin.log("text :: Focus gained", null);
				if (!isVisible()) {
					setItems(provider.getSuggestions());
					open();
					return;
				}
				if (!noJobsCreated() && lastFocusLostControl != text) {
					Q7UIPlugin.log("text :: Job canceled", null);
					cancelJobs();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				Q7UIPlugin.log("text :: Focus lost", null);
				lastFocusLostControl = text;
				Control control = Display.getCurrent().getFocusControl();
				if (control != browser && control != list) {
					addJob();
					return;
				}
				Q7UIPlugin.log("text :: Stay in focus", null);
			}

		});

		list.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				Q7UIPlugin.log("list :: Focus gained", null);
				if (!noJobsCreated() && lastFocusLostControl != list) {
					Q7UIPlugin.log("list :: Job canceled", null);
					cancelJobs();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				Q7UIPlugin.log("list :: Focus lost", null);
				lastFocusLostControl = list;
				Control control = Display.getCurrent().getFocusControl();
				if (control != browser && control != text) {
					addJob();
					return;
				}
				Q7UIPlugin.log("list :: Stay in focus", null);
			}

		});

		browser.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				Q7UIPlugin.log("browser :: Focus gained", null);
				if (!noJobsCreated() && lastFocusLostControl != browser) {
					Q7UIPlugin.log("browser :: Job canceled", null);
					cancelJobs();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				Q7UIPlugin.log("browser :: Focus lost", null);
				lastFocusLostControl = browser;
				Control control = Display.getCurrent().getFocusControl();
				if (control != list && control != text) {
					addJob();
					return;
				}
				Q7UIPlugin.log("browser :: Stay in focus", null);
			}

		});

		Listener keyDown = new Listener() {

			@Override
			public void handleEvent(Event e) {
				if (e.character == '\r') { // Return key
					text.notifyListeners(SWT.DefaultSelection, e);
				} else {
					text.notifyListeners(SWT.KeyDown, e);
				}
			}

		};

		list.addListener(SWT.KeyDown, keyDown);
		browser.addListener(SWT.KeyDown, keyDown);

		Listener traverse = new Listener() {

			@Override
			public void handleEvent(Event e) {
				text.notifyListeners(SWT.Traverse, e);
			}

		};

		list.addListener(SWT.Traverse, traverse);
		browser.addListener(SWT.Traverse, traverse);

		text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleDefaultSelection(e);
			}
		});
	}

	@Override
	public void activate() {
		lastFocusLostControl = null;
		jobs = new ArrayList<Job>();
		super.activate();
	}

	@Override
	public void deactivate() {
		Q7UIPlugin.log("deactivate() call", null);
		if (shell == null) {
			return;
		}
		if (!isActive()) {
			super.deactivate();
			return;
		}
		addJob();
	}

	@Override
	protected void focusLost() {
	}

	@Override
	protected boolean dependsOnExternalFocusListener() {
		return false;
	}

	public void open() {
		if (!isActive()) {
			return;
		}
		setShellBounds();
		setChildElementsBounds();
		shell.setVisible(true);
	}

	public void close() {
		shell.setVisible(false);
		description.setVisible(false);
	}

	private void addJob() {
		Q7UIPlugin.log("Job scheduled", null);
		Job job = new Job("Deactivate") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						Q7UIPlugin.log("Job started", null);
						deactivateAll();
						jobs.removeAll(jobs);
					}
				});
				return Status.OK_STATUS;
			}

		};
		job.schedule(100);
		if (jobs == null) {
			jobs = new ArrayList<Job>();
		}
		jobs.add(job);
	}

	private void cancelJobs() {
		if (jobs == null) {
			return;
		}
		for (Job job : jobs) {
			job.cancel();
		}
		jobs.removeAll(jobs);
	}

	private boolean noJobsCreated() {
		return jobs == null || jobs.isEmpty();
	}

	private void deactivateAll() {
		Q7UIPlugin.log("DEACTIVATE!", null);
		lastFocusLostControl = null;
		if (shell != null) {
			close();
		}
		super.fireApplyEditorValue();
		super.deactivate();
	}

	public boolean isVisible() {
		return shell.getVisible();
	}

	public boolean isActive() {
		return suggestions != null && !suggestions.isEmpty();
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

	public void setSuggestionProvider(ISuggestionProvider provider) {
		this.provider = provider;
		// NPE if secure storage password is provided while field gets focus
		setItems(provider.getSuggestions());
	}

	private void setItems(java.util.List<SuggestionItem> suggestions) {
		Set<String> set = new TreeSet<String>();
		for (SuggestionItem suggestion : suggestions) {
			set.add(suggestion.getName());
		}
		String[] items = set.toArray(new String[0]);
		list.setItems(items);
		this.suggestions = suggestions;
	}

	private String getDescription(String name) {
		for (SuggestionItem suggestion : suggestions) {
			if (name.equals(suggestion.getName())) {
				return suggestion.getDescription();
			}
		}
		return null;
	}

	private static String applyStyling(String html) {
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

	private void setShellBounds() {
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

		shell.setBounds(shellX, shellY, popupSize.x, popupSize.y);
	}

	private void setChildElementsBounds() {
		Rectangle shellBounds = shell.getBounds();
		Rectangle screenBounds = shell.getDisplay().getBounds();

		int descX = shellBounds.x;
		int descY = shellBounds.y;

		int spaceBefore = shellBounds.x - 30;
		int spaceAfter = screenBounds.width - (shellBounds.x + shellBounds.width) - 30;

		if (spaceBefore > spaceAfter && descWidth > spaceAfter) {
			if (descWidth > spaceBefore) {
				descWidth = spaceBefore;
			}
			descX -= descWidth;

		} else {
			if (descWidth > spaceAfter) {
				descWidth = spaceAfter;
			}
			descX += shellBounds.width;
		}

		list.setSize(shellBounds.width, shellBounds.height);
		description.setBounds(descX, descY, descWidth, descHeight);
		browser.setSize(descWidth, descHeight);
	}

}
