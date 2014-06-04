package cn.nbcc.resassistant.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.nebula.widgets.datechooser.DateChooser;

public class NewProjectDialog extends TitleAreaDialog {
	private Text pathText;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewProjectDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("\u9009\u62E9\u9879\u76EE\u8D44\u6E90\u6587\u4EF6\u5939");
		setTitle("\u7533\u62A5\u9879\u76EE\u6587\u4EF6\u5939\u9009\u62E9\u5BF9\u8BDD\u6846");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label.setText("\u9879\u76EE\u8D44\u6E90\u6587\u4EF6\u5939\uFF1A");
		
		pathText = new Text(container, SWT.BORDER);
		pathText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button importBtn = new Button(container, SWT.NONE);
		GridData gd_importBtn = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_importBtn.widthHint = 61;
		importBtn.setLayoutData(gd_importBtn);
		importBtn.setText("\u5BFC\u5165");
		
		List resList = new List(container, SWT.BORDER);
		resList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Button delBtn = new Button(composite, SWT.NONE);
		delBtn.setLayoutData(new RowData(54, SWT.DEFAULT));
		delBtn.setText("\u5220\u9664");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u9879\u76EE\u72B6\u6001\uFF1A");
		
		Combo combo = new Combo(container, SWT.NONE);
		combo.setItems(new String[] {"\u5F85\u7533\u62A5", "\u7533\u62A5\u4E2D", "\u5DF2\u7533\u62A5", "\u5DF2\u7ED3\u9898"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		combo.select(0);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u9879\u76EE\u7533\u8BF7\u622A\u6B62\u65F6\u95F4:");
		
		CalendarCombo calendarCombo = new CalendarCombo(container, SWT.NONE);
		GridData gd_calendarCombo = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_calendarCombo.widthHint = 122;
		calendarCombo.setLayoutData(gd_calendarCombo);
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setText("\u81EA\u52A8\u63D0\u9192");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u63D0\u4EA4\u8054\u7CFB\u4EBA:");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Button button = new Button(container, SWT.CHECK);
		button.setSelection(true);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		button.setText("\u62F7\u8D1D\u9879\u76EE\u5230\u9879\u76EE\u7A7A\u95F4\u4E2D");
		new Label(container, SWT.NONE);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(459, 446);
	}
}

