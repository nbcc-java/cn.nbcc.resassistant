package cn.nbcc.resassistant.dialogs;


import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cn.nbcc.resassistant.model.Contactor;
import cn.nbcc.resassistant.model.ContactorManager;

public class ContactorListDialog extends TitleAreaDialog {
	public class ContactorLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof Contactor) {
				Contactor contactor = (Contactor) element;
				return String.format("%s\t%s%s", contactor.getId(),contactor.getName(),contactor.isDefaultContactor()?"*(默认)":"");
			}
			return "";
		}

	}


	private List toSelList;
	private Button delSelAllBtn;
	private List selectedList;
	private Button imprtBtn;
	private Text filterText;
	private ListViewer toSelListViewer;
	private ContactorListViewerFilter filter;
	protected java.util.Set<Contactor> cnSet;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ContactorListDialog(Shell parentShell) {
		super(parentShell);
		cnSet = new HashSet<Contactor>();
	}
	
	/**
	 * 添加对话框的标题
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("联系人列表对话框");
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("\u9009\u62E9\u8054\u7CFB\u4EBA");
		setTitle("\u8054\u7CFB\u4EBA\u5BF9\u8BDD\u6846");
		
		Composite area = (Composite) super.createDialogArea(parent);
		
		
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		label.setText("\u53EF\u9009\u8054\u7CFB\u4EBA");
		new Label(container, SWT.NONE);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		label_1.setText("\u5DF2\u9009\u8054\u7CFB\u4EBA");
		
		filterText = new Text(container, SWT.BORDER);
		filterText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				filter.setPattern(filterText.getText());
				
			}
		});
		filterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		final ListViewer selectedListViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL|SWT.MULTI);
		selectedList = selectedListViewer.getList();
		selectedList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 4));
		selectedListViewer.setContentProvider(new ArrayContentProvider());
		selectedListViewer.setLabelProvider(new ContactorLabelProvider());
		
		toSelListViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL|SWT.MULTI);
		toSelList = toSelListViewer.getList();
		toSelList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 3));
		toSelListViewer.setContentProvider(new ArrayContentProvider());
		toSelListViewer.setLabelProvider(new ContactorLabelProvider());
		toSelListViewer.setInput(ContactorManager.getInstance().getContactors());
		filter = new ContactorListViewerFilter(toSelListViewer);
		
//		toSelListViewer.addFilter(new ViewerFilter() {
//			
//			@Override
//			public boolean select(Viewer viewer, Object parentElement, Object element) {
//				if (filterText.getText().isEmpty()) {
////					toSelListViewer.removeFilter(this);
//					return true;
//				}
//
//				StringMatcher matcher = new StringMatcher(filterText.getText().trim(), true, false);
//				return matcher.match(((Contactor)element).toString());
//			}
//		});
		
		
		Button button = new Button(container, SWT.NONE);
		button.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		button.setText("\u5168\u90E8\u5BFC\u5165");
		
		delSelAllBtn = new Button(container, SWT.NONE);
		delSelAllBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		delSelAllBtn.setText("\u5168\u90E8\u53D6\u6D88");
		
		imprtBtn = new Button(container, SWT.NONE);
		imprtBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) toSelListViewer.getSelection();
				if (!selection.isEmpty()) {
					cnSet.addAll(selection.toList());
					selectedListViewer.setInput(cnSet.toArray());
					selectedListViewer.refresh();
					System.out.println(cnSet);
				}
			}
		});
		imprtBtn.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		imprtBtn.setText("\u5BFC\u5165");

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
		return new Point(450, 396);
	}

	public Contactor[] getContactors() {
		Contactor [] cs = new Contactor[cnSet.size()];
		cnSet.toArray(cs);
		Arrays.sort(cs);
		return cs;
	}

}

