package cn.nbcc.resassistant.handlers;

import org.eclipse.core.commands.*;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import cn.nbcc.resassistant.dialogs.NewContactorDialog;

public class AddContactorHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		/*MessageDialog.openInformation(
				window.getShell(),
				"Resassistant",
				"Hello£¬Eclipse world");*/
		new NewContactorDialog(window.getShell()).open();
		return null;
	}

}

