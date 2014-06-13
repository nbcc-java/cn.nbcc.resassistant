package cn.nbcc.resassistant.dialogs;

import java.io.IOException;
import java.nio.file.*;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cn.nbcc.resassistant.model.*;
import cn.nbcc.resassistant.utils.*;

public class NewProjectDialog extends TitleAreaDialog {
	
	
	
	
	private DataBindingContext m_bindingContext;
	private Text pathText;
	private Text contactorText;
	private Text yearText;
	private Button mdfBtn;
	private Tree tree;
	private CheckboxTreeViewer checkboxTreeViewer;
	private ResearchProject rp;	//项目对象
	private Combo statusCombo;
	private CalendarCombo deadlineCombo;
	private Button remindBtn;
	private Contactor[] contactors;
	

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewProjectDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.RESIZE);
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (rp==null) {
			newShell.setText("新建项目对话框");
		}else {
			newShell.setText("修改项目对话框");
		}
	}
	
	@Override
	protected void okPressed() {
		boolean isValid = checkValid();
		if(isValid)
		{
			Path des = Paths.get(ContextConstants.DATA_FOLDER);
			Path src = Paths.get(pathText.getText().trim());
			Path zipFilePath = des.resolve(src.getFileName().toString()+".zip");
			
			FileSystem fs =FileUtils.createZipFileSystem(zipFilePath, true);
			FileUtils.ZipFileVisitor zfv = new FileUtils.ZipFileVisitor(src, fs);
			try {
				Files.walkFileTree(src, zfv);
				MessageDialog.openConfirm(getShell(), "消息", "已将项目拷贝到数据目录中");
				rp.setSrcPath(zipFilePath.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			rp.setTitle(DirectoryUtils.getFileName(pathText.getText()));
			rp.setDeadlineDate(deadlineCombo.getDate().getTime());
			rp.setStatus(ResearchProjectStatus.APPLYING);
			rp.setContactor(getContactors());
			super.okPressed();
		}
	}

	private Contactor[] getContactors() {
		return contactors;
	}

	private boolean  checkValid() {
		String errMsg = null;
		if (pathText.getText().isEmpty()) {
			errMsg = "目录不能为空";
		}else if(!DirectoryUtils.exists(pathText.getText())){
			errMsg = "指定目录不存在";
		}
		if (contactorText.getText().isEmpty()) {
			errMsg="联系人不能为空";
		}
		if (deadlineCombo.getDateAsString().isEmpty()) {
			errMsg="截止时间不能为空";
		}
		setErrorMessage(errMsg);
		return errMsg==null?true:false;
		
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
		importBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String dirPath = new DirectoryDialog(getShell(),SWT.OPEN).open();
				if (dirPath!=null) {
					pathText.setText(dirPath);
					
//					checkboxTreeViewer.setInput(Paths.get(dirPath));
					checkboxTreeViewer.setInput(Paths.get(dirPath).toFile());
				}
				
			}
		});
		GridData gd_importBtn = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_importBtn.widthHint = 61;
		importBtn.setLayoutData(gd_importBtn);
		importBtn.setText("\u5BFC\u5165");
		
		checkboxTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
		tree = checkboxTreeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		checkboxTreeViewer.setContentProvider(new FileTreeContentProvider());
		checkboxTreeViewer.setLabelProvider(new FileTreeLabelProvider());
		
		//监听状态，如果选择一个树节点，则其所有子节点将被选中
		checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (event.getChecked()) {
					//check all its children
					checkboxTreeViewer.setSubtreeChecked(event.getElement(), true);
				}else
					checkboxTreeViewer.setSubtreeChecked(event.getElement(), false);
				
			}
		});
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Button selAllBtn = new Button(composite, SWT.NONE);
		selAllBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTreeViewer.setAllChecked(true);
			}
		});
		selAllBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		selAllBtn.setText("\u5168\u90E8\u9009\u4E2D");
		
		Button deSelAllBtn = new Button(composite, SWT.NONE);
		deSelAllBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTreeViewer.setAllChecked(false);
			}
		});
		deSelAllBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		deSelAllBtn.setText("\u5168\u90E8\u4E0D\u9009");
		
		Button refreshBtn = new Button(composite, SWT.NONE);
		refreshBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		refreshBtn.setText("\u5237\u65B0");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u9879\u76EE\u6240\u5C5E\u5E74\u5EA6:");
		
		yearText = new Text(container, SWT.BORDER);
		yearText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		yearText.setEnabled(false);
		
		mdfBtn = new Button(container, SWT.CHECK);
		mdfBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		mdfBtn.setText("\u4FEE\u6539");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u9879\u76EE\u72B6\u6001\uFF1A");
		
		statusCombo = new Combo(container, SWT.NONE);
		statusCombo.setItems(new String[] {"\u5F85\u7533\u62A5", "\u7533\u62A5\u4E2D", "\u5DF2\u7533\u62A5", "\u5DF2\u7ED3\u9898"});
		statusCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		statusCombo.select(0);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u9879\u76EE\u7533\u8BF7\u622A\u6B62\u65F6\u95F4:");
		
		deadlineCombo = new CalendarCombo(container, SWT.NONE);
		GridData gd_deadlineCombo = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_deadlineCombo.widthHint = 122;
		deadlineCombo.setLayoutData(gd_deadlineCombo);
		
		remindBtn = new Button(container, SWT.NONE);
		remindBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new ReminderDialog(getShell()).open();
			}
		});
		remindBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		remindBtn.setText("\u81EA\u52A8\u63D0\u9192");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u63D0\u4EA4\u8054\u7CFB\u4EBA:");
		
		contactorText = new Text(container, SWT.BORDER);
		contactorText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button selCnBtn = new Button(container, SWT.NONE);
		selCnBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ContactorListDialog contactorListDialog =new ContactorListDialog(getShell());
				if(contactorListDialog.open()== IDialogConstants.OK_ID)
					contactors = contactorListDialog.getContactors();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < contactors.length; i++) {
					Contactor contactor = contactors[i];
					sb.append(contactor.toString());
					sb.append(",");
				}
				contactorText.setText(sb.deleteCharAt(sb.length()-1).toString());
			}
		});
		selCnBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		selCnBtn.setText("\u9009\u62E9\u8054\u7CFB\u4EBA");
		
		Button copyToBtn = new Button(container, SWT.CHECK);
		copyToBtn.setSelection(true);
		copyToBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		copyToBtn.setText("\u62F7\u8D1D\u9879\u76EE\u5230\u9879\u76EE\u7A7A\u95F4\u4E2D");
		new Label(container, SWT.NONE);

		
		if (rp==null) {
			rp = new ResearchProject();
		}
		fillFields(rp);
		
		return area;
	}

	/**
	 * 根据当前项目信息填写对话框组件
	 * @param rp
	 */
	private void fillFields(ResearchProject rp) {
		pathText.setText(rp.getSrcPath());
		contactorText.setText(rp.getFirstContactor()==null?"":rp.getFirstContactor().toString());
		DateUtils.getYear(rp.getStartDate());
		yearText.setText(DateUtils.getYear(rp.getStartDate())+"");
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
		return new Point(757, 552);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue mdfBtnObserveSelectionObserveWidget = SWTObservables.observeSelection(mdfBtn);
		IObservableValue yearTextEnabledObserveValue = PojoObservables.observeValue(yearText, "enabled");
		bindingContext.bindValue(mdfBtnObserveSelectionObserveWidget, yearTextEnabledObserveValue, null, null);
		//
		return bindingContext;
	}

	public ResearchProject getResearchProject() {
		return rp;
	}
}

