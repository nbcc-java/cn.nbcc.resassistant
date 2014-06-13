package cn.nbcc.resassistant.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.beans.PojoObservables;

public class ReminderDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Button smsBtn;
	private Button trayBtn;
	private Combo dateCombo;
	private Combo timeCombo;
	private Group group;
	private Button hrBtn;
	private Button minBtn;
	private Button secBtn;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ReminderDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("\u8BBE\u7F6E\u63D0\u9192\u65B9\u5F0F");
		setTitle("\u63D0\u9192\u5BF9\u8BDD\u6846");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		smsBtn = new Button(container, SWT.CHECK);
		smsBtn.setText("\u77ED\u4FE1\u63D0\u9192");
		
		dateCombo = new Combo(container, SWT.NONE);
		dateCombo.setItems(new String[] {"\u5F53\u5929", "\u524D\u4E00\u5929", "\u524D\u4E00\u5468"});
		dateCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateCombo.select(1);
		
		timeCombo = new Combo(container, SWT.NONE);
		timeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		for (int t = 0; t < 1440; t+=30) {
			timeCombo.add(String.format("%2d:%2d", t/60,t%60).replace(' ', '0'));
			timeCombo.select(1440/60);
			System.out.printf("%2d:%2d",t/60,t%60);
		}
		
		trayBtn = new Button(container, SWT.CHECK);
		trayBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		trayBtn.setText("\u6258\u76D8\u63D0\u9192");
		
		group = new Group(container, SWT.NONE);
		group.setText("\u95F4\u9694\u65F6\u957F");
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 2, 1));
		
		secBtn = new Button(group, SWT.RADIO);
		secBtn.setSelection(true);
		secBtn.setBounds(0, 0, 97, 17);
		secBtn.setText("30\u79D2");
		
		minBtn = new Button(group, SWT.RADIO);
		minBtn.setText("1\u5206\u949F");
		
		hrBtn = new Button(group, SWT.RADIO);
		hrBtn.setText("1\u5C0F\u65F6");

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
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue smsBtnObserveSelectionObserveWidget = SWTObservables.observeSelection(smsBtn);
		IObservableValue dateComboEnabledObserveValue = PojoObservables.observeValue(dateCombo, "enabled");
		bindingContext.bindValue(smsBtnObserveSelectionObserveWidget, dateComboEnabledObserveValue, null, null);
		//
		IObservableValue smsBtnObserveSelectionObserveWidget_1 = SWTObservables.observeSelection(smsBtn);
		IObservableValue timeComboEnabledObserveValue = PojoObservables.observeValue(timeCombo, "enabled");
		bindingContext.bindValue(smsBtnObserveSelectionObserveWidget_1, timeComboEnabledObserveValue, null, null);
		//
		IObservableValue trayBtnObserveSelectionObserveWidget = SWTObservables.observeSelection(trayBtn);
		IObservableValue groupEnabledObserveValue = PojoObservables.observeValue(group, "enabled");
		bindingContext.bindValue(trayBtnObserveSelectionObserveWidget, groupEnabledObserveValue, null, null);
		//
		IObservableValue trayBtnObserveSelectionObserveWidget_1 = SWTObservables.observeSelection(trayBtn);
		IObservableValue hrBtnEnabledObserveValue = PojoObservables.observeValue(hrBtn, "enabled");
		bindingContext.bindValue(trayBtnObserveSelectionObserveWidget_1, hrBtnEnabledObserveValue, null, null);
		//
		IObservableValue trayBtnObserveSelectionObserveWidget_2 = SWTObservables.observeSelection(trayBtn);
		IObservableValue minBtnEnabledObserveValue = PojoObservables.observeValue(minBtn, "enabled");
		bindingContext.bindValue(trayBtnObserveSelectionObserveWidget_2, minBtnEnabledObserveValue, null, null);
		//
		IObservableValue trayBtnObserveSelectionObserveWidget_3 = SWTObservables.observeSelection(trayBtn);
		IObservableValue secBtnEnabledObserveValue = PojoObservables.observeValue(secBtn, "enabled");
		bindingContext.bindValue(trayBtnObserveSelectionObserveWidget_3, secBtnEnabledObserveValue, null, null);
		//
		return bindingContext;
	}
}

